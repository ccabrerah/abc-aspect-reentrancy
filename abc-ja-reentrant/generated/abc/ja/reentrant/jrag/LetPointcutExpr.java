
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;



public class LetPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        sootMethod_computed = false;
        sootMethod_value = null;
        methodSig_computed = false;
        methodSig_value = null;
        methodVars_computed = false;
        methodVars_value = null;
        isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
        isCircular_ParameterDeclaration_values = null;
        isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
        isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
    }
     @SuppressWarnings({"unchecked", "cast"})  public LetPointcutExpr clone() throws CloneNotSupportedException {
        LetPointcutExpr node = (LetPointcutExpr)super.clone();
        node.sootMethod_computed = false;
        node.sootMethod_value = null;
        node.methodSig_computed = false;
        node.methodSig_value = null;
        node.methodVars_computed = false;
        node.methodVars_value = null;
        node.isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
        node.isCircular_ParameterDeclaration_values = null;
        node.isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
        node.isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LetPointcutExpr copy() {
      try {
          LetPointcutExpr node = (LetPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LetPointcutExpr fullCopy() {
        LetPointcutExpr res = (LetPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Pointcuts.jrag at line 108


  public Pointcut pointcut() {
    java.util.List vars = methodVars();

    int jp = vars.indexOf(new Var("thisJoinPoint", null));
    int jpsp = vars.indexOf(new Var("thisJoinPointStaticPart", null));
    int ejp = vars.indexOf(new Var("thisEnclosingJoinPointStaticPart", null));

    Var bound_var = new Var(getVarAccess().name(), getVarAccess().pos());

    return new Let(bound_var, vars, methodSig(), jp, jpsp, ejp, pos());
  }

    // Declared in Pointcuts.jrag at line 159


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
      if (getExpr().refersTo(param)) {
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

    // Declared in Variables.jrag at line 112


  //
  // type-checking a let-pointcut
  //
  public void typeCheck()
  {
    // check type of expression
    TypeDecl varType = getVarAccess().decl().type();
    if (!getExpr().type().assignConversionTo(varType, getExpr())
        && !getExpr().type().isUnknown())
    {
      error("Can not assign the pointcut formal " + getVarAccess() +
            " of type " + varType.typeName() + " a value of type " +
            getExpr().type().typeName());
    }

    // check that the variables used in a let-pointcut are
    // bound in the current cflow
    Iterator i = pointcutFormals().iterator();
    while (i.hasNext()) {
      ParameterDeclaration param = (ParameterDeclaration) i.next();
      if (!param.isImplicit() && getExpr().refersTo(param) &&
          !bindsInCurrentCflow(param.name()))
      {
        error("Pointcut formal " + param.name() +
              " is not bound within the enclosing cflow:" +
              " it cannot be used within this let(..)");
      }
    }

    // check for circular let-pointcut definitions
    if (((ParameterDeclaration) getVarAccess().decl()).isCircular())
      error("The pointcut variable " + getVarAccess().name() +
            " is circularly defined through let pointcuts.");
  }

    // Declared in eaj.ast at line 3
    // Declared in eaj.ast line 12

    public LetPointcutExpr() {
        super();


    }

    // Declared in eaj.ast at line 10


    // Declared in eaj.ast line 12
    public LetPointcutExpr(VarAccess p0, Expr p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in eaj.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in eaj.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in eaj.ast at line 2
    // Declared in eaj.ast line 12
    public void setVarAccess(VarAccess node) {
        setChild(node, 0);
    }

    // Declared in eaj.ast at line 5

    public VarAccess getVarAccess() {
        return (VarAccess)getChild(0);
    }

    // Declared in eaj.ast at line 9


    public VarAccess getVarAccessNoTransform() {
        return (VarAccess)getChildNoTransform(0);
    }

    // Declared in eaj.ast at line 2
    // Declared in eaj.ast line 12
    public void setExpr(Expr node) {
        setChild(node, 1);
    }

    // Declared in eaj.ast at line 5

    public Expr getExpr() {
        return (Expr)getChild(1);
    }

    // Declared in eaj.ast at line 9


    public Expr getExprNoTransform() {
        return (Expr)getChildNoTransform(1);
    }

    protected boolean sootMethod_computed = false;
    protected SootMethod sootMethod_value;
    // Declared in Pointcuts.jrag at line 120
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
    String name = "let$" + hostType().adviceCounter++;
    ArrayList paramtypes = new ArrayList();
    Type returntype = getVarAccess().decl().type().getSootType();
    int modifiers = soot.Modifier.STATIC | soot.Modifier.PUBLIC;

    Iterator i = pointcutFormals().iterator();
    while (i.hasNext()) {
      ParameterDeclaration param = (ParameterDeclaration) i.next();
      if (getExpr().refersTo(param))
        paramtypes.add(param.type().getSootType());
    }

    SootMethod method =
      new SootMethod(name, paramtypes, returntype, modifiers);
    hostType().getSootClassDecl().addMethod(method);

    return method;
  }

    protected boolean methodSig_computed = false;
    protected MethodSig methodSig_value;
    // Declared in Pointcuts.jrag at line 141
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

    protected boolean methodVars_computed = false;
    protected java.util.List methodVars_value;
    // Declared in Pointcuts.jrag at line 146
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
    ArrayList vars = new ArrayList();
    Iterator i = pointcutFormals().iterator();
    while (i.hasNext()) {
      ParameterDeclaration param = (ParameterDeclaration) i.next();
      if (getExpr().refersTo(param))
        vars.add(new Var(param.name(),
                         polyglot.util.Position.COMPILER_GENERATED));
    }
    return vars;
  }

    // Declared in Variables.jrag at line 106
 @SuppressWarnings({"unchecked", "cast"})     public int binds(String var) {
        int binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private int binds_compute(String var) {  return getVarAccess().name().equals(var) ? 1 : 0;  }

    protected java.util.Map isCircular_ParameterDeclaration_visited;
    protected java.util.Set isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
    protected java.util.Set isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
    protected java.util.Map isCircular_ParameterDeclaration_values = new java.util.HashMap(4);
 @SuppressWarnings({"unchecked", "cast"})     public boolean isCircular(ParameterDeclaration decl) {
        Object _parameters = decl;
if(isCircular_ParameterDeclaration_visited == null) isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
if(isCircular_ParameterDeclaration_values == null) isCircular_ParameterDeclaration_values = new java.util.HashMap(4);
        if(isCircular_ParameterDeclaration_computed.contains(_parameters))
            return ((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue();
        if (!isCircular_ParameterDeclaration_initialized.contains(_parameters)) {
            isCircular_ParameterDeclaration_initialized.add(_parameters);
            isCircular_ParameterDeclaration_values.put(_parameters, Boolean.valueOf(true));
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
            CIRCLE_INDEX = 1;
            boolean new_isCircular_ParameterDeclaration_value;
            do {
                isCircular_ParameterDeclaration_visited.put(_parameters, new Integer(CIRCLE_INDEX));
                CHANGE = false;
                new_isCircular_ParameterDeclaration_value = isCircular_compute(decl);
                if (new_isCircular_ParameterDeclaration_value!=((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue())
                    CHANGE = true;
                isCircular_ParameterDeclaration_values.put(_parameters, Boolean.valueOf(new_isCircular_ParameterDeclaration_value));
                CIRCLE_INDEX++;
            } while (CHANGE);
            if(isFinal && num == boundariesCrossed)
{
            isCircular_ParameterDeclaration_computed.add(_parameters);
            }
            else {
            RESET_CYCLE = true;
            isCircular_compute(decl);
            RESET_CYCLE = false;
            isCircular_ParameterDeclaration_computed.remove(_parameters);
            isCircular_ParameterDeclaration_initialized.remove(_parameters);
            }
            IN_CIRCLE = false; 
            return new_isCircular_ParameterDeclaration_value;
        }
        if(!new Integer(CIRCLE_INDEX).equals(isCircular_ParameterDeclaration_visited.get(_parameters))) {
            isCircular_ParameterDeclaration_visited.put(_parameters, new Integer(CIRCLE_INDEX));
            if (RESET_CYCLE) {
                isCircular_ParameterDeclaration_computed.remove(_parameters);
                isCircular_ParameterDeclaration_initialized.remove(_parameters);
                return ((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue();
            }
            boolean new_isCircular_ParameterDeclaration_value = isCircular_compute(decl);
            if (new_isCircular_ParameterDeclaration_value!=((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue())
                CHANGE = true;
            isCircular_ParameterDeclaration_values.put(_parameters, Boolean.valueOf(new_isCircular_ParameterDeclaration_value));
            return new_isCircular_ParameterDeclaration_value;
        }
        return ((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue();
    }

    private boolean isCircular_compute(ParameterDeclaration decl) {
    if (getVarAccess().decl() != decl)
      return false;

    Iterator i = pointcutFormals().iterator();
    while (i.hasNext()) {
      ParameterDeclaration param = (ParameterDeclaration) i.next();
      if (getExpr().refersTo(param) && param.isCircular())
        return true;
    }
    return false;
  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
