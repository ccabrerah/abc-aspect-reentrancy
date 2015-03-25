
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class SimpleNamePattern extends NamePattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
        regex_computed = false;
        regex_value = null;
        matchesType_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SimpleNamePattern clone() throws CloneNotSupportedException {
        SimpleNamePattern node = (SimpleNamePattern)super.clone();
        node.regex_computed = false;
        node.regex_value = null;
        node.matchesType_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SimpleNamePattern copy() {
      try {
          SimpleNamePattern node = (SimpleNamePattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SimpleNamePattern fullCopy() {
        SimpleNamePattern res = (SimpleNamePattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Patterns.jrag at line 91


    protected Access buildAccess()
    {
        return new ParseName(getPattern());
    }

    // Declared in Patterns.jrag at line 197

    public String toString() {
    	return getPattern();
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 106

    public SimpleNamePattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 106
    public SimpleNamePattern(String p0) {
        setPattern(p0);
    }

    // Declared in AspectJ.ast at line 15


    // Declared in AspectJ.ast line 106
    public SimpleNamePattern(beaver.Symbol p0) {
        setPattern(p0);
    }

    // Declared in AspectJ.ast at line 19


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 22

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 106
    private String tokenString_Pattern;

    // Declared in AspectJ.ast at line 3

    public void setPattern(String value) {
        tokenString_Pattern = value;
    }

    // Declared in AspectJ.ast at line 6

    public int Patternstart;

    // Declared in AspectJ.ast at line 7

    public int Patternend;

    // Declared in AspectJ.ast at line 8

    public void setPattern(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setPattern is only valid for String lexemes");
        tokenString_Pattern = (String)symbol.value;
        Patternstart = symbol.getStart();
        Patternend = symbol.getEnd();
    }

    // Declared in AspectJ.ast at line 15

    public String getPattern() {
        return tokenString_Pattern != null ? tokenString_Pattern : "";
    }

    // Declared in DeclarePrecedence.jrag at line 127
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet decls() {
        SimpleSet decls_value = decls_compute();
        return decls_value;
    }

    private SimpleSet decls_compute() {  return lookupType(getPattern());  }

    // Declared in DeclarePrecedence.jrag at line 130
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        TypeDecl type_value = type_compute();
        return type_value;
    }

    private TypeDecl type_compute() {  return decls().size() == 1 ? (TypeDecl)decls().iterator().next() : unknownType();  }

    // Declared in DeclarePrecedence.jrag at line 133
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTypeAccess() {
        boolean isTypeAccess_value = isTypeAccess_compute();
        return isTypeAccess_value;
    }

    private boolean isTypeAccess_compute() {  return !type().isUnknown();  }

    // Declared in DeclarePrecedence.jrag at line 155
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStar() {
        boolean isStar_value = isStar_compute();
        return isStar_value;
    }

    private boolean isStar_compute() {  return getPattern().equals("*");  }

    // Declared in Patterns.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public boolean containsWildcard() {
        boolean containsWildcard_value = containsWildcard_compute();
        return containsWildcard_value;
    }

    private boolean containsWildcard_compute() {  return getPattern().indexOf('*') != -1;  }

    // Declared in Patterns.jrag at line 121
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }

    protected boolean regex_computed = false;
    protected java.util.regex.Pattern regex_value;
    // Declared in PatternsCodegen.jrag at line 327
 @SuppressWarnings({"unchecked", "cast"})     public java.util.regex.Pattern regex() {
        if(regex_computed)
            return regex_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        regex_value = regex_compute();
        if(isFinal && num == boundariesCrossed)
            regex_computed = true;
        return regex_value;
    }

    private java.util.regex.Pattern regex_compute() {
        String p = getPattern();
        p = p.replaceAll("\\*", ".*");
        p = p.replaceAll("\\$","\\\\\\$");
        p = "^" + p + "$";
        return java.util.regex.Pattern.compile(p);
    }

    // Declared in PatternsCodegen.jrag at line 341
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesTypeAndName(SootFieldRef sfr) {
        boolean matchesTypeAndName_SootFieldRef_value = matchesTypeAndName_compute(sfr);
        return matchesTypeAndName_SootFieldRef_value;
    }

    private boolean matchesTypeAndName_compute(SootFieldRef sfr) {  return matchesName(MethodCategory.getName(sfr.resolve()));  }

    // Declared in PatternsCodegen.jrag at line 362
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesTypeAndName(SootMethodRef smr) {
        boolean matchesTypeAndName_SootMethodRef_value = matchesTypeAndName_compute(smr);
        return matchesTypeAndName_SootMethodRef_value;
    }

    private boolean matchesTypeAndName_compute(SootMethodRef smr) {  return matchesName(MethodCategory.getName(smr.resolve()));  }

    // Declared in PatternsCodegen.jrag at line 365
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesName(String name) {
        boolean matchesName_String_value = matchesName_compute(name);
        return matchesName_String_value;
    }

    private boolean matchesName_compute(String name) {  return regex().matcher(name).matches();  }

    // Declared in PatternsCodegen.jrag at line 495
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(SootClass c) {
        boolean matchesType_SootClass_value = matchesType_compute(c);
        return matchesType_SootClass_value;
    }

    private boolean matchesType_compute(SootClass c) {
        Set set = matchSubtype() ? superTypes(c) : new HashSet();
        set.add(c);
        for(Iterator iter = set.iterator(); iter.hasNext(); ) {
          c = (SootClass)iter.next();
          String name = c.getShortName();
          int index = name.lastIndexOf('$');
          if (index != -1)
            name = name.substring(index + 1, name.length());
          if(matchesName(name))
            return true;
        }
        return false;
    }

    // Declared in PatternsCodegen.jrag at line 528
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(Type t) {
        boolean matchesType_Type_value = matchesType_compute(t);
        return matchesType_Type_value;
    }

    private boolean matchesType_compute(Type t) {
        if(! (t instanceof RefType)) {
            return matchesName(t.toString());
        }
        SootClass c = ((RefType) t).getSootClass();
        return matchesType(c);
    }

    // Declared in PatternsCodegen.jrag at line 564
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
      if(matchesName(t.name()))
        return true;
      if(matchSubtype()) {
        for(Iterator iter = t.superTypes().iterator(); iter.hasNext(); ) {
          String name = t.name();
          if(matchesName(name))
            return true;
        }
      }
      return false;
    }

    // Declared in DeclarePrecedence.jrag at line 128
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupType(String name) {
        SimpleSet lookupType_String_value = getParent().Define_SimpleSet_lookupType(this, null, name);
        return lookupType_String_value;
    }

    // Declared in DeclarePrecedence.jrag at line 129
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl unknownType() {
        TypeDecl unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
        return unknownType_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
