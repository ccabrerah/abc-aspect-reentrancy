
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;


public class LongLiteral extends Literal implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constant_computed = false;
        constant_value = null;
        type_computed = false;
        type_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LongLiteral clone() throws CloneNotSupportedException {
        LongLiteral node = (LongLiteral)super.clone();
        node.constant_computed = false;
        node.constant_value = null;
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LongLiteral copy() {
      try {
          LongLiteral node = (LongLiteral)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LongLiteral fullCopy() {
        LongLiteral res = (LongLiteral)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 293


  public void toString(StringBuffer s) {
    s.append(getLITERAL());
    s.append("L");
  }

    // Declared in TypeCheck.jrag at line 575

 public void typeCheck() {
   if(constant().error)
     error("The value of the long literal " + getLITERAL() + " is not legal");

 }

    // Declared in Expressions.jrag at line 23

  public soot.Value eval(Body b) {
    return soot.jimple.LongConstant.v(constant().longValue());
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 126

    public LongLiteral() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 126
    public LongLiteral(String p0) {
        setLITERAL(p0);
    }

    // Declared in java.ast at line 15


    // Declared in java.ast line 126
    public LongLiteral(beaver.Symbol p0) {
        setLITERAL(p0);
    }

    // Declared in java.ast at line 19


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 22

  public boolean mayHaveRewrite() { return false; }

    // Declared in ConstantExpression.jrag at line 224
 @SuppressWarnings({"unchecked", "cast"})     public boolean isHex() {
        boolean isHex_value = isHex_compute();
        return isHex_value;
    }

    private boolean isHex_compute() {  return getLITERAL().toLowerCase().startsWith("0x");  }

    // Declared in ConstantExpression.jrag at line 225
 @SuppressWarnings({"unchecked", "cast"})     public boolean isOctal() {
        boolean isOctal_value = isOctal_compute();
        return isOctal_value;
    }

    private boolean isOctal_compute() {  return getLITERAL().startsWith("0");  }

    // Declared in ConstantExpression.jrag at line 226
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDecimal() {
        boolean isDecimal_value = isDecimal_compute();
        return isDecimal_value;
    }

    private boolean isDecimal_compute() {  return !isHex() && !isOctal();  }

    // Declared in ConstantExpression.jrag at line 230
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPositive() {
        boolean isPositive_value = isPositive_compute();
        return isPositive_value;
    }

    private boolean isPositive_compute() {  return !getLITERAL().startsWith("-");  }

    // Declared in ConstantExpression.jrag at line 257
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        if(constant_computed)
            return constant_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        constant_value = constant_compute();
        if(isFinal && num == boundariesCrossed)
            constant_computed = true;
        return constant_value;
    }

    private Constant constant_compute() {
    try {
      return Constant.create(Literal.parseLong(getLITERAL()));
    } catch (NumberFormatException e) {
      Constant c = Constant.create(0L);
      c.error = true;
      return c;
    }
  }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 302
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

    private TypeDecl type_compute() {  return typeLong();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
