
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;



public class ParMethodAccess extends MethodAccess implements Cloneable {
    public void flushCache() {
        super.flushCache();
        typeArguments_MethodDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParMethodAccess clone() throws CloneNotSupportedException {
        ParMethodAccess node = (ParMethodAccess)super.clone();
        node.typeArguments_MethodDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParMethodAccess copy() {
      try {
          ParMethodAccess node = (ParMethodAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParMethodAccess fullCopy() {
        ParMethodAccess res = (ParMethodAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in GenericMethods.jrag at line 11

  public void typeCheck() {
    super.typeCheck();
    if(!decl().hostType().isUnknown()) {
      if(!(decl() instanceof ParMethodDecl))
        error("can not have type parameters on a non generic method");
      else {
        ParMethodDecl m = (ParMethodDecl)decl();
        if(m.numTypeParameter() != getNumTypeArgument())
          error("generic method " + m.signature() + " requires " + m.numTypeParameter() + " type arguments");
        else {
        }
      }
    }
  }

    // Declared in GenericMethods.jrag at line 115


  public void toString(StringBuffer s) {
    s.append("<");
    for(int i = 0; i < getNumTypeArgument(); i++) {
      if(i != 0) s.append(", ");
      getTypeArgument(i).toString(s);
    }
    s.append(">");
    super.toString(s);
  }

    // Declared in GenericMethods.ast at line 3
    // Declared in GenericMethods.ast line 7

    public ParMethodAccess() {
        super();

        setChild(new List(), 0);
        setChild(new List(), 1);

    }

    // Declared in GenericMethods.ast at line 12


    // Declared in GenericMethods.ast line 7
    public ParMethodAccess(String p0, List<Expr> p1, List<Access> p2) {
        setID(p0);
        setChild(p1, 0);
        setChild(p2, 1);
    }

    // Declared in GenericMethods.ast at line 19


    // Declared in GenericMethods.ast line 7
    public ParMethodAccess(beaver.Symbol p0, List<Expr> p1, List<Access> p2) {
        setID(p0);
        setChild(p1, 0);
        setChild(p2, 1);
    }

    // Declared in GenericMethods.ast at line 25


  protected int numChildren() {
    return 2;
  }

    // Declared in GenericMethods.ast at line 28

  public boolean mayHaveRewrite() { return false; }

    // Declared in java.ast at line 2
    // Declared in java.ast line 17
    public void setArgList(List<Expr> list) {
        setChild(list, 0);
    }

    // Declared in java.ast at line 6


    private int getNumArg = 0;

    // Declared in java.ast at line 7

    public int getNumArg() {
        return getArgList().getNumChild();
    }

    // Declared in java.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Expr getArg(int i) {
        return (Expr)getArgList().getChild(i);
    }

    // Declared in java.ast at line 15


    public void addArg(Expr node) {
        List<Expr> list = getArgList();
        list.addChild(node);
    }

    // Declared in java.ast at line 20


    public void setArg(Expr node, int i) {
        List<Expr> list = getArgList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 24

    public List<Expr> getArgs() {
        return getArgList();
    }

    // Declared in java.ast at line 27

    public List<Expr> getArgsNoTransform() {
        return getArgListNoTransform();
    }

    // Declared in java.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgList() {
        return (List<Expr>)getChild(0);
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgListNoTransform() {
        return (List<Expr>)getChildNoTransform(0);
    }

    // Declared in GenericMethods.ast at line 2
    // Declared in GenericMethods.ast line 7
    public void setTypeArgumentList(List<Access> list) {
        setChild(list, 1);
    }

    // Declared in GenericMethods.ast at line 6


    private int getNumTypeArgument = 0;

    // Declared in GenericMethods.ast at line 7

    public int getNumTypeArgument() {
        return getTypeArgumentList().getNumChild();
    }

    // Declared in GenericMethods.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Access getTypeArgument(int i) {
        return (Access)getTypeArgumentList().getChild(i);
    }

    // Declared in GenericMethods.ast at line 15


    public void addTypeArgument(Access node) {
        List<Access> list = getTypeArgumentList();
        list.addChild(node);
    }

    // Declared in GenericMethods.ast at line 20


    public void setTypeArgument(Access node, int i) {
        List<Access> list = getTypeArgumentList();
        list.setChild(node, i);
    }

    // Declared in GenericMethods.ast at line 24

    public List<Access> getTypeArguments() {
        return getTypeArgumentList();
    }

    // Declared in GenericMethods.ast at line 27

    public List<Access> getTypeArgumentsNoTransform() {
        return getTypeArgumentListNoTransform();
    }

    // Declared in GenericMethods.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getTypeArgumentList() {
        return (List<Access>)getChild(1);
    }

    // Declared in GenericMethods.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getTypeArgumentListNoTransform() {
        return (List<Access>)getChildNoTransform(1);
    }

    // Declared in MethodSignature.jrag at line 292
 @SuppressWarnings({"unchecked", "cast"})     public ArrayList typeArguments(MethodDecl m) {
        Object _parameters = m;
if(typeArguments_MethodDecl_values == null) typeArguments_MethodDecl_values = new java.util.HashMap(4);
        if(typeArguments_MethodDecl_values.containsKey(_parameters))
            return (ArrayList)typeArguments_MethodDecl_values.get(_parameters);
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        ArrayList typeArguments_MethodDecl_value = typeArguments_compute(m);
        if(isFinal && num == boundariesCrossed)
            typeArguments_MethodDecl_values.put(_parameters, typeArguments_MethodDecl_value);
        return typeArguments_MethodDecl_value;
    }

    private ArrayList typeArguments_compute(MethodDecl m) {
    ArrayList typeArguments = new ArrayList();
    for(int i = 0; i < getNumTypeArgument(); i++)
      typeArguments.add(getTypeArgument(i).type());
    return typeArguments;
  }

    // Declared in GenericMethods.jrag at line 78
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeArgumentListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        return super.Define_NameType_nameType(caller, child);
    }

    // Declared in GenericMethods.jrag at line 79
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getTypeArgumentListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupType(name);
        }
        return super.Define_SimpleSet_lookupType(caller, child, name);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
