
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class RegexCount extends Regex implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexCount clone() throws CloneNotSupportedException {
        RegexCount node = (RegexCount)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexCount copy() {
      try {
          RegexCount node = (RegexCount)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexCount fullCopy() {
        RegexCount res = (RegexCount)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in MakeStateMachine.jrag at line 87


    public void makeSM(StateMachine sm, SMNode start,
                                  SMNode finish, boolean own_start)
    {
        int min = getMin().constant().intValue();
        int max = getMax().constant().intValue();
        if (min == 0)
            sm.newTransition(start, finish, null);
        SMNode middle = start;
        // max is always >= 1
        for (int i = 1; i < max; i++) {
            SMNode s = (SMNode) sm.newState();
            getRegex().makeSM(sm, middle, s, false);
            if (i >= min)
                sm.newTransition(s, finish, null);
            middle = s;
        }
        getRegex().makeSM(sm, middle, finish, false);
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 27

    public RegexCount() {
        super();


    }

    // Declared in tm.ast at line 10


    // Declared in tm.ast line 27
    public RegexCount(Regex p0, IntegerLiteral p1, IntegerLiteral p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
    }

    // Declared in tm.ast at line 16


  protected int numChildren() {
    return 3;
  }

    // Declared in tm.ast at line 19

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 27
    public void setRegex(Regex node) {
        setChild(node, 0);
    }

    // Declared in tm.ast at line 5

    public Regex getRegex() {
        return (Regex)getChild(0);
    }

    // Declared in tm.ast at line 9


    public Regex getRegexNoTransform() {
        return (Regex)getChildNoTransform(0);
    }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 27
    public void setMin(IntegerLiteral node) {
        setChild(node, 1);
    }

    // Declared in tm.ast at line 5

    public IntegerLiteral getMin() {
        return (IntegerLiteral)getChild(1);
    }

    // Declared in tm.ast at line 9


    public IntegerLiteral getMinNoTransform() {
        return (IntegerLiteral)getChildNoTransform(1);
    }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 27
    public void setMax(IntegerLiteral node) {
        setChild(node, 2);
    }

    // Declared in tm.ast at line 5

    public IntegerLiteral getMax() {
        return (IntegerLiteral)getChild(2);
    }

    // Declared in tm.ast at line 9


    public IntegerLiteral getMaxNoTransform() {
        return (IntegerLiteral)getChildNoTransform(2);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
