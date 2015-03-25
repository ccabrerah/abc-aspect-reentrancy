
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class AfterThrowingSpec extends AfterSpec implements Cloneable {
    public void flushCache() {
        super.flushCache();
        paramList_computed = false;
        paramList_value = null;
        paramNameList_computed = false;
        paramNameList_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AfterThrowingSpec clone() throws CloneNotSupportedException {
        AfterThrowingSpec node = (AfterThrowingSpec)super.clone();
        node.paramList_computed = false;
        node.paramList_value = null;
        node.paramNameList_computed = false;
        node.paramNameList_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AfterThrowingSpec copy() {
      try {
          AfterThrowingSpec node = (AfterThrowingSpec)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AfterThrowingSpec fullCopy() {
        AfterThrowingSpec res = (AfterThrowingSpec)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AdviceCodegen.jrag at line 168


  protected java.util.List methodFormals() {
    java.util.List list = super.methodFormals();
    if(hasExceptionParameter())
      list.add(getExceptionParameter().formal());
    return list;
  }

    // Declared in AdviceCodegen.jrag at line 202

  public abc.weaving.aspectinfo.AdviceSpec adviceSpec() {
    if(hasExceptionParameter())
      return new AfterThrowingArgAdvice(
                    getExceptionParameter().formal(), pos());
    else
      return new AfterThrowingAdvice(pos());
  }

    // Declared in AdviceCodegen.jrag at line 240


  public void jimplify2(Body b) {
    super.jimplify2(b);
    if (hasExceptionParameter())
      getExceptionParameter().jimplify2(b);
  }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 20

    public AfterThrowingSpec() {
        super();

        setChild(new List(), 0);
        setChild(new Opt(), 1);

    }

    // Declared in AspectJ.ast at line 12


    // Declared in AspectJ.ast line 20
    public AfterThrowingSpec(List<ParameterDeclaration> p0, Opt<ParameterDeclaration> p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in AspectJ.ast at line 17


  protected int numChildren() {
    return 2;
  }

    // Declared in AspectJ.ast at line 20

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

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 20
    public void setExceptionParameterOpt(Opt<ParameterDeclaration> opt) {
        setChild(opt, 1);
    }

    // Declared in AspectJ.ast at line 6


    public boolean hasExceptionParameter() {
        return getExceptionParameterOpt().getNumChild() != 0;
    }

    // Declared in AspectJ.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getExceptionParameter() {
        return (ParameterDeclaration)getExceptionParameterOpt().getChild(0);
    }

    // Declared in AspectJ.ast at line 14


    public void setExceptionParameter(ParameterDeclaration node) {
        getExceptionParameterOpt().setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<ParameterDeclaration> getExceptionParameterOpt() {
        return (Opt<ParameterDeclaration>)getChild(1);
    }

    // Declared in AspectJ.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<ParameterDeclaration> getExceptionParameterOptNoTransform() {
        return (Opt<ParameterDeclaration>)getChildNoTransform(1);
    }

    // Declared in Advice.jrag at line 65
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupAfterVariable(String name) {
        SimpleSet lookupAfterVariable_String_value = lookupAfterVariable_compute(name);
        return lookupAfterVariable_String_value;
    }

    private SimpleSet lookupAfterVariable_compute(String name) {
    if(hasExceptionParameter() &&
       getExceptionParameter().name().equals(name))
      return SimpleSet.emptySet.add(getExceptionParameter());
    return SimpleSet.emptySet;
  }

    // Declared in AdviceCodegen.jrag at line 37
 @SuppressWarnings({"unchecked", "cast"})     public String kind() {
        String kind_value = kind_compute();
        return kind_value;
    }

    private String kind_compute() {  return "after";  }

    // Declared in AdviceCodegen.jrag at line 64
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
    java.util.List list = super.paramList();
    if(hasExceptionParameter())
      list.add(getExceptionParameter().type().getSootType());
    return list;
  }

    // Declared in AdviceCodegen.jrag at line 99
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
    ArrayList list = super.paramNameList();
    if(hasExceptionParameter())
      list.add(getExceptionParameter().name());
    return list;
  }

    // Declared in Advice.jrag at line 93
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        if(caller == getExceptionParameterOptNoTransform()) {
            return false;
        }
        return super.Define_boolean_isConstructorParameter(caller, child);
    }

    // Declared in Advice.jrag at line 92
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        if(caller == getExceptionParameterOptNoTransform()) {
            return true;
        }
        return super.Define_boolean_isMethodParameter(caller, child);
    }

    // Declared in AdviceCodegen.jrag at line 238
    public int Define_int_localNum(ASTNode caller, ASTNode child) {
        if(caller == getExceptionParameterOptNoTransform()) {
            return getNumParameter();
        }
        return super.Define_int_localNum(caller, child);
    }

    // Declared in Advice.jrag at line 94
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        if(caller == getExceptionParameterOptNoTransform()) {
            return false;
        }
        return super.Define_boolean_isExceptionHandlerParameter(caller, child);
    }

    // Declared in Advice.jrag at line 95
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getExceptionParameterOptNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return super.Define_NameType_nameType(caller, child);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
