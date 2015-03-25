
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



public abstract class NamePattern extends Pattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public NamePattern clone() throws CloneNotSupportedException {
        NamePattern node = (NamePattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 105

    public NamePattern() {
        super();


    }

    // Declared in AspectJ.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in Pointcuts.jrag at line 623
 @SuppressWarnings({"unchecked", "cast"})     public boolean refersToInterface() {
        boolean refersToInterface_value = refersToInterface_compute();
        return refersToInterface_value;
    }

    private boolean refersToInterface_compute() {  return false;  }

    // Declared in Pointcuts.jrag at line 626
 @SuppressWarnings({"unchecked", "cast"})     public boolean explicitInterface() {
        boolean explicitInterface_value = explicitInterface_compute();
        return explicitInterface_value;
    }

    private boolean explicitInterface_compute() {  return false;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
