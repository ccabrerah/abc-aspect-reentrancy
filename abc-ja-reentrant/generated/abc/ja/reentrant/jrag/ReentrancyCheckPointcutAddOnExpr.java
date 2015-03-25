
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;



public class ReentrancyCheckPointcutAddOnExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ReentrancyCheckPointcutAddOnExpr clone() throws CloneNotSupportedException {
        ReentrancyCheckPointcutAddOnExpr node = (ReentrancyCheckPointcutAddOnExpr)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ReentrancyCheckPointcutAddOnExpr copy() {
      try {
          ReentrancyCheckPointcutAddOnExpr node = (ReentrancyCheckPointcutAddOnExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ReentrancyCheckPointcutAddOnExpr fullCopy() {
        ReentrancyCheckPointcutAddOnExpr res = (ReentrancyCheckPointcutAddOnExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PointcutCodegen.jrag at line 16



    public AspectDecl enclosingAspect = null;

    // Declared in PointcutCodegen.jrag at line 18


    public Pointcut pointcut() {
	//obtener aspectinfo del aspecto
	Aspect aspectInfoForAspect = globalAspectInfo().getAspect( this.enclosingAspect.abcClass() );

        return new ReentrancyCheckPointcutAddOn( aspectInfoForAspect, pos() );
    }

    // Declared in reentrant.ast at line 3
    // Declared in reentrant.ast line 7

    public ReentrancyCheckPointcutAddOnExpr() {
        super();


    }

    // Declared in reentrant.ast at line 10


    // Declared in reentrant.ast line 7
    public ReentrancyCheckPointcutAddOnExpr(PointcutExpr p0) {
        setChild(p0, 0);
    }

    // Declared in reentrant.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in reentrant.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in reentrant.ast at line 2
    // Declared in reentrant.ast line 7
    public void setPointcut(PointcutExpr node) {
        setChild(node, 0);
    }

    // Declared in reentrant.ast at line 5

    public PointcutExpr getPointcut() {
        return (PointcutExpr)getChild(0);
    }

    // Declared in reentrant.ast at line 9


    public PointcutExpr getPointcutNoTransform() {
        return (PointcutExpr)getChildNoTransform(0);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
