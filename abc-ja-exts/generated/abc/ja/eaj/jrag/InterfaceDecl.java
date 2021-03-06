
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;


public class InterfaceDecl extends ReferenceType implements Cloneable {
    public void flushCache() {
        super.flushCache();
        methodsSignatureMap_computed = false;
        methodsSignatureMap_value = null;
        ancestorMethods_String_values = null;
        memberTypes_String_values = null;
        memberFields_String_values = null;
        isStatic_computed = false;
        castingConversionTo_TypeDecl_values = null;
        instanceOf_TypeDecl_values = null;
        isCircular_visited = 0;
        isCircular_computed = false;
        isCircular_initialized = false;
        implementedInterfaces_computed = false;
        implementedInterfaces_value = null;
        subtype_TypeDecl_visited = new java.util.HashMap(4);
        isCircularWithoutDeclareParents_visited = 0;
        isCircularWithoutDeclareParents_computed = false;
        isCircularWithoutDeclareParents_initialized = false;
        introducedInterfaces_computed = false;
        introducedInterfaces_value = null;
        allIntroducedFields_computed = false;
        allIntroducedFields_value = null;
        sootClass_computed = false;
        sootClass_value = null;
        abcClass_computed = false;
        abcClass_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public InterfaceDecl clone() throws CloneNotSupportedException {
        InterfaceDecl node = (InterfaceDecl)super.clone();
        node.methodsSignatureMap_computed = false;
        node.methodsSignatureMap_value = null;
        node.ancestorMethods_String_values = null;
        node.memberTypes_String_values = null;
        node.memberFields_String_values = null;
        node.isStatic_computed = false;
        node.castingConversionTo_TypeDecl_values = null;
        node.instanceOf_TypeDecl_values = null;
        node.isCircular_visited = 0;
        node.isCircular_computed = false;
        node.isCircular_initialized = false;
        node.implementedInterfaces_computed = false;
        node.implementedInterfaces_value = null;
        node.subtype_TypeDecl_visited = new java.util.HashMap(4);
        node.isCircularWithoutDeclareParents_visited = 0;
        node.isCircularWithoutDeclareParents_computed = false;
        node.isCircularWithoutDeclareParents_initialized = false;
        node.introducedInterfaces_computed = false;
        node.introducedInterfaces_value = null;
        node.allIntroducedFields_computed = false;
        node.allIntroducedFields_value = null;
        node.sootClass_computed = false;
        node.sootClass_value = null;
        node.abcClass_computed = false;
        node.abcClass_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public InterfaceDecl copy() {
      try {
          InterfaceDecl node = (InterfaceDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public InterfaceDecl fullCopy() {
        InterfaceDecl res = (InterfaceDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AccessControl.jrag at line 167


  public void accessControl() {
    super.accessControl();
    
    if(!isCircular()) {
      // 9.1.2
      HashSet set = new HashSet();
      for(int i = 0; i < getNumSuperInterfaceId(); i++) {
        TypeDecl decl = getSuperInterfaceId(i).type();

        if(!decl.isInterfaceDecl() && !decl.isUnknown())
          error("interface " + fullName() + " tries to extend non interface type " + decl.fullName());
        if(!decl.isCircular() && !decl.accessibleFrom(this))
          error("interface " + fullName() + " can not extend non accessible type " + decl.fullName());

        if(set.contains(decl))
          error("extended interface " + decl.fullName() + " mentionened multiple times in extends clause");
        set.add(decl);
      }
    }
  }

    // Declared in Modifiers.jrag at line 104

  
  public void checkModifiers() {
    super.checkModifiers();
  }

    // Declared in PrettyPrint.jadd at line 99

  
  public void toString(StringBuffer s) {
    getModifiers().toString(s);
    s.append("interface " + name());
    if(getNumSuperInterfaceId() > 0) {
      s.append(" extends ");
      getSuperInterfaceId(0).toString(s);
      for(int i = 1; i < getNumSuperInterfaceId(); i++) {
        s.append(", ");
        getSuperInterfaceId(i).toString(s);
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

    // Declared in TypeHierarchyCheck.jrag at line 312


  public void nameCheck() {
    super.nameCheck();
    if(isCircular())
      error("circular inheritance dependency in " + typeName()); 
    else {
      for(int i = 0; i < getNumSuperInterfaceId(); i++) {
        TypeDecl typeDecl = getSuperInterfaceId(i).type();
        if(typeDecl.isCircular())
          error("circular inheritance dependency in " + typeName()); 
      }
    }
    for(Iterator iter = methodsSignatureMap().values().iterator(); iter.hasNext(); ) {
      SimpleSet set = (SimpleSet)iter.next();
      if(set.size() > 1) {
        Iterator i2 = set.iterator();
        MethodDecl m = (MethodDecl)i2.next();
        while(i2.hasNext()) {
          MethodDecl n = (MethodDecl)i2.next();
          if(!n.mayOverrideReturn(m) && !m.mayOverrideReturn(n))
            error("multiply inherited methods with the same signature must have the same return type");
        }
      }
    }
  }

    // Declared in Generics.jrag at line 189


  public TypeDecl makeGeneric(Signatures.ClassSignature s) {
    if(s.hasFormalTypeParameters()) {
      ASTNode node = getParent();
      int index = node.getIndexOfChild(this);
      node.setChild(
          new GenericInterfaceDecl(
            getModifiersNoTransform(),
            getID(),
            s.hasSuperinterfaceSignature() ? s.superinterfaceSignature() : getSuperInterfaceIdListNoTransform(),
            getBodyDeclListNoTransform(),
            s.typeParameters()
          ),
          index
      );
      return (TypeDecl)node.getChildNoTransform(index);
    }
    else {
      if(s.hasSuperinterfaceSignature())
        setSuperInterfaceIdList(s.superinterfaceSignature());
      return this;
    }
  }

    // Declared in Generics.jrag at line 1122

  public InterfaceDecl p(Parameterization parTypeDecl) {
    InterfaceDecl c = new InterfaceDeclSubstituted(
      (Modifiers)getModifiers().fullCopy(),
      getID(),
      getSuperInterfaceIdList().substitute(parTypeDecl),
      new List(),
      this
    );
    return c;
  }

    // Declared in Java2Rewrites.jrag at line 100


  public FieldDeclaration createStaticClassField(String name) {
    return methodHolder().createStaticClassField(name);
  }

    // Declared in Java2Rewrites.jrag at line 103

  public MethodDecl createStaticClassMethod() {
    return methodHolder().createStaticClassMethod();
  }

    // Declared in Java2Rewrites.jrag at line 107

  // create anonymous class to delegate to
  private TypeDecl methodHolder = null;

    // Declared in Java2Rewrites.jrag at line 108

  public TypeDecl methodHolder() {
    if(methodHolder != null)
      return methodHolder;
    String name = "$" + nextAnonymousIndex();
    ClassDecl c = addMemberClass(new ClassDecl(
      new Modifiers(new List()),
      name,
      new Opt(),
      new List(),
      new List()
    ));
    methodHolder = c;
    return c;
  }

    // Declared in DeclareParentsAnalysis.jrag at line 111


  public void typeCheck() {
    super.typeCheck();
    for(Iterator iter = declareParentsImplements().iterator(); iter.hasNext(); ) {
      DeclareParentsImplements e = (DeclareParentsImplements)iter.next();
      if(e.getPattern().matchesType(this)) {
        for(int i = 0; i < e.getNumTypeAccess(); i++) {
          TypeDecl typeDecl = e.getTypeAccess(i).type();
          if(typeDecl.isClassDecl() && !typeDecl.isUnknown())
            e.error("class " + typeDecl.typeName() + " may not be a parent of " + typeName());
        }
      }
    }
    for(Iterator iter = declareParentsExtends().iterator(); iter.hasNext(); ) {
      DeclareParentsExtends e = (DeclareParentsExtends)iter.next();
      if(e.getPattern().matchesType(this)) {
        for(int i = 0; i < e.getNumTypeAccess(); i++) {
          TypeDecl typeDecl = e.getTypeAccess(i).type();
          if(typeDecl.isClassDecl() && !typeDecl.isUnknown())
            e.error("class " + typeDecl.typeName() + " may not be a parent of " + typeName());
        }
      }
    }
  }

    // Declared in DeclareParentsAnalysis.jrag at line 353


  public Iterator superinterfacesIteratorWithoutDeclareParents() {
    return new Iterator() {
      public boolean hasNext() {
        computeNextCurrent();
        return current != null;
      }
      public Object next() {
        return current;
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
      private int index = 0;
      private TypeDecl current = null;
      private void computeNextCurrent() {
        current = null;
        if(isCircularWithoutDeclareParents()) return;
        while(index < getNumSuperInterfaceId()) {
          TypeDecl typeDecl = getSuperInterfaceId(index++).type();
          if(!typeDecl.isCircularWithoutDeclareParents() && typeDecl.isInterfaceDecl()) {
            current = typeDecl;
            return;
          }
        }
      }
    };
  }

    // Declared in DeclareParentsAnalysis.jrag at line 505

  public boolean isPublic() {
    return super.isPublic() || mustBePublic;
  }

    // Declared in IntertypeMethodCodegeneration.jrag at line 13

  
  // generate code for each inter-type method that is visible in this type
  public void generateIntertypeDecls() {
    super.generateIntertypeDecls();
    for(Iterator iter = localMethodsIterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(m instanceof IntertypeMethodDecl) {
        IntertypeMethodDecl methodDecl = (IntertypeMethodDecl)m;
        // do not generate code in the declaring aspect
        // TODO: should maybe not be included in localMethodsIterator()
        if(methodDecl.hostAspect() != this)
          methodDecl.createIntertypeMethod(this);
      }
    }
  }

    // Declared in EmitJimple.jrag at line 170


  public void jimplify1phase2() {
    SootClass sc = getSootClassDecl();
    sc.setSuperclass(typeObject().getSootClassDecl());
    for(Iterator iter = superinterfacesIterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      if(typeDecl != typeObject() && !sc.implementsInterface(typeDecl.jvmName()))
        sc.addInterface(typeDecl.getSootClassDecl());
    }
    if(isNestedType())
      sc.setOuterClass(enclosingType().getSootClassDecl());
    super.jimplify1phase2();
  }

    // Declared in AspectJCodegen.jrag at line 216

  
  public void jimplify1phase1() {
    super.jimplify1phase1();
    AbcClass abc = abcClass();
    globalAspectInfo().addWeavableClass(abc);
    // register types affected by a declare parents introduction
    // in the global aspect info structure
    if(affectedByDeclareParents())
      globalAspectInfo().getExtendedClasses().add(getSootClassDecl());
  }

    // Declared in PatternsCodegen.jrag at line 662

    public void addSuperTypes(Set set)
    {
        for (int i = 0; i < getNumSuperInterfaceId; i++) {
            TypeDecl iface = getSuperInterfaceId(i).type();
            if (!iface.isUnknown() && !set.contains(iface)) {
                set.add(iface);
                iface.addSuperTypes(set);
            }
        }
    }

    // Declared in Pointcuts.jrag at line 517

    public void addInheritedPointcuts(Set visited, Set names)
    {
        visited.add(this);
        Iterator i = superinterfacesIterator();
        while (i.hasNext()) {
            TypeDecl iface = (TypeDecl) i.next();
            if (!visited.contains(iface)) {
                iface.addLocalPointcuts(names);
                iface.addInheritedPointcuts(visited, names);
            }
        }
    }

    // Declared in java.ast at line 3
    // Declared in java.ast line 64

    public InterfaceDecl() {
        super();

        setChild(new List(), 1);
        setChild(new List(), 2);

    }

    // Declared in java.ast at line 12


    // Declared in java.ast line 64
    public InterfaceDecl(Modifiers p0, String p1, List<Access> p2, List<BodyDecl> p3) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 20


    // Declared in java.ast line 64
    public InterfaceDecl(Modifiers p0, beaver.Symbol p1, List<Access> p2, List<BodyDecl> p3) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 27


  protected int numChildren() {
    return 3;
  }

    // Declared in java.ast at line 30

  public boolean mayHaveRewrite() { return false; }

    // Declared in java.ast at line 2
    // Declared in java.ast line 64
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
    // Declared in java.ast line 64
    public void setSuperInterfaceIdList(List<Access> list) {
        setChild(list, 1);
    }

    // Declared in java.ast at line 6


    private int getNumSuperInterfaceId = 0;

    // Declared in java.ast at line 7

    public int getNumSuperInterfaceId() {
        return getSuperInterfaceIdList().getNumChild();
    }

    // Declared in java.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Access getSuperInterfaceId(int i) {
        return (Access)getSuperInterfaceIdList().getChild(i);
    }

    // Declared in java.ast at line 15


    public void addSuperInterfaceId(Access node) {
        List<Access> list = getSuperInterfaceIdList();
        list.addChild(node);
    }

    // Declared in java.ast at line 20


    public void setSuperInterfaceId(Access node, int i) {
        List<Access> list = getSuperInterfaceIdList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 24

    public List<Access> getSuperInterfaceIds() {
        return getSuperInterfaceIdList();
    }

    // Declared in java.ast at line 27

    public List<Access> getSuperInterfaceIdsNoTransform() {
        return getSuperInterfaceIdListNoTransform();
    }

    // Declared in java.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getSuperInterfaceIdList() {
        return (List<Access>)getChild(1);
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getSuperInterfaceIdListNoTransform() {
        return (List<Access>)getChildNoTransform(1);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 64
    public void setBodyDeclList(List<BodyDecl> list) {
        setChild(list, 2);
    }

    // Declared in java.ast at line 6


    private int getNumBodyDecl = 0;

    // Declared in java.ast at line 7

    public int getNumBodyDecl() {
        return getBodyDeclList().getNumChild();
    }

    // Declared in java.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public BodyDecl getBodyDecl(int i) {
        return (BodyDecl)getBodyDeclList().getChild(i);
    }

    // Declared in java.ast at line 15


    public void addBodyDecl(BodyDecl node) {
        List<BodyDecl> list = getBodyDeclList();
        list.addChild(node);
    }

    // Declared in java.ast at line 20


    public void setBodyDecl(BodyDecl node, int i) {
        List<BodyDecl> list = getBodyDeclList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 24

    public List<BodyDecl> getBodyDecls() {
        return getBodyDeclList();
    }

    // Declared in java.ast at line 27

    public List<BodyDecl> getBodyDeclsNoTransform() {
        return getBodyDeclListNoTransform();
    }

    // Declared in java.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<BodyDecl> getBodyDeclList() {
        return (List<BodyDecl>)getChild(2);
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<BodyDecl> getBodyDeclListNoTransform() {
        return (List<BodyDecl>)getChildNoTransform(2);
    }

    // Declared in DeclareParentsAnalysis.jrag at line 292


    public Iterator superinterfacesIterator() {
    return new Iterator() {
      public boolean hasNext() {
        computeNextCurrent();
        return current != null;
      }
      public Object next() {
        return current;
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
      private int index = 0;
      private Iterator iter = null;
      private TypeDecl current = null;
      private void computeNextCurrent() {
        current = null;
        if(isCircular()) return;
        while(index < getNumSuperInterfaceId()) {
          TypeDecl typeDecl = getSuperInterfaceId(index++).type();
          if(!typeDecl.isCircular() && typeDecl.isInterfaceDecl()) {
            current = typeDecl;
            return;
          }
        }
        if(iter == null)
          iter = introducedInterfaces().iterator();
        while(iter.hasNext()) {
          TypeDecl typeDecl = (TypeDecl)iter.next();
          if(!typeDecl.isCircular() && typeDecl.isInterfaceDecl()) {
            current = typeDecl;
            return;
          }
        }
      }
    };
  }

    // Declared in TypeAnalysis.jrag at line 694
private boolean refined_TypeAnalysis_isCircular()
{
    for(int i = 0; i < getNumSuperInterfaceId(); i++) {
      Access a = getSuperInterfaceId(i).lastAccess();
      while(a != null) {
        if(a.type().isCircular())
          return true;
        a = (a.isQualified() && a.qualifier().isTypeAccess()) ? (Access)a.qualifier() : null;
      }
    }
    return false;
  }

    // Declared in Generics.jrag at line 53
private boolean refined_Generics_castingConversionTo_TypeDecl(TypeDecl type)
{
    TypeDecl S = this;
    TypeDecl T = type;
    if(T.isArrayDecl())
      return T.instanceOf(S);
    else if(T.isReferenceType() && !T.isFinal()) {
      return true;
    }
    else {
      return T.instanceOf(S);
    }
  }

    // Declared in LookupConstructor.jrag at line 23
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupSuperConstructor() {
        Collection lookupSuperConstructor_value = lookupSuperConstructor_compute();
        return lookupSuperConstructor_value;
    }

    private Collection lookupSuperConstructor_compute() {  return typeObject().constructors();  }

    // Declared in LookupMethod.jrag at line 325
 @SuppressWarnings({"unchecked", "cast"})     public HashMap methodsSignatureMap() {
        if(methodsSignatureMap_computed)
            return methodsSignatureMap_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        methodsSignatureMap_value = methodsSignatureMap_compute();
        if(isFinal && num == boundariesCrossed)
            methodsSignatureMap_computed = true;
        return methodsSignatureMap_value;
    }

    private HashMap methodsSignatureMap_compute() {
    HashMap map = new HashMap(localMethodsSignatureMap());
    for(Iterator outerIter = superinterfacesIterator(); outerIter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)outerIter.next();
      for(Iterator iter = typeDecl.methodsIterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        if(!m.isPrivate() && m.accessibleFrom(this) && !localMethodsSignatureMap().containsKey(m.signature()))
          putSimpleSetElement(map, m.signature(), m);
      }
    }
    for(Iterator iter = typeObject().methodsIterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(m.isPublic() && !map.containsKey(m.signature()))
        putSimpleSetElement(map, m.signature(), m);
    }
    return map;
  }

    // Declared in LookupMethod.jrag at line 397
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet ancestorMethods(String signature) {
        Object _parameters = signature;
if(ancestorMethods_String_values == null) ancestorMethods_String_values = new java.util.HashMap(4);
        if(ancestorMethods_String_values.containsKey(_parameters))
            return (SimpleSet)ancestorMethods_String_values.get(_parameters);
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet ancestorMethods_String_value = ancestorMethods_compute(signature);
        if(isFinal && num == boundariesCrossed)
            ancestorMethods_String_values.put(_parameters, ancestorMethods_String_value);
        return ancestorMethods_String_value;
    }

    private SimpleSet ancestorMethods_compute(String signature) {
    SimpleSet set = SimpleSet.emptySet;
    for(Iterator outerIter = superinterfacesIterator(); outerIter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)outerIter.next();
      for(Iterator iter = typeDecl.methodsSignature(signature).iterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        set = set.add(m);
      }
    }
    if(!superinterfacesIterator().hasNext()) {
      for(Iterator iter = typeObject().methodsSignature(signature).iterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        if(m.isPublic())
          set = set.add(m);
      }
    }
    return set;
  }

    // Declared in DeclareParentsAnalysis.jrag at line 278
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet memberTypes(String name) {
        Object _parameters = name;
if(memberTypes_String_values == null) memberTypes_String_values = new java.util.HashMap(4);
        if(memberTypes_String_values.containsKey(_parameters))
            return (SimpleSet)memberTypes_String_values.get(_parameters);
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet memberTypes_String_value = memberTypes_compute(name);
        if(isFinal && num == boundariesCrossed)
            memberTypes_String_values.put(_parameters, memberTypes_String_value);
        return memberTypes_String_value;
    }

    private SimpleSet memberTypes_compute(String name) {
    SimpleSet set = localTypeDecls(name);
    if(!set.isEmpty()) return set;
    for(Iterator outerIter = superinterfacesIteratorWithoutDeclareParents(); outerIter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)outerIter.next();
      for(Iterator iter = typeDecl.memberTypes(name).iterator(); iter.hasNext(); ) {
        TypeDecl decl = (TypeDecl)iter.next();
        if(!decl.isPrivate())
          set = set.add(decl);
      }
    }
    return set;
  }

    // Declared in LookupVariable.jrag at line 297
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet memberFields(String name) {
        Object _parameters = name;
if(memberFields_String_values == null) memberFields_String_values = new java.util.HashMap(4);
        if(memberFields_String_values.containsKey(_parameters))
            return (SimpleSet)memberFields_String_values.get(_parameters);
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet memberFields_String_value = memberFields_compute(name);
        if(isFinal && num == boundariesCrossed)
            memberFields_String_values.put(_parameters, memberFields_String_value);
        return memberFields_String_value;
    }

    private SimpleSet memberFields_compute(String name) {
    SimpleSet fields = localFields(name);
    if(!fields.isEmpty()) 
      return fields;
    for(Iterator outerIter = superinterfacesIterator(); outerIter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)outerIter.next();
      for(Iterator iter = typeDecl.memberFields(name).iterator(); iter.hasNext(); ) {
        FieldDeclaration f = (FieldDeclaration)iter.next();
        if(f.accessibleFrom(this) && !f.isPrivate()) {
          fields = fields.add(f);
        }
      }
    }
    return fields;
  }

    // Declared in Modifiers.jrag at line 203
 @SuppressWarnings({"unchecked", "cast"})     public boolean isAbstract() {
        boolean isAbstract_value = isAbstract_compute();
        return isAbstract_value;
    }

    private boolean isAbstract_compute() {  return true;  }

    // Declared in Modifiers.jrag at line 206
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        if(isStatic_computed)
            return isStatic_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        isStatic_value = isStatic_compute();
        if(isFinal && num == boundariesCrossed)
            isStatic_computed = true;
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return getModifiers().isStatic() || isMemberType();  }

    // Declared in AutoBoxing.jrag at line 148
 @SuppressWarnings({"unchecked", "cast"})     public boolean castingConversionTo(TypeDecl type) {
        Object _parameters = type;
if(castingConversionTo_TypeDecl_values == null) castingConversionTo_TypeDecl_values = new java.util.HashMap(4);
        if(castingConversionTo_TypeDecl_values.containsKey(_parameters))
            return ((Boolean)castingConversionTo_TypeDecl_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean castingConversionTo_TypeDecl_value = castingConversionTo_compute(type);
        if(isFinal && num == boundariesCrossed)
            castingConversionTo_TypeDecl_values.put(_parameters, Boolean.valueOf(castingConversionTo_TypeDecl_value));
        return castingConversionTo_TypeDecl_value;
    }

    private boolean castingConversionTo_compute(TypeDecl type) {
    if(refined_Generics_castingConversionTo_TypeDecl(type))
      return true;
    boolean canUnboxThis = !unboxed().isUnknown();
    boolean canUnboxType = !type.unboxed().isUnknown();
    if(canUnboxThis && !canUnboxType)
      return unboxed().wideningConversionTo(type);
    return false;
    /*
    else if(unboxingConversionTo(type))
      return true;
    return false;
    */
  }

    // Declared in TypeAnalysis.jrag at line 213
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInterfaceDecl() {
        boolean isInterfaceDecl_value = isInterfaceDecl_compute();
        return isInterfaceDecl_value;
    }

    private boolean isInterfaceDecl_compute() {  return true;  }

    // Declared in GenericsSubtype.jrag at line 388
 @SuppressWarnings({"unchecked", "cast"})     public boolean instanceOf(TypeDecl type) {
        Object _parameters = type;
if(instanceOf_TypeDecl_values == null) instanceOf_TypeDecl_values = new java.util.HashMap(4);
        if(instanceOf_TypeDecl_values.containsKey(_parameters))
            return ((Boolean)instanceOf_TypeDecl_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean instanceOf_TypeDecl_value = instanceOf_compute(type);
        if(isFinal && num == boundariesCrossed)
            instanceOf_TypeDecl_values.put(_parameters, Boolean.valueOf(instanceOf_TypeDecl_value));
        return instanceOf_TypeDecl_value;
    }

    private boolean instanceOf_compute(TypeDecl type) {  return subtype(type);  }

    // Declared in TypeAnalysis.jrag at line 430
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfClassDecl(ClassDecl type) {
        boolean isSupertypeOfClassDecl_ClassDecl_value = isSupertypeOfClassDecl_compute(type);
        return isSupertypeOfClassDecl_ClassDecl_value;
    }

    private boolean isSupertypeOfClassDecl_compute(ClassDecl type) {
    if(super.isSupertypeOfClassDecl(type))
      return true;
    for(Iterator iter = type.interfacesIterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      if(typeDecl.instanceOf(this))
        return true;
    }
    return type.hasSuperclass() && type.superclass() != null && type.superclass().instanceOf(this);
  }

    // Declared in TypeAnalysis.jrag at line 443
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfInterfaceDecl(InterfaceDecl type) {
        boolean isSupertypeOfInterfaceDecl_InterfaceDecl_value = isSupertypeOfInterfaceDecl_compute(type);
        return isSupertypeOfInterfaceDecl_InterfaceDecl_value;
    }

    private boolean isSupertypeOfInterfaceDecl_compute(InterfaceDecl type) {
    if(super.isSupertypeOfInterfaceDecl(type))
      return true;
    for(Iterator iter = type.superinterfacesIterator(); iter.hasNext(); ) {
      TypeDecl superinterface = (TypeDecl)iter.next();
      if(superinterface.instanceOf(this))
        return true;
    }
    return false;
  }

    // Declared in TypeAnalysis.jrag at line 460
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfArrayDecl(ArrayDecl type) {
        boolean isSupertypeOfArrayDecl_ArrayDecl_value = isSupertypeOfArrayDecl_compute(type);
        return isSupertypeOfArrayDecl_ArrayDecl_value;
    }

    private boolean isSupertypeOfArrayDecl_compute(ArrayDecl type) {
    if(super.isSupertypeOfArrayDecl(type))
      return true;
    for(Iterator iter = type.interfacesIterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      if(typeDecl.instanceOf(this))
        return true;
    }
    return false;
  }

    protected int isCircular_visited;
    protected boolean isCircular_computed = false;
    protected boolean isCircular_initialized = false;
    protected boolean isCircular_value;
 @SuppressWarnings({"unchecked", "cast"})     public boolean isCircular() {
        if(isCircular_computed)
            return isCircular_value;
        if (!isCircular_initialized) {
            isCircular_initialized = true;
            isCircular_value = true;
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
            CIRCLE_INDEX = 1;
            do {
                isCircular_visited = CIRCLE_INDEX;
                CHANGE = false;
                boolean new_isCircular_value = isCircular_compute();
                if (new_isCircular_value!=isCircular_value)
                    CHANGE = true;
                isCircular_value = new_isCircular_value; 
                CIRCLE_INDEX++;
            } while (CHANGE);
            if(isFinal && num == boundariesCrossed)
{
            isCircular_computed = true;
            }
            else {
            RESET_CYCLE = true;
            isCircular_compute();
            RESET_CYCLE = false;
              isCircular_computed = false;
              isCircular_initialized = false;
            }
            IN_CIRCLE = false; 
            return isCircular_value;
        }
        if(isCircular_visited != CIRCLE_INDEX) {
            isCircular_visited = CIRCLE_INDEX;
            if (RESET_CYCLE) {
                isCircular_computed = false;
                isCircular_initialized = false;
                return isCircular_value;
            }
            boolean new_isCircular_value = isCircular_compute();
            if (new_isCircular_value!=isCircular_value)
                CHANGE = true;
            isCircular_value = new_isCircular_value; 
            return isCircular_value;
        }
        return isCircular_value;
    }

    private boolean isCircular_compute() {
    if(refined_TypeAnalysis_isCircular())
      return true;
    for(Iterator iter = introducedInterfaces().iterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      if(typeDecl.isCircular())
        return true;
    }
    return false;
  }

    // Declared in Generics.jrag at line 378
 @SuppressWarnings({"unchecked", "cast"})     public HashSet implementedInterfaces() {
        if(implementedInterfaces_computed)
            return implementedInterfaces_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        implementedInterfaces_value = implementedInterfaces_compute();
        if(isFinal && num == boundariesCrossed)
            implementedInterfaces_computed = true;
        return implementedInterfaces_value;
    }

    private HashSet implementedInterfaces_compute() {
    HashSet set= new HashSet();
    set.addAll(typeObject().implementedInterfaces());
    for(Iterator iter = superinterfacesIterator(); iter.hasNext(); ) {
      InterfaceDecl decl = (InterfaceDecl)iter.next();
      set.add(decl);
      set.addAll(decl.implementedInterfaces());
    }
    return set;
  }

    protected java.util.Map subtype_TypeDecl_visited;
    protected java.util.Set subtype_TypeDecl_computed = new java.util.HashSet(4);
    protected java.util.Set subtype_TypeDecl_initialized = new java.util.HashSet(4);
    protected java.util.Map subtype_TypeDecl_values = new java.util.HashMap(4);
 @SuppressWarnings({"unchecked", "cast"})     public boolean subtype(TypeDecl type) {
        Object _parameters = type;
if(subtype_TypeDecl_visited == null) subtype_TypeDecl_visited = new java.util.HashMap(4);
if(subtype_TypeDecl_values == null) subtype_TypeDecl_values = new java.util.HashMap(4);
        if(subtype_TypeDecl_computed.contains(_parameters))
            return ((Boolean)subtype_TypeDecl_values.get(_parameters)).booleanValue();
        if (!subtype_TypeDecl_initialized.contains(_parameters)) {
            subtype_TypeDecl_initialized.add(_parameters);
            subtype_TypeDecl_values.put(_parameters, Boolean.valueOf(true));
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
            CIRCLE_INDEX = 1;
            boolean new_subtype_TypeDecl_value;
            do {
                subtype_TypeDecl_visited.put(_parameters, new Integer(CIRCLE_INDEX));
                CHANGE = false;
                new_subtype_TypeDecl_value = subtype_compute(type);
                if (new_subtype_TypeDecl_value!=((Boolean)subtype_TypeDecl_values.get(_parameters)).booleanValue())
                    CHANGE = true;
                subtype_TypeDecl_values.put(_parameters, Boolean.valueOf(new_subtype_TypeDecl_value));
                CIRCLE_INDEX++;
            } while (CHANGE);
            if(isFinal && num == boundariesCrossed)
{
            subtype_TypeDecl_computed.add(_parameters);
            }
            else {
            RESET_CYCLE = true;
            subtype_compute(type);
            RESET_CYCLE = false;
            subtype_TypeDecl_computed.remove(_parameters);
            subtype_TypeDecl_initialized.remove(_parameters);
            }
            IN_CIRCLE = false; 
            return new_subtype_TypeDecl_value;
        }
        if(!new Integer(CIRCLE_INDEX).equals(subtype_TypeDecl_visited.get(_parameters))) {
            subtype_TypeDecl_visited.put(_parameters, new Integer(CIRCLE_INDEX));
            if (RESET_CYCLE) {
                subtype_TypeDecl_computed.remove(_parameters);
                subtype_TypeDecl_initialized.remove(_parameters);
                return ((Boolean)subtype_TypeDecl_values.get(_parameters)).booleanValue();
            }
            boolean new_subtype_TypeDecl_value = subtype_compute(type);
            if (new_subtype_TypeDecl_value!=((Boolean)subtype_TypeDecl_values.get(_parameters)).booleanValue())
                CHANGE = true;
            subtype_TypeDecl_values.put(_parameters, Boolean.valueOf(new_subtype_TypeDecl_value));
            return new_subtype_TypeDecl_value;
        }
        return ((Boolean)subtype_TypeDecl_values.get(_parameters)).booleanValue();
    }

    private boolean subtype_compute(TypeDecl type) {  return type.supertypeInterfaceDecl(this);  }

    // Declared in GenericsSubtype.jrag at line 426
 @SuppressWarnings({"unchecked", "cast"})     public boolean supertypeClassDecl(ClassDecl type) {
        boolean supertypeClassDecl_ClassDecl_value = supertypeClassDecl_compute(type);
        return supertypeClassDecl_ClassDecl_value;
    }

    private boolean supertypeClassDecl_compute(ClassDecl type) {
    if(super.supertypeClassDecl(type))
      return true;
    for(Iterator iter = type.interfacesIterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      if(typeDecl.subtype(this))
        return true;
    }
    return type.hasSuperclass() && type.superclass() != null && type.superclass().subtype(this);
  }

    // Declared in GenericsSubtype.jrag at line 439
 @SuppressWarnings({"unchecked", "cast"})     public boolean supertypeInterfaceDecl(InterfaceDecl type) {
        boolean supertypeInterfaceDecl_InterfaceDecl_value = supertypeInterfaceDecl_compute(type);
        return supertypeInterfaceDecl_InterfaceDecl_value;
    }

    private boolean supertypeInterfaceDecl_compute(InterfaceDecl type) {
    if(super.supertypeInterfaceDecl(type))
      return true;
    for(Iterator iter = type.superinterfacesIterator(); iter.hasNext(); ) {
      TypeDecl superinterface = (TypeDecl)iter.next();
      if(superinterface.subtype(this))
        return true;
    }
    return false;
  }

    // Declared in GenericsSubtype.jrag at line 456
 @SuppressWarnings({"unchecked", "cast"})     public boolean supertypeArrayDecl(ArrayDecl type) {
        boolean supertypeArrayDecl_ArrayDecl_value = supertypeArrayDecl_compute(type);
        return supertypeArrayDecl_ArrayDecl_value;
    }

    private boolean supertypeArrayDecl_compute(ArrayDecl type) {
    if(super.supertypeArrayDecl(type))
      return true;
    for(Iterator iter = type.interfacesIterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      if(typeDecl.subtype(this))
        return true;
    }
    return false;
  }

    // Declared in GenericsCodegen.jrag at line 330
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet bridgeCandidates(String signature) {
        SimpleSet bridgeCandidates_String_value = bridgeCandidates_compute(signature);
        return bridgeCandidates_String_value;
    }

    private SimpleSet bridgeCandidates_compute(String signature) {  return ancestorMethods(signature);  }

    protected int isCircularWithoutDeclareParents_visited;
    protected boolean isCircularWithoutDeclareParents_computed = false;
    protected boolean isCircularWithoutDeclareParents_initialized = false;
    protected boolean isCircularWithoutDeclareParents_value;
 @SuppressWarnings({"unchecked", "cast"})     public boolean isCircularWithoutDeclareParents() {
        if(isCircularWithoutDeclareParents_computed)
            return isCircularWithoutDeclareParents_value;
        if (!isCircularWithoutDeclareParents_initialized) {
            isCircularWithoutDeclareParents_initialized = true;
            isCircularWithoutDeclareParents_value = true;
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
            CIRCLE_INDEX = 1;
            do {
                isCircularWithoutDeclareParents_visited = CIRCLE_INDEX;
                CHANGE = false;
                boolean new_isCircularWithoutDeclareParents_value = isCircularWithoutDeclareParents_compute();
                if (new_isCircularWithoutDeclareParents_value!=isCircularWithoutDeclareParents_value)
                    CHANGE = true;
                isCircularWithoutDeclareParents_value = new_isCircularWithoutDeclareParents_value; 
                CIRCLE_INDEX++;
            } while (CHANGE);
            if(isFinal && num == boundariesCrossed)
{
            isCircularWithoutDeclareParents_computed = true;
            }
            else {
            RESET_CYCLE = true;
            isCircularWithoutDeclareParents_compute();
            RESET_CYCLE = false;
              isCircularWithoutDeclareParents_computed = false;
              isCircularWithoutDeclareParents_initialized = false;
            }
            IN_CIRCLE = false; 
            return isCircularWithoutDeclareParents_value;
        }
        if(isCircularWithoutDeclareParents_visited != CIRCLE_INDEX) {
            isCircularWithoutDeclareParents_visited = CIRCLE_INDEX;
            if (RESET_CYCLE) {
                isCircularWithoutDeclareParents_computed = false;
                isCircularWithoutDeclareParents_initialized = false;
                return isCircularWithoutDeclareParents_value;
            }
            boolean new_isCircularWithoutDeclareParents_value = isCircularWithoutDeclareParents_compute();
            if (new_isCircularWithoutDeclareParents_value!=isCircularWithoutDeclareParents_value)
                CHANGE = true;
            isCircularWithoutDeclareParents_value = new_isCircularWithoutDeclareParents_value; 
            return isCircularWithoutDeclareParents_value;
        }
        return isCircularWithoutDeclareParents_value;
    }

    private boolean isCircularWithoutDeclareParents_compute() {
    for(int i = 0; i < getNumSuperInterfaceId(); i++) {
      Access a = getSuperInterfaceId(i).lastAccess();
      while(a != null) {
        if(a.type().isCircularWithoutDeclareParents())
          return true;
        a = (a.isQualified() && a.qualifier().isTypeAccess()) ? (Access)a.qualifier() : null;
      }
    }
    return false;
  }

    // Declared in DeclareParentsAnalysis.jrag at line 424
 @SuppressWarnings({"unchecked", "cast"})     public Collection introducedInterfaces() {
        if(introducedInterfaces_computed)
            return introducedInterfaces_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        introducedInterfaces_value = introducedInterfaces_compute();
        if(isFinal && num == boundariesCrossed)
            introducedInterfaces_computed = true;
        return introducedInterfaces_value;
    }

    private Collection introducedInterfaces_compute() {
    Collection c = super.introducedInterfaces();
    for(Iterator iter = declareParentsExtends().iterator(); iter.hasNext(); ) {
      DeclareParentsExtends e = (DeclareParentsExtends)iter.next();
      if(e.getPattern().matchesType(this)) {
        for(int i = 0; i < e.getNumTypeAccess(); i++) {
          TypeDecl typeDecl = e.getTypeAccess(i).type();
          if(typeDecl instanceof InterfaceDecl)
            c.add(typeDecl);
        }
      }
    }
    for(Iterator iter = c.iterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      boolean found = false;
      if(typeDecl == this)
        found  = true;
      for(Iterator i2 = superinterfacesIteratorWithoutDeclareParents(); !found && i2.hasNext(); )
        if(i2.next() == typeDecl)
          found = true;
      if(found)
        iter.remove();
    }
    return c;
  }

    protected boolean allIntroducedFields_computed = false;
    protected Collection allIntroducedFields_value;
    // Declared in IntertypeFieldNameAnalysis.jrag at line 112
 @SuppressWarnings({"unchecked", "cast"})     public Collection allIntroducedFields() {
        if(allIntroducedFields_computed)
            return allIntroducedFields_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        allIntroducedFields_value = allIntroducedFields_compute();
        if(isFinal && num == boundariesCrossed)
            allIntroducedFields_computed = true;
        return allIntroducedFields_value;
    }

    private Collection allIntroducedFields_compute() {
    Collection set = new HashSet();
    for(Iterator iter = superinterfacesIterator(); iter.hasNext(); ) {
      InterfaceDecl decl = (InterfaceDecl)iter.next();
      set.addAll(decl.allIntroducedFields());
    }
    set.addAll(introducedFields());
    return set;
  }

    // Declared in EmitJimple.jrag at line 76
 @SuppressWarnings({"unchecked", "cast"})     public SootClass sootClass() {
        if(sootClass_computed)
            return sootClass_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        sootClass_value = sootClass_compute();
        if(isFinal && num == boundariesCrossed)
            sootClass_computed = true;
        return sootClass_value;
    }

    private SootClass sootClass_compute() {
    if(Program.verbose())
      System.out.println("Creating from source " + jvmName());
    SootClass sc = new SootClass(jvmName(), sootTypeModifiers());
    Scene.v().addClass(sc);
    sc.setApplicationClass();
    sc.addTag(new soot.tagkit.SourceFileTag(sourceNameWithoutPath()));
    return sc;
  }

    // Declared in EmitJimple.jrag at line 106
 @SuppressWarnings({"unchecked", "cast"})     public int sootTypeModifiers() {
        int sootTypeModifiers_value = sootTypeModifiers_compute();
        return sootTypeModifiers_value;
    }

    private int sootTypeModifiers_compute() {  return super.sootTypeModifiers() | soot.Modifier.INTERFACE;  }

    // Declared in AspectJCodegen.jrag at line 36
 @SuppressWarnings({"unchecked", "cast"})     public AbcClass abcClass() {
        if(abcClass_computed)
            return abcClass_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        abcClass_value = abcClass_compute();
        if(isFinal && num == boundariesCrossed)
            abcClass_computed = true;
        return abcClass_value;
    }

    private AbcClass abcClass_compute() {  return AbcFactory.AbcClass(getSootClassDecl());  }

    // Declared in Pointcuts.jrag at line 281
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupInheritedPointcut(String name) {
        SimpleSet lookupInheritedPointcut_String_value = lookupInheritedPointcut_compute(name);
        return lookupInheritedPointcut_String_value;
    }

    private SimpleSet lookupInheritedPointcut_compute(String name) {
        SimpleSet decls = SimpleSet.emptySet;

        Iterator i = superinterfacesIterator();
        while (i.hasNext()) {
            TypeDecl iface = (TypeDecl) i.next();
            Iterator j = iface.lookupMemberPointcut(name).iterator();
            while (j.hasNext())
                decls = decls.add(j.next());
        }

        return decls;
    }

    // Declared in TypeAnalysis.jrag at line 98
 @SuppressWarnings({"unchecked", "cast"})     public MethodDecl unknownMethod() {
        MethodDecl unknownMethod_value = getParent().Define_MethodDecl_unknownMethod(this, null);
        return unknownMethod_value;
    }

    // Declared in Annotations.jrag at line 378
    public boolean Define_boolean_withinDeprecatedAnnotation(ASTNode caller, ASTNode child) {
        if(caller == getSuperInterfaceIdListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return isDeprecated() || withinDeprecatedAnnotation();
        }
        return super.Define_boolean_withinDeprecatedAnnotation(caller, child);
    }

    // Declared in TypeAnalysis.jrag at line 578
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(caller == getSuperInterfaceIdListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return hostType();
        }
        return super.Define_TypeDecl_hostType(caller, child);
    }

    // Declared in Annotations.jrag at line 278
    public boolean Define_boolean_withinSuppressWarnings(ASTNode caller, ASTNode child, String s) {
        if(caller == getSuperInterfaceIdListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return hasAnnotationSuppressWarnings(s) || withinSuppressWarnings(s);
        }
        return super.Define_boolean_withinSuppressWarnings(caller, child, s);
    }

    // Declared in SyntacticClassification.jrag at line 75
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getSuperInterfaceIdListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        return super.Define_NameType_nameType(caller, child);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
