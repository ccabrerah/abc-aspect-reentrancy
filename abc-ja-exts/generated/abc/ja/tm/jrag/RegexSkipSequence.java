
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class RegexSkipSequence extends Regex implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexSkipSequence clone() throws CloneNotSupportedException {
        RegexSkipSequence node = (RegexSkipSequence)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexSkipSequence copy() {
      try {
          RegexSkipSequence node = (RegexSkipSequence)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexSkipSequence fullCopy() {
        RegexSkipSequence res = (RegexSkipSequence)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in MakeStateMachine.jrag at line 106


    public void makeSM(StateMachine sm, SMNode start,
                                         SMNode finish, boolean own_start)
    {
        SMNode middle = (SMNode) sm.newState();
        getLhs().makeSM(sm, start, middle, own_start);
        getRhs().makeSM(sm, middle, finish, false);

        for (int i = 0; i < numSymbols(); i++)
            sm.newTransition(middle, middle, symbol(i).name());
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 28

    public RegexSkipSequence() {
        super();

        setChild(new List(), 1);

    }

    // Declared in tm.ast at line 11


    // Declared in tm.ast line 28
    public RegexSkipSequence(Regex p0, List<SymbolAccess> p1, Regex p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
    }

    // Declared in tm.ast at line 17


  protected int numChildren() {
    return 3;
  }

    // Declared in tm.ast at line 20

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 28
    public void setLhs(Regex node) {
        setChild(node, 0);
    }

    // Declared in tm.ast at line 5

    public Regex getLhs() {
        return (Regex)getChild(0);
    }

    // Declared in tm.ast at line 9


    public Regex getLhsNoTransform() {
        return (Regex)getChildNoTransform(0);
    }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 28
    public void setForbiddenList(List<SymbolAccess> list) {
        setChild(list, 1);
    }

    // Declared in tm.ast at line 6


    private int getNumForbidden = 0;

    // Declared in tm.ast at line 7

    public int getNumForbidden() {
        return getForbiddenList().getNumChild();
    }

    // Declared in tm.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public SymbolAccess getForbidden(int i) {
        return (SymbolAccess)getForbiddenList().getChild(i);
    }

    // Declared in tm.ast at line 15


    public void addForbidden(SymbolAccess node) {
        List<SymbolAccess> list = getForbiddenList();
        list.addChild(node);
    }

    // Declared in tm.ast at line 20


    public void setForbidden(SymbolAccess node, int i) {
        List<SymbolAccess> list = getForbiddenList();
        list.setChild(node, i);
    }

    // Declared in tm.ast at line 24

    public List<SymbolAccess> getForbiddens() {
        return getForbiddenList();
    }

    // Declared in tm.ast at line 27

    public List<SymbolAccess> getForbiddensNoTransform() {
        return getForbiddenListNoTransform();
    }

    // Declared in tm.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<SymbolAccess> getForbiddenList() {
        return (List<SymbolAccess>)getChild(1);
    }

    // Declared in tm.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<SymbolAccess> getForbiddenListNoTransform() {
        return (List<SymbolAccess>)getChildNoTransform(1);
    }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 28
    public void setRhs(Regex node) {
        setChild(node, 2);
    }

    // Declared in tm.ast at line 5

    public Regex getRhs() {
        return (Regex)getChild(2);
    }

    // Declared in tm.ast at line 9


    public Regex getRhsNoTransform() {
        return (Regex)getChildNoTransform(2);
    }

    // Declared in MakeStateMachine.jrag at line 117
 @SuppressWarnings({"unchecked", "cast"})     public int numSymbols() {
        int numSymbols_value = getParent().Define_int_numSymbols(this, null);
        return numSymbols_value;
    }

    // Declared in MakeStateMachine.jrag at line 124
 @SuppressWarnings({"unchecked", "cast"})     public SymbolDecl symbol(int i) {
        SymbolDecl symbol_int_value = getParent().Define_SymbolDecl_symbol(this, null, i);
        return symbol_int_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
