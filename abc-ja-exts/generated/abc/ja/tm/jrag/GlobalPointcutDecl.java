
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;



public class GlobalPointcutDecl extends BodyDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public GlobalPointcutDecl clone() throws CloneNotSupportedException {
        GlobalPointcutDecl node = (GlobalPointcutDecl)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public GlobalPointcutDecl copy() {
      try {
          GlobalPointcutDecl node = (GlobalPointcutDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public GlobalPointcutDecl fullCopy() {
        GlobalPointcutDecl res = (GlobalPointcutDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Pointcuts.jrag at line 188




  //
  // register all global pointcut declarations so that they are
  // used during matching and weaving
  //
  public void jimplify2()
  {
    getPointcutExpr().jimplify2();
    ((abc.ja.eaj.AbcExtension) abc.main.Main.v().getAbcExtension())
        .registerGlobalPointcutDecl(getPattern().classnamePattern(),
                                    getPointcutExpr().pointcut());
  }

    // Declared in eaj.ast at line 3
    // Declared in eaj.ast line 22

    public GlobalPointcutDecl() {
        super();


    }

    // Declared in eaj.ast at line 10


    // Declared in eaj.ast line 22
    public GlobalPointcutDecl(Pattern p0, PointcutExpr p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in eaj.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in eaj.ast at line 18

  public boolean mayHaveRewrite() { return false; }

    // Declared in eaj.ast at line 2
    // Declared in eaj.ast line 22
    public void setPattern(Pattern node) {
        setChild(node, 0);
    }

    // Declared in eaj.ast at line 5

    public Pattern getPattern() {
        return (Pattern)getChild(0);
    }

    // Declared in eaj.ast at line 9


    public Pattern getPatternNoTransform() {
        return (Pattern)getChildNoTransform(0);
    }

    // Declared in eaj.ast at line 2
    // Declared in eaj.ast line 22
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 1);
    }

    // Declared in eaj.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(1);
    }

    // Declared in eaj.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(1);
    }

    // Declared in Pointcuts.jrag at line 27
    public boolean Define_boolean_bindsInCurrentCflow(ASTNode caller, ASTNode child, String name) {
        if(caller == getPointcutExprNoTransform()) {
            return getPointcutExpr().binds(name) > 0;
        }
        return getParent().Define_boolean_bindsInCurrentCflow(this, caller, name);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
