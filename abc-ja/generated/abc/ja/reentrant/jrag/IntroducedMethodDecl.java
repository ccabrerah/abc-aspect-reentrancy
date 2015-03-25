
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


// The aspect method is of type IntroducedMethodDecl and has specialized name binding behavior

public class IntroducedMethodDecl extends MethodDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntroducedMethodDecl clone() throws CloneNotSupportedException {
        IntroducedMethodDecl node = (IntroducedMethodDecl)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntroducedMethodDecl copy() {
      try {
          IntroducedMethodDecl node = (IntroducedMethodDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntroducedMethodDecl fullCopy() {
        IntroducedMethodDecl res = (IntroducedMethodDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in IntertypeMethodCodegeneration.jrag at line 541


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("@IntroducedType(" + getIntroducedType().typeName() + ") ");
    //s.append("@ITDBodyDecl(" + getITDBodyDecl().typeName() + ") ");

    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name() + "(");
    if(getNumParameter() > 0) {
      getParameter(0).toString(s);
      for(int i = 1; i < getNumParameter(); i++) {
        s.append(", ");
        getParameter(i).toString(s);
      }
    }
    s.append(")");
    if(getNumException() > 0) {
      s.append(" throws ");
      getException(0).toString(s);
      for(int i = 1; i < getNumException(); i++) {
        s.append(", ");
        getException(i).toString(s);
      }
    }
    if(hasBlock()) {
      s.append(" ");
      getBlock().toString(s);
    }
    else {
      s.append(";\n");
    }
  }

    // Declared in AspectJCodegen.jrag at line 279


  public void jimplify2() {
    super.jimplify2();

    // record the real modifiers, name, and declaring class, for introduced
    // methods so that the pattern matcher can operate correctly
    if (getITDBodyDecl() instanceof IntertypeMethodDecl)
    {
      IntertypeMethodDecl orig = (IntertypeMethodDecl) getITDBodyDecl();

      AbcClass real_class = orig.hostType().abcClass();

      MethodCategory.registerRealNameAndClass(
        sootMethod(), orig.sootTypeModifiers(), orig.name(),
        orig.hostType().abcClass(), orig.isStatic() ? 0 : 1, 0);
    }
    else if (getITDBodyDecl() instanceof IntertypeConstructorDecl)
    {
      IntertypeConstructorDecl orig =
        (IntertypeConstructorDecl) getITDBodyDecl();

      MethodCategory.registerRealNameAndClass(sootMethod(),
        orig.sootTypeModifiers(), "<init>", orig.hostType().abcClass(), 1, 0);
    }
  }

    // Declared in IntertypeMethod.ast at line 3
    // Declared in IntertypeMethod.ast line 6

    public IntroducedMethodDecl() {
        super();

        setChild(new List(), 2);
        setChild(new List(), 3);
        setChild(new Opt(), 4);

    }

    // Declared in IntertypeMethod.ast at line 13


    // Declared in IntertypeMethod.ast line 6
    public IntroducedMethodDecl(Modifiers p0, Access p1, String p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5, TypeDecl p6, BodyDecl p7) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
        setIntroducedType(p6);
        setITDBodyDecl(p7);
    }

    // Declared in IntertypeMethod.ast at line 25


    // Declared in IntertypeMethod.ast line 6
    public IntroducedMethodDecl(Modifiers p0, Access p1, beaver.Symbol p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5, TypeDecl p6, BodyDecl p7) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
        setIntroducedType(p6);
        setITDBodyDecl(p7);
    }

    // Declared in IntertypeMethod.ast at line 36


  protected int numChildren() {
    return 5;
  }

    // Declared in IntertypeMethod.ast at line 39

  public boolean mayHaveRewrite() { return false; }

    // Declared in java.ast at line 2
    // Declared in java.ast line 88
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
    // Declared in java.ast line 88
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
    // Declared in java.ast line 88
    public void setParameterList(List<ParameterDeclaration> list) {
        setChild(list, 2);
    }

    // Declared in java.ast at line 6


    private int getNumParameter = 0;

    // Declared in java.ast at line 7

    public int getNumParameter() {
        return getParameterList().getNumChild();
    }

    // Declared in java.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getParameter(int i) {
        return (ParameterDeclaration)getParameterList().getChild(i);
    }

    // Declared in java.ast at line 15


    public void addParameter(ParameterDeclaration node) {
        List<ParameterDeclaration> list = getParameterList();
        list.addChild(node);
    }

    // Declared in java.ast at line 20


    public void setParameter(ParameterDeclaration node, int i) {
        List<ParameterDeclaration> list = getParameterList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 24

    public List<ParameterDeclaration> getParameters() {
        return getParameterList();
    }

    // Declared in java.ast at line 27

    public List<ParameterDeclaration> getParametersNoTransform() {
        return getParameterListNoTransform();
    }

    // Declared in java.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterList() {
        return (List<ParameterDeclaration>)getChild(2);
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(2);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 88
    public void setExceptionList(List<Access> list) {
        setChild(list, 3);
    }

    // Declared in java.ast at line 6


    private int getNumException = 0;

    // Declared in java.ast at line 7

    public int getNumException() {
        return getExceptionList().getNumChild();
    }

    // Declared in java.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Access getException(int i) {
        return (Access)getExceptionList().getChild(i);
    }

