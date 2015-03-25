
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


// ---------------------- Modifier patterns ---------------

public class ModifierPattern extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        modifierMask_computed = false;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ModifierPattern clone() throws CloneNotSupportedException {
        ModifierPattern node = (ModifierPattern)super.clone();
        node.modifierMask_computed = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ModifierPattern copy() {
      try {
          ModifierPattern node = (ModifierPattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ModifierPattern fullCopy() {
        ModifierPattern res = (ModifierPattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 116

    public ModifierPattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 116
    public ModifierPattern(Modifier p0) {
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

    // Declared in PatternsCodegen.jrag at line 673
 @SuppressWarnings({"unchecked", "cast"})     public boolean matches(int m) {
        boolean matches_int_value = matches_compute(m);
        return matches_int_value;
    }

    private boolean matches_compute(int m) {  return (modifierMask() & m) != 0;  }

    protected boolean modifierMask_computed = false;
    protected int modifierMask_value;
    // Declared in PatternsCodegen.jrag at line 675
 @SuppressWarnings({"unchecked", "cast"})     public int modifierMask() {
        if(modifierMask_computed)
            return modifierMask_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        modifierMask_value = modifierMask_compute();
        if(isFinal && num == boundariesCrossed)
            modifierMask_computed = true;
        return modifierMask_value;
    }

    private int modifierMask_compute() {
      String s = getModifier().getID();
      if(s.equals("public")) return soot.Modifier.PUBLIC;
      if(s.equals("private")) return soot.Modifier.PRIVATE;
      if(s.equals("protected")) return soot.Modifier.PROTECTED;
      if(s.equals("static")) return soot.Modifier.STATIC;
      if(s.equals("abstract")) return soot.Modifier.ABSTRACT;
      if(s.equals("final")) return soot.Modifier.FINAL;
      if(s.equals("native")) return soot.Modifier.NATIVE;
      if(s.equals("synchronized")) return soot.Modifier.SYNCHRONIZED;
      if(s.equals("transient")) return soot.Modifier.TRANSIENT;
      if(s.equals("volatile")) return soot.Modifier.VOLATILE;
      if(s.equals("strictfp")) return soot.Modifier.STRICTFP;
      throw new Error("Trying to match unsupported modifier: " + s);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
