
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;


public class AroundSymbol extends SymbolKind implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public AroundSymbol clone() throws CloneNotSupportedException {
        AroundSymbol node = (AroundSymbol)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AroundSymbol copy() {
      try {
          AroundSymbol node = (AroundSymbol)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AroundSymbol fullCopy() {
        AroundSymbol res = (AroundSymbol)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in TMInfo.jrag at line 67

    void addAroundFormals(java.util.List<Formal> formals)
    {
        for (int i = 0; i < getNumVarAccess(); i++) {
            ParameterDeclaration decl;
            decl = (ParameterDeclaration) getVarAccess(i).decl();
            formals.add(decl.formal());
        }
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 15

    public AroundSymbol() {
        super();

        setChild(new List(), 0);

    }

    // Declared in tm.ast at line 11


    // Declared in tm.ast line 15
    public AroundSymbol(List<VarAccess> p0) {
        setChild(p0, 0);
    }

    // Declared in tm.ast at line 15


  protected int numChildren() {
    return 1;
  }

    // Declared in tm.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 15
    public void setVarAccessList(List<VarAccess> list) {
        setChild(list, 0);
    }

    // Declared in tm.ast at line 6


    private int getNumVarAccess = 0;

    // Declared in tm.ast at line 7

    public int getNumVarAccess() {
        return getVarAccessList().getNumChild();
    }

    // Declared in tm.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public VarAccess getVarAccess(int i) {
        return (VarAccess)getVarAccessList().getChild(i);
    }

    // Declared in tm.ast at line 15


    public void addVarAccess(VarAccess node) {
        List<VarAccess> list = getVarAccessList();
        list.addChild(node);
    }

    // Declared in tm.ast at line 20


    public void setVarAccess(VarAccess node, int i) {
        List<VarAccess> list = getVarAccessList();
        list.setChild(node, i);
    }

    // Declared in tm.ast at line 24

    public List<VarAccess> getVarAccesss() {
        return getVarAccessList();
    }

    // Declared in tm.ast at line 27

    public List<VarAccess> getVarAccesssNoTransform() {
        return getVarAccessListNoTransform();
    }

    // Declared in tm.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<VarAccess> getVarAccessList() {
        return (List<VarAccess>)getChild(0);
    }

    // Declared in tm.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<VarAccess> getVarAccessListNoTransform() {
        return (List<VarAccess>)getChildNoTransform(0);
    }

    // Declared in SymbolCodegen.jrag at line 40
 @SuppressWarnings({"unchecked", "cast"})     public String kind() {
        String kind_value = kind_compute();
        return kind_value;
    }

    private String kind_compute() {  return "around";  }

    // Declared in SymbolCodegen.jrag at line 83
 @SuppressWarnings({"unchecked", "cast"})     public AbstractAdviceSpec adviceSpec() {
        AbstractAdviceSpec adviceSpec_value = adviceSpec_compute();
        return adviceSpec_value;
    }

    private AbstractAdviceSpec adviceSpec_compute() {  return new BeforeAdvice(pos());  }

    // Declared in TMCodegen.jrag at line 247
 @SuppressWarnings({"unchecked", "cast"})     public AroundSymbol aroundSymbol() {
        AroundSymbol aroundSymbol_value = aroundSymbol_compute();
        return aroundSymbol_value;
    }

    private AroundSymbol aroundSymbol_compute() {  return this;  }

    // Declared in TMPointcutCodegen.jrag at line 38
 @SuppressWarnings({"unchecked", "cast"})     public boolean matchesBefore() {
        boolean matchesBefore_value = matchesBefore_compute();
        return matchesBefore_value;
    }

    private boolean matchesBefore_compute() {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
