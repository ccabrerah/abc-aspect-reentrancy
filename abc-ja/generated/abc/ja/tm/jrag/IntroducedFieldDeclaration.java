
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



public class IntroducedFieldDeclaration extends FieldDeclaration implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntroducedFieldDeclaration clone() throws CloneNotSupportedException {
        IntroducedFieldDeclaration node = (IntroducedFieldDeclaration)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntroducedFieldDeclaration copy() {
      try {
          IntroducedFieldDeclaration node = (IntroducedFieldDeclaration)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntroducedFieldDeclaration fullCopy() {
        IntroducedFieldDeclaration res = (IntroducedFieldDeclaration)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in IntertypeFieldCodegeneration.jrag at line 277

  
  // change name binding to search for methods in introducedType in IntroducedMethodDecl body
  public IntroducedFieldDeclaration(Modifiers m, Access t, String d, Opt init,
                                                               String originalName, TypeDecl aspectType) {
    this(m, t, d, init, originalName);
    this.aspectType = aspectType;
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 282

  private TypeDecl aspectType;

    // Declared in IntertypeFieldCodegeneration.jrag at line 314


  public void toString(StringBuffer s) {
    s.append(indent());
     s.append("@AspectType(" + aspectType().typeName() + ") ");
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name());
    if(hasInit()) {
      s.append(" = ");
      getInit().toString(s);
    }
    s.append(";\n");
  }

    // Declared in AspectJCodegen.jrag at line 304


  public void jimplify2() {
    super.jimplify2();

    FieldDeclaration orig = (FieldDeclaration)
      aspectType().memberFields(getOriginalName()).iterator().next();
    int mods = orig.sootTypeModifiers();

    // record the real modifiers, name, and declaring class, for introduced
    // fields so that the pattern matcher can operate correctly
    MethodCategory.registerRealNameAndClass(
      sootRef().resolve(), mods, getOriginalName(), aspectType().abcClass());
  }

    // Declared in IntertypeField.ast at line 3
    // Declared in IntertypeField.ast line 4

    public IntroducedFieldDeclaration() {
        super();

        setChild(new Opt(), 2);

    }

    // Declared in IntertypeField.ast at line 11


    // Declared in IntertypeField.ast line 4
    public IntroducedFieldDeclaration(Modifiers p0, Access p1, String p2, Opt<Expr> p3, String p4) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setOriginalName(p4);
    }

    // Declared in IntertypeField.ast at line 20


    // Declared in IntertypeField.ast line 4
    public IntroducedFieldDeclaration(Modifiers p0, Access p1, beaver.Symbol p2, Opt<Expr> p3, beaver.Symbol p4) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setOriginalName(p4);
    }

    // Declared in IntertypeField.ast at line 28


  protected int numChildren() {
    return 3;
  }

    // Declared in IntertypeField.ast at line 31

  public boolean mayHaveRewrite() { return false; }

    // Declared in java.ast at line 2
    // Declared in java.ast line 77
    public void setModifiers(Modifiers node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Modifiers getModifiers() {
        return (Modifiers)getChild(0);
    }

    // Declared in java.ast at line 9


    public Modifiers getModifiersNoTransform() {
        return (Modifiers)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 77
    public void setTypeAccess(Access node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Access getTypeAccess() {
        return (Access)getChild(1);
    }

    // Declared in java.ast at line 9


    public Access getTypeAccessNoTransform() {
        return (Access)getChildNoTransform(1);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 77
    public void setInitOpt(Opt<Expr> opt) {
        setChild(opt, 2);
    }

    // Declared in java.ast at line 6


    public boolean hasInit() {
        return getInitOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Expr getInit() {
        return (Expr)getInitOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setInit(Expr node) {
        getInitOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Expr> getInitOpt() {
        return (Opt<Expr>)getChild(2);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Expr> getInitOptNoTransform() {
        return (Opt<Expr>)getChildNoTransform(2);
    }

    // Declared in IntertypeField.ast at line 2
    // Declared in IntertypeField.ast line 4
    private String tokenString_OriginalName;

    // Declared in IntertypeField.ast at line 3

    public void setOriginalName(String value) {
        tokenString_OriginalName = value;
    }

    // Declared in IntertypeField.ast at line 6

    public int OriginalNamestart;

    // Declared in IntertypeField.ast at line 7

    public int OriginalNameend;

    // Declared in IntertypeField.ast at line 8

    public void setOriginalName(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setOriginalName is only valid for String lexemes");
        tokenString_OriginalName = (String)symbol.value;
        OriginalNamestart = symbol.getStart();
        OriginalNameend = symbol.getEnd();
    }

    // Declared in IntertypeField.ast at line 15

    public String getOriginalName() {
        return tokenString_OriginalName != null ? tokenString_OriginalName : "";
    }

    // Declared in IntertypeFieldCodegeneration.jrag at line 283
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl aspectType() {
        TypeDecl aspectType_value = aspectType_compute();
        return aspectType_value;
    }

    private TypeDecl aspectType_compute() {  return aspectType;  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 286
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getInitOptNoTransform()){
    SimpleSet set = lookupVariable(name);
    if(!set.isEmpty())
      return set;
    return aspectType().memberFields(name); // TODO: Does this work for fields in nested aspects/classes?
  }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in IntertypeFieldCodegeneration.jrag at line 294
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getInitOptNoTransform()){
    SimpleSet set = hostType().memberTypes(name);
    if(!set.isEmpty()) return set;
    set = aspectType().memberTypes(name);
    if(!set.isEmpty()) return set;
    set = aspectType().lookupType(name);
    if(set.isEmpty())
      System.err.println("Could not find " + name + " from " + name() + " in neither " + hostType().typeName() + " nor " + aspectType().typeName());
    return set;
  }
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
