
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;


public class NullLiteral extends Literal implements Cloneable {
    public void flushCache() {
        super.flushCache();
        type_computed = false;
        type_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NullLiteral clone() throws CloneNotSupportedException {
        NullLiteral node = (NullLiteral)super.clone();
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NullLiteral copy() {
      try {
          NullLiteral node = (NullLiteral)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NullLiteral fullCopy() {
        NullLiteral res = (NullLiteral)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Expressions.jrag at line 35

  public soot.Value eval(Body b) {
    return soot.jimple.NullConstant.v();
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 132

    public NullLiteral() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 132
    public NullLiteral(String p0) {
        setLITERAL(p0);
    }

    // Declared in java.ast at line 15


    // Declared in java.ast line 132
    public NullLiteral(beaver.Symbol p0) {
        setLITERAL(p0);
    }

    // Declared in java.ast at line 19


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 22

  public boolean mayHaveRewrite() { return false; }

    // Declared in ConstantExpression.jrag at line 468
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        boolean isConstant_value = isConstant_compute();
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return false;  }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 308
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        if(type_computed)
            return type_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        type_value = type_compute();
        if(isFinal && num == boundariesCrossed)
            type_computed = true;
        return type_value;
    }

    private TypeDecl type_compute() {  return typeNull();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
