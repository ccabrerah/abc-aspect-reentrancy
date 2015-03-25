
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;



public abstract class PointcutExpr extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        checkCallGraph_TypeDecl_values = null;
        checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
        isStaticallyFalse_visited = 0;
        isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
        isCircular_ParameterDeclaration_values = null;
        isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
        isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
    }
     @SuppressWarnings({"unchecked", "cast"})  public PointcutExpr clone() throws CloneNotSupportedException {
        PointcutExpr node = (PointcutExpr)super.clone();
        node.checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        node.checkCallGraph_TypeDecl_values = null;
        node.checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        node.checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
        node.isStaticallyFalse_visited = 0;
        node.isCircular_ParameterDeclaration_visited = new java.util.HashMap(4);
        node.isCircular_ParameterDeclaration_values = null;
        node.isCircular_ParameterDeclaration_computed = new java.util.HashSet(4);
        node.isCircular_ParameterDeclaration_initialized = new java.util.HashSet(4);
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in PointcutsCodegen.jrag at line 30

    //
    // The abc backend works on a representation of AspectJ constructs
    // known as the "aspectinfo". Each pointcut expression in the frontend
    // is converted to an aspectinfo representation.
    //

    abstract public Pointcut pointcut();

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 60

    public PointcutExpr() {
        super();


    }

    // Declared in AspectJ.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in Pointcuts.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEmpty() {
        boolean isEmpty_value = isEmpty_compute();
        return isEmpty_value;
    }

    private boolean isEmpty_compute() {  return false;  }

    // Declared in Pointcuts.jrag at line 171
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupPointcutVariable(String name) {
        SimpleSet lookupPointcutVariable_String_value = lookupPointcutVariable_compute(name);
        return lookupPointcutVariable_String_value;
    }

    private SimpleSet lookupPointcutVariable_compute(String name) {
        SimpleSet result = SimpleSet.emptySet;
        Iterator iter = lookupVariable(name).iterator();
        while (iter.hasNext()) {
            Variable v = (Variable)iter.next();
            if(v.isPointcutVariable())
                result = result.add(v);
        }
        return result;
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

    private int checkCallGraph_compute(TypeDecl context) {  return 0;  }

    // Declared in Pointcuts.jrag at line 551
 @SuppressWarnings({"unchecked", "cast"})     public int binds(String var) {
        int binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private int binds_compute(String var) {  return 0;  }

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

    private boolean isStaticallyFalse_compute() {  return false;  }

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

    private boolean isCircular_compute(ParameterDeclaration decl) {  return false;  }

    // Declared in TMPointcutCodegen.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public Regex regex() {
        Regex regex_value = regex_compute();
        return regex_value;
    }

    private Regex regex_compute() {
        throw new InternalCompilerError("called regex() on regular pointcut");
    }

    // Declared in ImplicitVariables.jrag at line 167
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List pointcutFormals() {
        java.util.List pointcutFormals_value = getParent().Define_java_util_List_pointcutFormals(this, null);
        return pointcutFormals_value;
    }

    // Declared in Pointcuts.jrag at line 105
 @SuppressWarnings({"unchecked", "cast"})     public boolean bindsInCurrentCflow(String name) {
        boolean bindsInCurrentCflow_String_value = getParent().Define_boolean_bindsInCurrentCflow(this, null, name);
        return bindsInCurrentCflow_String_value;
    }

    // Declared in Pointcuts.jrag at line 159
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        return lookupVariable_String_value;
    }

    // Declared in PointcutsCodegen.jrag at line 343
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
        return hostType_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
