
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;
// Each IntertypeMethodDecl generates one static method holding the method body in the aspect and one
// delegating method in the introduced type.

public class IntertypeMethodDecl extends MethodDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        createAspectMethod_computed = false;
        createAspectMethod_value = null;
        overrides_MethodDecl_values = null;
        accessibleFrom_TypeDecl_values = null;
        sootRef_computed = false;
        sootRef_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntertypeMethodDecl clone() throws CloneNotSupportedException {
        IntertypeMethodDecl node = (IntertypeMethodDecl)super.clone();
        node.createAspectMethod_computed = false;
        node.createAspectMethod_value = null;
        node.overrides_MethodDecl_values = null;
        node.accessibleFrom_TypeDecl_values = null;
        node.sootRef_computed = false;
        node.sootRef_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntertypeMethodDecl copy() {
      try {
          IntertypeMethodDecl node = (IntertypeMethodDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IntertypeMethodDecl fullCopy() {
        IntertypeMethodDecl res = (IntertypeMethodDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in IntertypeMethodCodegeneration.jrag at line 48


  // for each class decl that has a memberMethod with an implementation that is also an intertype method decl
  //   if that method is not a member method of the superclass then generate code for that method

  // Each IntertypeMethodDecl generates two methods
  //   IntroducedMethodDecl m1 in the aspect, holding the method body
  //   MethodDecl m2 in the introduced type, that delegates the call to m1 
  // the delegation methods are needed to give the desired semantics for instance methods
  // the IntroducedMethodDecl node type is used to specialize name analysis
  public void createIntertypeMethod(TypeDecl introducedType) {
    generateIntertypeDecls();
    if(introducedType.isInterfaceDecl() || isAbstract()) {
      boolean found = false;
      for(int i = 0; !found && i < introducedType.getNumBodyDecl(); i++) {
        if(introducedType.getBodyDecl(i) instanceof MethodDecl) {
          MethodDecl m = (MethodDecl)introducedType.getBodyDecl(i);
          if(m.signature().equals(signature()) && m.name().equals(delegateName()))
            found = true;
        }
      }
      // create abstract signature
      if(!found)
        introducedType.addMemberMethod(createAbstractMethod());
    }
    else {
      MethodDecl aspectMethod = createAspectMethod();
      MethodDecl delegateMethod = createDelegateMethod(aspectMethod);
      introducedType.addMemberMethod(delegateMethod);
      if(hostType() != introducedType) {
        aspectMethod.generateSuperDispatchDelegate(introducedType);
      }
    }
  }

    // Declared in IntertypeMethodCodegeneration.jrag at line 73


  public MethodDecl createAbstractMethod() {
    return new MethodDecl(
      getModifiers().makePublicAbstract(),
      type().createQualifiedAccess(),
      delegateName(),
      copyParameterList(getParameterList()),
      copyTypeList(getExceptionList()),
      new Opt()
    );
  }

    // Declared in IntertypeMethodCodegeneration.jrag at line 398


  // decl().signature() -> method to call using invoke super
  // hostType() -> type to hold the new method
  // a new method called dispatchSuper$signature
  
  public MethodDecl refined_IntertypeMethodCodegeneration_createDelegateMethod(MethodDecl aspectMethod) {
    // make arguments from this and method parameters
    List args = new List();
    if(!isStatic())
      args.add(new ThisAccess("this"));
    for(int i = 0; i < getNumParameter(); i++) {
      args.add(getParameter(i).createAccess());
    }
    
    // delegate by calling the aspect method
    List statements = new List();
    if(type().isVoid())
    	statements.add(new ExprStmt(aspectMethod.createBoundAccess(args)));
    else
    	statements.add(new ReturnStmt(aspectMethod.createBoundAccess(args)));
      
    return new MethodDecl(
      getModifiers().makePublic(),
      type().createQualifiedAccess(),
      delegateName(),
      copyParameterList(getParameterList()),
      copyTypeList(getExceptionList()),
      new Opt(new Block(statements))
    );
  }

    // Declared in IntertypeMethodErrorCheck.jrag at line 13


  // public, private, default ok
  public void checkModifiers() {
    if(hostType().isClassDecl()) {
      // 8.4.3.1
      if(isAbstract() && !hostType().isAbstract())
        error("class must be abstract to include abstract methods");
      // 8.4.3.1
      // 8.4.3.1
      // 8.4.3.2
      if(isAbstract() && isStatic())
        error("method may not be abstract and static");
      if(isAbstract() && isSynchronized())
        error("method may not be abstract and synchronized");
      // 8.4.3.4
      if(isAbstract() && isNative())
        error("method may not be abstract and native");
      if(isAbstract() && isStrictfp())
        error("method may not be abstract and strictfp");
      if(isNative() && isStrictfp())
        error("method may not be native and strictfp");
    }
    else if(hostType().isInterfaceDecl()) {

      if(isStatic())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be static");
      if(isStrictfp())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be strictfp");
      if(isNative())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be native");
      if(getModifiers().isAbstract() && isSynchronized())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be abstract and synchronized");
    }
    if(isProtected())
      error("Inter-type method declarations may not be protected");
    // TODO: not enforced by ajc? The access modifier of abstract inter-type
    // methods has one constraint: It is illegal to declare an abstract
    // non-public inter-type method on a public interface. This is illegal
    // because it would say that a public interface has a constraint that only
    // non-public implementors must fulfill. This would not be compatible with
    // Java's type system.
    if(isAbstract() && !isPublic() && introducedType().isInterfaceDecl() && introducedType().isPublic())
      error("it is illegal to declare an abstract non-public inter-type method on a public interface");
  }

    // Declared in IntertypeMethodErrorCheck.jrag at line 61


  // override behavior in MethodDecl to change the condition for multiply declared
  public void nameCheck() {
      for(Iterator iter = introducedType().methodsSignature(signature()).iterator(); iter.hasNext(); ) {
        MethodDecl v = (MethodDecl)iter.next();
        if(v != this && v.accessibleFrom(hostAspect()) && !isAbstract()) {
          if(v instanceof IntertypeMethodDecl) {
            IntertypeMethodDecl m = (IntertypeMethodDecl)v;
            boolean overriddenInSubAspect = m.hostAspect().instanceOf(hostAspect()) && m.hostAspect() != hostAspect();
            boolean precedence = m.hostAspect().precedes(hostAspect()) && !hostAspect().precedes(m.hostAspect());
            if(!overriddenInSubAspect && !precedence)
              error("method is multiply declared in type " + hostType().typeName() + " by " + v);
          }
          else {
            error("method is multiply declared in type " + hostType().typeName() + " by " + v);
          }
        }
          //&& (!(v instanceof IntertypeMethodDecl) || (!zaps(v) && !v.zaps(this))))
          //error("method is multiply declared in type " + hostType().typeName() + " by " + v);
      }

    // 8.4
    // 8.4.2
    //if(!visibleOrZapped())
    //  error("method with signature " + signature() + " is multiply declared in type " + hostType().typeName());
    // 8.4.3.4
    if(isNative() && hasBlock())
      error("native methods must have an empty semicolon body");
    // 8.4.5
    if(isAbstract() && hasBlock())
      error("abstract methods must have an empty semicolon body");
    // 8.4.5
    if(!hasBlock() && !(isNative() || isAbstract()))
      error("only abstract and native methods may have an empty semicolon body");
    if(hasBlock() && !introducedType().isUnknown() && !introducedType().compilationUnit().weavable())
      error("Host of an intertype declaration must be a weavable class");
  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 263


  // add inter-type methods to generic inter-type collection phase
  protected void collectIntertypeDecls(HashMap map) {
    super.collectIntertypeDecls(map);
    TypeDecl typeDecl = introducedType();
    if(!map.containsKey(typeDecl))
      map.put(typeDecl, new ArrayList());
    Collection c = (Collection)map.get(typeDecl);
    c.add(this);
  }

    // Declared in IntertypeMethodPrettyPrint.jrag at line 11

  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" ");
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
    if(hasBlock()) {
      s.append(" ");
      getBlock().toString(s);
    }
    else {
      s.append(";\n");
    }
  }

    // Declared in AspectJCodegen.jrag at line 274


  public void jimplify2() {
    // do not generate any code for an IntertypeMethodDecl
    // weaving is done in the front-end
  }

    // Declared in AspectJCodegen.jrag at line 325


  public void jimplify1phase2() {
    // do not generate any code for an IntertypeMethodDecl
    // weaving is done in the front-end
  }

    // Declared in IntertypeMethod.ast at line 3
    // Declared in IntertypeMethod.ast line 3

    public IntertypeMethodDecl() {
        super();

        setChild(new List(), 2);
        setChild(new List(), 3);
        setChild(new Opt(), 4);

    }

    // Declared in IntertypeMethod.ast at line 13


    // Declared in IntertypeMethod.ast line 3
    public IntertypeMethodDecl(Modifiers p0, Access p1, String p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5, Access p6) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
        setChild(p6, 5);
    }

    // Declared in IntertypeMethod.ast at line 24


    // Declared in IntertypeMethod.ast line 3
    public IntertypeMethodDecl(Modifiers p0, Access p1, beaver.Symbol p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5, Access p6) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
        setChild(p4, 3);
        setChild(p5, 4);
        setChild(p6, 5);
    }

    // Declared in IntertypeMethod.ast at line 34


  protected int numChildren() {
    return 6;
  }

    // Declared in IntertypeMethod.ast at line 37

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
    // Declared in IntertypeMethod.ast line 3
    public void setTargetType(Access node) {
        setChild(node, 5);
    }

    // Declared in IntertypeMethod.ast at line 5

    public Access getTargetType() {
        return (Access)getChild(5);
    }

    // Declared in IntertypeMethod.ast at line 9


    public Access getTargetTypeNoTransform() {
        return (Access)getChildNoTransform(5);
    }

    // Declared in AspectJCodegen.jrag at line 100

    public MethodDecl createDelegateMethod(MethodDecl aspectMethod) {
    MethodDecl m = refined_IntertypeMethodCodegeneration_createDelegateMethod(aspectMethod);
    m.category = MethodCategory.INTERTYPE_METHOD_DELEGATOR;
    return m;
  }

    // Declared in IntertypeMethodCodegeneration.jrag at line 84
private MethodDecl refined_IntertypeMethodCodegeneration_createAspectMethod()
{
    if(!is$Final) throw new Error("IntertypeMethodDecl must be final when aspectMethod is created");
    // make aspect method static
    Modifiers modifiers = getModifiers().makePublic();
    if(!isStatic())
      modifiers.addModifier(new Modifier("static"));
    
    // add that for instance methods
    List parameterList = new List();
    if(!isStatic())
    	parameterList.add(new ParameterDeclaration(introducedType().createQualifiedAccess(), "this"));
    for(int i = 0; i < getNumParameter(); i++)
      parameterList.add(getParameter(i).qualifiedCopy());
      
    MethodDecl aspectMethod = new IntroducedMethodDecl(
      modifiers,
      type().createQualifiedAccess(),
      implBodyName(),
      parameterList,
      copyTypeList(getExceptionList()),
      (Opt)getBlockOpt().boundCopy(),
      introducedType(),
      this
    );
    aspectMethod = hostAspect().addMemberMethod(aspectMethod);
    return aspectMethod;
  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 224
private boolean refined_IntertypeMethodNameAnalysis_accessibleFrom_TypeDecl(TypeDecl type)
{
    if(isPublic()) {
      return true;
    }
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

    protected boolean createAspectMethod_computed = false;
    protected MethodDecl createAspectMethod_value;
    // Declared in AspectJCodegen.jrag at line 95
 @SuppressWarnings({"unchecked", "cast"})     public MethodDecl createAspectMethod() {
        if(createAspectMethod_computed)
            return createAspectMethod_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        createAspectMethod_value = createAspectMethod_compute();
        if(isFinal && num == boundariesCrossed)
            createAspectMethod_computed = true;
        return createAspectMethod_value;
    }

    private MethodDecl createAspectMethod_compute() {
    MethodDecl m = refined_IntertypeMethodCodegeneration_createAspectMethod();
    m.category = MethodCategory.INTERTYPE_METHOD_SOURCE;
    return m;
  }

    // Declared in IntertypeMethodCodegeneration.jrag at line 444
 @SuppressWarnings({"unchecked", "cast"})     public boolean generate() {
        boolean generate_value = generate_compute();
        return generate_value;
    }

    private boolean generate_compute() {  return false;  }

    // Declared in IntertypeMethodCodegeneration.jrag at line 531
 @SuppressWarnings({"unchecked", "cast"})     public String implBodyName() {
        String implBodyName_value = implBodyName_compute();
        return implBodyName_value;
    }

    private String implBodyName_compute() {  return "impl$body$" + name();  }

    // Declared in IntertypeMethodCodegeneration.jrag at line 532
 @SuppressWarnings({"unchecked", "cast"})     public String delegateName() {
        String delegateName_value = delegateName_compute();
        return delegateName_value;
    }

    private String delegateName_compute() {
    if(isPrivate())
      return "abc$interMethod$" + hostAspect().topLevelType().abcMangledName() + "$" + name();
    else if(isPublic())
      return name();
    else
      return "abc$interMethod$" + hostAspect().packageName().replace('.', '_') + "$" + name();
  }

    // Declared in IntertypeMethodErrorCheck.jrag at line 246
 @SuppressWarnings({"unchecked", "cast"})     public boolean visibleOrZapped() {
        boolean visibleOrZapped_value = visibleOrZapped_compute();
        return visibleOrZapped_value;
    }

    private boolean visibleOrZapped_compute() {
    SimpleSet set = hostType().methodsSignature(signature());
    if(set.contains(this))
      return true;
    String signature = introducedType().fullName() + "." + signature();
    if(hostAspect().localIntertypeMethodsSignatureMap().get(signature) != this)
      return false;
    return zapped();
  }

    // Declared in IntertypeMethodErrorCheck.jrag at line 256
 @SuppressWarnings({"unchecked", "cast"})     public boolean zapped() {
        boolean zapped_value = zapped_compute();
        return zapped_value;
    }

    private boolean zapped_compute() {
    for(Iterator iter = hostType().introducedMethods().iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(m.zaps(this)) {
        return true;
      }
    }
    return false;
  }

    // Declared in IntertypeMethodErrorCheck.jrag at line 358
 @SuppressWarnings({"unchecked", "cast"})     public boolean isAbstract() {
        boolean isAbstract_value = isAbstract_compute();
        return isAbstract_value;
    }

    private boolean isAbstract_compute() {  return getModifiers().isAbstract();  }

    // Declared in IntertypeMethodErrorCheck.jrag at line 360
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPublic() {
        boolean isPublic_value = isPublic_compute();
        return isPublic_value;
    }

    private boolean isPublic_compute() {  return getModifiers().isPublic();  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostAspect() {
        TypeDecl hostAspect_value = hostAspect_compute();
        return hostAspect_value;
    }

    private TypeDecl hostAspect_compute() {  return (TypeDecl)getParent().getParent();  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl introducedType() {
        TypeDecl introducedType_value = introducedType_compute();
        return introducedType_value;
    }

    private TypeDecl introducedType_compute() {  return getTargetType().type();  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        TypeDecl hostType_value = hostType_compute();
        return hostType_value;
    }

    private TypeDecl hostType_compute() {  return introducedType();  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 175
 @SuppressWarnings({"unchecked", "cast"})     public boolean zaps(MethodDecl m) {
        boolean zaps_MethodDecl_value = zaps_compute(m);
        return zaps_MethodDecl_value;
    }

    private boolean zaps_compute(MethodDecl m) {  return m.zappedByIntertypeMethodDecl(this);  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 178
 @SuppressWarnings({"unchecked", "cast"})     public boolean zappedByMethodDecl(MethodDecl m) {
        boolean zappedByMethodDecl_MethodDecl_value = zappedByMethodDecl_compute(m);
        return zappedByMethodDecl_MethodDecl_value;
    }

    private boolean zappedByMethodDecl_compute(MethodDecl m) {
    if(!m.isAbstract() && (isAbstract() || m.hostType() == hostType()))
      return accessibleFrom(m.hostType());
    return m.hostType() != hostType() && m.overrides(this) && m.accessibleFrom(hostType());
  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 188
 @SuppressWarnings({"unchecked", "cast"})     public boolean zappedByIntertypeMethodDecl(IntertypeMethodDecl m) {
        boolean zappedByIntertypeMethodDecl_IntertypeMethodDecl_value = zappedByIntertypeMethodDecl_compute(m);
        return zappedByIntertypeMethodDecl_IntertypeMethodDecl_value;
    }

    private boolean zappedByIntertypeMethodDecl_compute(IntertypeMethodDecl m) {
    if(m.hostAspect().precedes(hostAspect()) && !hostAspect().precedes(m.hostAspect()))
      return true;
    if(m.hostAspect().instanceOf(hostAspect()) && m.hostAspect() != hostAspect())
      return true;
    if(isAbstract() && !m.isAbstract())
      return m.accessibleFrom(hostType());
    return m.hostType() != hostType() && m.overrides(this) && m.accessibleFrom(hostType());
  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 201
 @SuppressWarnings({"unchecked", "cast"})     public boolean couldOverride(MethodDecl m) {
        boolean couldOverride_MethodDecl_value = couldOverride_compute(m);
        return couldOverride_MethodDecl_value;
    }

    private boolean couldOverride_compute(MethodDecl m) {
    if(!isStatic() && m instanceof IntertypeMethodDecl && m.isPrivate()) {
      IntertypeMethodDecl decl = (IntertypeMethodDecl)m;
      return decl.accessibleFrom(hostAspect()) && hostType().instanceOf(decl.hostType()) && decl.signature().equals(signature());
    }
    else if(!isStatic() && !isAbstract() && hostType().isInterfaceDecl()) {
      // does not use hostType().instanceOf(m.hostType()) since method introduced through
      // may override method in superclasses
      return !m.isPrivate() && m.accessibleFrom(hostType()) && m.signature().equals(signature());
    
    }
    return !isStatic() && !m.isPrivate() && m.accessibleFrom(hostType()) && 
     hostType().instanceOf(m.hostType()) && m.signature().equals(signature());
  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 216
 @SuppressWarnings({"unchecked", "cast"})     public boolean overrides(MethodDecl m) {
        Object _parameters = m;
if(overrides_MethodDecl_values == null) overrides_MethodDecl_values = new java.util.HashMap(4);
        if(overrides_MethodDecl_values.containsKey(_parameters))
            return ((Boolean)overrides_MethodDecl_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean overrides_MethodDecl_value = overrides_compute(m);
        if(isFinal && num == boundariesCrossed)
            overrides_MethodDecl_values.put(_parameters, Boolean.valueOf(overrides_MethodDecl_value));
        return overrides_MethodDecl_value;
    }

    private boolean overrides_compute(MethodDecl m) {
    if(!isStatic() && m instanceof IntertypeMethodDecl && m.isPrivate()) {
      IntertypeMethodDecl decl = (IntertypeMethodDecl)m;
      return decl.accessibleFrom(hostAspect()) && hostType().instanceOf(decl.hostType()) && decl.signature().equals(signature());
    }
    return super.overrides(m);
  }

    // Declared in Privileged.jrag at line 56
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
    return refined_IntertypeMethodNameAnalysis_accessibleFrom_TypeDecl(type);
  }

    // Declared in AspectJCodegen.jrag at line 260
 @SuppressWarnings({"unchecked", "cast"})     public SootMethodRef sootRef() {
        if(sootRef_computed)
            return sootRef_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        sootRef_value = sootRef_compute();
        if(isFinal && num == boundariesCrossed)
            sootRef_computed = true;
        return sootRef_value;
    }

    private SootMethodRef sootRef_compute() {
    ArrayList parameters = new ArrayList();
    for(int i = 0; i < getNumParameter(); i++)
      parameters.add(getParameter(i).type().getSootType());
    SootMethodRef ref = Scene.v().makeMethodRef(
      introducedType().getSootClassDecl(),
      name(),
      parameters,
      type().getSootType(),
      isStatic()
    );
    return ref;
  }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 36
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{
    SimpleSet set = parameterDeclaration(name);
    // A declaration of a method parameter name shadows any other variable declarations
    if(!set.isEmpty()) return set;
    // Search member fields
    set = introducedType().memberFields(name);
    if(!set.isEmpty()) return set;
    // Delegate to other declarations in aspect
    return lookupVariable(name);
  }
}
        return super.Define_SimpleSet_lookupVariable(caller, child, name);
    }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 31
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getBlockOptNoTransform()){
    Collection c = introducedType().memberMethods(name);
    if(!c.isEmpty()) return c;
    return lookupMethod(name);
  }
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 22
    public TypeDecl Define_TypeDecl_enclosingType(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return introducedType();
        }
        return getParent().Define_TypeDecl_enclosingType(this, caller);
    }

    // Declared in IntertypeConstructorNameAnalysis.jrag at line 48
    public TypeDecl Define_TypeDecl_thisType(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return introducedType();
        }
        return getParent().Define_TypeDecl_thisType(this, caller);
    }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 30
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return hostAspect();
        }
        if(caller == getBlockOptNoTransform()) {
            return hostAspect();
        }
        return getParent().Define_TypeDecl_hostType(this, caller);
    }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 23
    public boolean Define_boolean_isMemberType(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return introducedType().isMemberType();
        }
        return getParent().Define_boolean_isMemberType(this, caller);
    }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 24
    public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
        if(caller == getBlockOptNoTransform()) {
            return introducedType();
        }
        return getParent().Define_TypeDecl_enclosingInstance(this, caller);
    }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 12
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTargetTypeNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return super.Define_NameType_nameType(caller, child);
    }

    // Declared in IntertypeMethodNameAnalysis.jrag at line 46
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getBlockOptNoTransform()){
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
