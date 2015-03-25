
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;


public class RegexStar extends Regex implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexStar clone() throws CloneNotSupportedException {
        RegexStar node = (RegexStar)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexStar copy() {
      try {
          RegexStar node = (RegexStar)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public RegexStar fullCopy() {
        RegexStar res = (RegexStar)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in MakeStateMachine.jrag at line 59


    public void makeSM(StateMachine sm, SMNode start,
                                 SMNode finish, boolean own_start)
    {
        SMNode loop_node;
        if (own_start) {
            loop_node = start;
        } else {
            loop_node = (SMNode) sm.newState();
            sm.newTransition(start, loop_node, null);
        }
        getRegex().makeSM(sm, loop_node, loop_node, false);
        sm.newTransition(loop_node, finish, null);
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 25

    public RegexStar() {
        super();


    }

    // Declared in tm.ast at line 10


    // Declared in tm.ast line 25
    public RegexStar(Regex p0) {
        setChild(p0, 0);
    }

    // Declared in tm.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in tm.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 25
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

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
