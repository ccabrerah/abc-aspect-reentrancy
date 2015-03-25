
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;



public class TMAdviceSpec extends AdviceSpec implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public TMAdviceSpec clone() throws CloneNotSupportedException {
        TMAdviceSpec node = (TMAdviceSpec)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TMAdviceSpec copy() {
      try {
          TMAdviceSpec node = (TMAdviceSpec)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TMAdviceSpec fullCopy() {
        TMAdviceSpec res = (TMAdviceSpec)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in TMCodegen.jrag at line 43


    public abc.weaving.aspectinfo.AdviceSpec adviceSpec()
    {
        throw new RuntimeException("should not be called");
    }

    // Declared in TMCodegen.jrag at line 195


    public java.util.List methodFormals()
    {
        return ((TraceMatchDecl) getParent()).bodyAdviceFormals();
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 4

    public TMAdviceSpec() {
        super();

        setChild(new List(), 0);

    }

    // Declared in tm.ast at line 11


    // Declared in tm.ast line 4
    public TMAdviceSpec(List<ParameterDeclaration> p0) {
        setChild(p0, 0);
    }

    // Declared in tm.ast at line 15


  protected int numChildren() {
    return 1;
  }

    // Declared in tm.ast at line 18

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

    // Declared in TMCodegen.jrag at line 30
 @SuppressWarnings({"unchecked", "cast"})     public String kind() {
        String kind_value = kind_compute();
        return kind_value;
    }

    private String kind_compute() {  return "UNUSED";  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
