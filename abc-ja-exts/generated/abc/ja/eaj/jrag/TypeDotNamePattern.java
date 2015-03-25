
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;


public class TypeDotNamePattern extends NamePattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
        matchesType_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeDotNamePattern clone() throws CloneNotSupportedException {
        TypeDotNamePattern node = (TypeDotNamePattern)super.clone();
        node.matchesType_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeDotNamePattern copy() {
      try {
          TypeDotNamePattern node = (TypeDotNamePattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeDotNamePattern fullCopy() {
        TypeDotNamePattern res = (TypeDotNamePattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Patterns.jrag at line 206

    public String toString() {
    	return getLhs() + "." + getRhs();
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 109

    public TypeDotNamePattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 109
    public TypeDotNamePattern(Pattern p0, SimpleNamePattern p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in AspectJ.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in AspectJ.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 109
    public void setLhs(Pattern node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public Pattern getLhs() {
        return (Pattern)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public Pattern getLhsNoTransform() {
        return (Pattern)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 109
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

    // Declared in Patterns.jrag at line 114
 @SuppressWarnings({"unchecked", "cast"})     public Pattern base() {
        Pattern base_value = base_compute();
        return base_value;
    }

    private Pattern base_compute() {  return getLhs();  }

    // Declared in PatternsCodegen.jrag at line 344
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesTypeAndName(SootFieldRef sfr) {
        boolean matchesTypeAndName_SootFieldRef_value = matchesTypeAndName_compute(sfr);
        return matchesTypeAndName_SootFieldRef_value;
    }

    private boolean matchesTypeAndName_compute(SootFieldRef sfr) {
    	throw new InternalCompilerError("matches not implemented for "
    										+ getClass().getName());	
    }

    // Declared in PatternsCodegen.jrag at line 368
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesTypeAndName(SootMethodRef smr) {
        boolean matchesTypeAndName_SootMethodRef_value = matchesTypeAndName_compute(smr);
        return matchesTypeAndName_SootMethodRef_value;
    }

    private boolean matchesTypeAndName_compute(SootMethodRef smr) {
        throw new InternalCompilerError("matches not implemented for "
                                            + getClass().getName());
    }

    // Declared in PatternsCodegen.jrag at line 627
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

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
