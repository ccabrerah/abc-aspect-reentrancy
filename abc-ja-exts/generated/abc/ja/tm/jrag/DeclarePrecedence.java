
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class DeclarePrecedence extends BodyDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        firstAspect_computed = false;
        firstAspect_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclarePrecedence clone() throws CloneNotSupportedException {
        DeclarePrecedence node = (DeclarePrecedence)super.clone();
        node.firstAspect_computed = false;
        node.firstAspect_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclarePrecedence copy() {
      try {
          DeclarePrecedence node = (DeclarePrecedence)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclarePrecedence fullCopy() {
        DeclarePrecedence res = (DeclarePrecedence)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in DeclarePrecedenceNameAnalysis.jrag at line 38


  // add declare precedences to generic inter-type collection phase
  protected void collectIntertypeDecls(HashMap map) {
    super.collectIntertypeDecls(map);
    String key = "declare_precedence";
    if(!map.containsKey(key))
      map.put(key, new ArrayList());
    Collection c = (Collection)map.get(key);
    c.add(this);
  }

    // Declared in DeclarePrecedenceNameAnalysis.jrag at line 47


  public void refined_DeclarePrecedenceNameAnalysis_typeCheck() {
    super.typeCheck();
    for(int i = 0; i < getNumPattern(); i++)
      if(getPattern(i).isTypeAccess() && !getPattern(i).type().isAspectDecl())
        error("non aspect type " + getPattern(i).type().typeName() + " can not be used in a declare precendence statement");
  }

    // Declared in DeclarePrecedencePrettyPrint.jrag at line 11

  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("declare precedence: ");
    for(int i = 0; i < getNumPattern(); i++) {
      if(i != 0) s.append(", ");
      getPattern(i).toString(s);
    }
    s.append(";\n");
  }

    // Declared in DeclarePrecedence.jrag at line 65


  // In the precedence map, A has precedence over B
  //   iff B is in map.get(A)
  public void updatePrecedenceMap()
  {
    // based on original implementation by Aske Simon Christensen

    Map precedence = abc.main.Main.v().getAbcExtension()
                        .getGlobalAspectInfo().getPrecedenceRelation();

    // The aspects that have matched previous patterns
    Set passed = new HashSet();

    // Iterate through the list of patterns
    for (int i = 0; i < getNumPattern(); i++) {
      // The aspects that match the current pattern
      Set current = new HashSet();

      // Iterate through aspects
      AspectDecl a = firstAspect();
      while (a != null) {
        String aspectName = a.fullName();

        if (!precedence.containsKey(aspectName))
          precedence.put(aspectName, new HashSet());

        if (matchPattern(i, a)) {
          // It is an error if an aspect is matched by more than one pattern
          if (passed.contains(aspectName))
            error("Aspect " + aspectName + " is matched by more than " +
                  "one pattern on the precedence list");

          // Mark this aspect as being preceded by all passed aspects
          Iterator pi = passed.iterator();
          while (pi.hasNext()) {
            String prev = (String) pi.next();
            ((Set) precedence.get(prev)).add(aspectName);
          }

          // Add it to the current set
          current.add(aspectName);
        }
        a = a.nextAspect();
      }
      passed.addAll(current);
    }
  }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 42

    public DeclarePrecedence() {
        super();

        setChild(new List(), 0);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 42
    public DeclarePrecedence(List<Pattern> p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 15


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 42
    public void setPatternList(List<Pattern> list) {
        setChild(list, 0);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumPattern = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumPattern() {
        return getPatternList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Pattern getPattern(int i) {
        return (Pattern)getPatternList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addPattern(Pattern node) {
        List<Pattern> list = getPatternList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setPattern(Pattern node, int i) {
        List<Pattern> list = getPatternList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<Pattern> getPatterns() {
        return getPatternList();
    }

    // Declared in AspectJ.ast at line 27

    public List<Pattern> getPatternsNoTransform() {
        return getPatternListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Pattern> getPatternList() {
        return (List<Pattern>)getChild(0);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Pattern> getPatternListNoTransform() {
        return (List<Pattern>)getChildNoTransform(0);
    }

    // Declared in DeclarePrecedence.jrag at line 136


    public void typeCheck()
  {
    refined_DeclarePrecedenceNameAnalysis_typeCheck();
    if (numStars() > 1)
      error("multiple * patterns not allowed");

    updatePrecedenceMap();
  }

    // Declared in DeclarePrecedence.jrag at line 114
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchPattern(int i, TypeDecl typeDecl) {
        boolean matchPattern_int_TypeDecl_value = matchPattern_compute(i, typeDecl);
        return matchPattern_int_TypeDecl_value;
    }

    private boolean matchPattern_compute(int i, TypeDecl typeDecl) {
    if (!getPattern(i).isStar())
      return getPattern(i).matchesType(typeDecl);

    for (int j = 0; j < getNumPattern(); j++)
      if (i != j && getPattern(i).matchesType(typeDecl))
          return false;
    return true;
  }

    // Declared in DeclarePrecedence.jrag at line 145
 @SuppressWarnings({"unchecked", "cast"})     public int numStars() {
        int numStars_value = numStars_compute();
        return numStars_value;
    }

    private int numStars_compute() {
    int num = 0;
    for(int i = 0; i < getNumPattern(); i++)
      if(getPattern(i).isStar())
        num++;
    return num;
  }

    protected boolean firstAspect_computed = false;
    protected AspectDecl firstAspect_value;
    // Declared in DeclarePrecedence.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public AspectDecl firstAspect() {
        if(firstAspect_computed)
            return firstAspect_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        firstAspect_value = getParent().Define_AspectDecl_firstAspect(this, null);
        if(isFinal && num == boundariesCrossed)
            firstAspect_computed = true;
        return firstAspect_value;
    }

    // Declared in Patterns.jrag at line 147
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getPatternListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
