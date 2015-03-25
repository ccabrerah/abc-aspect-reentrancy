
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;


public class MethodPattern extends MemberPattern implements Cloneable, abc.weaving.aspectinfo.MethodPattern {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public MethodPattern clone() throws CloneNotSupportedException {
        MethodPattern node = (MethodPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public MethodPattern copy() {
      try {
          MethodPattern node = (MethodPattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public MethodPattern fullCopy() {
        MethodPattern res = (MethodPattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PatternsCodegen.jrag at line 84


    

    //
    // Matching method patterns
    //
    public boolean matchesCall(SootMethodRef smr)
    {
        SootMethod sm = smr.resolve();
        int real_mods = MethodCategory.getModifiers(sm);
        int first_real_arg = MethodCategory.getSkipFirst(sm);

        // exclude ITD-generated methods which correspond to constructors
        // and ITD-generated accessor methods for fields
        if (MethodCategory.getName(sm).equals(SootMethod.constructorName))
          return false;
        int category = MethodCategory.getCategory(sm);
        if (category == MethodCategory.ACCESSOR_GET
            || category == MethodCategory.ACCESSOR_SET)
          return false;

        // check modifiers
        for(int i  = 0; i < getNumModifierPattern(); i++)
          if(!getModifierPattern(i).matches(real_mods))
            return false;

        // check return type
        if(!getReturnTypePattern().matchesType(smr.returnType()))
            return false;

        // check type and name
        if(!getMemberNamePattern().matchesTypeAndName(smr))
            return false;

        // check formals
        LinkedList/*<soot.Type>*/ ftypes = new LinkedList(smr.parameterTypes());
        LinkedList/*<FormalPattern>*/ fpats = new LinkedList();
        for(int i = 0; i < getNumFormalPattern(); i++)
          fpats.add(getFormalPattern(i));
        if(!matchesFormals(fpats, 0, ftypes, first_real_arg))
          return false;

        // check exceptions
        for(int i = 0; i < getNumThrowsPattern(); i++) {
          boolean found = false;
          for(Iterator iter = smr.resolve().getExceptions().iterator(); iter.hasNext() && !found; ) {
            soot.SootClass c = (soot.SootClass)iter.next();
            if(getThrowsPattern(i).matchesType(c))
              found = true;
          }
          if(!found)
            return false;
        }
        return true;
    }

    // Declared in PatternsCodegen.jrag at line 158



    public boolean matchesExecution(SootMethod sm)
    {
        return matchesCall(sm.makeRef());
    }

    // Declared in PatternsCodegen.jrag at line 163


    public boolean equivalent(abc.weaving.aspectinfo.MethodPattern p)
    {
        return false;
    }

    // Declared in PatternsCodegen.jrag at line 169


    public abc.aspectj.ast.MethodPattern getPattern()
    {
        throw new InternalCompilerError(
                    "Can not get polyglot frontend pattern from JastAdd");
    }

    // Declared in PatternsCodegen.jrag at line 696

    public void toString(StringBuffer sb)
    {
        sb.append("<method pattern>");
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 122

    public MethodPattern() {
        super();

        setChild(new List(), 0);
        setChild(new List(), 3);
        setChild(new List(), 4);

    }

    // Declared in AspectJ.ast at line 13


    // Declared in AspectJ.ast line 122
    public MethodPattern(List<ModifierPattern> p0, Pattern p1, NamePattern p2, List<FormalPattern> p3, List<Pattern> p4) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
        setChild(p3, 3);
        setChild(p4, 4);
    }

    // Declared in AspectJ.ast at line 21


  protected int numChildren() {
    return 5;
  }

    // Declared in AspectJ.ast at line 24

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 122
    public void setModifierPatternList(List<ModifierPattern> list) {
        setChild(list, 0);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumModifierPattern = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumModifierPattern() {
        return getModifierPatternList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public ModifierPattern getModifierPattern(int i) {
        return (ModifierPattern)getModifierPatternList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addModifierPattern(ModifierPattern node) {
        List<ModifierPattern> list = getModifierPatternList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setModifierPattern(ModifierPattern node, int i) {
        List<ModifierPattern> list = getModifierPatternList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<ModifierPattern> getModifierPatterns() {
        return getModifierPatternList();
    }

    // Declared in AspectJ.ast at line 27

    public List<ModifierPattern> getModifierPatternsNoTransform() {
        return getModifierPatternListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<ModifierPattern> getModifierPatternList() {
        return (List<ModifierPattern>)getChild(0);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ModifierPattern> getModifierPatternListNoTransform() {
        return (List<ModifierPattern>)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 122
    public void setReturnTypePattern(Pattern node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public Pattern getReturnTypePattern() {
        return (Pattern)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public Pattern getReturnTypePatternNoTransform() {
        return (Pattern)getChildNoTransform(1);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 122
    public void setMemberNamePattern(NamePattern node) {
        setChild(node, 2);
    }

    // Declared in AspectJ.ast at line 5

    public NamePattern getMemberNamePattern() {
        return (NamePattern)getChild(2);
    }

    // Declared in AspectJ.ast at line 9


    public NamePattern getMemberNamePatternNoTransform() {
        return (NamePattern)getChildNoTransform(2);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 122
    public void setFormalPatternList(List<FormalPattern> list) {
        setChild(list, 3);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumFormalPattern = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumFormalPattern() {
        return getFormalPatternList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public FormalPattern getFormalPattern(int i) {
        return (FormalPattern)getFormalPatternList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addFormalPattern(FormalPattern node) {
        List<FormalPattern> list = getFormalPatternList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setFormalPattern(FormalPattern node, int i) {
        List<FormalPattern> list = getFormalPatternList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<FormalPattern> getFormalPatterns() {
        return getFormalPatternList();
    }

    // Declared in AspectJ.ast at line 27

    public List<FormalPattern> getFormalPatternsNoTransform() {
        return getFormalPatternListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<FormalPattern> getFormalPatternList() {
        return (List<FormalPattern>)getChild(3);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<FormalPattern> getFormalPatternListNoTransform() {
        return (List<FormalPattern>)getChildNoTransform(3);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 122
    public void setThrowsPatternList(List<Pattern> list) {
        setChild(list, 4);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumThrowsPattern = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumThrowsPattern() {
        return getThrowsPatternList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Pattern getThrowsPattern(int i) {
        return (Pattern)getThrowsPatternList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addThrowsPattern(Pattern node) {
        List<Pattern> list = getThrowsPatternList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setThrowsPattern(Pattern node, int i) {
        List<Pattern> list = getThrowsPatternList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<Pattern> getThrowsPatterns() {
        return getThrowsPatternList();
    }

    // Declared in AspectJ.ast at line 27

    public List<Pattern> getThrowsPatternsNoTransform() {
        return getThrowsPatternListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Pattern> getThrowsPatternList() {
        return (List<Pattern>)getChild(4);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Pattern> getThrowsPatternListNoTransform() {
        return (List<Pattern>)getChildNoTransform(4);
    }

    // Declared in Patterns.jrag at line 167
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodPattern() {
        boolean isMethodPattern_value = isMethodPattern_compute();
        return isMethodPattern_value;
    }

    private boolean isMethodPattern_compute() {  return true;  }

    // Declared in PatternsCodegen.jrag at line 40
 @SuppressWarnings({"unchecked", "cast"})     public MethodPattern methodPattern() {
        MethodPattern methodPattern_value = methodPattern_compute();
        return methodPattern_value;
    }

    private MethodPattern methodPattern_compute() {  return this;  }

    // Declared in Patterns.jrag at line 45
    public boolean Define_boolean_denotesMember(ASTNode caller, ASTNode child) {
        if(caller == getMemberNamePatternNoTransform()) {
            return true;
        }
        return getParent().Define_boolean_denotesMember(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
