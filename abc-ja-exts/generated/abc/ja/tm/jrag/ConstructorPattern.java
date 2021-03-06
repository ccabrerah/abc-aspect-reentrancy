
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;



public class ConstructorPattern extends MemberPattern implements Cloneable, abc.weaving.aspectinfo.ConstructorPattern {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstructorPattern clone() throws CloneNotSupportedException {
        ConstructorPattern node = (ConstructorPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstructorPattern copy() {
      try {
          ConstructorPattern node = (ConstructorPattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstructorPattern fullCopy() {
        ConstructorPattern res = (ConstructorPattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PatternsCodegen.jrag at line 238



    //
    // Matching constructor patterns
    //
    public boolean matchesConstructor(SootMethod sc)
    {
        // check modifiers
        for(int i  = 0; i < getNumModifierPattern(); i++)
          if(!getModifierPattern(i).matches(sc.getModifiers()))
            return false;

        // check type and name
        if(!getMemberNamePattern().matchesTypeAndName(sc.makeRef()))
            return false;

        // check formals
        LinkedList/*<soot.Type>*/ ftypes = new LinkedList(sc.getParameterTypes());
        LinkedList/*<FormalPattern>*/ fpats = new LinkedList();
        for(int i = 0; i < getNumFormalPattern(); i++)
          fpats.add(getFormalPattern(i));
        if(!matchesFormals(fpats, 0, ftypes, 0))
          return false;

        // check exceptions
        for(int i = 0; i < getNumThrowsPattern(); i++) {
          boolean found = false;
          for(Iterator iter = sc.getExceptions().iterator(); iter.hasNext() && !found; ) {
            soot.SootClass c = (soot.SootClass)iter.next();
            if(getThrowsPattern(i).matchesType(c))
              found = true;
          }
          if(!found)
            return false;
        }
        return true;
    }

    // Declared in PatternsCodegen.jrag at line 271


    public boolean equivalent(
                                abc.weaving.aspectinfo.ConstructorPattern p)
    {
        return false;
    }

    // Declared in PatternsCodegen.jrag at line 277


    public abc.aspectj.ast.ConstructorPattern getPattern()
    {
        throw new InternalCompilerError(
            "Can not get polyglot frontend pattern from JastAdd");
    }

    // Declared in PatternsCodegen.jrag at line 700

    public void toString(StringBuffer sb)
    {
        sb.append("<constructor pattern>");
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 124

    public ConstructorPattern() {
        super();

        setChild(new List(), 0);
        setChild(new List(), 2);
        setChild(new List(), 3);

    }

    // Declared in AspectJ.ast at line 13


    // Declared in AspectJ.ast line 124
    public ConstructorPattern(List<ModifierPattern> p0, NamePattern p1, List<FormalPattern> p2, List<Pattern> p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
        setChild(p3, 3);
    }

    // Declared in AspectJ.ast at line 20


  protected int numChildren() {
    return 4;
  }

    // Declared in AspectJ.ast at line 23

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 124
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
    // Declared in AspectJ.ast line 124
    public void setMemberNamePattern(NamePattern node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public NamePattern getMemberNamePattern() {
        return (NamePattern)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public NamePattern getMemberNamePatternNoTransform() {
        return (NamePattern)getChildNoTransform(1);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 124
    public void setFormalPatternList(List<FormalPattern> list) {
        setChild(list, 2);
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
        return (List<FormalPattern>)getChild(2);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<FormalPattern> getFormalPatternListNoTransform() {
        return (List<FormalPattern>)getChildNoTransform(2);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 124
    public void setThrowsPatternList(List<Pattern> list) {
        setChild(list, 3);
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
        return (List<Pattern>)getChild(3);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Pattern> getThrowsPatternListNoTransform() {
        return (List<Pattern>)getChildNoTransform(3);
    }

    // Declared in Patterns.jrag at line 168
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstructorPattern() {
        boolean isConstructorPattern_value = isConstructorPattern_compute();
        return isConstructorPattern_value;
    }

    private boolean isConstructorPattern_compute() {  return true;  }

    // Declared in Patterns.jrag at line 173
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesEmptyArgList() {
        boolean matchesEmptyArgList_value = matchesEmptyArgList_compute();
        return matchesEmptyArgList_value;
    }

    private boolean matchesEmptyArgList_compute() {
        for (int i = 0; i < getNumFormalPattern(); i++)
            if (!getFormalPattern(i).matchesEmptyArgList())
                return false;
                
        return true;
    }

    // Declared in PatternsCodegen.jrag at line 41
 @SuppressWarnings({"unchecked", "cast"})     public ConstructorPattern constructorPattern() {
        ConstructorPattern constructorPattern_value = constructorPattern_compute();
        return constructorPattern_value;
    }

    private ConstructorPattern constructorPattern_compute() {  return this;  }

    // Declared in Pointcuts.jrag at line 620
 @SuppressWarnings({"unchecked", "cast"})     public boolean refersToInterfaceConstructor() {
        boolean refersToInterfaceConstructor_value = refersToInterfaceConstructor_compute();
        return refersToInterfaceConstructor_value;
    }

    private boolean refersToInterfaceConstructor_compute() {  return getMemberNamePattern().refersToInterface();  }

    // Declared in Patterns.jrag at line 46
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
