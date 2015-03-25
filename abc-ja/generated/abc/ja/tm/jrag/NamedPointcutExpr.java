
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



public class NamedPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        checkCallGraph_TypeDecl_values = null;
        checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
        isStaticallyFalse_visited = 0;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NamedPointcutExpr clone() throws CloneNotSupportedException {
        NamedPointcutExpr node = (NamedPointcutExpr)super.clone();
        node.checkCallGraph_TypeDecl_visited = new java.util.HashMap(4);
        node.checkCallGraph_TypeDecl_values = null;
        node.checkCallGraph_TypeDecl_computed = new java.util.HashSet(4);
        node.checkCallGraph_TypeDecl_initialized = new java.util.HashSet(4);
        node.isStaticallyFalse_visited = 0;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NamedPointcutExpr copy() {
      try {
          NamedPointcutExpr node = (NamedPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NamedPointcutExpr fullCopy() {
        NamedPointcutExpr res = (NamedPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Declare.jrag at line 63

  public void collectNonStaticPointcuts(HashSet set) {
    super.collectNonStaticPointcuts(set);
    PointcutAccess access = (PointcutAccess) getName().lastAccess();
    PointcutDecl decl = access.decl();
    if(decl != null)
      decl.collectNonStaticPointcuts(set);
  }

    // Declared in Pointcuts.jrag at line 400


    // check that a concrete pointcut doesn't call an abstract pointcut
    public void checkModifiers()
    {
        if (!hostType().isAbstract() && hostType().isAspectDecl() &&
            callsAbstractPointcut())
        {
            error("Cannot refer to the abstract pointcut " +
                  decl().hostType().typeName() + "." + decl().name() +
                  " from a concrete aspect.");
        }
    }

    // Declared in PointcutsCodegen.jrag at line 139


    public Pointcut pointcut() {
        java.util.List arg_patterns = new ArrayList();

        for(int i = 0; i < getNumPattern(); i++)
            arg_patterns.add(getPattern(i).argPattern());

        Object key = new Object();
        Map decl_map = new HashMap();
        decl_map.put(key, decl().pointcutDecl());

        return new PointcutRef(key, decl_map, arg_patterns,
                                pos(), getName().lastAccess().isQualified());
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 91

    public NamedPointcutExpr() {
        super();

        setChild(new List(), 1);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 91
    public NamedPointcutExpr(Access p0, List<BindingPattern> p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in AspectJ.ast at line 16


  protected int numChildren() {
    return 2;
  }

    // Declared in AspectJ.ast at line 19

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 91
    public void setName(Access node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public Access getName() {
        return (Access)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public Access getNameNoTransform() {
        return (Access)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 91
    public void setPatternList(List<BindingPattern> list) {
        setChild(list, 1);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumPattern = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumPattern() {
        return getPatternList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public BindingPattern getPattern(int i) {
        return (BindingPattern)getPatternList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addPattern(BindingPattern node) {
        List<BindingPattern> list = getPatternList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setPattern(BindingPattern node, int i) {
        List<BindingPattern> list = getPatternList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<BindingPattern> getPatterns() {
        return getPatternList();
    }

    // Declared in AspectJ.ast at line 27

    public List<BindingPattern> getPatternsNoTransform() {
        return getPatternListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<BindingPattern> getPatternList() {
        return (List<BindingPattern>)getChild(1);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<BindingPattern> getPatternListNoTransform() {
        return (List<BindingPattern>)getChildNoTransform(1);
    }

    // Declared in Pointcuts.jrag at line 393
 @SuppressWarnings({"unchecked", "cast"})     public PointcutDecl decl() {
        PointcutDecl decl_value = decl_compute();
        return decl_value;
    }

    private PointcutDecl decl_compute() {  return ((PointcutAccess) getName().lastAccess()).decl();  }

    // Declared in Pointcuts.jrag at line 411
 @SuppressWarnings({"unchecked", "cast"})     public boolean callsAbstractPointcut() {
        boolean callsAbstractPointcut_value = callsAbstractPointcut_compute();
        return callsAbstractPointcut_value;
    }

    private boolean callsAbstractPointcut_compute() {  return checkCallGraph(hostType()) == 1;  }

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

    private int checkCallGraph_compute(TypeDecl context) {
        // don't give additional error if a pointcut can't be
        // resolved anyway
        if (decl() == null)
            return 0;

        // if the call is qualified, look up normally and use the
        // declaring type as the context
        if (! (getName() instanceof PointcutAccess))
            return decl().checkCallGraph(decl().hostType());

        String name = ((PointcutAccess) getName()).name();
        Iterator i = context.lookupMemberPointcut(name).iterator();
        while (i.hasNext()) {
            PointcutDecl decl = (PointcutDecl) i.next();
            if (!decl.isAbstract())
                return decl.checkCallGraph(context);
        }
        i = context.lookupPointcut(name).iterator();
        while (i.hasNext()) {
            PointcutDecl decl = (PointcutDecl) i.next();
            if (!decl.isAbstract())
                return decl.checkCallGraph(context);
        }
        return 1;
    }

    // Declared in Pointcuts.jrag at line 581
 @SuppressWarnings({"unchecked", "cast"})     public int binds(String var) {
        int binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private int binds_compute(String var) {
    	int result = 0;
    	for(int i = 0; i < getNumPattern(); i++) {
    		if(getPattern(i).binds(var))
    			result++;
    	}
    	return (result > 2 ? 2 : result);
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

    private boolean isStaticallyFalse_compute() {  return decl().getPointcutExpr().isStaticallyFalse();  }

    // Declared in Pointcuts.jrag at line 41
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getPatternListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return NameType.AMBIGUOUS_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