    // Declared in java.ast at line 15


    public void addException(Access node) {
        List<Access> list = getExceptionList();
        list.addChild(node);
    }

    // Declared in java.ast at line 20


    public void setException(Access node, int i) {
        List<Access> list = getExceptionList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 24

    public List<Access> getExceptions() {
        return getExceptionList();
    }

    // Declared in java.ast at line 27

    public List<Access> getExceptionsNoTransform() {
        return getExceptionListNoTransform();
    }

    // Declared in java.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionList() {
        return (List<Access>)getChild(3);
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionListNoTransform() {
        return (List<Access>)getChildNoTransform(3);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 88
    public void setBlockOpt(Opt<Block> opt) {
        setChild(opt, 4);
    }

    // Declared in java.ast at line 6


    public boolean hasBlock() {
        return getBlockOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Block getBlock() {
        return (Block)getBlockOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setBlock(Block node) {
        getBlockOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Block> getBlockOpt() {
        return (Opt<Block>)getChild(4);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Block> getBlockOptNoTransform() {
        return (Opt<Block>)getChildNoTransform(4);
    }

    // Declared in IntertypeMethod.ast at line 2
    // Declared in IntertypeMethod.ast line 6
    private TypeDecl tokenTypeDecl_IntroducedType;

    // Declared in IntertypeMethod.ast at line 3

    public void setIntroducedType(TypeDecl value) {
        tokenTypeDecl_IntroducedType = value;
    }

    // Declared in IntertypeMethod.ast at line 6

    public TypeDecl getIntroducedType() {
        return tokenTypeDecl_IntroducedType;
    }

    // Declared in IntertypeMethod.ast at line 2
    // Declared in IntertypeMethod.ast line 6
    private BodyDecl tokenBodyDecl_ITDBodyDecl;

    // Declared in IntertypeMethod.ast at line 3

    public void setITDBodyDecl(BodyDecl value) {
        tokenBodyDecl_ITDBodyDecl = value;
    }

    // Declared in IntertypeMethod.ast at line 6

    public BodyDecl getITDBodyDecl() {
        return tokenBodyDecl_ITDBodyDecl;
    }

    // Declared in IntertypeMethodCodegeneration.jrag at line 447
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl introducedType() {
        TypeDecl introducedType_value = introducedType_compute();
        return introducedType_value;
    }

    private TypeDecl introducedType_compute() {  return getIntroducedType();  }

    // Declared in IntertypeMethodCodegeneration.jrag at line 485
    public TypeDecl Define_TypeDecl_thisType(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return introducedType();
        }
        return getParent().Define_TypeDecl_thisType(this, caller);
    }

    // Declared in IntertypeMethodCodegeneration.jrag at line 482
    public TypeDecl Define_TypeDecl_enclosingType(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return introducedType();
        }
        return getParent().Define_TypeDecl_enclosingType(this, caller);
    }

    // Declared in IntertypeMethodCodegeneration.jrag at line 455
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getBlockOptNoTransform()){
    SimpleSet set = parameterDeclaration(name);
    if(!set.isEmpty()) return set;
    set = introducedType().memberFields(name);
    if(!set.isEmpty()) return set;
    return getITDBodyDecl().lookupVariable(name);
  }
        return super.Define_SimpleSet_lookupVariable(caller, child, name);
    }

    // Declared in IntertypeMethodCodegeneration.jrag at line 448
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getBlockOptNoTransform()){
    Collection c = introducedType().memberMethods(name);
    if(!c.isEmpty())
      return c;
    return getITDBodyDecl().lookupMethod(name);
  }
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in IntertypeMethodCodegeneration.jrag at line 477
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return introducedType();
        }
        if(caller == getBlockOptNoTransform()) {
            return introducedType();
        }
        return getParent().Define_TypeDecl_hostType(this, caller);
    }

    // Declared in IntertypeMethodCodegeneration.jrag at line 483
    public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return introducedType();
        }
        return getParent().Define_TypeDecl_enclosingInstance(this, caller);
    }

    // Declared in IntertypeMethodCodegeneration.jrag at line 469
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getBlockOptNoTransform()){
    SimpleSet set = introducedType().memberTypes(name);
    if(!set.isEmpty())
      return set;
    return getITDBodyDecl().lookupType(name);
  }
        if(caller == getTypeAccessNoTransform()){
    SimpleSet set = introducedType().memberTypes(name);
    if(!set.isEmpty())
      return set;
    return getITDBodyDecl().lookupType(name);
  }
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in IntertypeMethodCodegeneration.jrag at line 480
    public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return false;
        }
        return super.Define_boolean_inStaticContext(caller, child);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
