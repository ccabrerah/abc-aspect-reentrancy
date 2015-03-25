
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;


public class BeforeSymbol extends SymbolKind implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public BeforeSymbol clone() throws CloneNotSupportedException {
        BeforeSymbol node = (BeforeSymbol)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public BeforeSymbol copy() {
      try {
          BeforeSymbol node = (BeforeSymbol)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public BeforeSymbol fullCopy() {
        BeforeSymbol res = (BeforeSymbol)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in tm.ast at line 3
    // Declared in tm.ast line 11

    public BeforeSymbol() {
        super();


    }

    // Declared in tm.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in tm.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in SymbolCodegen.jrag at line 36
 @SuppressWarnings({"unchecked", "cast"})     public String kind() {
        String kind_value = kind_compute();
        return kind_value;
    }

    private String kind_compute() {  return "before";  }

    // Declared in SymbolCodegen.jrag at line 82
 @SuppressWarnings({"unchecked", "cast"})     public AbstractAdviceSpec adviceSpec() {
        AbstractAdviceSpec adviceSpec_value = adviceSpec_compute();
        return adviceSpec_value;
    }

    private AbstractAdviceSpec adviceSpec_compute() {  return new BeforeAdvice(pos());  }

    // Declared in TMPointcutCodegen.jrag at line 37
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesBefore() {
        boolean matchesBefore_value = matchesBefore_compute();
        return matchesBefore_value;
    }

    private boolean matchesBefore_compute() {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
