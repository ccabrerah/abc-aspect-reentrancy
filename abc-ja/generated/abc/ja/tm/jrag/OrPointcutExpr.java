
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class OrPointcutExpr extends BinaryPointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isStaticallyFalse_visited = 0;
    }
     @SuppressWarnings({"unchecked", "cast"})  public OrPointcutExpr clone() throws CloneNotSupportedException {
        OrPointcutExpr node = (OrPointcutExpr)super.clone();
        node.isStaticallyFalse_visited = 0;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public OrPointcutExpr copy() {
      try {
          OrPointcutExpr node = (OrPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public OrPointcutExpr fullCopy() {
        OrPointcutExpr res = (OrPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PointcutsCodegen.jrag at line 40


    public Pointcut pointcut() {
        return OrPointcut.construct(getLhs().pointcut(),
                                    getRhs().pointcut(), pos());
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 62

    public OrPointcutExpr() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 62
    public OrPointcutExpr(PointcutExpr p0, PointcutExpr p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in AspectJ.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in AspectJ.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 61
    public void setLhs(PointcutExpr node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public PointcutExpr getLhs() {
        return (PointcutExpr)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public PointcutExpr getLhsNoTransform() {
        return (PointcutExpr)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 61
    public void setRhs(PointcutExpr node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public PointcutExpr getRhs() {
        return (PointcutExpr)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public PointcutExpr getRhsNoTransform() {
        return (PointcutExpr)getChildNoTransform(1);
    }

    // Declared in Pointcuts.jrag at line 558
 @SuppressWarnings({"unchecked", "cast"})     public int binds(String var) {
        int binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private int binds_compute(String var) {
        int combination = getLhs().binds(var) * getRhs().binds(var);
        return (combination > 2) ? 2 : combination;
    }

    protected int isStaticallyFalse_visited;
    protected boolean isStaticallyFalse_computed = false;
    protected boolean isStaticallyFalse_initialized = false;
    protected boolean isStaticallyFalse_value;
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStaticallyFalse() {
        if(isStaticallyFalse_computed)
            return isStaticallyFalse_value;
        if (!isStaticallyFalse_initialized) {
            isStaticallyFalse_initialized = true;
            isStaticallyFalse_value = false;
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
            CIRCLE_INDEX = 1;
            do {
                isStaticallyFalse_visited = CIRCLE_INDEX;
                CHANGE = false;
                boolean new_isStaticallyFalse_value = isStaticallyFalse_compute();
                if (new_isStaticallyFalse_value!=isStaticallyFalse_value)
                    CHANGE = true;
                isStaticallyFalse_value = new_isStaticallyFalse_value; 
                CIRCLE_INDEX++;
            } while (CHANGE);
            if(isFinal && num == boundariesCrossed)
{
            isStaticallyFalse_computed = true;
            }
            else {
            RESET_CYCLE = true;
            isStaticallyFalse_compute();
            RESET_CYCLE = false;
              isStaticallyFalse_computed = false;
              isStaticallyFalse_initialized = false;
            }
            IN_CIRCLE = false; 
            return isStaticallyFalse_value;
        }
        if(isStaticallyFalse_visited != CIRCLE_INDEX) {
            isStaticallyFalse_visited = CIRCLE_INDEX;
            if (RESET_CYCLE) {
                isStaticallyFalse_computed = false;
                isStaticallyFalse_initialized = false;
                return isStaticallyFalse_value;
            }
            boolean new_isStaticallyFalse_value = isStaticallyFalse_compute();
            if (new_isStaticallyFalse_value!=isStaticallyFalse_value)
                CHANGE = true;
            isStaticallyFalse_value = new_isStaticallyFalse_value; 
            return isStaticallyFalse_value;
        }
        return isStaticallyFalse_value;
    }

    private boolean isStaticallyFalse_compute() {  return getLhs().isStaticallyFalse() && getRhs().isStaticallyFalse();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
