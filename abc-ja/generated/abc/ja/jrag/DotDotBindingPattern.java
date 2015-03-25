
package abc.ja.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class DotDotBindingPattern extends BindingPattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public DotDotBindingPattern clone() throws CloneNotSupportedException {
        DotDotBindingPattern node = (DotDotBindingPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DotDotBindingPattern copy() {
      try {
          DotDotBindingPattern node = (DotDotBindingPattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DotDotBindingPattern fullCopy() {
        DotDotBindingPattern res = (DotDotBindingPattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 136

    public DotDotBindingPattern() {
        super();


    }

    // Declared in AspectJ.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in Pointcuts.jrag at line 608
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDotDot() {
        boolean isDotDot_value = isDotDot_compute();
        return isDotDot_value;
    }

    private boolean isDotDot_compute() {  return true;  }

    // Declared in PointcutsCodegen.jrag at line 206
 @SuppressWarnings({"unchecked", "cast"})     public ArgPattern argPattern() {
        ArgPattern argPattern_value = argPattern_compute();
        return argPattern_value;
    }

    private ArgPattern argPattern_compute() {  return new ArgFill(pos());  }

    // Declared in PointcutsCodegen.jrag at line 220
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut targetPointcut() {
        Pointcut targetPointcut_value = targetPointcut_compute();
        return targetPointcut_value;
    }

    private Pointcut targetPointcut_compute() {
        throw new InternalCompilerError(
            "trying to create an aspectinfo pointcut for 'target(..)'");
    }

    // Declared in PointcutsCodegen.jrag at line 237
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut thisPointcut() {
        Pointcut thisPointcut_value = thisPointcut_compute();
        return thisPointcut_value;
    }

    private Pointcut thisPointcut_compute() {
        throw new InternalCompilerError(
            "trying to create an aspectinfo pointcut for 'this(..)'");
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
