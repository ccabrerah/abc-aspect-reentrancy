
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class DeclareError extends BodyDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclareError clone() throws CloneNotSupportedException {
        DeclareError node = (DeclareError)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclareError copy() {
      try {
          DeclareError node = (DeclareError)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclareError fullCopy() {
        DeclareError res = (DeclareError)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Declare.jrag at line 34

  public void typeCheck() {
    HashSet set = new HashSet();
    collectNonStaticPointcuts(set);
    if(!set.isEmpty()) {
      StringBuffer s = new StringBuffer();
      for(Iterator iter = set.iterator(); iter.hasNext(); )
        s.append(iter.next() + " ");
      s.append("pointcut designator cannot be used in declare statement");
      error(s.toString());
    }
  }

    // Declared in DeclareCodegen.jrag at line 34


    public void jimplify2()
    {
        getPointcutExpr().jimplify2();
        globalAspectInfo().addDeclareMessage(declareMessage());
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 40

    public DeclareError() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 40
    public DeclareError(PointcutExpr p0, String p1) {
        setChild(p0, 0);
        setMessage(p1);
    }

    // Declared in AspectJ.ast at line 16


    // Declared in AspectJ.ast line 40
    public DeclareError(PointcutExpr p0, beaver.Symbol p1) {
        setChild(p0, 0);
        setMessage(p1);
    }

    // Declared in AspectJ.ast at line 21


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 24

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 40
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 40
    private String tokenString_Message;

    // Declared in AspectJ.ast at line 3

    public void setMessage(String value) {
        tokenString_Message = value;
    }

    // Declared in AspectJ.ast at line 6

    public int Messagestart;

    // Declared in AspectJ.ast at line 7

    public int Messageend;

    // Declared in AspectJ.ast at line 8

    public void setMessage(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setMessage is only valid for String lexemes");
        tokenString_Message = (String)symbol.value;
        Messagestart = symbol.getStart();
        Messageend = symbol.getEnd();
    }

    // Declared in AspectJ.ast at line 15

    public String getMessage() {
        return tokenString_Message != null ? tokenString_Message : "";
    }

    // Declared in DeclareCodegen.jrag at line 47
 @SuppressWarnings({"unchecked", "cast"})     public DeclareMessage declareMessage() {
        DeclareMessage declareMessage_value = declareMessage_compute();
        return declareMessage_value;
    }

    private DeclareMessage declareMessage_compute() {  return new DeclareMessage(DeclareMessage.ERROR,
                           getPointcutExpr().pointcut(),
                           getMessage(),
                           aspectClass(),
                           pos());  }

    // Declared in Pointcuts.jrag at line 114
    public boolean Define_boolean_bindsInCurrentCflow(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()) {
            return getPointcutExpr().binds(name) > 0;
        }
        return getParent().Define_boolean_bindsInCurrentCflow(this, caller, name);
    }

    // Declared in Patterns.jrag at line 133
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getPointcutExprNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
