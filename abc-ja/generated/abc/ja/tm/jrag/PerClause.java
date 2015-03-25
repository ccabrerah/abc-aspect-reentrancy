
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



public abstract class PerClause extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public PerClause clone() throws CloneNotSupportedException {
        PerClause node = (PerClause)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in AspectJCodegen.jrag at line 234


  public abstract Per aspectInfo();

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 23

    public PerClause() {
        super();


    }

    // Declared in AspectJ.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJNameAnalysis.jrag at line 78
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPerObject() {
        boolean isPerObject_value = isPerObject_compute();
        return isPerObject_value;
    }

    private boolean isPerObject_compute() {  return false;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
