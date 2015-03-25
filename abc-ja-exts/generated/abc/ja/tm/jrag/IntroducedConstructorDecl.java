
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class IntroducedConstructorDecl extends ConstructorDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntroducedConstructorDecl clone() throws CloneNotSupportedException {
        IntroducedConstructorDecl node = (IntroducedConstructorDecl)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntroducedConstructorDecl copy() {
      try {
          IntroducedConstructorDecl node = (IntroducedConstructorDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntroducedConstructorDecl fullCopy() {
        IntroducedConstructorDecl res = (IntroducedConstructorDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in IntertypeConstructorCodegeneration.jrag at line 142



  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("@IntroducedType(" + introducedType().typeName() + ") ");
    //s.append("@ITDBodyDecl(" + getITDBodyDecl().typeName() + ") ");

    getModifiers().toString(s);
    s.append(name() + "(");
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
    s.append(" {\n");
    indent++;
    if(hasConstructorInvocation()) {
      s.append(indent());
      getConstructorInvocation().toString(s);
    }
    for(int i = 0; i < getBlock().getNumStmt(); i++) {
      s.append(indent());
      getBlock().getStmt(i).toString(s);
    }
    indent--;
    s.append(indent());
    s.append("}\n");
  }

    // Declared in IntertypeConstructor.ast at line 3
    // Declared in IntertypeConstructor.ast line 2

    public IntroducedConstructorDecl() {
        super();

        setChild(new List(), 1);
        setChild(new List(), 2);
        setChild(new Opt(), 3);

    }

    // Declared in IntertypeConstructor.ast at line 13


    // Declared in IntertypeConstructor.ast line 2
    public IntroducedConstructorDecl(Modifiers p0, String p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5, IntertypeConstructorDecl p6) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
        setITDBodyDecl(p6);
    }

    // Declared in IntertypeConstructor.ast at line 24


    // Declared in IntertypeConstructor.ast line 2
    public IntroducedConstructorDecl(Modifiers p0, beaver.Symbol p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5, IntertypeConstructorDecl p6) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
        setITDBodyDecl(p6);
    }

    // Declared in IntertypeConstructor.ast at line 34


  protected int numChildren() {
    return 5;
  }

    // Declared in IntertypeConstructor.ast at line 37

  public boolean mayHaveRewrite() { return true; }

    // Declared in java.ast at line 2
    // Declared in java.ast line 72
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
    // Declared in java.ast line 72
    public void setParameterList(List<ParameterDeclaration> list) {
        setChild(list, 1);
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
        return (List<ParameterDeclaration>)getChild(1);
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(1);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 72
    public void setExceptionList(List<Access> list) {
        setChild(list, 2);
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
        return (List<Access>)getChild(2);
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionListNoTransform() {
        return (List<Access>)getChildNoTransform(2);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 72
    public void setConstructorInvocationOpt(Opt<Stmt> opt) {
        setChild(opt, 3);
    }

    // Declared in java.ast at line 6


    public boolean hasConstructorInvocation() {
        return getConstructorInvocationOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Stmt getConstructorInvocation() {
        return (Stmt)getConstructorInvocationOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setConstructorInvocation(Stmt node) {
        getConstructorInvocationOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Stmt> getConstructorInvocationOpt() {
        return (Opt<Stmt>)getChild(3);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Stmt> getConstructorInvocationOptNoTransform() {
        return (Opt<Stmt>)getChildNoTransform(3);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 72
    public void setBlock(Block node) {
        setChild(node, 4);
    }

    // Declared in java.ast at line 5

    public Block getBlock() {
        return (Block)getChild(4);
    }

    // Declared in java.ast at line 9


    public Block getBlockNoTransform() {
        return (Block)getChildNoTransform(4);
    }

    // Declared in IntertypeConstructor.ast at line 2
    // Declared in IntertypeConstructor.ast line 2
    private IntertypeConstructorDecl tokenIntertypeConstructorDecl_ITDBodyDecl;

    // Declared in IntertypeConstructor.ast at line 3

    public void setITDBodyDecl(IntertypeConstructorDecl value) {
        tokenIntertypeConstructorDecl_ITDBodyDecl = value;
    }

    // Declared in IntertypeConstructor.ast at line 6

    public IntertypeConstructorDecl getITDBodyDecl() {
        return tokenIntertypeConstructorDecl_ITDBodyDecl;
    }

    // Declared in IntertypeConstructorCodegeneration.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl introducedType() {
        TypeDecl introducedType_value = introducedType_compute();
        return introducedType_value;
    }

    private TypeDecl introducedType_compute() {  return hostType();  }

    // Declared in IntertypeConstructorCodegeneration.jrag at line 24
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getBlockNoTransform()){
    SimpleSet set = parameterDeclaration(name);
    if(!set.isEmpty()) return set;
    set = introducedType().memberFields(name);
    if(!set.isEmpty()) return set;
    return getITDBodyDecl().lookupVariable(name);
  }
        return super.Define_SimpleSet_lookupVariable(caller, child, name);
    }

    // Declared in IntertypeConstructorCodegeneration.jrag at line 17
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getBlockNoTransform()){
    Collection c = introducedType().memberMethods(name);
    if(!c.isEmpty())
      return c;
    return getITDBodyDecl().lookupMethod(name);
  }
        return super.Define_Collection_lookupMethod(caller, child, name);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
