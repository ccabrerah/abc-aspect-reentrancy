
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;




// ----------------------- NamePatterns ------------------

public abstract class Pattern extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        matchesType_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Pattern clone() throws CloneNotSupportedException {
        Pattern node = (Pattern)super.clone();
        node.matchesType_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in Patterns.jrag at line 85

    
    // Build Access nodes for patterns with no wildcards
    protected Access buildAccess()
    {
        throw new RuntimeException(
                "buildAccess() not supported for " + getClass().getName());
    }

    // Declared in PatternsCodegen.jrag at line 42

    public abc.weaving.aspectinfo.TypePattern typePattern() {
      return new abc.weaving.aspectinfo.TypePattern() {
        public boolean matchesType(Type cl) {
          return Pattern.this.matchesType(cl);
        }
        public abc.aspectj.ast.TypePatternExpr getPattern() {
          throw new InternalCompilerError(
                    "Can not get polyglot frontend pattern from JastAdd");
        }
        public boolean equivalent(abc.weaving.aspectinfo.TypePattern p) {
          return false;
        }
        public String toString()
        {
          return "<type pattern>";
        }
      };
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 98

    public Pattern() {
        super();


    }

    // Declared in AspectJ.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in DeclarePrecedence.jrag at line 132
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTypeAccess() {
        boolean isTypeAccess_value = isTypeAccess_compute();
        return isTypeAccess_value;
    }

    private boolean isTypeAccess_compute() {  return false;  }

    // Declared in DeclarePrecedence.jrag at line 154
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStar() {
        boolean isStar_value = isStar_compute();
        return isStar_value;
    }

    private boolean isStar_compute() {  return false;  }

    // Declared in Patterns.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public boolean containsWildcard() {
        boolean containsWildcard_value = containsWildcard_compute();
        return containsWildcard_value;
    }

    private boolean containsWildcard_compute() {  return false;  }

    // Declared in Patterns.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public Pattern patternRoot() {
        Pattern patternRoot_value = patternRoot_compute();
        return patternRoot_value;
    }

    private Pattern patternRoot_compute() {
      Pattern root = this;
      while(root.getParent() instanceof Pattern && !root.denotesMember())
        root = (Pattern)root.getParent();
      return root;
    }

    // Declared in Patterns.jrag at line 60
 @SuppressWarnings({"unchecked", "cast"})     public boolean isExplicitTypeName() {
        boolean isExplicitTypeName_value = isExplicitTypeName_compute();
        return isExplicitTypeName_value;
    }

    private boolean isExplicitTypeName_compute() {  return false;  }

    // Declared in Patterns.jrag at line 108
 @SuppressWarnings({"unchecked", "cast"})     public Pattern base() {
        Pattern base_value = base_compute();
        return base_value;
    }

    private Pattern base_compute() {
        throw new InternalCompilerError(
            "base() is undefined for " + getClass().getName());
    }

    // Declared in Patterns.jrag at line 117
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }

    // Declared in PatternsCodegen.jrag at line 287
 @SuppressWarnings({"unchecked", "cast"})     public ClassnamePattern classnamePattern() {
        ClassnamePattern classnamePattern_value = classnamePattern_compute();
        return classnamePattern_value;
    }

    private ClassnamePattern classnamePattern_compute() {  return new ClassnamePattern() {
            public boolean matchesClass(SootClass cl)
            {
            	if(abc.main.Debug.v().patternMatches) {
            		System.err.println("Matching classname pattern " + Pattern.this + " against "
            				+ cl + ": " + matchesType(cl.getType()));
            	}
                return matchesType(cl);
            }

            public boolean equivalent(ClassnamePattern p)
            {
                return false;
            }

            public abc.aspectj.ast.ClassnamePatternExpr getPattern()
            {
                throw new InternalCompilerError(
                        "Can not get polyglot frontend pattern from JastAdd");
            }

            public String toString()
            {
                return "<classname pattern>";
            }
        };  }

    // Declared in PatternsCodegen.jrag at line 339
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesTypeAndName(SootFieldRef sfr) {
        boolean matchesTypeAndName_SootFieldRef_value = matchesTypeAndName_compute(sfr);
        return matchesTypeAndName_SootFieldRef_value;
    }

    private boolean matchesTypeAndName_compute(SootFieldRef sfr) {  return false;  }

    // Declared in PatternsCodegen.jrag at line 359
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesTypeAndName(SootMethodRef smr) {
        boolean matchesTypeAndName_SootMethodRef_value = matchesTypeAndName_compute(smr);
        return matchesTypeAndName_SootMethodRef_value;
    }

    private boolean matchesTypeAndName_compute(SootMethodRef smr) {  return false;  }

    // Declared in PatternsCodegen.jrag at line 360
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesName(String name) {
        boolean matchesName_String_value = matchesName_compute(name);
        return matchesName_String_value;
    }

    private boolean matchesName_compute(String name) {  return false;  }

    // Declared in PatternsCodegen.jrag at line 494
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(SootClass c) {
        boolean matchesType_SootClass_value = matchesType_compute(c);
        return matchesType_SootClass_value;
    }

    private boolean matchesType_compute(SootClass c) {  return false;  }

    // Declared in PatternsCodegen.jrag at line 527
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(Type t) {
        boolean matchesType_Type_value = matchesType_compute(t);
        return matchesType_Type_value;
    }

    private boolean matchesType_compute(Type t) {  return false;  }

    protected java.util.Map matchesType_TypeDecl_values;
    // Declared in PatternsCodegen.jrag at line 563
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

    private boolean matchesType_compute(TypeDecl t) {  return false;  }

    // Declared in PointcutsCodegen.jrag at line 251
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        boolean isVariable_value = isVariable_compute();
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return false;  }

    // Declared in PointcutsCodegen.jrag at line 254
 @SuppressWarnings({"unchecked", "cast"})     public String variableName() {
        String variableName_value = variableName_compute();
        return variableName_value;
    }

    private String variableName_compute() {
        throw new InternalCompilerError(
            "variableName() not defined for " + getClass().getName() + ")");
    }

    // Declared in PointcutsCodegen.jrag at line 266
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        TypeDecl type_value = type_compute();
        return type_value;
    }

    private TypeDecl type_compute() {
        throw new InternalCompilerError(
            "type() not defined for " + getClass().getName() + ")");
    }

    // Declared in Patterns.jrag at line 39
 @SuppressWarnings({"unchecked", "cast"})     public boolean denotesMember() {
        boolean denotesMember_value = getParent().Define_boolean_denotesMember(this, null);
        return denotesMember_value;
    }

    // Declared in PatternsCodegen.jrag at line 486
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchSubtype() {
        boolean matchSubtype_value = getParent().Define_boolean_matchSubtype(this, null);
        return matchSubtype_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
