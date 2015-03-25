
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;


public class RegexSymbol extends Regex implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexSymbol clone() throws CloneNotSupportedException {
        RegexSymbol node = (RegexSymbol)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexSymbol copy() {
      try {
          RegexSymbol node = (RegexSymbol)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexSymbol fullCopy() {
        RegexSymbol res = (RegexSymbol)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in MakeStateMachine.jrag at line 53


    public void makeSM(StateMachine sm, SMNode start,
                                   SMNode finish, boolean own_start)
    {
        sm.newTransition(start, finish, getSymbolAccess().name());
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 24

    public RegexSymbol() {
        super();


    }

    // Declared in tm.ast at line 10


    // Declared in tm.ast line 24
    public RegexSymbol(SymbolAccess p0) {
        setChild(p0, 0);
    }

    // Declared in tm.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in tm.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 24
    public void setSymbolAccess(SymbolAccess node) {
        setChild(node, 0);
    }

    // Declared in tm.ast at line 5

    public SymbolAccess getSymbolAccess() {
        return (SymbolAccess)getChild(0);
    }

    // Declared in tm.ast at line 9


    public SymbolAccess getSymbolAccessNoTransform() {
        return (SymbolAccess)getChildNoTransform(0);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
