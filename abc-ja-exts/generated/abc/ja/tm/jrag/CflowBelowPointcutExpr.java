
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class CflowBelowPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        checkCallGraph_TypeDecl_values = null;
        checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
        isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
        isCircular_ParameterDeclaration_values = null;
        isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
        isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
    }
     @SuppressWarnings({"unchecked", "cast"})  public CflowBelowPointcutExpr clone() throws CloneNotSupportedException {
        CflowBelowPointcutExpr node = (CflowBelowPointcutExpr)super.clone();
        node.checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        node.checkCallGraph_TypeDecl_values = null;
        node.checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        node.checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
        node.isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
        node.isCircular_ParameterDeclaration_values = null;
        node.isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
        node.isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CflowBelowPointcutExpr copy() {
      try {
          CflowBelowPointcutExpr node = (CflowBelowPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CflowBelowPointcutExpr fullCopy() {
        CflowBelowPointcutExpr res = (CflowBelowPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Declare.jrag at line 74

  public void collectNonStaticPointcuts(HashSet set) {
    set.add("cflowbelow()");
    super.collectNonStaticPointcuts(set);
  }

    // Declared in PointcutsCodegen.jrag at line 80


    public Pointcut pointcut() {
        return new CflowBelow(getPointcut().pointcut(), pos());
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 83

    public CflowBelowPointcutExpr() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 83
    public CflowBelowPointcutExpr(PointcutExpr p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 83
    public void setPointcut(PointcutExpr node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public PointcutExpr getPointcut() {
        return (PointcutExpr)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


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

    // Declared in Pointcuts.jrag at line 565
 @SuppressWarnings({"unchecked", "cast"})     public int binds(String var) {
        int binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private int binds_compute(String var) {  return getPointcut().binds(var);  }

    protected java.util.Map isCircular_ParameterDeclaration_visited;
    protected java.util.Set isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
    protected java.util.Set isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
    protected java.util.Map isCircular_ParameterDeclaration_values = new java.util.HashMap(4);
 @SuppressWarnings({"unchecked", "cast"})     public boolean isCircular(ParameterDeclaration decl) {
        Object _parameters = decl;
if(isCircular_ParameterDeclaration_visited == null) isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
if(isCircular_ParameterDeclaration_values == null) isCircular_ParameterDeclaration_values = new java.util.HashMap(4);
        if(isCircular_ParameterDeclaration_computed.contains(_parameters))
            return ((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue();
        if (!isCircular_ParameterDeclaration_initialized.contains(_parameters)) {
            isCircular_ParameterDeclaration_initialized.add(_parameters);
            isCircular_ParameterDeclaration_values.put(_parameters, Boolean.valueOf(true));
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
            CIRCLE_INDEX = 1;
            boolean new_isCircular_ParameterDeclaration_value;
            do {
                isCircular_ParameterDeclaration_visited.put(_parameters, new Integer(CIRCLE_INDEX));
                CHANGE = false;
                new_isCircular_ParameterDeclaration_value = isCircular_compute(decl);
                if (new_isCircular_ParameterDeclaration_value!=((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue())
                    CHANGE = true;
                isCircular_ParameterDeclaration_values.put(_parameters, Boolean.valueOf(new_isCircular_ParameterDeclaration_value));
                CIRCLE_INDEX++;
            } while (CHANGE);
            if(isFinal && num == boundariesCrossed)
{
            isCircular_ParameterDeclaration_computed.add(_parameters);
            }
            else {
            RESET_CYCLE = true;
            isCircular_compute(decl);
            RESET_CYCLE = false;
            isCircular_ParameterDeclaration_computed.remove(_parameters);
            isCircular_ParameterDeclaration_initialized.remove(_parameters);
            }
            IN_CIRCLE = false; 
            return new_isCircular_ParameterDeclaration_value;
        }
        if(!new Integer(CIRCLE_INDEX).equals(isCircular_ParameterDeclaration_visited.get(_parameters))) {
            isCircular_ParameterDeclaration_visited.put(_parameters, new Integer(CIRCLE_INDEX));
            if (RESET_CYCLE) {
                isCircular_ParameterDeclaration_computed.remove(_parameters);
                isCircular_ParameterDeclaration_initialized.remove(_parameters);
                return ((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue();
            }
            boolean new_isCircular_ParameterDeclaration_value = isCircular_compute(decl);
            if (new_isCircular_ParameterDeclaration_value!=((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue())
                CHANGE = true;
            isCircular_ParameterDeclaration_values.put(_parameters, Boolean.valueOf(new_isCircular_ParameterDeclaration_value));
            return new_isCircular_ParameterDeclaration_value;
        }
        return ((Boolean)isCircular_ParameterDeclaration_values.get(_parameters)).booleanValue();
    }

    private boolean isCircular_compute(ParameterDeclaration decl) {  return getPointcut().isCircular(decl);  }

    // Declared in Pointcuts.jrag at line 126
    public boolean Define_boolean_bindsInCurrentCflow(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutNoTransform()) {
            return getPointcut().binds(name) > 0;
        }
        return getParent().Define_boolean_bindsInCurrentCflow(this, caller, name);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
