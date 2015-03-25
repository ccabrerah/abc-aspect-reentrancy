
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



public class ExplicitTypeNamePattern extends NamePattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
        matchesType_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ExplicitTypeNamePattern clone() throws CloneNotSupportedException {
        ExplicitTypeNamePattern node = (ExplicitTypeNamePattern)super.clone();
        node.matchesType_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ExplicitTypeNamePattern copy() {
      try {
          ExplicitTypeNamePattern node = (ExplicitTypeNamePattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ExplicitTypeNamePattern fullCopy() {
        ExplicitTypeNamePattern res = (ExplicitTypeNamePattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Patterns.jrag at line 96


    protected Access buildAccess()
    {
        return getAccess();
    }

    // Declared in Patterns.jrag at line 209

    public String toString() {
    	return getAccess().toString();
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 111

    public ExplicitTypeNamePattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 111
    public ExplicitTypeNamePattern(Access p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 111
    public void setAccess(Access node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public Access getAccess() {
        return (Access)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public Access getAccessNoTransform() {
        return (Access)getChildNoTransform(0);
    }

    // Declared in DeclarePrecedence.jrag at line 134
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTypeAccess() {
        boolean isTypeAccess_value = isTypeAccess_compute();
        return isTypeAccess_value;
    }

    private boolean isTypeAccess_compute() {  return true;  }

    // Declared in Patterns.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public boolean isExplicitTypeName() {
        boolean isExplicitTypeName_value = isExplicitTypeName_compute();
        return isExplicitTypeName_value;
    }

    private boolean isExplicitTypeName_compute() {  return true;  }

    // Declared in Patterns.jrag at line 120
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return getAccess().predNameType();  }

    // Declared in PatternsCodegen.jrag at line 509
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(SootClass c) {
        boolean matchesType_SootClass_value = matchesType_compute(c);
        return matchesType_SootClass_value;
    }

    private boolean matchesType_compute(SootClass c) {
        Set set = matchSubtype() ? superTypes(c) : new HashSet();
        set.add(c);
        for(Iterator iter = set.iterator(); iter.hasNext(); ) {
          c = (SootClass)iter.next();

          if(getAccess().type().jvmName().equals(c.getName())
             && getAccess().isTypeAccess())
            return true;
        }
        return false;
    }

    // Declared in PatternsCodegen.jrag at line 535
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(Type t) {
        boolean matchesType_Type_value = matchesType_compute(t);
        return matchesType_Type_value;
    }

    private boolean matchesType_compute(Type t) {
        boolean builtin_pat = getAccess().type().isPrimitive()
                                || getAccess().type().isVoid();
        boolean builtin_type = ! (t instanceof RefType);

        if (builtin_pat && builtin_type) {
            return getAccess().type().name().equals(t.toString())
                    && getAccess().isTypeAccess();
        }
        if (builtin_pat || builtin_type) {
            return false;
        }
        SootClass c = ((RefType) t).getSootClass();
        return matchesType(c);
    }

    // Declared in PatternsCodegen.jrag at line 576
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
      if (getAccess().type() == t)
        return true;

      if (!matchSubtype())
        return false;

      Iterator i = t.superTypes().iterator();
      while (i.hasNext()) {
        TypeDecl supertype = (TypeDecl) i.next();
        if (getAccess().type() == supertype)
          return true;
      }
      return false;
    }

    // Declared in Pointcuts.jrag at line 627
 @SuppressWarnings({"unchecked", "cast"})     public boolean explicitInterface() {
        boolean explicitInterface_value = explicitInterface_compute();
        return explicitInterface_value;
    }

    private boolean explicitInterface_compute() {  return type().isInterfaceDecl();  }

    // Declared in PointcutsCodegen.jrag at line 252
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        boolean isVariable_value = isVariable_compute();
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return getAccess().isVariable();  }

    // Declared in PointcutsCodegen.jrag at line 258
 @SuppressWarnings({"unchecked", "cast"})     public String variableName() {
        String variableName_value = variableName_compute();
        return variableName_value;
    }

    private String variableName_compute() {  return getAccess().variableName();  }

    // Declared in PointcutsCodegen.jrag at line 270
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        TypeDecl type_value = type_compute();
        return type_value;
    }

    private TypeDecl type_compute() {  return getAccess().type();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
