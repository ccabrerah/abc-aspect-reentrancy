
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;

public class LocalVarsPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        declares_String_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LocalVarsPointcutExpr clone() throws CloneNotSupportedException {
        LocalVarsPointcutExpr node = (LocalVarsPointcutExpr)super.clone();
        node.declares_String_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LocalVarsPointcutExpr copy() {
      try {
          LocalVarsPointcutExpr node = (LocalVarsPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LocalVarsPointcutExpr fullCopy() {
        LocalVarsPointcutExpr res = (LocalVarsPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Pointcuts.jrag at line 57




  //
  // Converting to the backend representation of pointcuts.
  //

  public Pointcut pointcut() {
    ArrayList list = new ArrayList();
    for(int i = 0; i < getNumParameter(); i++)
      list.add(getParameter(i).formal());
    return new abc.weaving.aspectinfo.LocalPointcutVars(getPointcutExpr().pointcut(), list, pos());
  }

    // Declared in Variables.jrag at line 85


  public void typeCheck()
  {
    for (int i = 0; i < getNumParameter(); i++) {
      int bindings = getPointcutExpr().binds(getParameter(i).name());
      if (bindings < 1) 
        error("The private pointcut variable " + getParameter(i).name() +
              " may be unbound.");
      else if (bindings > 1)
        error("The private pointcut variable " + getParameter(i).name() +
              " may be bound multiple times.");
    }
  }

    // Declared in eaj.ast at line 3
    // Declared in eaj.ast line 1

    public LocalVarsPointcutExpr() {
        super();

        setChild(new List(), 0);

    }

    // Declared in eaj.ast at line 11


    // Declared in eaj.ast line 1
    public LocalVarsPointcutExpr(List<ParameterDeclaration> p0, PointcutExpr p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in eaj.ast at line 16


  protected int numChildren() {
    return 2;
  }

    // Declared in eaj.ast at line 19

  public boolean mayHaveRewrite() { return false; }

    // Declared in eaj.ast at line 2
    // Declared in eaj.ast line 1
    public void setParameterList(List<ParameterDeclaration> list) {
        setChild(list, 0);
    }

    // Declared in eaj.ast at line 6


    private int getNumParameter = 0;

    // Declared in eaj.ast at line 7

    public int getNumParameter() {
        return getParameterList().getNumChild();
    }

    // Declared in eaj.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getParameter(int i) {
        return (ParameterDeclaration)getParameterList().getChild(i);
    }

    // Declared in eaj.ast at line 15


    public void addParameter(ParameterDeclaration node) {
        List<ParameterDeclaration> list = getParameterList();
        list.addChild(node);
    }

    // Declared in eaj.ast at line 20


    public void setParameter(ParameterDeclaration node, int i) {
        List<ParameterDeclaration> list = getParameterList();
        list.setChild(node, i);
    }

    // Declared in eaj.ast at line 24

    public List<ParameterDeclaration> getParameters() {
        return getParameterList();
    }

    // Declared in eaj.ast at line 27

    public List<ParameterDeclaration> getParametersNoTransform() {
        return getParameterListNoTransform();
    }

    // Declared in eaj.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterList() {
        return (List<ParameterDeclaration>)getChild(0);
    }

    // Declared in eaj.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(0);
    }

    // Declared in eaj.ast at line 2
    // Declared in eaj.ast line 1
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 1);
    }

    // Declared in eaj.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(1);
    }

    // Declared in eaj.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(1);
    }

    // Declared in Variables.jrag at line 43
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localLookupVariable(String name) {
        SimpleSet localLookupVariable_String_value = localLookupVariable_compute(name);
        return localLookupVariable_String_value;
    }

    private SimpleSet localLookupVariable_compute(String name) {
    for (int i = 0; i < getNumParameter(); i++) {
      if (getParameter(i).name().equals(name))
        return SimpleSet.emptySet.add(getParameter(i));
    }
    return SimpleSet.emptySet;
  }

    protected java.util.Map declares_String_values;
    // Declared in Variables.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public boolean declares(String name) {
        Object _parameters = name;
if(declares_String_values == null) declares_String_values = new java.util.HashMap(4);
        if(declares_String_values.containsKey(_parameters))
            return ((Boolean)declares_String_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean declares_String_value = declares_compute(name);
        if(isFinal && num == boundariesCrossed)
            declares_String_values.put(_parameters, Boolean.valueOf(declares_String_value));
        return declares_String_value;
    }

    private boolean declares_compute(String name) {
    for (int i = 0; i < getNumParameter(); i++)
      if (getParameter(i).name().equals(name))
        return true;
    return false;
  }

    // Declared in Variables.jrag at line 82
 @SuppressWarnings({"unchecked", "cast"})     public int binds(String var) {
        int binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private int binds_compute(String var) {  return this.declares(var) ? 0 : getPointcutExpr().binds(var);  }

    // Declared in Variables.jrag at line 24
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isConstructorParameter(this, caller);
    }

    // Declared in Variables.jrag at line 23
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isMethodParameter(this, caller);
    }

    // Declared in Variables.jrag at line 101
    public boolean Define_boolean_bindsInCurrentCflow(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()) {
            return this.declares(name) ? getPointcutExpr().binds(name) > 0
                        : bindsInCurrentCflow(name);
        }
        return getParent().Define_boolean_bindsInCurrentCflow(this, caller, name);
    }

    // Declared in Variables.jrag at line 37
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()){
    SimpleSet result = localLookupVariable(name);
    return result.isEmpty() ? lookupVariable(name) : result;
  }
        if(caller == getParameterListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    SimpleSet result = localLookupVariable(name);
    return result.isEmpty() ? lookupVariable(name) : result;
  }
}
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in Variables.jrag at line 25
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
    }

    // Declared in Variables.jrag at line 26
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in Variables.jrag at line 63
    public java.util.List Define_java_util_List_pointcutFormals(ASTNode caller, ASTNode child) {
        if(caller == getPointcutExprNoTransform()){
    java.util.List result = pointcutFormals();
    // handling shadowing by removing formals from the list
    Iterator outer = result.iterator();
    while (outer.hasNext()) {
      ParameterDeclaration decl = (ParameterDeclaration) outer.next();
      if (this.declares(decl.name()))
        outer.remove();
    }
    // add private pointcut variables
    for (int i = 0; i < getNumParameter(); i++)
      result.add(getParameter(i));

    return result;
  }
        return getParent().Define_java_util_List_pointcutFormals(this, caller);
    }

    // Declared in Variables.jrag at line 53
    public boolean Define_boolean_isPointcutVariable(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return true;
        }
        return getParent().Define_boolean_isPointcutVariable(this, caller);
    }

    // Declared in Variables.jrag at line 181
    public boolean Define_boolean_isCircular(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return getPointcutExpr().isCircular(getParameter(index));
        }
        return getParent().Define_boolean_isCircular(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
