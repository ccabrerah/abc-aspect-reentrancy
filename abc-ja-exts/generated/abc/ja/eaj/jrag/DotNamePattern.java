
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;


public class DotNamePattern extends NamePattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
        matchesType_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DotNamePattern clone() throws CloneNotSupportedException {
        DotNamePattern node = (DotNamePattern)super.clone();
        node.matchesType_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DotNamePattern copy() {
      try {
          DotNamePattern node = (DotNamePattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DotNamePattern fullCopy() {
        DotNamePattern res = (DotNamePattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Patterns.jrag at line 101


    protected Access buildAccess()
    {
        return getLhs().buildAccess().qualifiesAccess(getRhs().buildAccess());
    }

    // Declared in Patterns.jrag at line 200

    public String toString() {
    	return getLhs() + "." + getRhs();
    }

    // Declared in PatternsCodegen.jrag at line 433


    protected boolean declaresMethod(SootClass sc, SootMethodRef smr)
    {
        return sc.declaresMethod(smr.getSubSignature());
    }

    // Declared in PatternsCodegen.jrag at line 439

    
    protected boolean declaresField(SootClass sc, SootFieldRef sfr) {
    	return sc.declaresField(sfr.name(), sfr.type());
    }

    // Declared in PatternsCodegen.jrag at line 443


    protected boolean containsOverriddenMethod(SootClass sc, SootMethodRef smr)
    {
        SootMethodRef overridden =
            Scene.v().makeMethodRef(sc, smr.name(), smr.parameterTypes(),
                                    smr.returnType(), smr.isStatic());

        try {
            overridden.resolve();
            return true;
        } catch(RuntimeException e) {
            return false;
        }
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 107

    public DotNamePattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 107
    public DotNamePattern(NamePattern p0, SimpleNamePattern p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in AspectJ.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in AspectJ.ast at line 18

  public boolean mayHaveRewrite() { return true; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 107
    public void setLhs(NamePattern node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public NamePattern getLhs() {
        return (NamePattern)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public NamePattern getLhsNoTransform() {
        return (NamePattern)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 107
    public void setRhs(SimpleNamePattern node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public SimpleNamePattern getRhs() {
        return (SimpleNamePattern)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public SimpleNamePattern getRhsNoTransform() {
        return (SimpleNamePattern)getChildNoTransform(1);
    }

    // Declared in Patterns.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public boolean containsWildcard() {
        boolean containsWildcard_value = containsWildcard_compute();
        return containsWildcard_value;
    }

    private boolean containsWildcard_compute() {  return getLhs().containsWildcard() || getRhs().containsWildcard();  }

    // Declared in Patterns.jrag at line 113
 @SuppressWarnings({"unchecked", "cast"})     public Pattern base() {
        Pattern base_value = base_compute();
        return base_value;
    }

    private Pattern base_compute() {  return getLhs();  }

    // Declared in Patterns.jrag at line 119
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return getLhs().predNameType();  }

    // Declared in PatternsCodegen.jrag at line 349
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesTypeAndName(SootFieldRef sfr) {
        boolean matchesTypeAndName_SootFieldRef_value = matchesTypeAndName_compute(sfr);
        return matchesTypeAndName_SootFieldRef_value;
    }

    private boolean matchesTypeAndName_compute(SootFieldRef sfr) {
    	// abc-2006-2, section 4.1.2
        // -------------------------
    	// member part
    	if(!getRhs().matchesName(MethodCategory.getName(sfr.resolve())))
    		return false;
        // type part
        return getLhs().matchesType(MethodCategory.getClass(sfr.resolve()));
    }

    // Declared in PatternsCodegen.jrag at line 373
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesTypeAndName(SootMethodRef smr) {
        boolean matchesTypeAndName_SootMethodRef_value = matchesTypeAndName_compute(smr);
        return matchesTypeAndName_SootMethodRef_value;
    }

    private boolean matchesTypeAndName_compute(SootMethodRef smr) {
        // abc-2006-2, section 4.1.1
        // -------------------------
        // C (the context) is implicit
        // R is the static type for method called X in the shadow
        // X is any method that smr overrides
        // Z is a (reflexive transitive) supertype of R and matches the pattern
        // P is the class that contains X

        // deal with the transformations caused by ITDs
        // (note that ITD-introduced methods are static)
        if (smr.isStatic())
        {
            SootMethod sm = smr.resolve();
            SootClass real_class = MethodCategory.getClass(sm);
            String real_name = MethodCategory.getName(sm);
            int real_mods = MethodCategory.getModifiers(sm);
            boolean is_static =
                soot.Modifier.isStatic(MethodCategory.getModifiers(sm));

            smr = Scene.v().makeMethodRef(real_class, real_name,
                smr.parameterTypes(), smr.returnType(), is_static);
        }

        // match member part
        if (!getRhs().matchesName(smr.name()))
            return false;

        // match type part
        SootClass classR = smr.declaringClass();

        // in the simple case, we don't have to consider super-types:
        if (getLhs().matchesType(classR))
            return true;

        // constructors can't be inherited from super-types
        if (smr.name().equals(SootMethod.constructorName))
            return false;

        // find supertypes of R that matches the type pattern
        Set supertypes = superTypes(classR);

        Iterator i = supertypes.iterator();
        while (i.hasNext()) {
            SootClass classZ = (SootClass) i.next();
            if (!getLhs().matchesType(classZ))
                continue;

            // in the static case it must be the case that P = Z
            if(smr.isStatic() && declaresMethod(classZ, smr))
                return true;

            // otherwise it must be the case that hasSubtypeStar(P,Z)
            if (!smr.isStatic() && containsOverriddenMethod(classZ, smr))
                return true;
        }
        return false;
    }

    // Declared in PatternsCodegen.jrag at line 624
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(TypeDecl t) {
        Object _parameters = t;
if(matchesType_TypeDecl_values == null) matchesType_TypeDecl_values = new java.util.HashMap(4);
        if(matchesType_TypeDecl_values.containsKey(_parameters))
            return ((Boolean)matchesType_TypeDecl_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean matchesType_TypeDecl_value = matchesType_compute(t);
        if(isFinal && num == boundariesCrossed)
            matchesType_TypeDecl_values.put(_parameters, Boolean.valueOf(matchesType_TypeDecl_value));
        return matchesType_TypeDecl_value;
    }

    private boolean matchesType_compute(TypeDecl t) {
        throw new RuntimeException("?");
    }

    // Declared in Pointcuts.jrag at line 624
 @SuppressWarnings({"unchecked", "cast"})     public boolean refersToInterface() {
        boolean refersToInterface_value = refersToInterface_compute();
        return refersToInterface_value;
    }

    private boolean refersToInterface_compute() {  return getLhs().explicitInterface();  }

    // Declared in Patterns.jrag at line 43
 @SuppressWarnings({"unchecked", "cast"})     public boolean denotesMember() {
        boolean denotesMember_value = getParent().Define_boolean_denotesMember(this, null);
        return denotesMember_value;
    }

    // Declared in Patterns.jrag at line 56
    public boolean Define_boolean_denotesMember(ASTNode caller, ASTNode child) {
        if(caller == getRhsNoTransform()) {
            return false;
        }
        if(caller == getLhsNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_denotesMember(this, caller);
    }

    // Declared in Patterns.jrag at line 118
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getLhsNoTransform()) {
            return getRhs().predNameType();
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    // Declared in Patterns.jrag at line 66
    if(denotesMember() && !getLhs().containsWildcard() && !getLhs().isExplicitTypeName()) {
        duringPatterns++;
        ASTNode result = rewriteRule0();
        duringPatterns--;
        return result;
    }

    // Declared in Patterns.jrag at line 71
    if(!denotesMember() && !patternRoot().containsWildcard()) {
        duringPatterns++;
        ASTNode result = rewriteRule1();
        duringPatterns--;
        return result;
    }

    return super.rewriteTo();
}

    // Declared in Patterns.jrag at line 66
    private DotNamePattern rewriteRule0() {
{
    		setLhs(new ExplicitTypeNamePattern(getLhs().buildAccess()));
    		return this;
    	}    }
    // Declared in Patterns.jrag at line 71
    private ExplicitTypeNamePattern rewriteRule1() {
{
    		return new ExplicitTypeNamePattern(buildAccess());
    	}    }
}
