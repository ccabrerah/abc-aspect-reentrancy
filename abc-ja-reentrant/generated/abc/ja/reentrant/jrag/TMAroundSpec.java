
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;


public class TMAroundSpec extends AroundSpec implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public TMAroundSpec clone() throws CloneNotSupportedException {
        TMAroundSpec node = (TMAroundSpec)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TMAroundSpec copy() {
      try {
          TMAroundSpec node = (TMAroundSpec)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TMAroundSpec fullCopy() {
        TMAroundSpec res = (TMAroundSpec)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in TMCodegen.jrag at line 200


    public java.util.List methodFormals()
    {
        return ((TraceMatchDecl) getParent()).bodyAdviceFormals();
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 5

    public TMAroundSpec() {
        super();

        setChild(new List(), 0);

    }

    // Declared in tm.ast at line 11


    // Declared in tm.ast line 5
    public TMAroundSpec(List<ParameterDeclaration> p0, Access p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in tm.ast at line 16


  protected int numChildren() {
    return 2;
  }

    // Declared in tm.ast at line 19

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 16
    public void setParameterList(List<ParameterDeclaration> list) {
        setChild(list, 0);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumParameter = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumParameter() {
        return getParameterList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration getParameter(int i) {
        return (ParameterDeclaration)getParameterList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addParameter(ParameterDeclaration node) {
        List<ParameterDeclaration> list = getParameterList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setParameter(ParameterDeclaration node, int i) {
        List<ParameterDeclaration> list = getParameterList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<ParameterDeclaration> getParameters() {
        return getParameterList();
    }

    // Declared in AspectJ.ast at line 27

    public List<ParameterDeclaration> getParametersNoTransform() {
        return getParameterListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterList() {
        return (List<ParameterDeclaration>)getChild(0);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ParameterDeclaration> getParameterListNoTransform() {
        return (List<ParameterDeclaration>)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 21
    public void setTypeAccess(Access node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public Access getTypeAccess() {
        return (Access)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public Access getTypeAccessNoTransform() {
        return (Access)getChildNoTransform(1);
    }

    // Declared in TMCodegen.jrag at line 261
 @SuppressWarnings({"unchecked", "cast"})     public int getNumAroundParameter() {
        int getNumAroundParameter_value = getNumAroundParameter_compute();
        return getNumAroundParameter_value;
    }

    private int getNumAroundParameter_compute() {  return aroundSymbol().getNumVarAccess();  }

    // Declared in TMCodegen.jrag at line 263
 @SuppressWarnings({"unchecked", "cast"})     public ParameterDeclaration getAroundParameter(int i) {
        ParameterDeclaration getAroundParameter_int_value = getAroundParameter_compute(i);
        return getAroundParameter_int_value;
    }

    private ParameterDeclaration getAroundParameter_compute(int i) {  return (ParameterDeclaration) aroundSymbol().getVarAccess(i).decl();  }

    // Declared in TMCodegen.jrag at line 257
 @SuppressWarnings({"unchecked", "cast"})     public AroundSymbol aroundSymbol() {
        AroundSymbol aroundSymbol_value = getParent().Define_AroundSymbol_aroundSymbol(this, null);
        return aroundSymbol_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
