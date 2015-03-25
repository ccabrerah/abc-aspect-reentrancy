
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;
// Each IntertypeFieldDeclaration generates a corresponding method in the introduced type

public class IntertypeFieldDeclaration extends FieldDeclaration implements Cloneable {
    public void flushCache() {
        super.flushCache();
        accessibleFrom_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntertypeFieldDeclaration clone() throws CloneNotSupportedException {
        IntertypeFieldDeclaration node = (IntertypeFieldDeclaration)super.clone();
        node.accessibleFrom_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntertypeFieldDeclaration copy() {
      try {
          IntertypeFieldDeclaration node = (IntertypeFieldDeclaration)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntertypeFieldDeclaration fullCopy() {
        IntertypeFieldDeclaration res = (IntertypeFieldDeclaration)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in IntertypeFieldCodegeneration.jrag at line 33


  public void generateIntertypeDecls() {
    createGetter();
    createSetter();
    if(hasInit())
      createInit();
    // add fields that are introduced directly into a class
    if(!introducedType().isInterfaceDecl())
      introduceFieldIn(introducedType());
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 43


  public void introduceFieldIn(TypeDecl typeDecl) {
    Modifiers m = new Modifiers(new List());
    m.addModifier(new Modifier("public"));
    if(isStatic())
      m.addModifier(new Modifier("static"));
    Opt init = new Opt();
    if(hasInit()) {
      List args = isStatic() ? new List() : new List().add(new ThisAccess("this"));
      init.addChild(createInit().createBoundAccess(args));
    }
    FieldDeclaration f = typeDecl.addMemberField(
      new IntroducedFieldDeclaration(
        m,
        type().createQualifiedAccess(),
        introducedName(),
        init,
        name(),
        hostAspect()
      )
    );
    //((IntroducedFieldDeclaration)f).originalField = this;
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 121


  public MethodDecl refined_IntertypeFieldCodegeneration_createInit() {
    String name = "field_init";
    TypeDecl typeDecl = hostAspect();
    MethodDecl m = (MethodDecl)typeDecl.getAccessor(this, name);
    if(m != null) return m;

    Modifiers modifiers = createAccessorModifiers(true);
    List parameters = !isStatic() ? new List().add(new ParameterDeclaration(introducedType(), "that")) : new List();
    Expr returnValue;
    if(getInit() instanceof ArrayInit) {
      TypeAccess access = type().elementType().createBoundAccess();
      for(int i = 0; i < type().dimension(); i++)
        access = new ArrayTypeAccess(access);
      returnValue = new ArrayCreationExpr(access, new Opt(getInit().boundCopy()));
    }
    else 
      returnValue =  (Expr)getInit().boundCopy();
    Stmt returnStmt = new ReturnStmt(new Opt(returnValue));
    m = new IntroducedMethodDecl(
      modifiers,
      type().createQualifiedAccess(),
      initName(),
      parameters,
      new List(),
      new Opt(new Block(new List().add(returnStmt))),
      introducedType(),
      this
    );
    m = typeDecl.addMemberMethod(m);
    typeDecl.addAccessor(this, name, m);
    return m;
    //return buildAccessor(modifiers, type(), initName(), parameters, returnStmt);
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 158



  // create dispatch method in aspect that returns the value of the field
  // if the introduced type is an interface then delegate to getter in interface
  public MethodDecl refined_IntertypeFieldCodegeneration_createGetter() {
    Modifiers m = createAccessorModifiers(isStatic());
    List parameters;
    Expr result;
    if(isStatic()) {
      parameters = new List();
      result = introducedType().createQualifiedAccess().qualifiesAccess(new VarAccess(introducedName()));
    }
    else {
      parameters = new List().add(new ParameterDeclaration(introducedType(), "that"));
      if(introducedType().isInterfaceDecl())
        result = new VarAccess("that").qualifiesAccess(new BoundMethodAccess(interfaceGetterName(), new List(), createInterfaceGetter()));
      else
        result = new VarAccess("that").qualifiesAccess(new VarAccess(introducedName()));
    }
    return buildAccessor(m, type(), getDispatchName(), parameters, new ReturnStmt(new Opt(result)));
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 177



  public MethodDecl refined_IntertypeFieldCodegeneration_createInterfaceGetter() {
    Modifiers m = createAccessorModifiers(false);
    List parameters = new List();
    return buildAccessor(m, type(), interfaceGetterName(), parameters, introducedType());
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 183


  public MethodDecl createInterfaceGetterImplementation(TypeDecl typeDecl) {
    Modifiers m = createAccessorModifiers(false);
    List parameters = new List();
    Expr result = new VarAccess(introducedName());
    return buildAccessor(m, type(), interfaceGetterName(), parameters, new ReturnStmt(new Opt(result)), typeDecl);
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 191



  public MethodDecl refined_IntertypeFieldCodegeneration_createSetter() {
    Modifiers m = createAccessorModifiers(isStatic());
    List parameters;
    Expr result;
    if(isStatic()) {
      parameters = new List().add(new ParameterDeclaration(type(), "v"));
      result = new AssignSimpleExpr(
        introducedType().createQualifiedAccess().qualifiesAccess(new VarAccess(introducedName())),
        new VarAccess("v")
      );
    }
    else {
      parameters = new List().add(new ParameterDeclaration(introducedType(), "that")).add(new ParameterDeclaration(type(), "v"));
      if(introducedType().isInterfaceDecl())
        result = new VarAccess("that").qualifiesAccess(new BoundMethodAccess(interfaceSetterName(), new List().add(new VarAccess("v")), createInterfaceSetter()));
      else
        result = new AssignSimpleExpr(
          new VarAccess("that").qualifiesAccess(new VarAccess(introducedName())),
          new VarAccess("v")
        );
    }
    return buildAccessor(m, type(), setDispatchName(), parameters, new ReturnStmt(new Opt(result)));
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 216



  public MethodDecl refined_IntertypeFieldCodegeneration_createInterfaceSetter() {
    Modifiers m = createAccessorModifiers(false);
    List parameters = new List().add(new ParameterDeclaration(type(), "v"));
    return buildAccessor(m, type(), interfaceSetterName(), parameters, introducedType());
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 222


  public MethodDecl createInterfaceSetterImplementation(TypeDecl typeDecl) {
    Modifiers m = createAccessorModifiers(false);
    List parameters = new List().add(new ParameterDeclaration(type(), "v"));
    Expr result = new AssignSimpleExpr(new VarAccess(introducedName()), new VarAccess("v"));
    return buildAccessor(m, type(), interfaceSetterName(), parameters, new ReturnStmt(new Opt(result)), typeDecl);
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 259


  private MethodDecl buildAccessor(Modifiers modifiers, TypeDecl type, 
                                                             String name, List parameters, Stmt stmt) {
    TypeDecl typeDecl = hostAspect();
    return buildAccessor(modifiers, type, name, parameters, stmt, typeDecl);
  }

    // Declared in IntertypeFieldErrorCheck.jrag at line 11

  public void typeCheck() {
    super.typeCheck();
    if(introducedType().isInterfaceDecl() && isStatic())
      error("static inter-type field on interface not supported");
  }

    // Declared in IntertypeFieldErrorCheck.jrag at line 17


  public void checkModifiers() {
    if(isPrivate() && hostType().isInterfaceDecl()) {
      if(isProtected())
        error("an interface field may not be protected");
      if(isTransient())
        error("an interface field may not be transient");
      if(isVolatile())
        error("an interface field may not be volatile");
    }
    else 
      super.typeCheck();
  }

    // Declared in IntertypeFieldErrorCheck.jrag at line 38


  public void nameCheck() {
    if(!introducedType().memberFields(name()).contains(this))
      error("field is already declared");
    else {
      for(Iterator iter = introducedType().memberFields(name()).iterator(); iter.hasNext(); ) {
        FieldDeclaration v = (FieldDeclaration)iter.next();
        if(v != this && v.accessibleFrom(hostAspect()))
          error("field named " + name() + " is multiply declared in type " + hostType().typeName());
      }
    }
    if(!introducedType().isUnknown() && !introducedType().compilationUnit().weavable())
      error("Host of an intertype declaration must be a weavable class");

  }

    // Declared in IntertypeFieldErrorCheck.jrag at line 54


  // disable definite assignment in intertype field declarations
  public void definiteAssignment() {
  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 93


  protected void collectIntertypeDecls(HashMap map) {
    super.collectIntertypeDecls(map);
    TypeDecl typeDecl = introducedType();
    if(!map.containsKey(typeDecl))
      map.put(typeDecl, new ArrayList());
    Collection c = (Collection)map.get(typeDecl);
    c.add(this);
  }

    // Declared in IntertypeFieldPrettyPrint.jrag at line 11

 public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" ");
    getTargetType().toString(s);
    s.append("." + name());
    if(hasInit()) {
      s.append(" = ");
      getInit().toString(s);
    }
    s.append(";\n");
  }

    // Declared in IntertypeJimple.jrag at line 67


  SootFieldRef introducedSootRef(TypeDecl base)
  {
    return Scene.v().makeFieldRef(
      base.getSootClassDecl(),
      introducedName(),
      type().getSootType(),
      isStatic()
    );
  }

    // Declared in IntertypeField.ast at line 3
    // Declared in IntertypeField.ast line 2

    public IntertypeFieldDeclaration() {
        super();

        setChild(new Opt(), 2);

    }

    // Declared in IntertypeField.ast at line 11


    // Declared in IntertypeField.ast line 2
    public IntertypeFieldDeclaration(Modifiers p0, Access p1, String p2, Opt<Expr> p3, Access p4) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setChild(p4, 3);
    }

    // Declared in IntertypeField.ast at line 20


    // Declared in IntertypeField.ast line 2
    public IntertypeFieldDeclaration(Modifiers p0, Access p1, beaver.Symbol p2, Opt<Expr> p3, Access p4) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setChild(p4, 3);
    }

    // Declared in IntertypeField.ast at line 28


  protected int numChildren() {
    return 4;
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
    // Declared in IntertypeField.ast line 2
    public void setTargetType(Access node) {
        setChild(node, 3);
    }

    // Declared in IntertypeField.ast at line 5

    public Access getTargetType() {
        return (Access)getChild(3);
    }

    // Declared in IntertypeField.ast at line 9


    public Access getTargetTypeNoTransform() {
        return (Access)getChildNoTransform(3);
    }

    // Declared in AspectJCodegen.jrag at line 134

    public MethodDecl createInit() {
    MethodDecl m = refined_IntertypeFieldCodegeneration_createInit();
    m.category = MethodCategory.INTERTYPE_FIELD_INITIALIZER;
    return m;
  }

    // Declared in AspectJCodegen.jrag at line 140

  
    public MethodDecl createGetter() {
    MethodDecl m  = refined_IntertypeFieldCodegeneration_createGetter();
    m.category = MethodCategory.ACCESSOR_GET;
    return m;
  }

    // Declared in AspectJCodegen.jrag at line 150

    public MethodDecl createInterfaceGetter() {
    MethodDecl m  = refined_IntertypeFieldCodegeneration_createInterfaceGetter();
    m.category = MethodCategory.ACCESSOR_GET;
    return m;
  }

    // Declared in AspectJCodegen.jrag at line 145

    public MethodDecl createSetter() {
    MethodDecl m  = refined_IntertypeFieldCodegeneration_createSetter();
    m.category = MethodCategory.ACCESSOR_SET;
    return m;
  }

    // Declared in AspectJCodegen.jrag at line 155

    public MethodDecl createInterfaceSetter() {
    MethodDecl m  = refined_IntertypeFieldCodegeneration_createInterfaceSetter();
    m.category = MethodCategory.ACCESSOR_SET;
    return m;
  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 130
private boolean refined_IntertypeFieldNameAnalysis_accessibleFrom_TypeDecl(TypeDecl type)
{
    if(isPublic())
      return true;
    else if(isProtected()) {
      if(hostPackage().equals(type.hostPackage()))
        return true;
      if(type.withinBodyThatSubclasses(hostAspect()) != null)
        return true;
      return false;
    }
    else if(isPrivate())
      return hostAspect().topLevelType() == type.topLevelType();
    else
      return hostPackage().equals(type.hostPackage());
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 87
 @SuppressWarnings({"unchecked", "cast"})     public boolean useAccessors() {
        boolean useAccessors_value = useAccessors_compute();
        return useAccessors_value;
    }

    private boolean useAccessors_compute() {  return !isPublic() || introducedType().isInterfaceDecl();  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 94
 @SuppressWarnings({"unchecked", "cast"})     public String abcMangledSignature() {
        String abcMangledSignature_value = abcMangledSignature_compute();
        return abcMangledSignature_value;
    }

    private String abcMangledSignature_compute() {  return hostAspect().abcMangledName() + "$" + introducedType().abcMangledName() + "$" + name();  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 97
 @SuppressWarnings({"unchecked", "cast"})     public String initName() {
        String initName_value = initName_compute();
        return initName_value;
    }

    private String initName_compute() {  return "abc$interFieldInit$" + abcMangledSignature();  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 100
 @SuppressWarnings({"unchecked", "cast"})     public String getDispatchName() {
        String getDispatchName_value = getDispatchName_compute();
        return getDispatchName_value;
    }

    private String getDispatchName_compute() {  return "abc$interFieldGetDispatch$" + abcMangledSignature();  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 102
 @SuppressWarnings({"unchecked", "cast"})     public String interfaceGetterName() {
        String interfaceGetterName_value = interfaceGetterName_compute();
        return interfaceGetterName_value;
    }

    private String interfaceGetterName_compute() {  return "abc$interFieldGet$" + abcMangledSignature();  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 105
 @SuppressWarnings({"unchecked", "cast"})     public String setDispatchName() {
        String setDispatchName_value = setDispatchName_compute();
        return setDispatchName_value;
    }

    private String setDispatchName_compute() {  return "abc$interFieldSetDispatch$" + abcMangledSignature();  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 107
 @SuppressWarnings({"unchecked", "cast"})     public String interfaceSetterName() {
        String interfaceSetterName_value = interfaceSetterName_compute();
        return interfaceSetterName_value;
    }

    private String interfaceSetterName_compute() {  return "abc$interFieldSet$" + abcMangledSignature();  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 112
 @SuppressWarnings({"unchecked", "cast"})     public String introducedName() {
        String introducedName_value = introducedName_compute();
        return introducedName_value;
    }

    private String introducedName_compute() {
    if(isPrivate())
      return "abc$interField$" + hostAspect().topLevelType().abcMangledName() + "$" + name();
    else if(isPublic())
      return name();
    else
      return "abc$interField$" + hostAspect().packageName().replace('.', '_') + "$" + name();
  }

    // Declared in IntertypeFieldCodegeneration.jrag at line 274
 @SuppressWarnings({"unchecked", "cast"})     public boolean generate() {
        boolean generate_value = generate_compute();
        return generate_value;
    }

    private boolean generate_compute() {  return false;  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 12
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFinal() {
        boolean isFinal_value = isFinal_compute();
        return isFinal_value;
    }

    private boolean isFinal_compute() {  return getModifiers().isFinal();  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 13
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        boolean isStatic_value = isStatic_compute();
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return getModifiers().isStatic();  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPublic() {
        boolean isPublic_value = isPublic_compute();
        return isPublic_value;
    }

    private boolean isPublic_compute() {  return getModifiers().isPublic();  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public boolean isClassVariable() {
        boolean isClassVariable_value = isClassVariable_compute();
        return isClassVariable_value;
    }

    private boolean isClassVariable_compute() {  return isStatic();  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInstanceVariable() {
        boolean isInstanceVariable_value = isInstanceVariable_compute();
        return isInstanceVariable_value;
    }

    private boolean isInstanceVariable_compute() {  return !isStatic();  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostAspect() {
        TypeDecl hostAspect_value = hostAspect_compute();
        return hostAspect_value;
    }

    private TypeDecl hostAspect_compute() {  return (TypeDecl)getParent().getParent();  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 23
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl introducedType() {
        TypeDecl introducedType_value = introducedType_compute();
        return introducedType_value;
    }

    private TypeDecl introducedType_compute() {  return getTargetType().type();  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 34
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        TypeDecl hostType_value = hostType_compute();
        return hostType_value;
    }

    private TypeDecl hostType_compute() {  return introducedType();  }

    // Declared in Privileged.jrag at line 43
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
    return refined_IntertypeFieldNameAnalysis_accessibleFrom_TypeDecl(type);
  }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 43
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getInitOptNoTransform()){
    Collection c = introducedType().memberMethods(name);
    if(!c.isEmpty()) return c;
    return lookupMethod(name);
  }
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 37
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getInitOptNoTransform()){
    SimpleSet set = introducedType().memberFields(name);
    if(!set.isEmpty())
      return set;
    return lookupVariable(name);
  }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 28
    public TypeDecl Define_TypeDecl_enclosingType(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return introducedType();
        }
        return getParent().Define_TypeDecl_enclosingType(this, caller);
    }

    // Declared in IntertypeFieldErrorCheck.jrag at line 57
    public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return false;
        }
        return super.Define_boolean_mayBeProtected(caller, child);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 47
    public TypeDecl Define_TypeDecl_thisType(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return introducedType();
        }
        return getParent().Define_TypeDecl_thisType(this, caller);
    }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 35
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return hostAspect();
        }
        return getParent().Define_TypeDecl_hostType(this, caller);
    }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 29
    public boolean Define_boolean_isMemberType(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return introducedType().isMemberType();
        }
        return getParent().Define_boolean_isMemberType(this, caller);
    }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 30
    public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return introducedType();
        }
        return getParent().Define_TypeDecl_enclosingInstance(this, caller);
    }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 18
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTargetTypeNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return super.Define_NameType_nameType(caller, child);
    }

    // Declared in IntertypeFieldNameAnalysis.jrag at line 48
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getInitOptNoTransform()){
    SimpleSet set = introducedType().memberTypes(name);
    if(!set.isEmpty()) return set;
    set = introducedType().lookupType(name);
    if(!set.isEmpty()) return set;
    return lookupType(name);
  }
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
