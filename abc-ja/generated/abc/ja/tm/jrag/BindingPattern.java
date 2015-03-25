
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;

 
// -----------------------------------------------------------


public abstract class BindingPattern extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public BindingPattern clone() throws CloneNotSupportedException {
        BindingPattern node = (BindingPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 134

    public BindingPattern() {
        super();


    }

    // Declared in AspectJ.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in PointcutsCodegen.jrag at line 204
 @SuppressWarnings({"unchecked", "cast"})     public abstract ArgPattern argPattern();
    // Declared in PointcutsCodegen.jrag at line 218
 @SuppressWarnings({"unchecked", "cast"})     public abstract Pointcut targetPointcut();
    // Declared in PointcutsCodegen.jrag at line 235
 @SuppressWarnings({"unchecked", "cast"})     public abstract Pointcut thisPointcut();
    // Declared in Pointcuts.jrag at line 592
 @SuppressWarnings({"unchecked", "cast"})     public boolean binds(String var) {
        boolean binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private boolean binds_compute(String var) {  return false;  }

    // Declared in Pointcuts.jrag at line 607
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDotDot() {
        boolean isDotDot_value = isDotDot_compute();
        return isDotDot_value;
    }

    private boolean isDotDot_compute() {  return false;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
