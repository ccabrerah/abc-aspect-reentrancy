
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;

public class LCflowPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        checkCallGraph_TypeDecl_values = null;
        checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
    }
     @SuppressWarnings({"unchecked", "cast"})  public LCflowPointcutExpr clone() throws CloneNotSupportedException {
        LCflowPointcutExpr node = (LCflowPointcutExpr)super.clone();
        node.checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        node.checkCallGraph_TypeDecl_values = null;
        node.checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        node.checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LCflowPointcutExpr copy() {
      try {
          LCflowPointcutExpr node = (LCflowPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LCflowPointcutExpr fullCopy() {
        LCflowPointcutExpr res = (LCflowPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Declare.jrag at line 2

  public void collectNonStaticPointcuts(HashSet set) {
    set.add("lcflow()");
    super.collectNonStaticPointcuts(set);
  }

    // Declared in PointcutCodegen.jrag at line 3

    public Pointcut pointcut() {
        return new LCflow(getPointcut().pointcut(), pos());
    }

    // Declared in reentrant.ast at line 3
    // Declared in reentrant.ast line 1

    public LCflowPointcutExpr() {
        super();


    }

    // Declared in reentrant.ast at line 10


    // Declared in reentrant.ast line 1
    public LCflowPointcutExpr(PointcutExpr p0) {
        setChild(p0, 0);
    }

    // Declared in reentrant.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in reentrant.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in reentrant.ast at line 2
    // Declared in reentrant.ast line 1
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

    protected java.util.Map checkCallGraph_TypeDecl_visited;
    protected java.util.Set checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
    protected java.util.Set checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
    protected java.util.Map checkCallGraph_TypeDecl_values = new java.util.HashMap(4);
 @SuppressWarnings({"unchecked", "cast"})     public int checkCallGraph(TypeDecl context) {
        Object _parameters = context;
if(checkCallGraph_TypeDecl_visited == null) checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
if(checkCallGraph_TypeDecl_values == null) checkCallGraph_TypeDecl_values = new java.util.HashMap(4);
        if(checkCallGraph_TypeDecl_computed.contains(_parameters))
            return ((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue();
        if (!checkCallGraph_TypeDecl_initialized.contains(_parameters)) {
            checkCallGraph_TypeDecl_initialized.add(_parameters);
            checkCallGraph_TypeDecl_values.put(_parameters, new Integer(2));
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
            CIRCLE_INDEX = 1;
            int new_checkCallGraph_TypeDecl_value;
            do {
                checkCallGraph_TypeDecl_visited.put(_parameters, new Integer(CIRCLE_INDEX));
                CHANGE = false;
                new_checkCallGraph_TypeDecl_value = checkCallGraph_compute(context);
                if (new_checkCallGraph_TypeDecl_value!=((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue())
                    CHANGE = true;
                checkCallGraph_TypeDecl_values.put(_parameters, new Integer(new_checkCallGraph_TypeDecl_value));
                CIRCLE_INDEX++;
            } while (CHANGE);
            if(isFinal && num == boundariesCrossed)
{
            checkCallGraph_TypeDecl_computed.add(_parameters);
            }
            else {
            RESET_CYCLE = true;
            checkCallGraph_compute(context);
            RESET_CYCLE = false;
            checkCallGraph_TypeDecl_computed.remove(_parameters);
            checkCallGraph_TypeDecl_initialized.remove(_parameters);
            }
            IN_CIRCLE = false; 
            return new_checkCallGraph_TypeDecl_value;
        }
        if(!new Integer(CIRCLE_INDEX).equals(checkCallGraph_TypeDecl_visited.get(_parameters))) {
            checkCallGraph_TypeDecl_visited.put(_parameters, new Integer(CIRCLE_INDEX));
            if (RESET_CYCLE) {
                checkCallGraph_TypeDecl_computed.remove(_parameters);
                checkCallGraph_TypeDecl_initialized.remove(_parameters);
                return ((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue();
            }
            int new_checkCallGraph_TypeDecl_value = checkCallGraph_compute(context);
            if (new_checkCallGraph_TypeDecl_value!=((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue())
                CHANGE = true;
            checkCallGraph_TypeDecl_values.put(_parameters, new Integer(new_checkCallGraph_TypeDecl_value));
            return new_checkCallGraph_TypeDecl_value;
        }
        return ((Integer)checkCallGraph_TypeDecl_values.get(_parameters)).intValue();
    }

    private int checkCallGraph_compute(TypeDecl context) {  return getPointcut().checkCallGraph(context);  }

    // Declared in Pointcuts.jrag at line 11
 @SuppressWarnings({"unchecked", "cast"})     public int binds(String var) {
        int binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private int binds_compute(String var) {  return getPointcut().binds(var);  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
