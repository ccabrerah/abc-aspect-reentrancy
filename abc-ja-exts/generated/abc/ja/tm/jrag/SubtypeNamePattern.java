
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class SubtypeNamePattern extends NamePattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
        matchesType_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SubtypeNamePattern clone() throws CloneNotSupportedException {
        SubtypeNamePattern node = (SubtypeNamePattern)super.clone();
        node.matchesType_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SubtypeNamePattern copy() {
      try {
          SubtypeNamePattern node = (SubtypeNamePattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SubtypeNamePattern fullCopy() {
        SubtypeNamePattern res = (SubtypeNamePattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Patterns.jrag at line 212

    public String toString() {
    	return getPattern() + "+";
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 112

    public SubtypeNamePattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 112
    public SubtypeNamePattern(NamePattern p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 17

  public boolean mayHaveRewrite() { return true; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 112
    public void setPattern(NamePattern node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public NamePattern getPattern() {
        return (NamePattern)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public NamePattern getPatternNoTransform() {
        return (NamePattern)getChildNoTransform(0);
    }

    // Declared in Patterns.jrag at line 62
 @SuppressWarnings({"unchecked", "cast"})     public boolean isExplicitTypeName() {
        boolean isExplicitTypeName_value = isExplicitTypeName_compute();
        return isExplicitTypeName_value;
    }

    private boolean isExplicitTypeName_compute() {  return true;  }

    // Declared in PatternsCodegen.jrag at line 523
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(SootClass c) {
        boolean matchesType_SootClass_value = matchesType_compute(c);
        return matchesType_SootClass_value;
    }

    private boolean matchesType_compute(SootClass c) {  return getPattern().matchesType(c);  }

    // Declared in PatternsCodegen.jrag at line 552
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(Type t) {
        boolean matchesType_Type_value = matchesType_compute(t);
        return matchesType_Type_value;
    }

    private boolean matchesType_compute(Type t) {  return getPattern().matchesType(t);  }

    // Declared in PatternsCodegen.jrag at line 594
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

    private boolean matchesType_compute(TypeDecl t) {  return getPattern().matchesType(t);  }

    // Declared in PatternsCodegen.jrag at line 488
    public boolean Define_boolean_matchSubtype(ASTNode caller, ASTNode child) {
        if(caller == getPatternNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_matchSubtype(this, caller);
    }

public ASTNode rewriteTo() {
    // Declared in Patterns.jrag at line 77
    if(!getPattern().containsWildcard() && !getPattern().isExplicitTypeName()) {
        duringPatterns++;
        ASTNode result = rewriteRule0();
        duringPatterns--;
        return result;
    }

    return super.rewriteTo();
}

    // Declared in Patterns.jrag at line 77
    private SubtypeNamePattern rewriteRule0() {
{
    		setPattern(new ExplicitTypeNamePattern(getPattern().buildAccess()));
    		return this;
    	}    }
}
