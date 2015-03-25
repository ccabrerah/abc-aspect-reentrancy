
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;



public class LevelPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public LevelPointcutExpr clone() throws CloneNotSupportedException {
        LevelPointcutExpr node = (LevelPointcutExpr)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LevelPointcutExpr copy() {
      try {
          LevelPointcutExpr node = (LevelPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LevelPointcutExpr fullCopy() {
        LevelPointcutExpr res = (LevelPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PointcutCodegen.jrag at line 11


    public Pointcut pointcut() {
        return new LevelPointcut( getlevel(), pos() );
    }

    // Declared in reentrant.ast at line 3
    // Declared in reentrant.ast line 5

    public LevelPointcutExpr() {
        super();


    }

    // Declared in reentrant.ast at line 10


    // Declared in reentrant.ast line 5
    public LevelPointcutExpr(int p0) {
        setlevel(p0);
    }

    // Declared in reentrant.ast at line 14


  protected int numChildren() {
    return 0;
  }

    // Declared in reentrant.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in reentrant.ast at line 2
    // Declared in reentrant.ast line 5
    private int tokenint_level;

    // Declared in reentrant.ast at line 3

    public void setlevel(int value) {
        tokenint_level = value;
    }

    // Declared in reentrant.ast at line 6

    public int getlevel() {
        return tokenint_level;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
