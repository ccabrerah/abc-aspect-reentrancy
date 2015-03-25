
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;



public class FrequentSymbols extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public FrequentSymbols clone() throws CloneNotSupportedException {
        FrequentSymbols node = (FrequentSymbols)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FrequentSymbols copy() {
      try {
          FrequentSymbols node = (FrequentSymbols)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FrequentSymbols fullCopy() {
        FrequentSymbols res = (FrequentSymbols)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in tm.ast at line 3
    // Declared in tm.ast line 17

    public FrequentSymbols() {
        super();

        setChild(new List(), 0);
        is$Final(true);

    }

    // Declared in tm.ast at line 12


    // Declared in tm.ast line 17
    public FrequentSymbols(List<SymbolAccess> p0) {
        setChild(p0, 0);
        is$Final(true);
    }

    // Declared in tm.ast at line 17


  protected int numChildren() {
    return 1;
  }

    // Declared in tm.ast at line 20

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 17
    public void setSymbolAccessList(List<SymbolAccess> list) {
        setChild(list, 0);
    }

    // Declared in tm.ast at line 6


    private int getNumSymbolAccess = 0;

    // Declared in tm.ast at line 7

    public int getNumSymbolAccess() {
        return getSymbolAccessList().getNumChild();
    }

    // Declared in tm.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public SymbolAccess getSymbolAccess(int i) {
        return (SymbolAccess)getSymbolAccessList().getChild(i);
    }

    // Declared in tm.ast at line 15


    public void addSymbolAccess(SymbolAccess node) {
        List<SymbolAccess> list = getSymbolAccessList();
        list.addChild(node);
    }

    // Declared in tm.ast at line 20


    public void setSymbolAccess(SymbolAccess node, int i) {
        List<SymbolAccess> list = getSymbolAccessList();
        list.setChild(node, i);
    }

    // Declared in tm.ast at line 24

    public List<SymbolAccess> getSymbolAccesss() {
        return getSymbolAccessList();
    }

    // Declared in tm.ast at line 27

    public List<SymbolAccess> getSymbolAccesssNoTransform() {
        return getSymbolAccessListNoTransform();
    }

    // Declared in tm.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<SymbolAccess> getSymbolAccessList() {
        return (List<SymbolAccess>)getChild(0);
    }

    // Declared in tm.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<SymbolAccess> getSymbolAccessListNoTransform() {
        return (List<SymbolAccess>)getChildNoTransform(0);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
