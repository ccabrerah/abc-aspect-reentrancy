
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public abstract class BinaryPattern extends Pattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public BinaryPattern clone() throws CloneNotSupportedException {
        BinaryPattern node = (BinaryPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 99

    public BinaryPattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 99
    public BinaryPattern(Pattern p0, Pattern p1) {
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

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
