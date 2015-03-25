
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



public abstract class AdviceSpec extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        paramList_computed = false;
        paramList_value = null;
        paramNameList_computed = false;
        paramNameList_value = null;
        getAroundParameterList_computed = false;
        getAroundParameterList_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AdviceSpec clone() throws CloneNotSupportedException {
        AdviceSpec node = (AdviceSpec)super.clone();
        node.paramList_computed = false;
        node.paramList_value = null;
        node.paramNameList_computed = false;
        node.paramNameList_value = null;
        node.getAroundParameterList_computed = false;
        node.getAroundParameterList_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in AdviceCodegen.jrag at line 154


  protected java.util.List methodFormals() {
    ArrayList list = new ArrayList();
    for(int i = 0; i < getNumParameter(); i++)
      list.add(getParameter(i).formal());
    return list;
  }

    // Declared in AdviceCodegen.jrag at line 187


  abstract public abc.weaving.aspectinfo.AdviceSpec adviceSpec();

    // Declared in AdviceCodegen.jrag at line 223


  public void jimplify2(Body b) {
    for(int i = 0; i < getNumParameter(); i++)
      getParameter(i).jimplify2(b);
  }

    // Declared in AdviceCodegen.jrag at line 346


  public void jimplify1phase2() { }

    // Declared in ImplicitVariables.jrag at line 100



    // this is run before the implicit parameters are added
    public void nameCheck()
    {
        super.nameCheck();
        for (int i = 0; i < getNumParameter(); i++)
            getParameter(i).errorIfImplicitName("advice bodies");
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 16

    public AdviceSpec() {
        super();

        setChild(new List(), 0);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 16
    public AdviceSpec(List<ParameterDeclaration> p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 15


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 16
    public void setParameterList(List<ParameterDeclaration> list) {
        setChild(list, 0);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumParameter = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumParameter() {
        return getParameterList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getParameter(int i) {
        return (ParameterDeclaration)getParameterList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addParameter(ParameterDeclaration node) {
        List<ParameterDeclaration> list = getParameterList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setParameter(ParameterDeclaration node, int i) {
        List<ParameterDeclaration> list = getParameterList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<ParameterDeclaration> getParameters() {
        return getParameterList();
    }

    // Declared in AspectJ.ast at line 27

    public List<ParameterDeclaration> getParametersNoTransform() {
        return getParameterListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterList() {
        return (List<ParameterDeclaration>)getChild(0);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(0);
    }

    // Declared in AdviceCodegen.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public abstract String kind();
    // Declared in Advice.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl returnType() {
        TypeDecl returnType_value = returnType_compute();
        return returnType_value;
    }

    private TypeDecl returnType_compute() {  return typeVoid();  }

    // Declared in Advice.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localLookupVariable(String name) {
        SimpleSet localLookupVariable_String_value = localLookupVariable_compute(name);
        return localLookupVariable_String_value;
    }

    private SimpleSet localLookupVariable_compute(String name) {
    for(int i = 0; i < getNumParameter(); i++)
      if(getParameter(i).name().equals(name))
        return SimpleSet.emptySet.add(getParameter(i));
    return localLookupImplicitVariable(name);
  }

    // Declared in Advice.jrag at line 57
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupAfterVariable(String name) {
        SimpleSet lookupAfterVariable_String_value = lookupAfterVariable_compute(name);
        return lookupAfterVariable_String_value;
    }

    private SimpleSet lookupAfterVariable_compute(String name) {  return SimpleSet.emptySet;  }

    protected boolean paramList_computed = false;
    protected java.util.List paramList_value;
    // Declared in AdviceCodegen.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List paramList() {
        if(paramList_computed)
            return paramList_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        paramList_value = paramList_compute();
        if(isFinal && num == boundariesCrossed)
            paramList_computed = true;
        return paramList_value;
    }

    private java.util.List paramList_compute() {
    ArrayList list = new ArrayList();
    for(int i = 0; i < getNumParameter(); i++)
      list.add(getParameter(i).type().getSootType());
    return list;
  }

    protected boolean paramNameList_computed = false;
    protected ArrayList paramNameList_value;
    // Declared in AdviceCodegen.jrag at line 85
 @SuppressWarnings({"unchecked", "cast"})     public ArrayList paramNameList() {
        if(paramNameList_computed)
            return paramNameList_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        paramNameList_value = paramNameList_compute();
        if(isFinal && num == boundariesCrossed)
            paramNameList_computed = true;
        return paramNameList_value;
    }

    private ArrayList paramNameList_compute() {
    ArrayList list = new ArrayList();
    for(int i = 0; i < getNumParameter(); i++)
      list.add(getParameter(i).name());
    return list;
  }

    // Declared in AdviceCodegen.jrag at line 268
 @SuppressWarnings({"unchecked", "cast"})     public AroundSpec aroundSpec() {
        AroundSpec aroundSpec_value = aroundSpec_compute();
        return aroundSpec_value;
    }

    private AroundSpec aroundSpec_compute() {  return null;  }

    // Declared in ImplicitVariables.jrag at line 58
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localLookupImplicitVariable(String name) {
        SimpleSet localLookupImplicitVariable_String_value = localLookupImplicitVariable_compute(name);
        return localLookupImplicitVariable_String_value;
    }

    private SimpleSet localLookupImplicitVariable_compute(String name) {
        if (name.equals("thisJoinPoint")
            || name.equals("thisJoinPointStaticPart")
            || name.equals("thisEnclosingJoinPointStaticPart"))
        {
            ParameterDeclaration decl = implicitVarDecl(name);
            // add decl to tree (as parameter to the advice method)
            // and force possible rewrites to this decl (by reading it)
            addParameter(decl);
            getParameter(getNumParameter() - 1);
            return SimpleSet.emptySet.add(decl);
        }
        return SimpleSet.emptySet;
    }

    // Declared in ImplicitVariables.jrag at line 77
 @SuppressWarnings({"unchecked", "cast"})     public int getNumAroundParameter() {
        int getNumAroundParameter_value = getNumAroundParameter_compute();
        return getNumAroundParameter_value;
    }

    private int getNumAroundParameter_compute() {
        return getAroundParameterList().size();
    }

    protected boolean getAroundParameterList_computed = false;
    protected ArrayList getAroundParameterList_value;
    // Declared in ImplicitVariables.jrag at line 82
 @SuppressWarnings({"unchecked", "cast"})     public ArrayList getAroundParameterList() {
        if(getAroundParameterList_computed)
            return getAroundParameterList_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        getAroundParameterList_value = getAroundParameterList_compute();
        if(isFinal && num == boundariesCrossed)
            getAroundParameterList_computed = true;
        return getAroundParameterList_value;
    }

    private ArrayList getAroundParameterList_compute() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < getNumParameter(); i++) {
            ParameterDeclaration decl = getParameter(i);
            if (!decl.isImplicit())
                list.add(decl);
        }
        return list;
    }

    // Declared in ImplicitVariables.jrag at line 93
 @SuppressWarnings({"unchecked", "cast"})     public ParameterDeclaration getAroundParameter(int i) {
        ParameterDeclaration getAroundParameter_int_value = getAroundParameter_compute(i);
        return getAroundParameter_int_value;
    }

    private ParameterDeclaration getAroundParameter_compute(int i) {
        return (ParameterDeclaration)getAroundParameterList().get(i);
    }

    // Declared in Advice.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeVoid() {
        TypeDecl typeVoid_value = getParent().Define_TypeDecl_typeVoid(this, null);
        return typeVoid_value;
    }

    // Declared in AdviceCodegen.jrag at line 322
 @SuppressWarnings({"unchecked", "cast"})     public Aspect aspectClass() {
        Aspect aspectClass_value = getParent().Define_Aspect_aspectClass(this, null);
        return aspectClass_value;
    }

    // Declared in AdviceCodegen.jrag at line 323
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
        return hostType_value;
    }

    // Declared in ImplicitVariables.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public ParameterDeclaration implicitVarDecl(String name) {
        ParameterDeclaration implicitVarDecl_String_value = getParent().Define_ParameterDeclaration_implicitVarDecl(this, null, name);
        return implicitVarDecl_String_value;
    }

    // Declared in Advice.jrag at line 82
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isConstructorParameter(this, caller);
    }

    // Declared in Advice.jrag at line 81
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return true;
        }
        return getParent().Define_boolean_isMethodParameter(this, caller);
    }

    // Declared in AdviceCodegen.jrag at line 260
    public int Define_int_localNum(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return index;
        }
        return getParent().Define_int_localNum(this, caller);
    }

    // Declared in Advice.jrag at line 83
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
    }

    // Declared in Advice.jrag at line 85
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in Advice.jrag at line 84
    public boolean Define_boolean_isPointcutVariable(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return true;
        }
        return getParent().Define_boolean_isPointcutVariable(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
