package abc.reentrant.weaving.weaver;

import soot.*;
import soot.util.*;
import soot.jimple.*;

import java.util.*;

import abc.soot.util.LocalGeneratorEx;
import abc.weaving.weaver.*;

/* Based on CflowCodeGenUtils.java (which needs to have relaxed access permissions (public for all classes and lots of methods) */

public class LCflowCodeGenUtils extends CflowCodeGenUtils {

    private static final String CLASS_NEW_STACK_GLOBAL =
            "org.aspectbench.runtime.internal.LCFlowStackGlobal";
    private static final String CLASS_NEW_STACK_FACTORY =
            "org.aspectbench.runtime.internal.LCFlowStackFactory";
    // Typed Cflow Stacks
    private static final String[] types = {"Ref", "Int", "Long", "Float", "Double"};
    private static final String[] CLASS_NEW_STACK_INTERFACE_TYPED = new String[types.length];
    private static final String[] CLASS_NEW_STACK_GLOBAL_TYPED = new String[types.length];
    private static final String[] CLASS_NEW_STACK_TYPED = new String[types.length];
    private static final String[] CLASS_NEW_STACK_CELL_TYPED = new String[types.length];

    static {
        // Initialise the typed cflow stack class names
        for (int i = 0; i < types.length; i++) {
            CLASS_NEW_STACK_INTERFACE_TYPED[i] =
                    "org.aspectbench.runtime.internal.LCFlowStackInterface$" + types[i];
            CLASS_NEW_STACK_GLOBAL_TYPED[i] =
                    "org.aspectbench.runtime.internal.LCFlowStackGlobal$CflowStack" + types[i];
            CLASS_NEW_STACK_TYPED[i] =
                    "org.aspectbench.runtime.internal.lcflowinternal.Stack" + types[i];
            CLASS_NEW_STACK_CELL_TYPED[i] =
                    "org.aspectbench.runtime.internal.lcflowinternal.Stack" + types[i] + "$Cell";
        }
    }

    /** Register all the classes that (l)Cflow codegen might use with Soot
     */
    public static void addBasicClassesToSoot() {
        // Add all classes except typed stack classes
        String[] classes = {
            CLASS_NEW_STACK_GLOBAL,
            CLASS_NEW_STACK_FACTORY
        };
        for (int i = 0; i < classes.length; i++) {
            Scene.v().addBasicClass(classes[i], SootClass.SIGNATURES);
        }

        // Add typed stack classes
        for (int i = 0; i < types.length; i++) {
            Scene.v().addBasicClass(CLASS_NEW_STACK_INTERFACE_TYPED[i],
                    SootClass.SIGNATURES);
            Scene.v().addBasicClass(CLASS_NEW_STACK_GLOBAL_TYPED[i],
                    SootClass.SIGNATURES);
            Scene.v().addBasicClass(CLASS_NEW_STACK_TYPED[i],
                    SootClass.SIGNATURES);
            Scene.v().addBasicClass(CLASS_NEW_STACK_CELL_TYPED[i],
                    SootClass.SIGNATURES);
        }

    }

    public static boolean isFactoryMethod(SootMethodRef m) {
        String className = m.declaringClass().getName();
        String methodName = m.name();
        if (className.equals(CLASS_NEW_STACK_FACTORY) &&
                methodName.startsWith("makeStack")) {
            return true;
        }

        // Not a cflow factory method
        return false;

    }

    /** Test whether a type is a (l)cflow thread-local type
     */
    public static boolean isThreadLocalType(Type t) {
        if (!(t instanceof RefType)) {
            return false;
        }
        RefType rt = (RefType) t;
        String name = rt.getClassName();

        for (int i = 0; i < types.length; i++) {
            if (name.equals(CLASS_NEW_STACK_TYPED[i])) {
                return true;
            }
        }

        return false;
    }

    /** Storing the classes and methods for the LCflowStackGlobal implementation
     *
     */
    public static class LCflowStackGlobalUtils extends CflowCodeGenUtils.CflowStackGlobalUtils{

        public LCflowStackGlobalUtils(String elemType) {
            super(elemType);
        }

