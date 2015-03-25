
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;



public class Proceed extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
        type_computed = false;
        type_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Proceed clone() throws CloneNotSupportedException {
        Proceed node = (Proceed)super.clone();
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Proceed copy() {
      try {
          Proceed node = (Proceed)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Proceed fullCopy() {
        Proceed res = (Proceed)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Advice.jrag at line 107


  public void typeCheck() {
    AroundSpec spec = aroundSpec();
    if (spec == null)
      error("Can only use proceed within around advice");
    else if (spec.getNumAroundParameter() != getNumArg()) {
      error("Proceed called with the wrong number of arguments");
    } else {
      for (int i = 0; i < getNumArg(); i++) {
        TypeDecl exprType = getArg(i).type();
        TypeDecl parmType = spec.getAroundParameter(i).type();

        if (!exprType.methodInvocationConversionTo(parmType)
            && !exprType.isUnknown() && !parmType.isUnknown())
        {
            error("Expression " + getArg(i) + " of type " + exprType.typeName()
            + " is not compatible with the proceed parameter type " +
            parmType.typeName());
        }
      }
    }
  }

    // Declared in AdviceCodegen.jrag at line 305


  private ArrayList buildProceedArgs(Body b)
  {
    ArrayList list = new ArrayList();
    AroundSpec spec = aroundSpec();
    int arg = 0;

    for(int i = 0; i < spec.getNumAroundParameter(); i++)
    {
      TypeDecl type = spec.getAroundParameter(i).type();
      list.add(
        asImmediate(b, getArg(arg).type().emitCastTo(b, getArg(arg), type))
      );
      arg++;
    }
    return list;
  }

    // Declared in AdviceCodegen.jrag at line 325


  public soot.Value eval(Body b)
  {
    AroundSpec spec = aroundSpec();
    soot.SootMethodRef ref = spec.proceedSig().getSootMethodRef();
    ArrayList args = buildProceedArgs(b);

    soot.Value result = Jimple.v().newStaticInvokeExpr(ref, args);

    return spec.returnType().isVoid() ?
                result : asLocal(b, result, type().getSootType());
  }

    // Declared in DeclareCodegen.jrag at line 70


    protected boolean reachedException(TypeDecl type)
    {
        return true; // assume that proceed() can throw anything
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 30

    public Proceed() {
        super();

        setChild(new List(), 0);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 30
    public Proceed(List<Expr> p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 15


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 30
    public void setArgList(List<Expr> list) {
        setChild(list, 0);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumArg = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumArg() {
        return getArgList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Expr getArg(int i) {
        return (Expr)getArgList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addArg(Expr node) {
        List<Expr> list = getArgList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setArg(Expr node, int i) {
        List<Expr> list = getArgList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<Expr> getArgs() {
        return getArgList();
    }

    // Declared in AspectJ.ast at line 27

    public List<Expr> getArgsNoTransform() {
        return getArgListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgList() {
        return (List<Expr>)getChild(0);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgListNoTransform() {
        return (List<Expr>)getChildNoTransform(0);
    }

    // Declared in Advice.jrag at line 104
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

    private TypeDecl type_compute() {  return aroundSpec() != null ? aroundSpec().returnType()
                                           : unknownType();  }

    // Declared in AdviceCodegen.jrag at line 271
 @SuppressWarnings({"unchecked", "cast"})     public AroundSpec aroundSpec() {
        AroundSpec aroundSpec_value = getParent().Define_AroundSpec_aroundSpec(this, null);
        return aroundSpec_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
