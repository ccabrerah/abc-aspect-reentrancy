
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class RegexSequence extends Regex implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexSequence clone() throws CloneNotSupportedException {
        RegexSequence node = (RegexSequence)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexSequence copy() {
      try {
          RegexSequence node = (RegexSequence)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexSequence fullCopy() {
        RegexSequence res = (RegexSequence)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in MakeStateMachine.jrag at line 45


    public void makeSM(StateMachine sm, SMNode start,
                                     SMNode finish, boolean own_start)
    {
        SMNode middle = (SMNode) sm.newState();
        getLhs().makeSM(sm, start, middle, own_start);
        getRhs().makeSM(sm, middle, finish, true);
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 23

    public RegexSequence() {
        super();


    }

    // Declared in tm.ast at line 10


    // Declared in tm.ast line 23
    public RegexSequence(Regex p0, Regex p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in tm.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in tm.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 23
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
    // Declared in tm.ast line 23
    public void setRhs(Regex node) {
        setChild(node, 1);
    }

    // Declared in tm.ast at line 5

    public Regex getRhs() {
        return (Regex)getChild(1);
    }

    // Declared in tm.ast at line 9


    public Regex getRhsNoTransform() {
        return (Regex)getChildNoTransform(1);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
