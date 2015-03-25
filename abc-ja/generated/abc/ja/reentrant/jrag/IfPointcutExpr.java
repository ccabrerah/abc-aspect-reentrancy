
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



public class IfPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isStaticallyFalse_visited = 0;
        methodVars_computed = false;
        methodVars_value = null;
        sootMethod_computed = false;
        sootMethod_value = null;
        methodSig_computed = false;
        methodSig_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IfPointcutExpr clone() throws CloneNotSupportedException {
        IfPointcutExpr node = (IfPointcutExpr)super.clone();
        node.isStaticallyFalse_visited = 0;
        node.methodVars_computed = false;
        node.methodVars_value = null;
        node.sootMethod_computed = false;
        node.sootMethod_value = null;
        node.methodSig_computed = false;
        node.methodSig_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IfPointcutExpr copy() {
      try {
          IfPointcutExpr node = (IfPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IfPointcutExpr fullCopy() {
        IfPointcutExpr res = (IfPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Declare.jrag at line 78

  public void collectNonStaticPointcuts(HashSet set) {
    set.add("if()");
    super.collectNonStaticPointcuts(set);
  }

    // Declared in Pointcuts.jrag at line 76


    // check that variables used in an if-pointcut are bound --- not
    // just by the whole pointcut, but within the containing cflow
    public void typeCheck()
    {
        super.typeCheck();
        Iterator i = pointcutFormals().iterator();
        while (i.hasNext()) {
            ParameterDeclaration param = (ParameterDeclaration) i.next();
            if (!param.isImplicit() && this.refersTo(param) &&
                !bindsInCurrentCflow(param.name()))
            {
                error("Pointcut formal " + param.name() +
                      " is not bound within the enclosing cflow:" +
                      " it cannot be used within this if(..)");
            }
        }
    }

    // Declared in PointcutsCodegen.jrag at line 107


    public Pointcut pointcut() {
        java.util.List vars = methodVars();

        int jp = vars.indexOf(new Var("thisJoinPoint", null));
        int jpsp = vars.indexOf(new Var("thisJoinPointStaticPart", null));
        int ejp = vars.indexOf(new Var("thisEnclosingJoinPointStaticPart", null));

        return new If(vars, methodSig(), jp, jpsp, ejp, pos());
    }

    // Declared in PointcutsCodegen.jrag at line 306




    //
    // Code generation for if-pointcuts
    //
    public void jimplify2()
    {
        SootMethod method = sootMethod();
        JimpleBody body = Jimple.v().newBody(method);
        method.setActiveBody(body);
        Body b = new Body(hostType(), body, this);

        Iterator i = pointcutFormals().iterator();
        int count = 0;
        while (i.hasNext()) {
            ParameterDeclaration param = (ParameterDeclaration) i.next();
            if (this.refersTo(param)) {
                Type type = param.type().getSootType();
                param.local = b.newLocal(param.name(), type);
                b.setLine(param);
                b.add(Jimple.v().newIdentityStmt(param.local,
                    Jimple.v().newParameterRef(type, count++)));
            }
        }

        b.add(Jimple.v().newReturnStmt(
            getExpr().asImmediate(b, getExpr().eval(b))));
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 85

    public IfPointcutExpr() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 85
    public IfPointcutExpr(Expr p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 85
    public void setExpr(Expr node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public Expr getExpr() {
        return (Expr)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public Expr getExprNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    protected int isStaticallyFalse_visited;
    protected boolean isStaticallyFalse_computed = false;
    protected boolean isStaticallyFalse_initialized = false;
    protected boolean isStaticallyFalse_value;
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStaticallyFalse() {
        if(isStaticallyFalse_computed)
            return isStaticallyFalse_value;
        if (!isStaticallyFalse_initialized) {
            isStaticallyFalse_initialized = true;
            isStaticallyFalse_value = false;
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
            CIRCLE_INDEX = 1;
            do {
                isStaticallyFalse_visited = CIRCLE_INDEX;
                CHANGE = false;
                boolean new_isStaticallyFalse_value = isStaticallyFalse_compute();
                if (new_isStaticallyFalse_value!=isStaticallyFalse_value)
                    CHANGE = true;
                isStaticallyFalse_value = new_isStaticallyFalse_value; 
                CIRCLE_INDEX++;
            } while (CHANGE);
            if(isFinal && num == boundariesCrossed)
{
            isStaticallyFalse_computed = true;
            }
            else {
            RESET_CYCLE = true;
            isStaticallyFalse_compute();
            RESET_CYCLE = false;
              isStaticallyFalse_computed = false;
              isStaticallyFalse_initialized = false;
            }
            IN_CIRCLE = false; 
            return isStaticallyFalse_value;
        }
        if(isStaticallyFalse_visited != CIRCLE_INDEX) {
            isStaticallyFalse_visited = CIRCLE_INDEX;
            if (RESET_CYCLE) {
                isStaticallyFalse_computed = false;
                isStaticallyFalse_initialized = false;
                return isStaticallyFalse_value;
            }
            boolean new_isStaticallyFalse_value = isStaticallyFalse_compute();
            if (new_isStaticallyFalse_value!=isStaticallyFalse_value)
                CHANGE = true;
            isStaticallyFalse_value = new_isStaticallyFalse_value; 
            return isStaticallyFalse_value;
        }
        return isStaticallyFalse_value;
    }

    private boolean isStaticallyFalse_compute() {  return getExpr().type().isBoolean() && getExpr().isConstant() 
    											&& !getExpr().constant().booleanValue();  }

    protected boolean methodVars_computed = false;
    protected java.util.List methodVars_value;
    // Declared in PointcutsCodegen.jrag at line 330
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List methodVars() {
        if(methodVars_computed)
            return methodVars_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        methodVars_value = methodVars_compute();
        if(isFinal && num == boundariesCrossed)
            methodVars_computed = true;
        return methodVars_value;
    }

    private java.util.List methodVars_compute() {
        java.util.List vars = new java.util.ArrayList();
        Iterator i = pointcutFormals().iterator();
        while (i.hasNext()) {
            ParameterDeclaration param = (ParameterDeclaration) i.next();
            if (this.refersTo(param))
                vars.add(new Var(param.name(), polyglot.util.
                                 Position.COMPILER_GENERATED));
        }
        return vars;
    }

    protected boolean sootMethod_computed = false;
    protected SootMethod sootMethod_value;
    // Declared in PointcutsCodegen.jrag at line 345
 @SuppressWarnings({"unchecked", "cast"})     public SootMethod sootMethod() {
        if(sootMethod_computed)
            return sootMethod_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        sootMethod_value = sootMethod_compute();
        if(isFinal && num == boundariesCrossed)
            sootMethod_computed = true;
        return sootMethod_value;
    }

    private SootMethod sootMethod_compute() {
        String name = "if$" + hostType().adviceCounter++;
        java.util.List paramtypes = new java.util.ArrayList();
        Type returntype = soot.BooleanType.v();
        int modifiers = soot.Modifier.STATIC | soot.Modifier.PUBLIC;

        Iterator i = pointcutFormals().iterator();
        while (i.hasNext()) {
            ParameterDeclaration param = (ParameterDeclaration) i.next();
            if (this.refersTo(param))
                paramtypes.add(param.type().getSootType());
        }

        SootMethod method =
            new SootMethod(name, paramtypes, returntype, modifiers);
        hostType().getSootClassDecl().addMethod(method);

        return method;
    }

    protected boolean methodSig_computed = false;
    protected MethodSig methodSig_value;
    // Declared in PointcutsCodegen.jrag at line 366
 @SuppressWarnings({"unchecked", "cast"})     public MethodSig methodSig() {
        if(methodSig_computed)
            return methodSig_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        methodSig_value = methodSig_compute();
        if(isFinal && num == boundariesCrossed)
            methodSig_computed = true;
        return methodSig_value;
    }

    private MethodSig methodSig_compute() {
        return AbcFactory.MethodSig(sootMethod());
    }

    // Declared in Pointcuts.jrag at line 68
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return NameType.EXPRESSION_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
