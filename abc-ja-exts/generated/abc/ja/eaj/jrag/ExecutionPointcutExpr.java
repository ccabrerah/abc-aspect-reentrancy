
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;


public class ExecutionPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ExecutionPointcutExpr clone() throws CloneNotSupportedException {
        ExecutionPointcutExpr node = (ExecutionPointcutExpr)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ExecutionPointcutExpr copy() {
      try {
          ExecutionPointcutExpr node = (ExecutionPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ExecutionPointcutExpr fullCopy() {
        ExecutionPointcutExpr res = (ExecutionPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Pointcuts.jrag at line 611



    public void typeCheck()
    {
        super.typeCheck();
        if (getPattern().refersToInterfaceConstructor())
            warning("Interface constructor execution is not a join point" +
                " (consider (..)+ to capture constructors of implementors)");
    }

    // Declared in PointcutsCodegen.jrag at line 88


    public Pointcut pointcut() {
        Pointcut withincode;
        if (getPattern().isMethodPattern())
            withincode = new WithinMethod(getPattern().methodPattern(), pos());
        else
            withincode = new
                WithinConstructor(getPattern().constructorPattern(), pos());

        return AndPointcut.construct(withincode, new Execution(pos()), pos());
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 68

    public ExecutionPointcutExpr() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 68
    public ExecutionPointcutExpr(MemberPattern p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 68
    public void setPattern(MemberPattern node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public MemberPattern getPattern() {
        return (MemberPattern)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public MemberPattern getPatternNoTransform() {
        return (MemberPattern)getChildNoTransform(0);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
