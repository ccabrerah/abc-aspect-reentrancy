
package abc.ja.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class DeclareSoft extends BodyDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclareSoft clone() throws CloneNotSupportedException {
        DeclareSoft node = (DeclareSoft)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclareSoft copy() {
      try {
          DeclareSoft node = (DeclareSoft)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclareSoft fullCopy() {
        DeclareSoft res = (DeclareSoft)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Declare.jrag at line 45

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
    if(!getAccess().type().instanceOf(typeThrowable()))
      error(getAccess().type().typeName() + " is not a subtype of Throwable");
  }

    // Declared in DeclareCodegen.jrag at line 55



    public void jimplify2()
    {
        getPointcutExpr().jimplify2();
        globalAspectInfo().addDeclareSoft(
            new abc.weaving.aspectinfo.DeclareSoft(
                getAccess().type().abcType(),
                getPointcutExpr().pointcut(),
                aspectClass(),
                pos()));
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 41

    public DeclareSoft() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 41
    public DeclareSoft(Access p0, PointcutExpr p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in AspectJ.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in AspectJ.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 41
    public void setAccess(Access node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public Access getAccess() {
        return (Access)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public Access getAccessNoTransform() {
        return (Access)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 41
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(1);
    }

    // Declared in Pointcuts.jrag at line 110
    public boolean Define_boolean_bindsInCurrentCflow(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()) {
            return getPointcutExpr().binds(name) > 0;
        }
        return getParent().Define_boolean_bindsInCurrentCflow(this, caller, name);
    }

    // Declared in Patterns.jrag at line 137
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        if(caller == getPointcutExprNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
