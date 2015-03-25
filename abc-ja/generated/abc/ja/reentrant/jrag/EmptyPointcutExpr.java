
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



public class EmptyPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public EmptyPointcutExpr clone() throws CloneNotSupportedException {
        EmptyPointcutExpr node = (EmptyPointcutExpr)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public EmptyPointcutExpr copy() {
      try {
          EmptyPointcutExpr node = (EmptyPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public EmptyPointcutExpr fullCopy() {
        EmptyPointcutExpr res = (EmptyPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PointcutsCodegen.jrag at line 84


    public Pointcut pointcut() {
        return new EmptyPointcut(pos());
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 93

    public EmptyPointcutExpr() {
        super();


    }

    // Declared in AspectJ.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in Pointcuts.jrag at line 72
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEmpty() {
        boolean isEmpty_value = isEmpty_compute();
        return isEmpty_value;
    }

    private boolean isEmpty_compute() {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
