
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;


public class ContinueStmt extends Stmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        targetStmt_computed = false;
        targetStmt_value = null;
        finallyList_computed = false;
        finallyList_value = null;
        isDAafter_Variable_values = null;
        isDUafterReachedFinallyBlocks_Variable_values = null;
        isDAafterReachedFinallyBlocks_Variable_values = null;
        isDUafter_Variable_values = null;
        canCompleteNormally_computed = false;
        lookupLabel_String_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ContinueStmt clone() throws CloneNotSupportedException {
        ContinueStmt node = (ContinueStmt)super.clone();
        node.targetStmt_computed = false;
        node.targetStmt_value = null;
        node.finallyList_computed = false;
        node.finallyList_value = null;
        node.isDAafter_Variable_values = null;
        node.isDUafterReachedFinallyBlocks_Variable_values = null;
        node.isDAafterReachedFinallyBlocks_Variable_values = null;
        node.isDUafter_Variable_values = null;
        node.canCompleteNormally_computed = false;
        node.lookupLabel_String_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ContinueStmt copy() {
      try {
          ContinueStmt node = (ContinueStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ContinueStmt fullCopy() {
        ContinueStmt res = (ContinueStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in BranchTarget.jrag at line 49

  public void collectBranches(Collection c) {
    c.add(this);
  }

    // Declared in NameCheck.jrag at line 384

  
  public void nameCheck() {
    if(!insideLoop())
      error("continue outside loop");
    else if(hasLabel()) {
      LabeledStmt label = lookupLabel(getLabel());
      if(label == null)
        error("labeled continue must have visible matching label");
      else if(!label.getStmt().continueLabel())
        error(getLabel() + " is not a loop label");
    }
  }

    // Declared in PrettyPrint.jadd at line 684


  public void toString(StringBuffer s) {
    s.append("continue ");
    if(hasLabel())
      s.append(getLabel());
    s.append(";\n");
  }

    // Declared in Statements.jrag at line 216


  public void jimplify2(Body b) {
    for(Iterator iter = finallyList().iterator(); iter.hasNext(); ) {
      FinallyHost stmt = (FinallyHost)iter.next();
      stmt.emitFinallyCode(b);
    }
    b.setLine(this);
    b.add(Jimple.v().newGotoStmt(targetStmt().continue_label()));
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 216

    public ContinueStmt() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 216
    public ContinueStmt(String p0) {
        setLabel(p0);
    }

    // Declared in java.ast at line 15


    // Declared in java.ast line 216
    public ContinueStmt(beaver.Symbol p0) {
        setLabel(p0);
    }

    // Declared in java.ast at line 19


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 22

  public boolean mayHaveRewrite() { return false; }

    // Declared in java.ast at line 2
    // Declared in java.ast line 216
    private String tokenString_Label;

    // Declared in java.ast at line 3

    public void setLabel(String value) {
        tokenString_Label = value;
    }

    // Declared in java.ast at line 6

    public int Labelstart;

    // Declared in java.ast at line 7

    public int Labelend;

    // Declared in java.ast at line 8

    public void setLabel(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setLabel is only valid for String lexemes");
        tokenString_Label = (String)symbol.value;
        Labelstart = symbol.getStart();
        Labelend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

    public String getLabel() {
        return tokenString_Label != null ? tokenString_Label : "";
    }

    // Declared in BranchTarget.jrag at line 65
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasLabel() {
        boolean hasLabel_value = hasLabel_compute();
        return hasLabel_value;
    }

    private boolean hasLabel_compute() {  return !getLabel().equals("");  }

    protected boolean targetStmt_computed = false;
    protected Stmt targetStmt_value;
    // Declared in BranchTarget.jrag at line 150
 @SuppressWarnings({"unchecked", "cast"})     public Stmt targetStmt() {
        if(targetStmt_computed)
            return targetStmt_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        targetStmt_value = targetStmt_compute();
        if(isFinal && num == boundariesCrossed)
            targetStmt_computed = true;
        return targetStmt_value;
    }

    private Stmt targetStmt_compute() {  return branchTarget(this);  }

    protected boolean finallyList_computed = false;
    protected ArrayList finallyList_value;
    // Declared in BranchTarget.jrag at line 181
 @SuppressWarnings({"unchecked", "cast"})     public ArrayList finallyList() {
        if(finallyList_computed)
            return finallyList_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        finallyList_value = finallyList_compute();
        if(isFinal && num == boundariesCrossed)
            finallyList_computed = true;
        return finallyList_value;
    }

    private ArrayList finallyList_compute() {
    ArrayList list = new ArrayList();
    collectFinally(this, list);
    return list;
  }

    // Declared in DefiniteAssignment.jrag at line 649
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        Object _parameters = v;
if(isDAafter_Variable_values == null) isDAafter_Variable_values = new java.util.HashMap(4);
        if(isDAafter_Variable_values.containsKey(_parameters))
            return ((Boolean)isDAafter_Variable_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        if(isFinal && num == boundariesCrossed)
            isDAafter_Variable_values.put(_parameters, Boolean.valueOf(isDAafter_Variable_value));
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return true;  }

    protected java.util.Map isDUafterReachedFinallyBlocks_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 936
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterReachedFinallyBlocks(Variable v) {
        Object _parameters = v;
if(isDUafterReachedFinallyBlocks_Variable_values == null) isDUafterReachedFinallyBlocks_Variable_values = new java.util.HashMap(4);
        if(isDUafterReachedFinallyBlocks_Variable_values.containsKey(_parameters))
            return ((Boolean)isDUafterReachedFinallyBlocks_Variable_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDUafterReachedFinallyBlocks_Variable_value = isDUafterReachedFinallyBlocks_compute(v);
        if(isFinal && num == boundariesCrossed)
            isDUafterReachedFinallyBlocks_Variable_values.put(_parameters, Boolean.valueOf(isDUafterReachedFinallyBlocks_Variable_value));
        return isDUafterReachedFinallyBlocks_Variable_value;
    }

    private boolean isDUafterReachedFinallyBlocks_compute(Variable v) {
    if(!isDUbefore(v) && finallyList().isEmpty())
      return false;
    for(Iterator iter = finallyList().iterator(); iter.hasNext(); ) {
      FinallyHost f = (FinallyHost)iter.next();
      if(!f.isDUafterFinally(v))
        return false;
    }
    return true;
  }

    protected java.util.Map isDAafterReachedFinallyBlocks_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 970
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterReachedFinallyBlocks(Variable v) {
        Object _parameters = v;
if(isDAafterReachedFinallyBlocks_Variable_values == null) isDAafterReachedFinallyBlocks_Variable_values = new java.util.HashMap(4);
        if(isDAafterReachedFinallyBlocks_Variable_values.containsKey(_parameters))
            return ((Boolean)isDAafterReachedFinallyBlocks_Variable_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDAafterReachedFinallyBlocks_Variable_value = isDAafterReachedFinallyBlocks_compute(v);
        if(isFinal && num == boundariesCrossed)
            isDAafterReachedFinallyBlocks_Variable_values.put(_parameters, Boolean.valueOf(isDAafterReachedFinallyBlocks_Variable_value));
        return isDAafterReachedFinallyBlocks_Variable_value;
    }

    private boolean isDAafterReachedFinallyBlocks_compute(Variable v) {
    if(isDAbefore(v))
      return true;
    if(finallyList().isEmpty())
      return false;
    for(Iterator iter = finallyList().iterator(); iter.hasNext(); ) {
      FinallyHost f = (FinallyHost)iter.next();
      if(!f.isDAafterFinally(v))
        return false;
    }
    return true;
  }

    // Declared in DefiniteAssignment.jrag at line 1175
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        Object _parameters = v;
if(isDUafter_Variable_values == null) isDUafter_Variable_values = new java.util.HashMap(4);
        if(isDUafter_Variable_values.containsKey(_parameters))
            return ((Boolean)isDUafter_Variable_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        if(isFinal && num == boundariesCrossed)
            isDUafter_Variable_values.put(_parameters, Boolean.valueOf(isDUafter_Variable_value));
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return true;  }

    // Declared in UnreachableStatements.jrag at line 106
 @SuppressWarnings({"unchecked", "cast"})     public boolean canCompleteNormally() {
        if(canCompleteNormally_computed)
            return canCompleteNormally_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        canCompleteNormally_value = canCompleteNormally_compute();
        if(isFinal && num == boundariesCrossed)
            canCompleteNormally_computed = true;
        return canCompleteNormally_value;
    }

    private boolean canCompleteNormally_compute() {  return false;  }

    protected java.util.Map lookupLabel_String_values;
    // Declared in BranchTarget.jrag at line 170
 @SuppressWarnings({"unchecked", "cast"})     public LabeledStmt lookupLabel(String name) {
        Object _parameters = name;
if(lookupLabel_String_values == null) lookupLabel_String_values = new java.util.HashMap(4);
        if(lookupLabel_String_values.containsKey(_parameters))
            return (LabeledStmt)lookupLabel_String_values.get(_parameters);
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        LabeledStmt lookupLabel_String_value = getParent().Define_LabeledStmt_lookupLabel(this, null, name);
        if(isFinal && num == boundariesCrossed)
            lookupLabel_String_values.put(_parameters, lookupLabel_String_value);
        return lookupLabel_String_value;
    }

    // Declared in NameCheck.jrag at line 361
 @SuppressWarnings({"unchecked", "cast"})     public boolean insideLoop() {
        boolean insideLoop_value = getParent().Define_boolean_insideLoop(this, null);
        return insideLoop_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
