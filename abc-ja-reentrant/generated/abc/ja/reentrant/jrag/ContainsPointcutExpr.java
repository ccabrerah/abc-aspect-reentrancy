
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;



public class ContainsPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ContainsPointcutExpr clone() throws CloneNotSupportedException {
        ContainsPointcutExpr node = (ContainsPointcutExpr)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ContainsPointcutExpr copy() {
      try {
          ContainsPointcutExpr node = (ContainsPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ContainsPointcutExpr fullCopy() {
        ContainsPointcutExpr res = (ContainsPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Pointcuts.jrag at line 34


  // The argument of the contains(..) pointcut is another pointcut:
  // the pointcut contains(pc) matches all joinpoint shadows that
  // contain another joinpoint shadow matching pc. Therefore, pc
  // must be a static pointcut.
  public void typeCheck()
  {
    super.typeCheck();
    HashSet dynamic = new HashSet();
    collectNonStaticPointcuts(dynamic);
    if (!dynamic.isEmpty()) {
      StringBuffer msg = new StringBuffer();
      msg.append("The argument of the contains() pointcut " +
                 "must be a static pointcut - the following pointcuts " +
                 "are not static: ");
      Iterator i = dynamic.iterator();
      while (i.hasNext())
        msg.append(i.next() + " ");
      error(msg.toString());
    }
  }

    // Declared in Pointcuts.jrag at line 88


  public Pointcut pointcut() {
    return new Contains(pos(), getPointcutExpr().pointcut());
  }

    // Declared in eaj.ast at line 3
    // Declared in eaj.ast line 14

    public ContainsPointcutExpr() {
        super();


    }

    // Declared in eaj.ast at line 10


    // Declared in eaj.ast line 14
    public ContainsPointcutExpr(PointcutExpr p0) {
        setChild(p0, 0);
    }

    // Declared in eaj.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in eaj.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in eaj.ast at line 2
    // Declared in eaj.ast line 14
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 0);
    }

    // Declared in eaj.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(0);
    }

    // Declared in eaj.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(0);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
