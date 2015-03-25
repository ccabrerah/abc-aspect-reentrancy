package abc.reentrant.weaving.aspectinfo;

import abc.reentrant.weaving.weaver.LCflowCodeGenUtils;
import java.util.*;
import polyglot.util.Position;
import polyglot.util.InternalCompilerError;
import soot.*;
import soot.jimple.*;
import soot.util.*;
import abc.weaving.matching.*;
import abc.weaving.residues.*;
import abc.weaving.tagkit.InstructionKindTag;
import abc.weaving.tagkit.InstructionShadowTag;
import abc.weaving.tagkit.InstructionSourceTag;
import abc.weaving.tagkit.Tagger;
import abc.weaving.weaver.*;
import abc.soot.util.LocalGeneratorEx;
import abc.weaving.aspectinfo.*;

/*Based on (a de-privatized version of) CflowSetup.java
 The most important changes are that some types are replaced with LCflow-related ones (like CflowCodeGenUtils->LCflowCodeGenUtils)*/
public class LCflowSetup extends CflowSetup {

    //ideally, this code would not be replicated here
    public static LCflowSetup construct(Aspect aspct,
            Pointcut pc,
            boolean isBelow,
            Hashtable typeMap,
            Position pos,
            int depth) {
        // this returns a set of String rather than Var because
        // each occurrence will have a different position
        Set/*<String>*/ fvs = new HashSet();
        pc.getFreeVars(fvs);
        List formals = new LinkedList();
        List actuals = new LinkedList();
        Iterator it = fvs.iterator();
        while (it.hasNext()) {
            String fv = (String) (it.next());
            AbcType type = (AbcType) typeMap.get(fv);
            if (type == null) {
                throw new InternalCompilerError("variable " + fv + " not in typemap");
            }
            Formal f = new Formal(type, fv, pos);
            formals.add(f);
            actuals.add(new Var(fv, pos));
        }

        return new LCflowSetup(aspct, pc, isBelow, formals, actuals, pos, depth);
    }

    public CflowCodeGenUtils.CflowCodeGen codeGen() {
        if (codeGen == null) // Init the codegen. This occurs when the cflow stack is first added to the aspect
        {
            getCflowStack();
        }
        return codeGen;
    }

    protected LCflowSetup(Aspect aspct, Pointcut pc, boolean isBelow,
            List/*<Formal>*/ formals, List/*<Var>*/ actuals,
            Position pos, int depth) {
        super(aspct, pc, isBelow, formals, actuals, pos, depth);
    }

    public void debugInfo(String prefix, StringBuffer sb) {
        sb.append(prefix + " type: " + spec + "\n");
        sb.append(prefix + " pointcut: " + pc + "\n");
        sb.append(prefix + " special: " + (isBelow ? "lcflowbelow" : "lcflow") + " setup\n");
    }

    public SootFieldRef getCflowStack() {
        if (cflowStack == null) {

            // First, init the cflow codegen
            List/*<Type>*/ formalTypes = new LinkedList();

            for (Iterator it = getFormals().iterator(); it.hasNext();
                    formalTypes.add(((Formal) it.next()).getType().getSootType()));



            codeGen = LCflowCodeGenUtils.LCflowCodeGenFactory.v(formalTypes);

            SootClass cl = getAspect().getInstanceClass().getSootClass();

            int i = 0;
            String name;
            do {
                name = "abc$" + codeGen.chooseName() + "$" + i;
                i++;
            } while (cl.declaresFieldByName(name));

            SootField cflowStackF = new SootField(name, codeGen.getCflowType(),
                    Modifier.PUBLIC | Modifier.STATIC);
            cl.addField(cflowStackF);
            cflowStack = cflowStackF.makeRef();

            codeGen.setCflowField(cflowStack);


            SootMethod preClinit = new AspectCodeGen().getPreClinit(cl);
            LocalGeneratorEx lg = new LocalGeneratorEx(preClinit.getActiveBody());
            this.preClinit = preClinit;

            Chain units = preClinit.getActiveBody().getUnits();

            Stmt returnStmt = (Stmt) units.getFirst();
            while (!(returnStmt instanceof ReturnVoidStmt)) {
                returnStmt = (Stmt) units.getSuccOf(returnStmt);
            }

            Chain cflowInitStmts = codeGen.genInitCflowField(lg, cflowStack);


            Iterator it = cflowInitStmts.iterator();
            while (it.hasNext()) {
                Stmt s = (Stmt) it.next();
                units.insertBefore(s, returnStmt);
            }

        }
        return cflowStack;
    }

//    protected Stmt getMethodFirstSafeCflowStatement(SootMethod m) {
    public Local getMethodLCflowLocal(LocalGeneratorEx localgen, SootMethod m, Local levelValue) {
        Local l = (Local) methodCflowLocals.get(m);
        if (l != null) {
            return l;
        }

        l = localgen.generateLocal(codeGen().getCflowType(), codeGen().chooseName());
        Stmt getIt = Jimple.v().newAssignStmt(l, Jimple.v().newStaticFieldRef(getCflowStack()));
        Tagger.tagStmt(getIt, InstructionKindTag.GET_CFLOW_LOCAL);

        Stmt insertAfter =
                getMethodFirstSafeCflowStatement(m);
        m.getActiveBody().getUnits().insertAfter(getIt, insertAfter);

        methodCflowLocals.put(m, l);
        return l;
    }
//    public Local getMethodCflowLocal(LocalGeneratorEx localgen, SootMethod m) {
//    public Local getMethodCflowThreadLocal(LocalGeneratorEx localgen, SootMethod m) {
//    public Chain makeAdviceExecutionStmts(AdviceApplication adviceappl, LocalGeneratorEx localgen, WeavingContext wc) {
}
