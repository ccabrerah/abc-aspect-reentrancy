
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;



public abstract class SymbolKind extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public SymbolKind clone() throws CloneNotSupportedException {
        SymbolKind node = (SymbolKind)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in TMInfo.jrag at line 66


    void addAroundFormals(java.util.List<Formal> formals) { }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 10

    public SymbolKind() {
        super();


    }

    // Declared in tm.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in tm.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in SymbolCodegen.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public abstract String kind();
    // Declared in SymbolCodegen.jrag at line 81
 @SuppressWarnings({"unchecked", "cast"})     public abstract AbstractAdviceSpec adviceSpec();
    // Declared in TMCodegen.jrag at line 246
 @SuppressWarnings({"unchecked", "cast"})     public AroundSymbol aroundSymbol() {
        AroundSymbol aroundSymbol_value = aroundSymbol_compute();
        return aroundSymbol_value;
    }

    private AroundSymbol aroundSymbol_compute() {  return null;  }

    // Declared in TMPointcutCodegen.jrag at line 36
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesBefore() {
        boolean matchesBefore_value = matchesBefore_compute();
        return matchesBefore_value;
    }

    private boolean matchesBefore_compute() {  return false;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
