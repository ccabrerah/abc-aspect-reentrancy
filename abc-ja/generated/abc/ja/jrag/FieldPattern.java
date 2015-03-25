
package abc.ja.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



public class FieldPattern extends MemberPattern implements Cloneable, abc.weaving.aspectinfo.FieldPattern {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public FieldPattern clone() throws CloneNotSupportedException {
        FieldPattern node = (FieldPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FieldPattern copy() {
      try {
          FieldPattern node = (FieldPattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FieldPattern fullCopy() {
        FieldPattern res = (FieldPattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PatternsCodegen.jrag at line 181



    //
    // Matching field patterns
    //
    // abc-2006-2, section 4.1.2:
    // 
    public boolean matchesFieldRef(SootFieldRef sfr)
    {
    	// Check these in roughly increasing order of complexity...
    	
    	// Check name and type of field
    	if(!getMemberNamePattern().matchesTypeAndName(sfr))
    		return false;
    	
    	// Check modifier list
    	SootField sf;
    	try {
    		sf = sfr.resolve();
    	} catch(Exception e) {
    		warning("Failed to resolve SootFieldRef " + sfr + " while matching... assuming no match, but this shouldn't happen. Please report a bug.");
    		return false;
    	}
    	for(int i = 0; i < getNumModifierPattern(); i++)
    		if(!getModifierPattern(i).matches(sf.getModifiers()))
    			return false;
    	
        return true;
    }

    // Declared in PatternsCodegen.jrag at line 207


    // Sometimes we need to introduce accessor methods for fields, e.g. when they're private.
    // These methods should still match field patterns.
    // Although, this seems to be dead code -- it's never called!
    public boolean matchesMethod(SootMethod sm)
    {
    	System.out.println("MATCHING METHOD TO FIELD!");
    	int cat = abc.weaving.aspectinfo.MethodCategory.getCategory(sm);
    	if(cat != MethodCategory.ACCESSOR_GET && cat != MethodCategory.ACCESSOR_SET)
    		return false;
    		
    	String name = MethodCategory.getName(sm);
    	SootClass realcl = MethodCategory.getClass(sm);
    	// FIXME: This (apparently) will not work for inner classes, according to
    	// comment in abc.aspectj.visit.PatternMatcher.AIFieldPattern.matchesMethod()
    	SootField sf = realcl.getField(name);
        return matchesFieldRef(sf.makeRef());
    }

    // Declared in PatternsCodegen.jrag at line 222


    public boolean equivalent(abc.weaving.aspectinfo.FieldPattern p)
    {
        return false;
    }

    // Declared in PatternsCodegen.jrag at line 228


    public abc.aspectj.ast.FieldPattern getPattern()
    {
        throw new InternalCompilerError(
                    "Can not get polyglot frontend pattern from JastAdd");
    }

    // Declared in PatternsCodegen.jrag at line 692


    // FIXME: more advanced pretty-printing for patterns?
    public void toString(StringBuffer sb)
    {
        sb.append("<field pattern>");
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 126

    public FieldPattern() {
        super();

        setChild(new List(), 0);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 126
    public FieldPattern(List<ModifierPattern> p0, Pattern p1, NamePattern p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
    }

    // Declared in AspectJ.ast at line 17


  protected int numChildren() {
    return 3;
  }

    // Declared in AspectJ.ast at line 20

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 126
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
    // Declared in AspectJ.ast line 126
    public void setTypePattern(Pattern node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public Pattern getTypePattern() {
        return (Pattern)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public Pattern getTypePatternNoTransform() {
        return (Pattern)getChildNoTransform(1);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 126
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

    // Declared in Patterns.jrag at line 169
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFieldPattern() {
        boolean isFieldPattern_value = isFieldPattern_compute();
        return isFieldPattern_value;
    }

    private boolean isFieldPattern_compute() {  return true;  }

    // Declared in PatternsCodegen.jrag at line 39
 @SuppressWarnings({"unchecked", "cast"})     public FieldPattern fieldPattern() {
        FieldPattern fieldPattern_value = fieldPattern_compute();
        return fieldPattern_value;
    }

    private FieldPattern fieldPattern_compute() {  return this;  }

    // Declared in Patterns.jrag at line 47
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
