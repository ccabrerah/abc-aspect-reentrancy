
package abc.ja.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



// ----------------------- Intertype declarations ----------

//IntertypeMethodDecl : MethodDecl ::= TargetType:Access;
//IntertypeConstructorDecl : ConstructorDecl ::= HostType:Access;
//IntertypeFieldDeclaration : FieldDeclaration ::= HostType:Access;


// ----------------------- Pointcuts -----------------------


public class PointcutDecl extends BodyDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        getImplicitList_computed = false;
        getImplicitList_value = null;
        checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        checkCallGraph_TypeDecl_values = null;
        checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
        pointcutDecl_computed = false;
        pointcutDecl_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PointcutDecl clone() throws CloneNotSupportedException {
        PointcutDecl node = (PointcutDecl)super.clone();
        node.getImplicitList_computed = false;
        node.getImplicitList_value = null;
        node.checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        node.checkCallGraph_TypeDecl_values = null;
        node.checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        node.checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
        node.pointcutDecl_computed = false;
        node.pointcutDecl_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PointcutDecl copy() {
      try {
          PointcutDecl node = (PointcutDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PointcutDecl fullCopy() {
        PointcutDecl res = (PointcutDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Advice.jrag at line 150


  // Pointcut decls should bind their variables, unless they're marked abstract.
  public void typeCheck() {
    if(!isAbstract()) {
      for(int i = 0; i < getNumParameter(); i++) {
        int bindings = getPointcutExpr().binds(getParameter(i).name());
        
        if(bindings == 0) {
          error("Concrete pointcut " + hostType().typeName() + "." + name() +
                " should bind its formal argument " + getParameter(i).name());
        } else if(bindings > 1) {
          error("Pointcut " + hostType().typeName() + "." + name() +
                " binds formal argument " + getParameter(i).name() +
                " multiple times.");
        }
      }
    } else if(isPrivate()) {
      error("Pointcut " + hostType().typeName() + "." + name() +
            " cannot be both abstract and private.");
    }
    overrideParameterCheck();
  }

    // Declared in ImplicitVariables.jrag at line 106

    public void implicitNameCheck()
    {
        super.nameCheck();
        for (int i = 0; i < getNumParameter(); i++)
            getParameter(i).errorIfImplicitName("pointcut declarations");
    }

    // Declared in Pointcuts.jrag at line 309


    
    // check for overriding pointcuts with the wrong number/types of parameters
    public void overrideParameterCheck()
    {
        Iterator i = hostType().lookupInheritedPointcut(name()).iterator();
        while (i.hasNext()) {
            PointcutDecl decl = (PointcutDecl) i.next();
            if (differentParams(decl))
                error("The pointcut " + hostType().typeName() + "." + name()
                      + " cannot override definition in "
                      + decl.hostType().typeName() 
                      + " because parameter types differ.");
                     
        }
    }

    // Declared in Pointcuts.jrag at line 334



    public void checkModifiers()
    {
        super.checkModifiers();
        if (isAbstract()) {
            if (!hostType().isAspectDecl())
                error("Abstract pointcuts are only allowed in aspects.");
            else if (!hostType().isAbstract())
                error("Pointcut " + hostType().typeName() + "." + name() +
                      " cannot be abstract because " + hostType().typeName() +
                      " is not abstract.");

            if (!getPointcutExpr().isEmpty())
                error("The pointcut " + hostType().typeName() + "." + name() +
                      " is abstract but has a body.");
        }
        if (hostType().isInterfaceDecl() && (isProtected() || isPrivate()))
            error("Pointcuts in interfaces must be public.");

        // check that this pointcut is not overriding another pointcut
        // that has stronger access privileges
        Iterator i = hostType().lookupInheritedPointcut(name()).iterator();
        while (i.hasNext()) {
            PointcutDecl decl = (PointcutDecl) i.next();
            if (weakerAccessPrivilegesThan(decl))
                error("The pointcut " + hostType().typeName() + "." + name() +
                      " cannot override " + decl.hostType().typeName() + "." +
                      decl.name() + " with weaker access privileges");
        }
    }

    // Declared in Pointcuts.jrag at line 376


    public void nameCheck()
    {
        super.nameCheck();
        implicitNameCheck();
        circularNameCheck();
        if (hostType().lookupMemberPointcut(name()).iterator().next() != this)
            error("Duplicate definition of pointcut " + name() + ".");
    }

    // Declared in Pointcuts.jrag at line 387



    // Check for cycles in named pointcuts
    public void circularNameCheck()
    {
        if(isCircular())
            error("The pointcut " + name() + " has a circular dependency.");
    }

    // Declared in Pointcuts.jrag at line 535

    public void addLocalPointcuts(Set names)
    {
        names.add(name());
    }

    // Declared in PointcutsCodegen.jrag at line 287


    protected java.util.List formals()
    {
        ArrayList list = new ArrayList();
        for (int i = 0; i < getNumParameter(); i++)
            list.add(getParameter(i).formal());
        return list;
    }

    // Declared in PointcutsCodegen.jrag at line 295


    public void jimplify2()
    {
        globalAspectInfo().addPointcutDecl(pointcutDecl());
        getPointcutExpr().jimplify2();
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 54

    public PointcutDecl() {
        super();

        setChild(new List(), 1);
        setChild(new List(), 3);

    }

    // Declared in AspectJ.ast at line 12


    // Declared in AspectJ.ast line 54
    public PointcutDecl(Modifiers p0, String p1, List<ParameterDeclaration> p2, PointcutExpr p3) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
        setChild(new List(), 3);
    }

    // Declared in AspectJ.ast at line 21


    // Declared in AspectJ.ast line 54
    public PointcutDecl(Modifiers p0, beaver.Symbol p1, List<ParameterDeclaration> p2, PointcutExpr p3) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
        setChild(new List(), 3);
    }

    // Declared in AspectJ.ast at line 29


  protected int numChildren() {
    return 3;
  }

    // Declared in AspectJ.ast at line 32

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 54
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
    // Declared in AspectJ.ast line 54
    private String tokenString_ID;

    // Declared in AspectJ.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in AspectJ.ast at line 6

    public int IDstart;

    // Declared in AspectJ.ast at line 7

    public int IDend;

    // Declared in AspectJ.ast at line 8

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in AspectJ.ast at line 15

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 54
    public void setParameterList(List<ParameterDeclaration> list) {
        setChild(list, 1);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumParameter = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumParameter() {
        return getParameterList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getParameter(int i) {
        return (ParameterDeclaration)getParameterList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addParameter(ParameterDeclaration node) {
        List<ParameterDeclaration> list = getParameterList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setParameter(ParameterDeclaration node, int i) {
        List<ParameterDeclaration> list = getParameterList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<ParameterDeclaration> getParameters() {
        return getParameterList();
    }

    // Declared in AspectJ.ast at line 27

    public List<ParameterDeclaration> getParametersNoTransform() {
        return getParameterListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterList() {
        return (List<ParameterDeclaration>)getChild(1);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(1);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 54
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 2);
    }

    // Declared in AspectJ.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(2);
    }

    // Declared in AspectJ.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(2);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 54
    public void setImplicitList(List<ParameterDeclaration> list) {
        setChild(list, 3);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumImplicit = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumImplicit() {
        return getImplicitList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getImplicit(int i) {
        return (ParameterDeclaration)getImplicitList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addImplicit(ParameterDeclaration node) {
        List<ParameterDeclaration> list = getImplicitList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setImplicit(ParameterDeclaration node, int i) {
        List<ParameterDeclaration> list = getImplicitList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<ParameterDeclaration> getImplicits() {
        return getImplicitList();
    }

    // Declared in AspectJ.ast at line 27

    public List<ParameterDeclaration> getImplicitsNoTransform() {
        return getImplicitListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


    public List<ParameterDeclaration> getImplicitListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(3);
    }

    // Declared in AspectJ.ast at line 35


    protected int getImplicitListChildPosition() {
        return 3;
    }

    protected boolean getImplicitList_computed = false;
    protected List getImplicitList_value;
    // Declared in ImplicitVariables.jrag at line 53
 @SuppressWarnings({"unchecked", "cast"})     public List getImplicitList() {
        if(getImplicitList_computed)
            return (List)ASTNode.getChild(this, getImplicitListChildPosition());
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        getImplicitList_value = getImplicitList_compute();
        setImplicitList(getImplicitList_value);
        if(isFinal && num == boundariesCrossed)
            getImplicitList_computed = true;
        return (List)ASTNode.getChild(this, getImplicitListChildPosition());
    }

    private List getImplicitList_compute() {  return new List().add(implicitVarDecl("thisJoinPoint"))
                  .add(implicitVarDecl("thisJoinPointStaticPart"))
                  .add(implicitVarDecl("thisEnclosingJoinPointStaticPart"));  }

    // Declared in Pointcuts.jrag at line 52
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPublic() {
        boolean isPublic_value = isPublic_compute();
        return isPublic_value;
    }

    private boolean isPublic_compute() {  return getModifiers().isPublic();  }

    // Declared in Pointcuts.jrag at line 53
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrivate() {
        boolean isPrivate_value = isPrivate_compute();
        return isPrivate_value;
    }

    private boolean isPrivate_compute() {  return getModifiers().isPrivate();  }

    // Declared in Pointcuts.jrag at line 54
 @SuppressWarnings({"unchecked", "cast"})     public boolean isProtected() {
        boolean isProtected_value = isProtected_compute();
        return isProtected_value;
    }

    private boolean isProtected_compute() {  return getModifiers().isProtected();  }

    // Declared in Pointcuts.jrag at line 55
 @SuppressWarnings({"unchecked", "cast"})     public boolean isAbstract() {
        boolean isAbstract_value = isAbstract_compute();
        return isAbstract_value;
    }

    private boolean isAbstract_compute() {  return getModifiers().isAbstract();  }

    // Declared in Pointcuts.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFinal() {
        boolean isFinal_value = isFinal_compute();
        return isFinal_value;
    }

    private boolean isFinal_compute() {  return getModifiers().isFinal();  }

    // Declared in Pointcuts.jrag at line 148
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localLookupVariable(String name) {
        SimpleSet localLookupVariable_String_value = localLookupVariable_compute(name);
        return localLookupVariable_String_value;
    }

    private SimpleSet localLookupVariable_compute(String name) {
        for (int i = 0; i < getNumImplicit(); i++)
            if (getImplicit(i).name().equals(name))
                return SimpleSet.emptySet.add(getImplicit(i));
        for(int i = 0; i < getNumParameter(); i++)
            if(getParameter(i).name().equals(name))
                return SimpleSet.emptySet.add(getParameter(i));
        return SimpleSet.emptySet;
    }

    // Declared in Pointcuts.jrag at line 188
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

    // Declared in Pointcuts.jrag at line 298
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupMemberPointcut(String name) {
        SimpleSet lookupMemberPointcut_String_value = lookupMemberPointcut_compute(name);
        return lookupMemberPointcut_String_value;
    }

    private SimpleSet lookupMemberPointcut_compute(String name) {
        if (name.equals(name()))
            return SimpleSet.emptySet.add(this);
        return SimpleSet.emptySet;
    }

    // Declared in Pointcuts.jrag at line 323
 @SuppressWarnings({"unchecked", "cast"})     public boolean differentParams(PointcutDecl other) {
        boolean differentParams_PointcutDecl_value = differentParams_compute(other);
        return differentParams_PointcutDecl_value;
    }

    private boolean differentParams_compute(PointcutDecl other) {
        if (getNumParameter() != other.getNumParameter())
            return true;
        for (int i = 0 ; i < getNumParameter(); i++)
            if (!getParameter(i).type().equals(other.getParameter(i).type()))
                return true;
        return false;
    }

    // Declared in Pointcuts.jrag at line 364
 @SuppressWarnings({"unchecked", "cast"})     public boolean weakerAccessPrivilegesThan(PointcutDecl other) {
        boolean weakerAccessPrivilegesThan_PointcutDecl_value = weakerAccessPrivilegesThan_compute(other);
        return weakerAccessPrivilegesThan_PointcutDecl_value;
    }

    private boolean weakerAccessPrivilegesThan_compute(PointcutDecl other) {
        // overriding public with non-public
        if (other.isPublic() && !isPublic()) return true;
        // overriding protected with non-public non-protected
        if (other.isProtected() && !isPublic() && !isProtected()) return true;
        // overriding package scope with private
        if (!other.isPrivate() && !other.isProtected() && !other.isPublic()
            && isPrivate()) return true;
        return false;
    }

    // Declared in Pointcuts.jrag at line 396
 @SuppressWarnings({"unchecked", "cast"})     public boolean isCircular() {
        boolean isCircular_value = isCircular_compute();
        return isCircular_value;
    }

    private boolean isCircular_compute() {  return checkCallGraph(hostType()) == 2;  }

    protected java.util.Map checkCallGraph_TypeDecl_visited;
    protected java.util.Set checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
    protected java.util.Set checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
    protected java.util.Map checkCallGraph_TypeDecl_values = new java.util.HashMap(4);
 @SuppressWarnings({"unchecked", "cast"})     public int checkCallGraph(TypeDecl context) {
        Object _parameters = context;
if(checkCallGraph_TypeDecl_visited == null) checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
if(checkCallGraph_TypeDecl_values == null) checkCallGraph_TypeDecl_values = new java.util.HashMap(4);
        if(checkCallGraph_TypeDecl_computed.contains(_parameters))
            return ((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue();
        if (!checkCallGraph_TypeDecl_initialized.contains(_parameters)) {
            checkCallGraph_TypeDecl_initialized.add(_parameters);
            checkCallGraph_TypeDecl_values.put(_parameters, new Integer(2));
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
            CIRCLE_INDEX = 1;
            int new_checkCallGraph_TypeDecl_value;
            do {
                checkCallGraph_TypeDecl_visited.put(_parameters, new Integer(CIRCLE_INDEX));
                CHANGE = false;
                new_checkCallGraph_TypeDecl_value = checkCallGraph_compute(context);
                if (new_checkCallGraph_TypeDecl_value!=((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue())
                    CHANGE = true;
                checkCallGraph_TypeDecl_values.put(_parameters, new Integer(new_checkCallGraph_TypeDecl_value));
                CIRCLE_INDEX++;
            } while (CHANGE);
            if(isFinal && num == boundariesCrossed)
{
            checkCallGraph_TypeDecl_computed.add(_parameters);
            }
            else {
            RESET_CYCLE = true;
            checkCallGraph_compute(context);
            RESET_CYCLE = false;
            checkCallGraph_TypeDecl_computed.remove(_parameters);
            checkCallGraph_TypeDecl_initialized.remove(_parameters);
            }
            IN_CIRCLE = false; 
            return new_checkCallGraph_TypeDecl_value;
        }
        if(!new Integer(CIRCLE_INDEX).equals(checkCallGraph_TypeDecl_visited.get(_parameters))) {
            checkCallGraph_TypeDecl_visited.put(_parameters, new Integer(CIRCLE_INDEX));
            if (RESET_CYCLE) {
                checkCallGraph_TypeDecl_computed.remove(_parameters);
                checkCallGraph_TypeDecl_initialized.remove(_parameters);
                return ((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue();
            }
            int new_checkCallGraph_TypeDecl_value = checkCallGraph_compute(context);
            if (new_checkCallGraph_TypeDecl_value!=((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue())
                CHANGE = true;
            checkCallGraph_TypeDecl_values.put(_parameters, new Integer(new_checkCallGraph_TypeDecl_value));
            return new_checkCallGraph_TypeDecl_value;
        }
        return ((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue();
    }

    private int checkCallGraph_compute(TypeDecl context) {  return isAbstract() ? 1 : getPointcutExpr().checkCallGraph(context);  }

    protected boolean pointcutDecl_computed = false;
    protected abc.weaving.aspectinfo.PointcutDecl pointcutDecl_value;
    // Declared in PointcutsCodegen.jrag at line 277
 @SuppressWarnings({"unchecked", "cast"})     public abc.weaving.aspectinfo.PointcutDecl pointcutDecl() {
        if(pointcutDecl_computed)
            return pointcutDecl_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        pointcutDecl_value = pointcutDecl_compute();
        if(isFinal && num == boundariesCrossed)
            pointcutDecl_computed = true;
        return pointcutDecl_value;
    }

    private abc.weaving.aspectinfo.PointcutDecl pointcutDecl_compute() {
        return new abc.weaving.aspectinfo.PointcutDecl(
                    name(),
                    formals(),
                    isAbstract() ? null : getPointcutExpr().pointcut(),
                    aspectClass(),
                    pos());
    }

    // Declared in ImplicitVariables.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public ParameterDeclaration implicitVarDecl(String name) {
        ParameterDeclaration implicitVarDecl_String_value = getParent().Define_ParameterDeclaration_implicitVarDecl(this, null, name);
        return implicitVarDecl_String_value;
    }

    // Declared in Pointcuts.jrag at line 37
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        if(caller == getImplicitListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return false;
        }
        if(caller == getParameterListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isConstructorParameter(this, caller);
    }

    // Declared in Pointcuts.jrag at line 47
    public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBePrivate(this, caller);
    }

    // Declared in Pointcuts.jrag at line 141
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()){
        SimpleSet result = localLookupVariable(name);
        if (!result.isEmpty())
            return result;
        return lookupVariable(name);
    }
        if(caller == getParameterListNoTransform()) { 
   int i = caller.getIndexOfChild(child);
{
        SimpleSet result = localLookupVariable(name);
        if (!result.isEmpty())
            return result;
        return lookupVariable(name);
    }
}
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in Pointcuts.jrag at line 49
    public boolean Define_boolean_mayBeAbstract(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeAbstract(this, caller);
    }

    // Declared in Pointcuts.jrag at line 50
    public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeFinal(this, caller);
    }

    // Declared in Pointcuts.jrag at line 64
    public boolean Define_boolean_isPointcutVariable(ASTNode caller, ASTNode child) {
        if(caller == getImplicitListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return true;
        }
        if(caller == getParameterListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return true;
        }
        return getParent().Define_boolean_isPointcutVariable(this, caller);
    }

    // Declared in Pointcuts.jrag at line 36
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        if(caller == getImplicitListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return false;
        }
        if(caller == getParameterListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isMethodParameter(this, caller);
    }

    // Declared in Pointcuts.jrag at line 46
    public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBePublic(this, caller);
    }

    // Declared in Pointcuts.jrag at line 108
    public boolean Define_boolean_bindsInCurrentCflow(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()) {
            return getPointcutExpr().binds(name) > 0;
        }
        return getParent().Define_boolean_bindsInCurrentCflow(this, caller, name);
    }

    // Declared in Pointcuts.jrag at line 48
    public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_mayBeProtected(this, caller);
    }

    // Declared in Pointcuts.jrag at line 38
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        if(caller == getImplicitListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return false;
        }
        if(caller == getParameterListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return false;
        }
        return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
    }

    // Declared in Pointcuts.jrag at line 39
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getImplicitListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        if(caller == getParameterListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        if(caller == getPointcutExprNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in ImplicitVariables.jrag at line 172
    public java.util.List Define_java_util_List_pointcutFormals(ASTNode caller, ASTNode child) {
        if(caller == getPointcutExprNoTransform()){
        ArrayList result = new ArrayList();
        for (int i = 0; i < getNumParameter(); i++)
            result.add(getParameter(i));
        for (int i = 0; i < getNumImplicit(); i++)
            result.add(getImplicit(i));
        return result;
    }
        return getParent().Define_java_util_List_pointcutFormals(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
