
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class ArgsPointcutExpr extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArgsPointcutExpr clone() throws CloneNotSupportedException {
        ArgsPointcutExpr node = (ArgsPointcutExpr)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArgsPointcutExpr copy() {
      try {
          ArgsPointcutExpr node = (ArgsPointcutExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArgsPointcutExpr fullCopy() {
        ArgsPointcutExpr res = (ArgsPointcutExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Declare.jrag at line 90

  public void collectNonStaticPointcuts(HashSet set) {
    set.add("args()");
    super.collectNonStaticPointcuts(set);
  }

    // Declared in Pointcuts.jrag at line 597
 

    // args is only allowed '..' once
    public void nameCheck()
    {
    	int dotdots = 0;
    	for(int i = 0; i < getNumPattern(); i++) {
    		if(getPattern(i).isDotDot())
    			dotdots++;
    	}
        if (dotdots > 1)
            error("The args() pointcut cannot contain more than one \"..\"");
    }

    // Declared in PointcutsCodegen.jrag at line 59


    public Pointcut pointcut() {
        java.util.List arg_patterns = new ArrayList();

        for(int i = 0; i < getNumPattern(); i++)
            arg_patterns.add(getPattern(i).argPattern());

        return new Args(arg_patterns, pos());
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 89

    public ArgsPointcutExpr() {
        super();

        setChild(new List(), 0);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 89
    public ArgsPointcutExpr(List<BindingPattern> p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 15


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 89
    public void setPatternList(List<BindingPattern> list) {
        setChild(list, 0);
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
        return (List<BindingPattern>)getChild(0);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<BindingPattern> getPatternListNoTransform() {
        return (List<BindingPattern>)getChildNoTransform(0);
    }

    // Declared in Pointcuts.jrag at line 573
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

    // Declared in Pointcuts.jrag at line 164
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getPatternListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return lookupPointcutVariable(name);
        }
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in Patterns.jrag at line 154
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
