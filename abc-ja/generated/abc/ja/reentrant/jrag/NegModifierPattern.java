
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class NegModifierPattern extends ModifierPattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public NegModifierPattern clone() throws CloneNotSupportedException {
        NegModifierPattern node = (NegModifierPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NegModifierPattern copy() {
      try {
          NegModifierPattern node = (NegModifierPattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NegModifierPattern fullCopy() {
        NegModifierPattern res = (NegModifierPattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 117

    public NegModifierPattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 117
    public NegModifierPattern(Modifier p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 116
    public void setModifier(Modifier node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public Modifier getModifier() {
        return (Modifier)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public Modifier getModifierNoTransform() {
        return (Modifier)getChildNoTransform(0);
    }

    // Declared in PatternsCodegen.jrag at line 674
 @SuppressWarnings({"unchecked", "cast"})     public boolean matches(int m) {
        boolean matches_int_value = matches_compute(m);
        return matches_int_value;
    }

    private boolean matches_compute(int m) {  return !super.matches(m);  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
