
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;



// ----------------------- Advice -------------------------


public class AdviceDecl extends BodyDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        name_computed = false;
        name_value = null;
        sootExceptionList_computed = false;
        sootExceptionList_value = null;
        abcExceptionList_computed = false;
        abcExceptionList_value = null;
        sootMethod_computed = false;
        sootMethod_value = null;
        proceedName_computed = false;
        proceedName_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AdviceDecl clone() throws CloneNotSupportedException {
        AdviceDecl node = (AdviceDecl)super.clone();
        node.name_computed = false;
        node.name_value = null;
        node.sootExceptionList_computed = false;
        node.sootExceptionList_value = null;
        node.abcExceptionList_computed = false;
        node.abcExceptionList_value = null;
        node.sootMethod_computed = false;
        node.sootMethod_value = null;
        node.proceedName_computed = false;
        node.proceedName_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AdviceDecl copy() {
      try {
          AdviceDecl node = (AdviceDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AdviceDecl fullCopy() {
        AdviceDecl res = (AdviceDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Advice.jrag at line 130

  
  // Pointcuts attached to advice should bind the variables the advice declares
  public void typeCheck() {
    AdviceSpec spec = getAdviceSpec();
    for(int i = 0; i < spec.getNumParameter(); i++) {
      int bindings = getPointcutExpr().binds(spec.getParameter(i).name());

      if(bindings > 1) {
        error("Advice formal " + spec.getParameter(i).name() + " is bound " +
              "multiple times in pointcut.");
      } else if(bindings == 0) {
        error("Advice formal " + spec.getParameter(i).name() +
              " is not bound " + "in pointcut.");
      }		
    }
    AroundSpec aroundspec = spec.aroundSpec();
    if (aroundspec != null && !aroundspec.getTypeAccess().type().isVoid()
        && getBlock().canCompleteNormally())
      error("the body of non void around advice must return a value");
  }

    // Declared in AdviceCodegen.jrag at line 42
		  
  
  public void jimplify1phase2() {
	  if(isEnabled()) {
	    hostType().getSootClassDecl().addMethod(sootMethod());
	    createAspectInfo();
	    getAdviceSpec().jimplify1phase2();
	  }
  }

    // Declared in AdviceCodegen.jrag at line 116


  protected void createAspectInfo() {
    int[] jp_params = implicitParameters();
    Pointcut pc = getPointcutExpr().pointcut();
    LinkedList methods = new LinkedList();
    getBlock().addAllEnclosedMethodSigs(methods);

    globalAspectInfo().addAdviceDecl(new abc.weaving.aspectinfo.AdviceDecl(
      getAdviceSpec().adviceSpec(),
      pc,
      methodSig(),
      aspectClass(),
      jp_params[0] /*jp*/,
      jp_params[1] /*jpsp*/,
      jp_params[2] /*ejp*/,
      methods,
      pos()
    ));
  }

    // Declared in AdviceCodegen.jrag at line 175


  protected MethodSig methodSig() {
    return new MethodSig(
      sootTypeModifiers(),
      AbcFactory.AbcClass(hostType().getSootClassDecl()),
      AbcFactory.AbcType(returnType().getSootType()),
      name(),
      getAdviceSpec().methodFormals(),
      abcExceptionList(),
      pos()
    );
  }

    // Declared in AdviceCodegen.jrag at line 246


  public void jimplify2() {
	  if(isEnabled()) {
	    JimpleBody body = Jimple.v().newBody(sootMethod());
	    sootMethod().setActiveBody(body);
	    Body b = new Body(hostType(), body, this);
	    getAdviceSpec().jimplify2(b);
	    getBlock().jimplify2(b);
	    if (getBlock().canCompleteNormally())
	        b.add(Jimple.v().newReturnVoidStmt());
	    getAdviceSpec().jimplify2();
	    getPointcutExpr().jimplify2();
	    MethodCategory.register(sootMethod(), MethodCategory.ADVICE_BODY);
	  }
  }

    // Declared in ImplicitVariables.jrag at line 127




    // some of this may eventually be split off into
    // ImplicitVariablesCodegen.jrag

    public static String implicitVarName(int i)
    {
        switch(i) {
            case 0:
                return "thisJoinPoint";
            case 1:
                return "thisJoinPointStaticPart";
            case 2:
                return "thisEnclosingJoinPointStaticPart";
        }
        throw new
            InternalCompilerError("No such implicit advice variable: " + i);
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 13

    public AdviceDecl() {
        super();

        setChild(new List(), 3);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 13
    public AdviceDecl(Modifiers p0, AdviceSpec p1, PointcutExpr p2, List<Access> p3, Block p4) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
        setChild(p3, 3);
        setChild(p4, 4);
    }

    // Declared in AspectJ.ast at line 19


  protected int numChildren() {
    return 5;
  }

    // Declared in AspectJ.ast at line 22

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setModifiers(Modifiers node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public Modifiers getModifiers() {
        return (Modifiers)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public Modifiers getModifiersNoTransform() {
        return (Modifiers)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setAdviceSpec(AdviceSpec node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public AdviceSpec getAdviceSpec() {
        return (AdviceSpec)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public AdviceSpec getAdviceSpecNoTransform() {
        return (AdviceSpec)getChildNoTransform(1);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 2);
    }

    // Declared in AspectJ.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(2);
    }

    // Declared in AspectJ.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(2);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setExceptionList(List<Access> list) {
        setChild(list, 3);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumException = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumException() {
        return getExceptionList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Access getException(int i) {
        return (Access)getExceptionList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addException(Access node) {
        List<Access> list = getExceptionList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setException(Access node, int i) {
        List<Access> list = getExceptionList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<Access> getExceptions() {
        return getExceptionList();
    }

    // Declared in AspectJ.ast at line 27

    public List<Access> getExceptionsNoTransform() {
        return getExceptionListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionList() {
        return (List<Access>)getChild(3);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionListNoTransform() {
        return (List<Access>)getChildNoTransform(3);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setBlock(Block node) {
        setChild(node, 4);
    }

    // Declared in AspectJ.ast at line 5

    public Block getBlock() {
        return (Block)getChild(4);
    }

    // Declared in AspectJ.ast at line 9


    public Block getBlockNoTransform() {
        return (Block)getChildNoTransform(4);
    }

    // Declared in Advice.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl returnType() {
        TypeDecl returnType_value = returnType_compute();
        return returnType_value;
    }

    private TypeDecl returnType_compute() {  return getAdviceSpec().returnType();  }

    protected boolean name_computed = false;
    protected String name_value;
    // Declared in AdviceCodegen.jrag at line 30
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        if(name_computed)
            return name_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        name_value = name_compute();
        if(isFinal && num == boundariesCrossed)
            name_computed = true;
        return name_value;
    }

    private String name_compute() {  return getAdviceSpec().kind() + "$" + hostType().adviceCounter++;  }

    // Declared in AdviceCodegen.jrag at line 40
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEnabled() {
        boolean isEnabled_value = isEnabled_compute();
        return isEnabled_value;
    }

    private boolean isEnabled_compute() {  return !(abc.main.Debug.v().lazyAdviceGeneration && getPointcutExpr().isStaticallyFalse());  }

    protected boolean sootExceptionList_computed = false;
    protected java.util.List sootExceptionList_value;
    // Declared in AdviceCodegen.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List sootExceptionList() {
        if(sootExceptionList_computed)
            return sootExceptionList_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        sootExceptionList_value = sootExceptionList_compute();
        if(isFinal && num == boundariesCrossed)
            sootExceptionList_computed = true;
        return sootExceptionList_value;
    }

    private java.util.List sootExceptionList_compute() {
    ArrayList list = new ArrayList();
    for (int i = 0; i < getNumException(); i++)
      list.add(getException(i).type().getSootClassDecl());
    return list;
  }

    protected boolean abcExceptionList_computed = false;
    protected java.util.List abcExceptionList_value;
    // Declared in AdviceCodegen.jrag at line 78
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List abcExceptionList() {
        if(abcExceptionList_computed)
            return abcExceptionList_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        abcExceptionList_value = abcExceptionList_compute();
        if(isFinal && num == boundariesCrossed)
            abcExceptionList_computed = true;
        return abcExceptionList_value;
    }

    private java.util.List abcExceptionList_compute() {
    ArrayList list = new ArrayList();
    for (int i = 0; i < getNumException(); i++)
      list.add(AbcFactory.AbcType(getException(i).type().getSootType()));
    return list;
  }

    protected boolean sootMethod_computed = false;
    protected SootMethod sootMethod_value;
    // Declared in AdviceCodegen.jrag at line 106
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
    SootMethod advice =
        new SootMethod(name(), getAdviceSpec().paramList(),
                       returnType().getSootType(),
                       sootTypeModifiers(), sootExceptionList());
    advice.addTag(
        new soot.tagkit.ParamNamesTag(getAdviceSpec().paramNameList()));
    return advice;
  }

    // Declared in AdviceCodegen.jrag at line 215
 @SuppressWarnings({"unchecked", "cast"})     public int sootTypeModifiers() {
        int sootTypeModifiers_value = sootTypeModifiers_compute();
        return sootTypeModifiers_value;
    }

    private int sootTypeModifiers_compute() {
    int result = 0;
    result |= soot.Modifier.PUBLIC;
    if(getModifiers().isSynchronized()) result |= soot.Modifier.SYNCHRONIZED;
    if(getModifiers().isStrictfp()) result |= soot.Modifier.STRICTFP;
    return result;
  }

    protected boolean proceedName_computed = false;
    protected String proceedName_value;
    // Declared in AdviceCodegen.jrag at line 291
 @SuppressWarnings({"unchecked", "cast"})     public String proceedName() {
        if(proceedName_computed)
            return proceedName_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        proceedName_value = proceedName_compute();
        if(isFinal && num == boundariesCrossed)
            proceedName_computed = true;
        return proceedName_value;
    }

    private String proceedName_compute() {  return "proceed" + name().substring(6);  }

    // Declared in ImplicitVariables.jrag at line 141
 @SuppressWarnings({"unchecked", "cast"})     public int[] implicitParameters() {
        int[] implicitParameters_value = implicitParameters_compute();
        return implicitParameters_value;
    }

    private int[] implicitParameters_compute() {
        int[] params = new int[3];
        AdviceSpec spec = getAdviceSpec();
        
        for (int i = 0; i < 3; i++) {
            params[i] = -1;

            for (int j = 0; j < spec.getNumParameter(); j++)
                if (spec.getParameter(j).name().equals(implicitVarName(i)))
                    params[i] = j;
        }
        return params;
    }

    // Declared in Advice.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        return lookupVariable_String_value;
    }

    // Declared in Advice.jrag at line 24
    public TypeDecl Define_TypeDecl_returnType(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return returnType();
        }
        return getParent().Define_TypeDecl_returnType(this, caller);
    }

    // Declared in Pointcuts.jrag at line 106
    public boolean Define_boolean_bindsInCurrentCflow(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()) {
            return getPointcutExpr().binds(name) > 0;
        }
        return getParent().Define_boolean_bindsInCurrentCflow(this, caller, name);
    }

    // Declared in AdviceCodegen.jrag at line 273
    public AroundSpec Define_AroundSpec_aroundSpec(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return getAdviceSpec().aroundSpec();
        }
        return getParent().Define_AroundSpec_aroundSpec(this, caller);
    }

    // Declared in Variables.jrag at line 186
    public boolean Define_boolean_isCircular(ASTNode caller, ASTNode child, ParameterDeclaration decl) {
        if(caller == getAdviceSpecNoTransform()) {
            return getPointcutExpr().isCircular(decl);
        }
        return getParent().Define_boolean_isCircular(this, caller, decl);
    }

    // Declared in Advice.jrag at line 46
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()){
    SimpleSet v = getAdviceSpec().localLookupVariable(name);
    if(!v.isEmpty()) return v;
    return lookupVariable(name);
  }
        if(caller == getBlockNoTransform()){
    SimpleSet v = getAdviceSpec().localLookupVariable(name);
    if(!v.isEmpty()) return v;
    v = getAdviceSpec().lookupAfterVariable(name);
    if(!v.isEmpty()) return v;
    return lookupVariable(name);
  }
        if(caller == getAdviceSpecNoTransform()){
    SimpleSet v = getAdviceSpec().localLookupVariable(name);
    if(!v.isEmpty()) return v;
    v = getAdviceSpec().lookupAfterVariable(name);
    if(!v.isEmpty()) return v;
    return lookupVariable(name);
  }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in Advice.jrag at line 76
    public boolean Define_boolean_mayBeSynchronized(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeSynchronized(this, caller);
    }

    // Declared in Advice.jrag at line 77
    public boolean Define_boolean_mayBeStrictfp(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeStrictfp(this, caller);
    }

    // Declared in Advice.jrag at line 74
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in Patterns.jrag at line 126
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getPointcutExprNoTransform()) {
            return NameType.TYPE_NAME;
        }
        if(caller == getExceptionListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in AdviceCodegen.jrag at line 294
    public MethodSig Define_MethodSig_proceedSig(ASTNode caller, ASTNode child) {
        if(caller == getAdviceSpecNoTransform()){
    return new MethodSig(
      soot.Modifier.PUBLIC | soot.Modifier.FINAL | soot.Modifier.STATIC,
      AbcFactory.AbcClass(hostType().getSootClassDecl()),
      AbcFactory.AbcType(returnType().getSootType()),
      proceedName(),
      getAdviceSpec().aroundSpec().proceedFormals(),
      new ArrayList(),
      pos());
  }
        return getParent().Define_MethodSig_proceedSig(this, caller);
    }

    // Declared in ImplicitVariables.jrag at line 182
    public java.util.List Define_java_util_List_pointcutFormals(ASTNode caller, ASTNode child) {
        if(caller == getPointcutExprNoTransform()){
        AdviceSpec spec = getAdviceSpec();
        ArrayList result = new ArrayList();
        for (int i = 0; i < spec.getNumParameter(); i++)
            result.add(spec.getParameter(i));
        return result;
    }
        return getParent().Define_java_util_List_pointcutFormals(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
