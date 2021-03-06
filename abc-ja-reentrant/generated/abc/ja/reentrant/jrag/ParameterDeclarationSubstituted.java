
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;


public class ParameterDeclarationSubstituted extends ParameterDeclaration implements Cloneable {
    public void flushCache() {
        super.flushCache();
        sourceVariableDecl_computed = false;
        sourceVariableDecl_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclarationSubstituted clone() throws CloneNotSupportedException {
        ParameterDeclarationSubstituted node = (ParameterDeclarationSubstituted)super.clone();
        node.sourceVariableDecl_computed = false;
        node.sourceVariableDecl_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclarationSubstituted copy() {
      try {
          ParameterDeclarationSubstituted node = (ParameterDeclarationSubstituted)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclarationSubstituted fullCopy() {
        ParameterDeclarationSubstituted res = (ParameterDeclarationSubstituted)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Generics.ast at line 3
    // Declared in Generics.ast line 30

    public ParameterDeclarationSubstituted() {
        super();


    }

    // Declared in Generics.ast at line 10


    // Declared in Generics.ast line 30
    public ParameterDeclarationSubstituted(Modifiers p0, Access p1, String p2, ParameterDeclaration p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setOriginal(p3);
    }

    // Declared in Generics.ast at line 18


    // Declared in Generics.ast line 30
    public ParameterDeclarationSubstituted(Modifiers p0, Access p1, beaver.Symbol p2, ParameterDeclaration p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setOriginal(p3);
    }

    // Declared in Generics.ast at line 25


  protected int numChildren() {
    return 2;
  }

    // Declared in Generics.ast at line 28

  public boolean mayHaveRewrite() { return false; }

    // Declared in java.ast at line 2
    // Declared in java.ast line 84
    public void setModifiers(Modifiers node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Modifiers getModifiers() {
        return (Modifiers)getChild(0);
    }

    // Declared in java.ast at line 9


    public Modifiers getModifiersNoTransform() {
        return (Modifiers)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 84
    public void setTypeAccess(Access node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Access getTypeAccess() {
        return (Access)getChild(1);
    }

    // Declared in java.ast at line 9


    public Access getTypeAccessNoTransform() {
        return (Access)getChildNoTransform(1);
    }

    // Declared in Generics.ast at line 2
    // Declared in Generics.ast line 30
    private ParameterDeclaration tokenParameterDeclaration_Original;

    // Declared in Generics.ast at line 3

    public void setOriginal(ParameterDeclaration value) {
        tokenParameterDeclaration_Original = value;
    }

    // Declared in Generics.ast at line 6

    public ParameterDeclaration getOriginal() {
        return tokenParameterDeclaration_Original;
    }

    // Declared in Generics.jrag at line 1309
 @SuppressWarnings({"unchecked", "cast"})     public Variable sourceVariableDecl() {
        if(sourceVariableDecl_computed)
            return sourceVariableDecl_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        sourceVariableDecl_value = sourceVariableDecl_compute();
        if(isFinal && num == boundariesCrossed)
            sourceVariableDecl_computed = true;
        return sourceVariableDecl_value;
    }

    private Variable sourceVariableDecl_compute() {  return getOriginal().sourceVariableDecl();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
