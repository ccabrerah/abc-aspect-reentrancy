
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;


public class ArraytypeNamePattern extends NamePattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
        matchesType_TypeDecl_values = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArraytypeNamePattern clone() throws CloneNotSupportedException {
        ArraytypeNamePattern node = (ArraytypeNamePattern)super.clone();
        node.matchesType_TypeDecl_values = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArraytypeNamePattern copy() {
      try {
          ArraytypeNamePattern node = (ArraytypeNamePattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArraytypeNamePattern fullCopy() {
        ArraytypeNamePattern res = (ArraytypeNamePattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Patterns.jrag at line 215

    public String toString() {
    	String dims = "";
    	for(int i = 0; i < getNumDims(); i++) {
    		dims += "[]";
    	}
    	return getPattern() + dims;
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 113

    public ArraytypeNamePattern() {
        super();

        setChild(new List(), 1);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 113
    public ArraytypeNamePattern(NamePattern p0, List<Dims> p1) {
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
    // Declared in AspectJ.ast line 113
    public void setPattern(NamePattern node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public NamePattern getPattern() {
        return (NamePattern)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public NamePattern getPatternNoTransform() {
        return (NamePattern)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 113
    public void setDimsList(List<Dims> list) {
        setChild(list, 1);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumDims = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumDims() {
        return getDimsList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Dims getDims(int i) {
        return (Dims)getDimsList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addDims(Dims node) {
        List<Dims> list = getDimsList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setDims(Dims node, int i) {
        List<Dims> list = getDimsList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<Dims> getDimss() {
        return getDimsList();
    }

    // Declared in AspectJ.ast at line 27

    public List<Dims> getDimssNoTransform() {
        return getDimsListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Dims> getDimsList() {
        return (List<Dims>)getChild(1);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Dims> getDimsListNoTransform() {
        return (List<Dims>)getChildNoTransform(1);
    }

    // Declared in PatternsCodegen.jrag at line 525
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(SootClass c) {
        boolean matchesType_SootClass_value = matchesType_compute(c);
        return matchesType_SootClass_value;
    }

    private boolean matchesType_compute(SootClass c) {  return false;  }

    // Declared in PatternsCodegen.jrag at line 554
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(Type t) {
        boolean matchesType_Type_value = matchesType_compute(t);
        return matchesType_Type_value;
    }

    private boolean matchesType_compute(Type t) {
        if (! (t instanceof ArrayType))
            return false;
        ArrayType arraytype = (ArrayType) t;
        return arraytype.numDimensions == getNumDims()
               && getPattern().matchesType(arraytype.baseType);
    }

    // Declared in PatternsCodegen.jrag at line 596
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesType(TypeDecl t) {
        Object _parameters = t;
if(matchesType_TypeDecl_values == null) matchesType_TypeDecl_values = new java.util.HashMap(4);
        if(matchesType_TypeDecl_values.containsKey(_parameters))
            return ((Boolean)matchesType_TypeDecl_values.get(_parameters)).booleanValue();
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean matchesType_TypeDecl_value = matchesType_compute(t);
        if(isFinal && num == boundariesCrossed)
            matchesType_TypeDecl_values.put(_parameters, Boolean.valueOf(matchesType_TypeDecl_value));
        return matchesType_TypeDecl_value;
    }

    private boolean matchesType_compute(TypeDecl t) {  return getNumDims() == t.dimension()
        && getPattern().matchesType(t.elementType());  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
