
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class PerThis extends PerClause implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public PerThis clone() throws CloneNotSupportedException {
        PerThis node = (PerThis)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PerThis copy() {
      try {
          PerThis node = (PerThis)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PerThis fullCopy() {
        PerThis res = (PerThis)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AspectJCodegen.jrag at line 240

  public Per aspectInfo() {
    return new abc.weaving.aspectinfo.PerThis(
      getPointcutExpr().pointcut(), pos()
    );
  }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 25

    public PerThis() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 25
    public PerThis(PointcutExpr p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 25
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(0);
    }

    // Declared in AspectJNameAnalysis.jrag at line 80
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPerObject() {
        boolean isPerObject_value = isPerObject_compute();
        return isPerObject_value;
    }

    private boolean isPerObject_compute() {  return true;  }

    // Declared in Pointcuts.jrag at line 118
    public boolean Define_boolean_bindsInCurrentCflow(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()) {
            return getPointcutExpr().binds(name) > 0;
        }
        return getParent().Define_boolean_bindsInCurrentCflow(this, caller, name);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
