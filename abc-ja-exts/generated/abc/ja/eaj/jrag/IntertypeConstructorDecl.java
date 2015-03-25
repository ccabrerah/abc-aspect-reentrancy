
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;

public class IntertypeConstructorDecl extends ConstructorDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        name_computed = false;
        name_value = null;
        accessibleFrom_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntertypeConstructorDecl clone() throws CloneNotSupportedException {
        IntertypeConstructorDecl node = (IntertypeConstructorDecl)super.clone();
        node.name_computed = false;
        node.name_value = null;
        node.accessibleFrom_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntertypeConstructorDecl copy() {
      try {
          IntertypeConstructorDecl node = (IntertypeConstructorDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntertypeConstructorDecl fullCopy() {
        IntertypeConstructorDecl res = (IntertypeConstructorDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in IntertypeConstructorCodegeneration.jrag at line 34




  public void generateIntertypeDecls() {
    if(isPrivate()) {
      createAccessor();
      return;
    }
    introducedType().addBodyDecl(createDelegate());
  }

    // Declared in IntertypeConstructorCodegeneration.jrag at line 42


  public ConstructorDecl refined_IntertypeConstructorCodegeneration_createDelegate() {
    List args = new List();
    args.add(new ThisAccess("this"));
    for(int i = 0; i < getNumParameter(); i++)
      args.add(new VarAccess(getParameter(i).name()));
    Block block = new Block(
      new List().add(
        new ExprStmt(
          createBodyDelegate().createBoundAccess(args)
        )
      ).add(
        new ReturnStmt(new Opt())
      )
    );
    Modifiers m = new Modifiers(new List().add(new Modifier("public")));
    List parameters = copyParameterList(getParameterList());
    return new IntroducedConstructorDecl(
      m,
      getID(),
      parameters,
      copyTypeList(getExceptionList()),
      (Opt)getConstructorInvocationOpt().boundCopy(),
      block,
      this
    );
  }

    // Declared in IntertypeConstructorCodegeneration.jrag at line 69


  public ConstructorDecl createAccessor() {
    ConstructorDecl c = (ConstructorDecl)hostType().getAccessor(this, "constructor");
    if(c != null) return c;

    // make sure enclosing varibles are added as parameters prior to building accessor
    addEnclosingVariables();

    Modifiers modifiers = new Modifiers(new List());
    modifiers.addModifier(new Modifier("synthetic"));
    modifiers.addModifier(new Modifier("public"));

    List parameters = createAccessorParameters();
    
    // add all parameters as arguments except for the dummy parameter
    List args = new List();
    args.add(new ThisAccess("this"));
    for(int i = 0; i < parameters.getNumChildNoTransform() - 1; i++)
      args.add(new VarAccess(((ParameterDeclaration)parameters.getChildNoTransform(i)).name()));

    c = new ConstructorDecl(
      modifiers,
      name(),
      parameters,
      copyTypeList(getExceptionList()),
      (Opt)getConstructorInvocationOpt().boundCopy(),
      new Block(
        new List().add(
          new ExprStmt(
            createBodyDelegate().createBoundAccess(args)
          )
        ).add(
          new ReturnStmt(new Opt())
        )
      )
    );
    c = hostType().addConstructor(c);
    c.addEnclosingVariables = false;
    hostType().addAccessor(this, "constructor", c);
    return c;
  }

    // Declared in IntertypeConstructorCodegeneration.jrag at line 116


  public MethodDecl refined_IntertypeConstructorCodegeneration_createBodyDelegate() {
    String name = "constructor_body";
    TypeDecl typeDecl = hostAspect();
    MethodDecl m = (MethodDecl)typeDecl.getAccessor(this, name);
    if(m != null) return m;

    List list = copyParameterList(getParameterList());
    list.insertChild(new ParameterDeclaration(hostType(), "this"), 0);
    Modifiers modifiers = createAccessorModifiers(true);
    m = new IntroducedMethodDecl(
      modifiers,
      hostType().typeVoid().createQualifiedAccess(),
      constructorBodyName(),
      list,
      new List(),
      new Opt(getBlock().boundCopy()),
      introducedType(),
      this
    );
    m = typeDecl.addMemberMethod(m);
    typeDecl.addAccessor(this, name, m);
    return m;
    //return buildAccessor(m, hostType().typeVoid(), "constructorInit", list, new Opt(getBlock().fullCopy()), hostAspect());
  }

    // Declared in IntertypeConstructorErrorCheck.jrag at line 12


  public void nameCheck() {
    if(!introducedType().isUnknown() && !introducedType().compilationUnit().weavable())
      error("Host of an intertype declaration must be a weavable class");
  }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 167


  protected void collectIntertypeDecls(HashMap map) {
    super.collectIntertypeDecls(map);
    TypeDecl typeDecl = introducedType();
    if(!map.containsKey(typeDecl))
      map.put(typeDecl, new ArrayList());
    Collection c = (Collection)map.get(typeDecl);
    c.add(this);
  }

    // Declared in IntertypeConstructorPrettyPrint.jrag at line 11

  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    getTargetType().toString(s);
    s.append("." + name() + "(");
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
    // Declared in IntertypeConstructor.ast line 1

    public IntertypeConstructorDecl() {
        super();

        setChild(new List(), 1);
        setChild(new List(), 2);
        setChild(new Opt(), 3);

    }

    // Declared in IntertypeConstructor.ast at line 13


    // Declared in IntertypeConstructor.ast line 1
    public IntertypeConstructorDecl(Modifiers p0, String p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5, Access p6) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
        setChild(p6, 5);
    }

    // Declared in IntertypeConstructor.ast at line 24


    // Declared in IntertypeConstructor.ast line 1
    public IntertypeConstructorDecl(Modifiers p0, beaver.Symbol p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5, Access p6) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
        setChild(p6, 5);
    }

    // Declared in IntertypeConstructor.ast at line 34


  protected int numChildren() {
    return 6;
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
    // Declared in IntertypeConstructor.ast line 1
    public void setTargetType(Access node) {
        setChild(node, 5);
    }

    // Declared in IntertypeConstructor.ast at line 5

    public Access getTargetType() {
        return (Access)getChild(5);
    }

    // Declared in IntertypeConstructor.ast at line 9


    public Access getTargetTypeNoTransform() {
        return (Access)getChildNoTransform(5);
    }

    // Declared in AspectJCodegen.jrag at line 117

  /*
  refine IntertypeMethodCodegeneration eq MethodDecl.createSuperDispatchDelegate(TypeDecl qualifierType) {
    MethodDecl m = IntertypeMethodCodegeneration.MethodDecl.createSuperDispatchDelegate(qualifierType);
    m.category = MethodCategory.INTERTYPE_SPECIAL_CALL_DELEGATOR;
      return m;
  }
  refine IntertypeMethodCodegeneration public MethodDecl IntertypeMethodDecl.createAbstractMethod() {
    MethodDecl m = IntertypeMethodCodegeneration.IntertypeMethodDecl.createAbstractMethod();
    m.category = MethodCategory.INTERTYPE_METHOD_SOURCE;
    return m;
  }*/

    public ConstructorDecl createDelegate() {
    ConstructorDecl c = refined_IntertypeConstructorCodegeneration_createDelegate();
    c.category = MethodCategory.INTERTYPE_CONSTRUCTOR_DELEGATOR;
    return c;
  }

    // Declared in AspectJCodegen.jrag at line 122

    public MethodDecl createBodyDelegate() {
    MethodDecl m = refined_IntertypeConstructorCodegeneration_createBodyDelegate();
    m.category = MethodCategory.INTERTYPE_CONSTRUCTOR_BODY;
    return m;
  }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 189
private boolean refined_IntertypeConstructorNameAnalysis_accessibleFrom_TypeDecl(TypeDecl type)
{
    if(!hostAspect().accessibleFrom(type))
      return false;
    else if(isPublic())
      return true;
    else if(isProtected()) {
      return true;
    }
    else if(isPrivate()) {
      return hostAspect().topLevelType() == type.topLevelType();
    }
    else
      return hostAspect().hostPackage().equals(type.hostPackage());
  }

    // Declared in IntertypeConstructorCodegeneration.jrag at line 13
 @SuppressWarnings({"unchecked", "cast"})     public boolean generate() {
        boolean generate_value = generate_compute();
        return generate_value;
    }

    private boolean generate_compute() {  return false;  }

    // Declared in IntertypeConstructorCodegeneration.jrag at line 110
 @SuppressWarnings({"unchecked", "cast"})     public String constructorBodyName() {
        String constructorBodyName_value = constructorBodyName_compute();
        return constructorBodyName_value;
    }

    private String constructorBodyName_compute() {  return "abc$interConstructorBody$" + abcMangledSignature();  }

    // Declared in IntertypeConstructorCodegeneration.jrag at line 113
 @SuppressWarnings({"unchecked", "cast"})     public String abcMangledSignature() {
        String abcMangledSignature_value = abcMangledSignature_compute();
        return abcMangledSignature_value;
    }

    private String abcMangledSignature_compute() {  return hostAspect().abcMangledName() + "$" + introducedType().abcMangledName();  }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 13
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostAspect() {
        TypeDecl hostAspect_value = hostAspect_compute();
        return hostAspect_value;
    }

    private TypeDecl hostAspect_compute() {  return (TypeDecl)getParent().getParent();  }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl introducedType() {
        TypeDecl introducedType_value = introducedType_compute();
        return introducedType_value;
    }

    private TypeDecl introducedType_compute() {  return getTargetType().type();  }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 23
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        TypeDecl hostType_value = hostType_compute();
        return hostType_value;
    }

    private TypeDecl hostType_compute() {  return introducedType();  }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 137
 @SuppressWarnings({"unchecked", "cast"})     public boolean isExactConstructorDecl() {
        boolean isExactConstructorDecl_value = isExactConstructorDecl_compute();
        return isExactConstructorDecl_value;
    }

    private boolean isExactConstructorDecl_compute() {  return false;  }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 187
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

    private String name_compute() {  return introducedType().name();  }

    // Declared in Privileged.jrag at line 70
 @SuppressWarnings({"unchecked", "cast"})     public boolean accessibleFrom(TypeDecl type) {
        Object _parameters = type;
if(accessibleFrom_TypeDecl_values == null) accessibleFrom_TypeDecl_values = new java.util.HashMap(4);
        if(accessibleFrom_TypeDecl_values.containsKey(_parameters))
            return ((Boolean)accessibleFrom_TypeDecl_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean accessibleFrom_TypeDecl_value = accessibleFrom_compute(type);
        if(isFinal && num == boundariesCrossed)
            accessibleFrom_TypeDecl_values.put(_parameters, Boolean.valueOf(accessibleFrom_TypeDecl_value));
        return accessibleFrom_TypeDecl_value;
    }

    private boolean accessibleFrom_compute(TypeDecl type) {
    if(type.isPrivileged())
      return true;
    return refined_IntertypeConstructorNameAnalysis_accessibleFrom_TypeDecl(type);
  }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 29
    public Collection Define_Collection_lookupSuperConstructor(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return hostType().lookupSuperConstructor();
        }
        if(caller == getConstructorInvocationOptNoTransform()) {
            return hostType().lookupSuperConstructor();
        }
        return getParent().Define_Collection_lookupSuperConstructor(this, caller);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 46
    public TypeDecl Define_TypeDecl_thisType(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return introducedType();
        }
        return getParent().Define_TypeDecl_thisType(this, caller);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 35
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getBlockNoTransform()){
    Collection c = introducedType().memberMethods(name);
    return !c.isEmpty() ? c : lookupMethod(name);
  }
        if(caller == getConstructorInvocationOptNoTransform()){
    Collection c = introducedType().memberMethods(name);
    return !c.isEmpty() ? c : lookupMethod(name);
  }
        return super.Define_Collection_lookupMethod(caller, child, name);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 19
    public TypeDecl Define_TypeDecl_enclosingType(ASTNode caller, ASTNode child) {
        if(caller == getConstructorInvocationOptNoTransform()) {
            return introducedType();
        }
        if(caller == getBlockNoTransform()) {
            return introducedType();
        }
        return getParent().Define_TypeDecl_enclosingType(this, caller);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 24
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return hostAspect();
        }
        return getParent().Define_TypeDecl_hostType(this, caller);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 20
    public boolean Define_boolean_isMemberType(ASTNode caller, ASTNode child) {
        if(caller == getConstructorInvocationOptNoTransform()) {
            return introducedType().isMemberType();
        }
        if(caller == getBlockNoTransform()) {
            return introducedType().isMemberType();
        }
        return getParent().Define_boolean_isMemberType(this, caller);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 21
    public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
        if(caller == getConstructorInvocationOptNoTransform()) {
            return introducedType();
        }
        if(caller == getBlockNoTransform()) {
            return introducedType();
        }
        return super.Define_TypeDecl_enclosingInstance(caller, child);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 11
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTargetTypeNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return super.Define_NameType_nameType(caller, child);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 27
    public Collection Define_Collection_lookupConstructor(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return hostType().constructors();
        }
        if(caller == getConstructorInvocationOptNoTransform()) {
            return hostType().constructors();
        }
        return getParent().Define_Collection_lookupConstructor(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