        @Override
        public SootClass factoryClass() {
            if (factoryClass == null) {
                factoryClass =
                        Scene.v().getSootClass("org.aspectbench.runtime.internal.LCFlowStackFactory");
            }
            return factoryClass;
        }

        @Override
        public SootClass typedStackClass(String celltype) {
            return Scene.v().getSootClass("org.aspectbench.runtime.internal.LCFlowStackInterface$" + celltype);
        }

        @Override
        public SootClass threadStackClass(String celltype) {
            return Scene.v().getSootClass("org.aspectbench.runtime.internal.lcflowinternal.Stack" + celltype); //here we will add a level-aware top, that will get called in runtime
        }

        //lcflow exclusive
        public SootClass threadCommonStackClass() {
            return Scene.v().getSootClass("org.aspectbench.runtime.internal.lcflowinternal.StackCommon"); //here we will add a level-aware top, that will get called in runtime
        }

        //lcflow exclusive
        public SootClass threadCommonStackCellClass() {
            return Scene.v().getSootClass("org.aspectbench.runtime.internal.lcflowinternal.StackCommon$CommonCell");
        }

        @Override
        public SootClass threadStackCellClass(String celltype) {
            return Scene.v().getSootClass("org.aspectbench.runtime.internal.lcflowinternal.Stack" + celltype + "$Cell");
        }

        @Override
        public SootMethodRef threadStackCellConstructor(String celltype) {
            List types = new ArrayList(3);
            types.add(threadStackCellType(celltype));
            types.add(toType(celltype));
            types.add(IntType.v()); //lcflow add (constructor aditionally takes level)
            return Scene.v().makeConstructorRef(threadStackCellClass(celltype), types);
        }

    }

    public static class LCflowStackGlobalCodeGen extends CflowStackGlobalCodeGen {

        @Override
        public void setFormals(List/*<Type>*/ types) {
            super.setFormals(types);

            //now, we 'upgrade' this.utils to our own implementation
            this.util = new LCflowStackGlobalUtils(super.util.elemType);

        }

        @Override
        protected ChainStmtBox protogenPush(LocalGeneratorEx localgen, Local cFlowLocal, List/*<Value>*/ values, boolean isSingleThreaded, boolean useStaticField) {

//            System.out.println(" ...protogenPush--- ");
            Chain c = new HashChain();

            // Create the new value to store at the top of the cflow stack
            Local newElemValue = localgen.generateLocal(
                    actualElemType, "cflowStackBounds");
            // Initialise the new value

            // Should it be an array?
            if (useArray) {
                // Create the array
                Stmt arrayInitStmt =
                        Jimple.v().newAssignStmt(newElemValue,
                        Jimple.v().newNewArrayExpr(arrayElemType,
                        IntConstant.v(values.size())));
                c.add(arrayInitStmt);
                // Copy each cflow variable into the array
                Iterator it = values.iterator();
                int i = 0;
                while (it.hasNext()) {
                    Value v = (Value) it.next();
                    Local temp = localgen.generateLocal(
                            v.getType(), "cflowBoundTemp");
                    Stmt copyToTemp =
                            Jimple.v().newAssignStmt(
                            temp,
                            v);
                    c.add(copyToTemp);
                    Chain copy = cgu.genCopyToArray(localgen, temp, newElemValue, i);
                    c.addAll(copy);
                    i++;
                }
            } else {
                // If not an array, no boxing or cast necessary
                Stmt copyStmt =
                        Jimple.v().newAssignStmt(
                        newElemValue,
                        (Value) values.get(0));
                c.add(copyStmt);
            }

            // Get the top element
            Local topElem = localgen.generateLocal(util.threadStackCellType(elemType), "cflowStackTop");
            Stmt getTopElem =
                    Jimple.v().newAssignStmt(
                    topElem,
                    getStackFieldInstance(isSingleThreaded, useStaticField, cFlowLocal));
            c.add(getTopElem);
            // Make a new element
            Local newTopElem = localgen.generateLocal(
                    util.threadStackCellType(elemType), "cflowStackNewTop");
            Stmt makeNewTopElem =
                    Jimple.v().newAssignStmt(
                    newTopElem,
                    Jimple.v().newNewExpr(
                    util.threadStackCellType(elemType)));
            c.add(makeNewTopElem);
            List constrParams = new ArrayList(2);
            constrParams.add(topElem);
            constrParams.add(newElemValue);


            /* TEST */
            //get current level
            Local levelValue = localgen.generateLocal(IntType.v(), "levelValue");//here we'll put the actual level

            //statically invoke Level.get() and store result in 'levelValue'
            Stmt getLevelStmt = Jimple.v().newAssignStmt(
                    levelValue,
                    Jimple.v().newStaticInvokeExpr(Scene.v().getMethod("<abc.reentrant.runtime.Level: int get()>").makeRef()));

            c.add(getLevelStmt);

            //lcflow: adding level to the constructor
            constrParams.add(levelValue);

            /* TEST */

            Stmt initNewTopElem =
                    Jimple.v().newInvokeStmt(
                    Jimple.v().newSpecialInvokeExpr(
                    newTopElem,
                    util.threadStackCellConstructor(elemType),
                    constrParams));
            c.add(initNewTopElem);
            // Set the new cell as top
            Stmt putNewTopElem =
                    Jimple.v().newAssignStmt(
                    getStackFieldInstance(isSingleThreaded, useStaticField, cFlowLocal),
                    newTopElem);
            c.add(putNewTopElem);

            return new ChainStmtBox(c, putNewTopElem);
        }

