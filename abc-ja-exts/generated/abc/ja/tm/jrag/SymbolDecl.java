
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;



public class SymbolDecl extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        perSymbolAdviceName_computed = false;
        perSymbolAdviceName_value = null;
        perSymbolSootMethod_computed = false;
        perSymbolSootMethod_value = null;
        getParameterList_computed = false;
        getParameterList_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SymbolDecl clone() throws CloneNotSupportedException {
        SymbolDecl node = (SymbolDecl)super.clone();
        node.perSymbolAdviceName_computed = false;
        node.perSymbolAdviceName_value = null;
        node.perSymbolSootMethod_computed = false;
        node.perSymbolSootMethod_value = null;
        node.getParameterList_computed = false;
        node.getParameterList_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SymbolDecl copy() {
      try {
          SymbolDecl node = (SymbolDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SymbolDecl fullCopy() {
        SymbolDecl res = (SymbolDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in SymbolCodegen.jrag at line 150


    public void jimplify1phase2()
    {
        hostType().getSootClassDecl().addMethod(perSymbolSootMethod());
        globalAspectInfo().addAdviceDecl(perSymbolAdviceDecl());
    }

    // Declared in SymbolCodegen.jrag at line 158


    public void jimplify2()
    {
        getPointcutExpr().jimplify2();
        SootMethod method = perSymbolSootMethod();
        JimpleBody body = Jimple.v().newBody(method);
        method.setActiveBody(body);
        Body b = new Body(hostType(), body, this);
        for (int i = 0; i < getNumParameter(); i++)
            getParameter(i).jimplify2(b);
        b.add(Jimple.v().newReturnVoidStmt());
        MethodCategory.register(method, MethodCategory.NO_EFFECTS_ON_BASE_CODE);
    }

    // Declared in TMErrorCheck.jrag at line 34


    public void typeCheck()
    {
        // do standard binding checks on the pointcut this symbol
        //   (delegate to symboldecl?)
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 7

    public SymbolDecl() {
        super();

        setChild(new List(), 2);

    }

    // Declared in tm.ast at line 11


    // Declared in tm.ast line 7
    public SymbolDecl(String p0, SymbolKind p1, PointcutExpr p2) {
        setID(p0);
        setChild(p1, 0);
        setChild(p2, 1);
        setChild(new List(), 2);
    }

    // Declared in tm.ast at line 19


    // Declared in tm.ast line 7
    public SymbolDecl(beaver.Symbol p0, SymbolKind p1, PointcutExpr p2) {
        setID(p0);
        setChild(p1, 0);
        setChild(p2, 1);
        setChild(new List(), 2);
    }

    // Declared in tm.ast at line 26


  protected int numChildren() {
    return 2;
  }

    // Declared in tm.ast at line 29

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 7
    private String tokenString_ID;

    // Declared in tm.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in tm.ast at line 6

    public int IDstart;

    // Declared in tm.ast at line 7

    public int IDend;

    // Declared in tm.ast at line 8

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in tm.ast at line 15

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 7
    public void setSymbolKind(SymbolKind node) {
        setChild(node, 0);
    }

    // Declared in tm.ast at line 5

    public SymbolKind getSymbolKind() {
        return (SymbolKind)getChild(0);
    }

    // Declared in tm.ast at line 9


    public SymbolKind getSymbolKindNoTransform() {
        return (SymbolKind)getChildNoTransform(0);
    }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 7
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 1);
    }

    // Declared in tm.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(1);
    }

    // Declared in tm.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(1);
    }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 7
    public void setParameterList(List<ParameterDeclaration> list) {
        setChild(list, 2);
    }

    // Declared in tm.ast at line 6


    private int getNumParameter = 0;

    // Declared in tm.ast at line 7

    public int getNumParameter() {
        return getParameterList().getNumChild();
    }

    // Declared in tm.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getParameter(int i) {
        return (ParameterDeclaration)getParameterList().getChild(i);
    }

    // Declared in tm.ast at line 15


    public void addParameter(ParameterDeclaration node) {
        List<ParameterDeclaration> list = getParameterList();
        list.addChild(node);
    }

    // Declared in tm.ast at line 20


    public void setParameter(ParameterDeclaration node, int i) {
        List<ParameterDeclaration> list = getParameterList();
        list.setChild(node, i);
    }

    // Declared in tm.ast at line 24

    public List<ParameterDeclaration> getParameters() {
        return getParameterList();
    }

    // Declared in tm.ast at line 27

    public List<ParameterDeclaration> getParametersNoTransform() {
        return getParameterListNoTransform();
    }

    // Declared in tm.ast at line 31


    public List<ParameterDeclaration> getParameterListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(2);
    }

    // Declared in tm.ast at line 35


    protected int getParameterListChildPosition() {
        return 2;
    }

    protected boolean perSymbolAdviceName_computed = false;
    protected String perSymbolAdviceName_value;
    // Declared in SymbolCodegen.jrag at line 42
 @SuppressWarnings({"unchecked", "cast"})     public String perSymbolAdviceName() {
        if(perSymbolAdviceName_computed)
            return perSymbolAdviceName_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        perSymbolAdviceName_value = perSymbolAdviceName_compute();
        if(isFinal && num == boundariesCrossed)
            perSymbolAdviceName_computed = true;
        return perSymbolAdviceName_value;
    }

    private String perSymbolAdviceName_compute() {  return getSymbolKind().kind() + "$" + hostType().adviceCounter++;  }

    // Declared in SymbolCodegen.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public TMAdviceDecl perSymbolAdviceDecl() {
        TMAdviceDecl perSymbolAdviceDecl_value = perSymbolAdviceDecl_compute();
        return perSymbolAdviceDecl_value;
    }

    private TMAdviceDecl perSymbolAdviceDecl_compute() {
        int[] implicit = implicitParameters();
        return new PerSymbolTMAdviceDecl(
            getSymbolKind().adviceSpec(),
            getPointcutExpr().pointcut(),
            perSymbolMethodSig(), // methodSig
            aspectClass(),
            implicit[0], implicit[1], implicit[2], // jp, jpsp, ejp
            perSymbolMethods(), // methods
            pos(), // symbol position
            traceMatchName(), // tm_id
            traceMatchPosition(), // tm_pos
            name(), // symbol name
            abc.tm.ast.TMAdviceDecl.OTHER);
    }

    // Declared in SymbolCodegen.jrag at line 68
 @SuppressWarnings({"unchecked", "cast"})     public int[] implicitParameters() {
        int[] implicitParameters_value = implicitParameters_compute();
        return implicitParameters_value;
    }

    private int[] implicitParameters_compute() {
        int[] implicit = symbolsImplicitParameters();
        int diff = -1;
        for (int i = 0; diff == -1 && i < getNumParameter(); i++)
            if (getParameter(i).isImplicit())
                diff = i;
        for (int i = 0; i < 3; i++)
            if (implicit[i] != -1)
                implicit[i] += diff;
        return implicit;
    }

    // Declared in SymbolCodegen.jrag at line 100
 @SuppressWarnings({"unchecked", "cast"})     public MethodSig perSymbolMethodSig() {
        MethodSig perSymbolMethodSig_value = perSymbolMethodSig_compute();
        return perSymbolMethodSig_value;
    }

    private MethodSig perSymbolMethodSig_compute() {
        return new MethodSig(soot.Modifier.PUBLIC,
            AbcFactory.AbcClass(hostType().getSootClassDecl()),
            AbcFactory.AbcType(returnType().getSootType()),
            perSymbolAdviceName(),
            perSymbolMethodFormals(),
            new ArrayList(),
            pos()
        );
    }

    // Declared in SymbolCodegen.jrag at line 112
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List perSymbolMethodFormals() {
        java.util.List perSymbolMethodFormals_value = perSymbolMethodFormals_compute();
        return perSymbolMethodFormals_value;
    }

    private java.util.List perSymbolMethodFormals_compute() {  return perSymbolFormals(true);  }

    // Declared in SymbolCodegen.jrag at line 115
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List perSymbolAdviceFormals() {
        java.util.List perSymbolAdviceFormals_value = perSymbolAdviceFormals_compute();
        return perSymbolAdviceFormals_value;
    }

    private java.util.List perSymbolAdviceFormals_compute() {  return perSymbolFormals(false);  }

    // Declared in SymbolCodegen.jrag at line 118
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List perSymbolFormals(boolean implicit) {
        java.util.List perSymbolFormals_boolean_value = perSymbolFormals_compute(implicit);
        return perSymbolFormals_boolean_value;
    }

    private java.util.List perSymbolFormals_compute(boolean implicit) {
        ArrayList formals = new ArrayList();
        for (int i = 0; i < getNumParameter(); i++)
            if (implicit || !getParameter(i).isImplicit())
                formals.add(getParameter(i).formal());
        return formals;
    }

    protected boolean perSymbolSootMethod_computed = false;
    protected SootMethod perSymbolSootMethod_value;
    // Declared in SymbolCodegen.jrag at line 127
 @SuppressWarnings({"unchecked", "cast"})     public SootMethod perSymbolSootMethod() {
        if(perSymbolSootMethod_computed)
            return perSymbolSootMethod_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        perSymbolSootMethod_value = perSymbolSootMethod_compute();
        if(isFinal && num == boundariesCrossed)
            perSymbolSootMethod_computed = true;
        return perSymbolSootMethod_value;
    }

    private SootMethod perSymbolSootMethod_compute() {
        String name = perSymbolAdviceName();
        ArrayList paramtypes = new ArrayList();
        Type returntype = soot.VoidType.v();
        int modifiers = soot.Modifier.PUBLIC;

        for (int i = 0; i < getNumParameter(); i++)
            paramtypes.add(getParameter(i).type().getSootType());

        SootMethod method =
            new SootMethod(name, paramtypes, returntype, modifiers);

        return method;
    }

    // Declared in SymbolCodegen.jrag at line 143
 @SuppressWarnings({"unchecked", "cast"})     public ArrayList perSymbolMethods() {
        ArrayList perSymbolMethods_value = perSymbolMethods_compute();
        return perSymbolMethods_value;
    }

    private ArrayList perSymbolMethods_compute() {
        ArrayList methods = new ArrayList();
        methods.add(perSymbolSootMethod());
        return methods;
    }

    // Declared in SymbolNameAnalysis.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

    protected boolean getParameterList_computed = false;
    protected List<ParameterDeclaration> getParameterList_value;
    // Declared in SymbolNameAnalysis.jrag at line 37
 @SuppressWarnings({"unchecked", "cast"})     public List<ParameterDeclaration> getParameterList() {
        if(getParameterList_computed)
            return (List<ParameterDeclaration>)ASTNode.getChild(this, getParameterListChildPosition());
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        getParameterList_value = getParameterList_compute();
        setParameterList(getParameterList_value);
        if(isFinal && num == boundariesCrossed)
            getParameterList_computed = true;
        return (List<ParameterDeclaration>)ASTNode.getChild(this, getParameterListChildPosition());
    }

    private List<ParameterDeclaration> getParameterList_compute() {
        List<ParameterDeclaration> params = new List<ParameterDeclaration>();
        for (int i = 0; i < numTraceMatchParameter(); i++) {
            ParameterDeclaration param = traceMatchParameter(i);
            if (this.refersTo(param)) {
                Modifiers mods = new Modifiers(new List());
                Access type = param.type().createQualifiedAccess();
                String name = param.name();

                ParameterDeclaration newparam = param.isImplicit()
                    ? new ImplicitParameterDeclaration(mods, type, name)
                    : new ParameterDeclaration(mods, type, name);
                params = params.add(newparam);
            }
        }
        return params;
    }

    // Declared in TMInfo.jrag at line 96
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List<String> formalNameList() {
        java.util.List<String> formalNameList_value = formalNameList_compute();
        return formalNameList_value;
    }

    private java.util.List<String> formalNameList_compute() {
        ArrayList<String> vars = new ArrayList<String>();
        for (int i = 0; i < getNumParameter(); i++)
            if (!getParameter(i).isImplicit())
                vars.add(getParameter(i).name());
        return vars;
    }

    // Declared in TMPointcutCodegen.jrag at line 74
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut closedPointcut() {
        Pointcut closedPointcut_value = closedPointcut_compute();
        return closedPointcut_value;
    }

    private Pointcut closedPointcut_compute() {  return new LocalPointcutVars(
            getPointcutExpr().pointcut(), perSymbolAdviceFormals(), pos());  }

    // Declared in SymbolCodegen.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public Aspect aspectClass() {
        Aspect aspectClass_value = getParent().Define_Aspect_aspectClass(this, null);
        return aspectClass_value;
    }

    // Declared in SymbolCodegen.jrag at line 26
 @SuppressWarnings({"unchecked", "cast"})     public String traceMatchName() {
        String traceMatchName_value = getParent().Define_String_traceMatchName(this, null);
        return traceMatchName_value;
    }

    // Declared in SymbolCodegen.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public polyglot.util.Position traceMatchPosition() {
        polyglot.util.Position traceMatchPosition_value = getParent().Define_polyglot_util_Position_traceMatchPosition(this, null);
        return traceMatchPosition_value;
    }

    // Declared in SymbolCodegen.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl returnType() {
        TypeDecl returnType_value = getParent().Define_TypeDecl_returnType(this, null);
        return returnType_value;
    }

    // Declared in SymbolCodegen.jrag at line 62
 @SuppressWarnings({"unchecked", "cast"})     public int[] symbolsImplicitParameters() {
        int[] symbolsImplicitParameters_value = getParent().Define_int_a_symbolsImplicitParameters(this, null);
        return symbolsImplicitParameters_value;
    }

    // Declared in SymbolNameAnalysis.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
        return hostType_value;
    }

    // Declared in SymbolNameAnalysis.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public int numTraceMatchParameter() {
        int numTraceMatchParameter_value = getParent().Define_int_numTraceMatchParameter(this, null);
        return numTraceMatchParameter_value;
    }

    // Declared in SymbolNameAnalysis.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public ParameterDeclaration traceMatchParameter(int i) {
        ParameterDeclaration traceMatchParameter_int_value = getParent().Define_ParameterDeclaration_traceMatchParameter(this, null, i);
        return traceMatchParameter_int_value;
    }

    // Declared in SymbolNameAnalysis.jrag at line 57
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isConstructorParameter(this, caller);
    }

    // Declared in SymbolNameAnalysis.jrag at line 56
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isMethodParameter(this, caller);
    }

    // Declared in SymbolCodegen.jrag at line 156
    public int Define_int_localNum(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return i;
        }
        return getParent().Define_int_localNum(this, caller);
    }

    // Declared in TMErrorCheck.jrag at line 40
    public boolean Define_boolean_bindsInCurrentCflow(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()) {
            return getPointcutExpr().binds(name) > 0;
        }
        return getParent().Define_boolean_bindsInCurrentCflow(this, caller, name);
    }

    // Declared in SymbolNameAnalysis.jrag at line 58
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
    }

    // Declared in SymbolCodegen.jrag at line 171
    public java.util.List Define_java_util_List_pointcutFormals(ASTNode caller, ASTNode child) {
        if(caller == getPointcutExprNoTransform()){
        ArrayList result = new ArrayList();
        lookupAllChildren();
        for (int i = 0; i < numTraceMatchParameter(); i++)
            if (this.refersTo(traceMatchParameter(i)))
                result.add(traceMatchParameter(i));
        return result;
    }
        return getParent().Define_java_util_List_pointcutFormals(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
