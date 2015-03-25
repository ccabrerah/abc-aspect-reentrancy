
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class AfterThrowingSymbol extends SymbolKind implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public AfterThrowingSymbol clone() throws CloneNotSupportedException {
        AfterThrowingSymbol node = (AfterThrowingSymbol)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AfterThrowingSymbol copy() {
      try {
          AfterThrowingSymbol node = (AfterThrowingSymbol)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AfterThrowingSymbol fullCopy() {
        AfterThrowingSymbol res = (AfterThrowingSymbol)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in tm.ast at line 3
    // Declared in tm.ast line 14

    public AfterThrowingSymbol() {
        super();

        setChild(new Opt(), 0);

    }

    // Declared in tm.ast at line 11


    // Declared in tm.ast line 14
    public AfterThrowingSymbol(Opt<VarAccess> p0) {
        setChild(p0, 0);
    }

    // Declared in tm.ast at line 15


  protected int numChildren() {
    return 1;
  }

    // Declared in tm.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 14
    public void setVarAccessOpt(Opt<VarAccess> opt) {
        setChild(opt, 0);
    }

    // Declared in tm.ast at line 6


    public boolean hasVarAccess() {
        return getVarAccessOpt().getNumChild() != 0;
    }

    // Declared in tm.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public VarAccess getVarAccess() {
        return (VarAccess)getVarAccessOpt().getChild(0);
    }

    // Declared in tm.ast at line 14


    public void setVarAccess(VarAccess node) {
        getVarAccessOpt().setChild(node, 0);
    }

    // Declared in tm.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<VarAccess> getVarAccessOpt() {
        return (Opt<VarAccess>)getChild(0);
    }

    // Declared in tm.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<VarAccess> getVarAccessOptNoTransform() {
        return (Opt<VarAccess>)getChildNoTransform(0);
    }

    // Declared in SymbolCodegen.jrag at line 39
 @SuppressWarnings({"unchecked", "cast"})     public String kind() {
        String kind_value = kind_compute();
        return kind_value;
    }

    private String kind_compute() {  return "afterThrowing";  }

    // Declared in SymbolCodegen.jrag at line 92
 @SuppressWarnings({"unchecked", "cast"})     public AbstractAdviceSpec adviceSpec() {
        AbstractAdviceSpec adviceSpec_value = adviceSpec_compute();
        return adviceSpec_value;
    }

    private AbstractAdviceSpec adviceSpec_compute() {
        if (!hasVarAccess())
            return new AfterThrowingAdvice(pos());
        ParameterDeclaration v = (ParameterDeclaration) getVarAccess().decl();
        return new AfterThrowingArgAdvice(v.formal(), pos());
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
