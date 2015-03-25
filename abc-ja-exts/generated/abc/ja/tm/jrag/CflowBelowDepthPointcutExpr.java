
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class CflowBelowDepthPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
        isCircular_ParameterDeclaration_values = null;
        isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
        isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
    }
     @SuppressWarnings({"unchecked", "cast"})  public CflowBelowDepthPointcutExpr clone() throws CloneNotSupportedException {
        CflowBelowDepthPointcutExpr node = (CflowBelowDepthPointcutExpr)super.clone();
        node.isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
        node.isCircular_ParameterDeclaration_values = null;
        node.isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
        node.isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CflowBelowDepthPointcutExpr copy() {
      try {
          CflowBelowDepthPointcutExpr node = (CflowBelowDepthPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CflowBelowDepthPointcutExpr fullCopy() {
        CflowBelowDepthPointcutExpr res = (CflowBelowDepthPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Pointcuts.jrag at line 80


  public Pointcut pointcut() {
    return new CflowBelowDepth(
      getPointcutExpr().pointcut(),
      pos(),
      new Var(getVarAccess().name(), getVarAccess().pos())
    );
  }

    // Declared in eaj.ast at line 3
    // Declared in eaj.ast line 10

    public CflowBelowDepthPointcutExpr() {
        super();


    }

    // Declared in eaj.ast at line 10


    // Declared in eaj.ast line 10
    public CflowBelowDepthPointcutExpr(VarAccess p0, PointcutExpr p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in eaj.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in eaj.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in eaj.ast at line 2
    // Declared in eaj.ast line 10
    public void setVarAccess(VarAccess node) {
        setChild(node, 0);
    }

    // Declared in eaj.ast at line 5

    public VarAccess getVarAccess() {
        return (VarAccess)getChild(0);
    }

    // Declared in eaj.ast at line 9


    public VarAccess getVarAccessNoTransform() {
        return (VarAccess)getChildNoTransform(0);
    }

    // Declared in eaj.ast at line 2
    // Declared in eaj.ast line 10
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 1);
    }

    // Declared in eaj.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(1);
    }

    // Declared in eaj.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(1);
    }

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

    private boolean isCircular_compute(ParameterDeclaration decl) {  return getPointcutExpr().isCircular(decl);  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