        @Override
        protected ChainStmtBox protogenIsValid(LocalGeneratorEx localgen,
                Local cFlowLocal, Local result, Stmt succeed, Stmt fail,
                boolean isSingleThreaded, boolean useStaticField) {
            throw new RuntimeException("this function is *not* used in a lcflow(use a normal cflow instead). Compiler programming error");
        }

        protected ChainStmtBox protogenIsValidLevelAware(LocalGeneratorEx localgen,
                Local cFlowLocal, Local result, Local level, Stmt succeed, Stmt fail,
                boolean isSingleThreaded, boolean useStaticField) {
            /*
             * This function calls StackCommon.getTop(level) and checks if the result is null or not.
             * getTop(level) traverses the stack and finds the first cell made on the specified level, or returns null if there is none
             *
             */

            Local topCellLocal =
                    localgen.generateLocal(
                    util.threadStackCellType(elemType), "cflowStackCell");

            Stmt getTopCell =
                    Jimple.v().newAssignStmt(
                    topCellLocal,
                    Jimple.v().newVirtualInvokeExpr(cFlowLocal, getCommonStackLevelAwareTopMethod().makeRef(), level));

            Expr testExpr =
                    Jimple.v().newNeExpr(topCellLocal, NullConstant.v());

            Chain c = cgu.genDecision(localgen, testExpr, result, succeed, fail);

            c.addFirst(getTopCell);

            return new ChainStmtBox(c, getTopCell);
        }

        protected SootMethod getCommonStackLevelAwareTopMethod() {
            List paramTypes = new LinkedList();
            paramTypes.add(IntType.v());

            return ((LCflowStackGlobalUtils)util).threadCommonStackClass().getMethod("getTop", paramTypes);
        }

        public static LCflowStackGlobalCodeGen v() {
            return new LCflowStackGlobalCodeGen();
        }


        @Override
        public ChainStmtBox genIsValid(LocalGeneratorEx localgen, Local cFlowLocal, Local result, Stmt succeed, Stmt fail) {
            throw new RuntimeException(" Lcflow doesn't needs genIsValid(), as it uses genIsValidLevelAware(). This is a compiler programming error ");
        }

        public ChainStmtBox genIsValidLevelAware(LocalGeneratorEx localgen, Local cFlowLocal, Local levelValue, Local result, Stmt succeed, Stmt fail) {
            return protogenIsValidLevelAware(localgen, cFlowLocal, result, levelValue, succeed, fail, false, false);
        }

    }

    public static class LCflowCodeGenFactory {

        public static CflowCodeGen v(List/*<Type>*/ types) {
            /*Initially, only a standard setup will be supported : stack based, threadlocal, no-static-field-cflow */
            CflowCodeGen g = null;

            g = LCflowStackGlobalCodeGen.v();

            g.setFormals(types);
            return g;
        }
    }

}

