
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class ImplicitParameterDeclaration extends ParameterDeclaration implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ImplicitParameterDeclaration clone() throws CloneNotSupportedException {
        ImplicitParameterDeclaration node = (ImplicitParameterDeclaration)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ImplicitParameterDeclaration copy() {
      try {
          ImplicitParameterDeclaration node = (ImplicitParameterDeclaration)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ImplicitParameterDeclaration fullCopy() {
        ImplicitParameterDeclaration res = (ImplicitParameterDeclaration)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 32

    public ImplicitParameterDeclaration() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 32
    public ImplicitParameterDeclaration(Modifiers p0, Access p1, String p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
    }

    // Declared in AspectJ.ast at line 17


    // Declared in AspectJ.ast line 32
    public ImplicitParameterDeclaration(Modifiers p0, Access p1, beaver.Symbol p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
    }

    // Declared in AspectJ.ast at line 23


  protected int numChildren() {
    return 2;
  }

    // Declared in AspectJ.ast at line 26

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

    // Declared in ImplicitVariables.jrag at line 75
 @SuppressWarnings({"unchecked", "cast"})     public boolean isImplicit() {
        boolean isImplicit_value = isImplicit_compute();
        return isImplicit_value;
    }

    private boolean isImplicit_compute() {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
