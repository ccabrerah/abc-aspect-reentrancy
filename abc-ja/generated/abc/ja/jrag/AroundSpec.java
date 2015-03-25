
package abc.ja.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class AroundSpec extends AdviceSpec implements Cloneable {
    public void flushCache() {
        super.flushCache();
        proceedMethod_computed = false;
        proceedMethod_value = null;
        proceedSig_computed = false;
        proceedSig_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AroundSpec clone() throws CloneNotSupportedException {
        AroundSpec node = (AroundSpec)super.clone();
        node.proceedMethod_computed = false;
        node.proceedMethod_value = null;
        node.proceedSig_computed = false;
        node.proceedSig_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AroundSpec copy() {
      try {
          AroundSpec node = (AroundSpec)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AroundSpec fullCopy() {
        AroundSpec res = (AroundSpec)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AdviceCodegen.jrag at line 209

  public abc.weaving.aspectinfo.AdviceSpec adviceSpec() {
    MethodCategory.register(proceedSig(), MethodCategory.PROCEED);
    AbcType return_type = AbcFactory.AbcType(returnType().getSootType());
    return new AroundAdvice(return_type, proceedSig(), pos());
  }

    // Declared in AdviceCodegen.jrag at line 275


  protected java.util.List proceedFormals()
  {
    ArrayList list = new ArrayList();
    for(int i = 0; i < getNumAroundParameter(); i++)
      list.add(getAroundParameter(i).formal());
    return list;
  }

    // Declared in AdviceCodegen.jrag at line 283


  protected java.util.List proceedParamList()
  {
    ArrayList list = new ArrayList();
    for(int i = 0; i < getNumAroundParameter(); i++)
        list.add(getAroundParameter(i).type().getSootType());
    return list;
  }

    // Declared in AdviceCodegen.jrag at line 348


  public void jimplify1phase2()
  {
    hostType().getSootClassDecl().addMethod(proceedMethod());
  }

    // Declared in AdviceCodegen.jrag at line 353


  public void jimplify2()
  {
    JimpleBody body = Jimple.v().newBody(proceedMethod());
    proceedMethod().setActiveBody(body);
    Body b = new Body(hostType(), body, this);
    jimplify2(b);
    b.add(Jimple.v().newReturnVoidStmt());
  }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 21

    public AroundSpec() {
        super();

        setChild(new List(), 0);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 21
    public AroundSpec(List<ParameterDeclaration> p0, Access p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in AspectJ.ast at line 16


  protected int numChildren() {
    return 2;
  }

    // Declared in AspectJ.ast at line 19

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

    // Declared in Advice.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl returnType() {
        TypeDecl returnType_value = returnType_compute();
        return returnType_value;
    }

    private TypeDecl returnType_compute() {  return getTypeAccess().type();  }

    // Declared in AdviceCodegen.jrag at line 38
 @SuppressWarnings({"unchecked", "cast"})     public String kind() {
        String kind_value = kind_compute();
        return kind_value;
    }

    private String kind_compute() {  return "around";  }

    // Declared in AdviceCodegen.jrag at line 269
 @SuppressWarnings({"unchecked", "cast"})     public AroundSpec aroundSpec() {
        AroundSpec aroundSpec_value = aroundSpec_compute();
        return aroundSpec_value;
    }

    private AroundSpec aroundSpec_compute() {  return this;  }

    protected boolean proceedMethod_computed = false;
    protected SootMethod proceedMethod_value;
    // Declared in AdviceCodegen.jrag at line 337
 @SuppressWarnings({"unchecked", "cast"})     public SootMethod proceedMethod() {
        if(proceedMethod_computed)
            return proceedMethod_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        proceedMethod_value = proceedMethod_compute();
        if(isFinal && num == boundariesCrossed)
            proceedMethod_computed = true;
        return proceedMethod_value;
    }

    private SootMethod proceedMethod_compute() {
    MethodSig ms = aroundSpec().proceedSig();
    SootMethod m = new SootMethod(ms.getName(), proceedParamList(),
                        ms.getReturnType().getSootType(), ms.getModifiers(),
                        new ArrayList());
    return m;
  }

    protected boolean proceedSig_computed = false;
    protected MethodSig proceedSig_value;
    // Declared in AdviceCodegen.jrag at line 293
 @SuppressWarnings({"unchecked", "cast"})     public MethodSig proceedSig() {
        if(proceedSig_computed)
            return proceedSig_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        proceedSig_value = getParent().Define_MethodSig_proceedSig(this, null);
        if(isFinal && num == boundariesCrossed)
            proceedSig_computed = true;
        return proceedSig_value;
    }

    // Declared in Advice.jrag at line 97
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return super.Define_NameType_nameType(caller, child);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
