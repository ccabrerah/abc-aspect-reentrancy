
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;



public class SymbolAccess extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public SymbolAccess clone() throws CloneNotSupportedException {
        SymbolAccess node = (SymbolAccess)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SymbolAccess copy() {
      try {
          SymbolAccess node = (SymbolAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SymbolAccess fullCopy() {
        SymbolAccess res = (SymbolAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in tm.ast at line 3
    // Declared in tm.ast line 19

    public SymbolAccess() {
        super();


    }

    // Declared in tm.ast at line 10


    // Declared in tm.ast line 19
    public SymbolAccess(String p0) {
        setID(p0);
    }

    // Declared in tm.ast at line 15


    // Declared in tm.ast line 19
    public SymbolAccess(beaver.Symbol p0) {
        setID(p0);
    }

    // Declared in tm.ast at line 19


  protected int numChildren() {
    return 0;
  }

    // Declared in tm.ast at line 22

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 19
    private String tokenString_ID;

    // Declared in tm.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in tm.ast at line 6

    public int IDstart;

    // Declared in tm.ast at line 7

    public int IDend;

    // Declared in tm.ast at line 8

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in tm.ast at line 15

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    // Declared in SymbolNameAnalysis.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
