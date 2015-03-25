
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;


public class TargetPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public TargetPointcutExpr clone() throws CloneNotSupportedException {
        TargetPointcutExpr node = (TargetPointcutExpr)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TargetPointcutExpr copy() {
      try {
          TargetPointcutExpr node = (TargetPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TargetPointcutExpr fullCopy() {
        TargetPointcutExpr res = (TargetPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Declare.jrag at line 86

  public void collectNonStaticPointcuts(HashSet set) {
    set.add("target()");
    super.collectNonStaticPointcuts(set);
  }

    // Declared in PointcutsCodegen.jrag at line 177


    public Pointcut pointcut() {
        return getPattern().targetPointcut();
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 88

    public TargetPointcutExpr() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 88
    public TargetPointcutExpr(BindingPattern p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 88
    public void setPattern(BindingPattern node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public BindingPattern getPattern() {
        return (BindingPattern)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public BindingPattern getPatternNoTransform() {
        return (BindingPattern)getChildNoTransform(0);
    }

    // Declared in Pointcuts.jrag at line 569
 @SuppressWarnings({"unchecked", "cast"})     public int binds(String var) {
        int binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private int binds_compute(String var) {  return getPattern().binds(var) ? 1 : 0;  }

    // Declared in Pointcuts.jrag at line 168
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getPatternNoTransform()) {
            return lookupPointcutVariable(name);
        }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in Patterns.jrag at line 158
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getPatternNoTransform()) {
            return NameType.AMBIGUOUS_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
