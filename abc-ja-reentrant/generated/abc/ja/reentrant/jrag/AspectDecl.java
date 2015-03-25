
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;
// ----------------------- Aspects ------------------------


public class AspectDecl extends ClassDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        localMethodsSignatureMap_computed = false;
        localMethodsSignatureMap_value = null;
        nextAspect_computed = false;
        nextAspect_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AspectDecl clone() throws CloneNotSupportedException {
        AspectDecl node = (AspectDecl)super.clone();
        node.localMethodsSignatureMap_computed = false;
        node.localMethodsSignatureMap_value = null;
        node.nextAspect_computed = false;
        node.nextAspect_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AspectDecl copy() {
      try {
          AspectDecl node = (AspectDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AspectDecl fullCopy() {
        AspectDecl res = (AspectDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AspectErrorCheck.jrag at line 12

  public void checkModifiers() {
    if(isNestedType() && !isStatic())
      error("inner aspects must be static");
    else
      super.checkModifiers();
  }

    // Declared in AspectErrorCheck.jrag at line 22


  public void typeCheck() {
    if(hasSuperclass() && superclass().isAspectDecl() && !superclass().isAbstract())
      error("aspects may only extend abstract aspects");
    else if(!hasSuperclass() || !superclass().isUnknown()) {
      if(instanceOf(lookupType("java.lang", "Cloneable")))
        error("aspect may not implement Cloneable");
      if(instanceOf(lookupType("java.io", "Serializable")))
        error("aspect may not implement Serializable");
      super.typeCheck();
    }
  }

    // Declared in JastAddPrettyPrint.jrag at line 11

  public void toString(StringBuffer s) {
    getModifiers().toString(s);
    s.append("aspect " + name());
    if(hasSuperClassAccess()) {
      s.append(" extends ");
      getSuperClassAccess().toString(s);
    }
    if(getNumImplements() > 0) {
      s.append(" implements ");
      getImplements(0).toString(s);
      for(int i = 1; i < getNumImplements(); i++) {
        s.append(", ");
        getImplements(i).toString(s);
      }
    }
    s.append(" {\n");
    indent++;
    for(int i=0; i < getNumBodyDecl(); i++) {
      getBodyDecl(i).toString(s);
    }
    indent--;
    s.append(indent() + "}\n");
  }

    // Declared in AspectJCodegen.jrag at line 226


  public void jimplify1phase1() {
    super.jimplify1phase1();
    Per perclause = hasPerClause() ? getPerClause().aspectInfo() : null;
    globalAspectInfo().addAspect(
      new Aspect(abcClass(), perclause, pos())
    );
  }

    // Declared in AspectJNameAnalysis.jrag at line 38


  private boolean addImplicitMethods = true;

    // Declared in AspectJNameAnalysis.jrag at line 39

  private void addImplicitMethods() {
    if(!addImplicitMethods || isAbstract())
      return;
    addImplicitMethods = false;
    List parameters = new List();
    if(isPerObject())
      parameters.add(new ParameterDeclaration(new Modifiers(new List()), typeObject().createQualifiedAccess(), "obj"));
    MethodDecl aspectOf = 
      new MethodDecl(
        new Modifiers(new List().add(new Modifier("public")).add(new Modifier("static")).add(new Modifier("synthetic"))),
        createQualifiedAccess(),
        "aspectOf",
        parameters,
        new List(),
        new Opt(new Block(new List().add(new ReturnStmt(new Opt(new NullLiteral())))))
      );
    parameters = new List();
    if(isPerObject())
      parameters.add(new ParameterDeclaration(new Modifiers(new List()), typeObject().createQualifiedAccess(), "obj"));
    MethodDecl hasAspect =
      new MethodDecl(
        new Modifiers(new List().add(new Modifier("public")).add(new Modifier("static")).add(new Modifier("synthetic"))),
        typeBoolean().createQualifiedAccess(),
        "hasAspect",
        parameters,
        new List(),
        new Opt(new Block(new List().add(new ReturnStmt(new Opt(new BooleanLiteral("true"))))))
      );
    aspectOf.category = MethodCategory.ASPECT_INSTANCE;
    hasAspect.category = MethodCategory.ASPECT_INSTANCE;
    addBodyDecl(aspectOf);
    addBodyDecl(hasAspect);
  }

    // Declared in rewritePointcuts.jrag at line 5



    //rewrite to add level constraints
    public int level = 0;

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 3

    public AspectDecl() {
        super();

        setChild(new List(), 1);
        setChild(new Opt(), 2);
        setChild(new List(), 3);
        setChild(new Opt(), 4);

    }

    // Declared in AspectJ.ast at line 14


    // Declared in AspectJ.ast line 3
    public AspectDecl(Modifiers p0, String p1, List<BodyDecl> p2, Opt<Access> p3, List<Access> p4, Opt<PerClause> p5) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
    }

    // Declared in AspectJ.ast at line 24


    // Declared in AspectJ.ast line 3
    public AspectDecl(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2, Opt<Access> p3, List<Access> p4, Opt<PerClause> p5) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
    }

    // Declared in AspectJ.ast at line 33


  protected int numChildren() {
    return 5;
  }

    // Declared in AspectJ.ast at line 36

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 3
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
    // Declared in AspectJ.ast line 3
    public void setBodyDeclList(List<BodyDecl> list) {
        setChild(list, 1);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumBodyDecl = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumBodyDecl() {
        return getBodyDeclList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public BodyDecl getBodyDecl(int i) {
        return (BodyDecl)getBodyDeclList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addBodyDecl(BodyDecl node) {
        List<BodyDecl> list = getBodyDeclList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setBodyDecl(BodyDecl node, int i) {
        List<BodyDecl> list = getBodyDeclList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<BodyDecl> getBodyDecls() {
        return getBodyDeclList();
    }

    // Declared in AspectJ.ast at line 27

    public List<BodyDecl> getBodyDeclsNoTransform() {
        return getBodyDeclListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<BodyDecl> getBodyDeclList() {
        return (List<BodyDecl>)getChild(1);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<BodyDecl> getBodyDeclListNoTransform() {
        return (List<BodyDecl>)getChildNoTransform(1);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 3
    public void setSuperClassAccessOpt(Opt<Access> opt) {
        setChild(opt, 2);
    }

    // Declared in AspectJ.ast at line 6


    public boolean hasSuperClassAccess() {
        return getSuperClassAccessOpt().getNumChild() != 0;
    }

    // Declared in AspectJ.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Access getSuperClassAccess() {
        return (Access)getSuperClassAccessOpt().getChild(0);
    }

    // Declared in AspectJ.ast at line 14


    public void setSuperClassAccess(Access node) {
        getSuperClassAccessOpt().setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Access> getSuperClassAccessOpt() {
        return (Opt<Access>)getChild(2);
    }

    // Declared in AspectJ.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Access> getSuperClassAccessOptNoTransform() {
        return (Opt<Access>)getChildNoTransform(2);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 3
    public void setImplementsList(List<Access> list) {
        setChild(list, 3);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumImplements = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumImplements() {
        return getImplementsList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Access getImplements(int i) {
        return (Access)getImplementsList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addImplements(Access node) {
        List<Access> list = getImplementsList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setImplements(Access node, int i) {
        List<Access> list = getImplementsList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<Access> getImplementss() {
        return getImplementsList();
    }

    // Declared in AspectJ.ast at line 27

    public List<Access> getImplementssNoTransform() {
        return getImplementsListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getImplementsList() {
        return (List<Access>)getChild(3);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getImplementsListNoTransform() {
        return (List<Access>)getChildNoTransform(3);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 3
    public void setPerClauseOpt(Opt<PerClause> opt) {
        setChild(opt, 4);
    }

    // Declared in AspectJ.ast at line 6


    public boolean hasPerClause() {
        return getPerClauseOpt().getNumChild() != 0;
    }

    // Declared in AspectJ.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public PerClause getPerClause() {
        return (PerClause)getPerClauseOpt().getChild(0);
    }

    // Declared in AspectJ.ast at line 14


    public void setPerClause(PerClause node) {
        getPerClauseOpt().setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<PerClause> getPerClauseOpt() {
        return (Opt<PerClause>)getChild(4);
    }

    // Declared in AspectJ.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<PerClause> getPerClauseOptNoTransform() {
        return (Opt<PerClause>)getChildNoTransform(4);
    }

    // Declared in IntertypeMethodErrorCheck.jrag at line 269
private HashMap refined_IntertypeMethodErrorCheck_localMethodsSignatureMap()
{
    HashMap map = new HashMap(getNumBodyDecl());
    for(int i = 0; i < getNumBodyDecl(); i++) {
      if(getBodyDecl(i) instanceof MethodDecl && !(getBodyDecl(i) instanceof IntertypeMethodDecl)) {
        MethodDecl decl = (MethodDecl)getBodyDecl(i);
        map.put(decl.signature(), decl);
      }
    }
    return map;
  }

    // Declared in AspectErrorCheck.jrag at line 20
 @SuppressWarnings({"unchecked", "cast"})     public boolean isAspectDecl() {
        boolean isAspectDecl_value = isAspectDecl_compute();
        return isAspectDecl_value;
    }

    private boolean isAspectDecl_compute() {  return true;  }

    // Declared in AspectJNameAnalysis.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public HashMap localMethodsSignatureMap() {
        if(localMethodsSignatureMap_computed)
            return localMethodsSignatureMap_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        localMethodsSignatureMap_value = localMethodsSignatureMap_compute();
        if(isFinal && num == boundariesCrossed)
            localMethodsSignatureMap_computed = true;
        return localMethodsSignatureMap_value;
    }

    private HashMap localMethodsSignatureMap_compute() {
    addImplicitMethods();
    return refined_IntertypeMethodErrorCheck_localMethodsSignatureMap();
  }

    // Declared in IntertypeMethodErrorCheck.jrag at line 281
 @SuppressWarnings({"unchecked", "cast"})     public HashMap localIntertypeMethodsSignatureMap() {
        HashMap localIntertypeMethodsSignatureMap_value = localIntertypeMethodsSignatureMap_compute();
        return localIntertypeMethodsSignatureMap_value;
    }

    private HashMap localIntertypeMethodsSignatureMap_compute() {
    HashMap map = new HashMap(getNumBodyDecl());
    for(int i = 0; i < getNumBodyDecl(); i++) {
      if(getBodyDecl(i) instanceof IntertypeMethodDecl) {
        IntertypeMethodDecl decl = (IntertypeMethodDecl)getBodyDecl(i);
        map.put(decl.introducedType().fullName() + "." + decl.signature(), decl);
      }
    }
    return map;
  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 55
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localLookupMethod(MethodDecl signature) {
        SimpleSet localLookupMethod_MethodDecl_value = localLookupMethod_compute(signature);
        return localLookupMethod_MethodDecl_value;
    }

    private SimpleSet localLookupMethod_compute(MethodDecl signature) {
    return SimpleSet.emptySet.add(signature);
  }

    // Declared in AspectJNameAnalysis.jrag at line 75
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPerObject() {
        boolean isPerObject_value = isPerObject_compute();
        return isPerObject_value;
    }

    private boolean isPerObject_compute() {  return hasPerClause() ? getPerClause().isPerObject() : superclass().isPerObject();  }

    protected boolean nextAspect_computed = false;
    protected AspectDecl nextAspect_value;
    // Declared in DeclarePrecedence.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public AspectDecl nextAspect() {
        if(nextAspect_computed)
            return nextAspect_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        nextAspect_value = nextAspect_compute();
        if(isFinal && num == boundariesCrossed)
            nextAspect_computed = true;
        return nextAspect_value;
    }

    private AspectDecl nextAspect_compute() {  return getModifiers().nextAspectRight();  }

    // Declared in DeclarePrecedence.jrag at line 39
 @SuppressWarnings({"unchecked", "cast"})     public AspectDecl nextAspectBelow(int start) {
        AspectDecl nextAspectBelow_int_value = nextAspectBelow_compute(start);
        return nextAspectBelow_int_value;
    }

    private AspectDecl nextAspectBelow_compute(int start) {  return start == 0 ? this : super.nextAspectBelow(start);  }

    // Declared in IntertypeJimple.jrag at line 95
 @SuppressWarnings({"unchecked", "cast"})     public int sootTypeModifiers() {
        int sootTypeModifiers_value = sootTypeModifiers_compute();
        return sootTypeModifiers_value;
    }

    private int sootTypeModifiers_compute() {
    int result = 0;
    result |= soot.Modifier.PUBLIC;
    if(isFinal()) result |= soot.Modifier.FINAL;
    if(isStatic()) result |= soot.Modifier.STATIC;
    if(isAbstract()) result |= soot.Modifier.ABSTRACT;
    return result;
  }

    // Declared in AspectJNameAnalysis.jrag at line 72
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeBoolean() {
        TypeDecl typeBoolean_value = getParent().Define_TypeDecl_typeBoolean(this, null);
        return typeBoolean_value;
    }

    // Declared in PatternsCodegen.jrag at line 491
    public boolean Define_boolean_matchSubtype(ASTNode caller, ASTNode child) {
        if(caller == getPerClauseOptNoTransform()) {
            return false;
        }
        return super.Define_boolean_matchSubtype(caller, child);
    }

    // Declared in Patterns.jrag at line 51
    public boolean Define_boolean_denotesMember(ASTNode caller, ASTNode child) {
        if(caller == getPerClauseOptNoTransform()) {
            return false;
        }
        return super.Define_boolean_denotesMember(caller, child);
    }

    // Declared in Pointcuts.jrag at line 237
    public SimpleSet Define_SimpleSet_lookupPointcut(ASTNode caller, ASTNode child, String name) {
        if(caller == getPerClauseOptNoTransform()){
        SimpleSet set = lookupMemberPointcut(name);
        if(!set.isEmpty()) return set;
        return lookupPointcut(name);
    }
        return super.Define_SimpleSet_lookupPointcut(caller, child, name);
    }

    // Declared in AspectJNameAnalysis.jrag at line 92
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getPerClauseOptNoTransform()) {
            return unqualifiedLookupMethod(name);
        }
        return super.Define_Collection_lookupMethod(caller, child, name);
    }

    // Declared in AspectJNameAnalysis.jrag at line 84
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getPerClauseOptNoTransform()){
    SimpleSet list = memberFields(name);
    if(!list.isEmpty()) return list;
    list = lookupVariable(name);
    if(inStaticContext() || isStatic() || (isAnonymous() && inExplicitConstructorInvocation()))
      list = removeInstanceVariables(list);
    return list;
  }
        return super.Define_SimpleSet_lookupVariable(caller, child, name);
    }

    // Declared in AspectJNameAnalysis.jrag at line 82
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(caller == getPerClauseOptNoTransform()) {
            return this;
        }
        return super.Define_TypeDecl_hostType(caller, child);
    }

    // Declared in Privileged.jrag at line 25
    public boolean Define_boolean_mayBePrivileged(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBePrivileged(this, caller);
    }

    // Declared in Patterns.jrag at line 128
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getPerClauseOptNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return super.Define_NameType_nameType(caller, child);
    }

    // Declared in AspectJNameAnalysis.jrag at line 93
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getPerClauseOptNoTransform()){
    SimpleSet c = memberTypes(name);
    if(!c.isEmpty()) 
      return c;
    if(name().equals(name))
      return SimpleSet.emptySet.add(this);

    c = lookupType(name);
    // 8.5.2
    if(isClassDecl() && isStatic() && !isTopLevelType()) {
      SimpleSet newSet = SimpleSet.emptySet;
      for(Iterator iter = c.iterator(); iter.hasNext(); ) {
        TypeDecl d = (TypeDecl)iter.next();
        //if(d.isStatic() || d.isTopLevelType() || this.instanceOf(d.enclosingType())) {
          newSet = newSet.add(d);
        //}
      }
      c = newSet;
    }
    return c;
  }
        return super.Define_SimpleSet_lookupType(caller, child, name);
    }

    // Declared in AspectJNameAnalysis.jrag at line 114
    public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
        if(caller == getPerClauseOptNoTransform()) {
            return true;
        }
        return super.Define_boolean_inStaticContext(caller, child);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
