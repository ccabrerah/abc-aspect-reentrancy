
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class DotDotNamePattern extends NamePattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
        matchesType_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DotDotNamePattern clone() throws CloneNotSupportedException {
        DotDotNamePattern node = (DotDotNamePattern)super.clone();
        node.matchesType_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DotDotNamePattern copy() {
      try {
          DotDotNamePattern node = (DotDotNamePattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DotDotNamePattern fullCopy() {
        DotDotNamePattern res = (DotDotNamePattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Patterns.jrag at line 203

    public String toString() {
    	return getLhs() + ".." + getRhs();
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 108

    public DotDotNamePattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 108
    public DotDotNamePattern(NamePattern p0, SimpleNamePattern p1) {
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
    // Declared in AspectJ.ast line 108
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
    // Declared in AspectJ.ast line 108
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

    // Declared in Patterns.jrag at line 26
 @SuppressWarnings({"unchecked", "cast"})     public boolean containsWildcard() {
        boolean containsWildcard_value = containsWildcard_compute();
        return containsWildcard_value;
    }

    private boolean containsWildcard_compute() {  return true;  }

    // Declared in Patterns.jrag at line 115
 @SuppressWarnings({"unchecked", "cast"})     public Pattern base() {
        Pattern base_value = base_compute();
        return base_value;
    }

    private Pattern base_compute() {  return getLhs();  }

    // Declared in PatternsCodegen.jrag at line 611
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
        if (!getRhs().matchesType(t))
            return false;
        String fullname = t.fullName();

        int i = fullname.lastIndexOf(".");
        while (i >= 0) {
            if (getLhs().matchesName(fullname.substring(0,i)))
                return true;
            i = fullname.lastIndexOf(".", i);
        }
        return false;
    }

    // Declared in Patterns.jrag at line 58
    public boolean Define_boolean_denotesMember(ASTNode caller, ASTNode child) {
        if(caller == getRhsNoTransform()) {
            return false;
        }
        if(caller == getLhsNoTransform()) {
            return false;
        }
        return getParent().Define_boolean_denotesMember(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
