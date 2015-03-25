
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class OrPattern extends BinaryPattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
        matchesType_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public OrPattern clone() throws CloneNotSupportedException {
        OrPattern node = (OrPattern)super.clone();
        node.matchesType_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public OrPattern copy() {
      try {
          OrPattern node = (OrPattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public OrPattern fullCopy() {
        OrPattern res = (OrPattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Patterns.jrag at line 191

    public String toString() {
    	return "(" + getLhs() + ") || (" + getRhs() + ")";
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 101

    public OrPattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 101
    public OrPattern(Pattern p0, Pattern p1) {
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
    // Declared in AspectJ.ast line 99
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
    // Declared in AspectJ.ast line 99
    public void setRhs(Pattern node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public Pattern getRhs() {
        return (Pattern)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public Pattern getRhsNoTransform() {
        return (Pattern)getChildNoTransform(1);
    }

    // Declared in PatternsCodegen.jrag at line 604
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

    private boolean matchesType_compute(TypeDecl t) {  return getLhs().matchesType(t) || getRhs().matchesType(t);  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
