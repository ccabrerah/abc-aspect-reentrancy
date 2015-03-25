
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;


public class StarBindingPattern extends BindingPattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public StarBindingPattern clone() throws CloneNotSupportedException {
        StarBindingPattern node = (StarBindingPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public StarBindingPattern copy() {
      try {
          StarBindingPattern node = (StarBindingPattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public StarBindingPattern fullCopy() {
        StarBindingPattern res = (StarBindingPattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 135

    public StarBindingPattern() {
        super();


    }

    // Declared in AspectJ.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in PointcutsCodegen.jrag at line 205
 @SuppressWarnings({"unchecked", "cast"})     public ArgPattern argPattern() {
        ArgPattern argPattern_value = argPattern_compute();
        return argPattern_value;
    }

    private ArgPattern argPattern_compute() {  return new ArgAny(pos());  }

    // Declared in PointcutsCodegen.jrag at line 219
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut targetPointcut() {
        Pointcut targetPointcut_value = targetPointcut_compute();
        return targetPointcut_value;
    }

    private Pointcut targetPointcut_compute() {  return new TargetAny(pos());  }

    // Declared in PointcutsCodegen.jrag at line 236
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut thisPointcut() {
        Pointcut thisPointcut_value = thisPointcut_compute();
        return thisPointcut_value;
    }

    private Pointcut thisPointcut_compute() {  return new ThisAny(pos());  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
