
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;


public class NameBindingPattern extends BindingPattern implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public NameBindingPattern clone() throws CloneNotSupportedException {
        NameBindingPattern node = (NameBindingPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NameBindingPattern copy() {
      try {
          NameBindingPattern node = (NameBindingPattern)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NameBindingPattern fullCopy() {
        NameBindingPattern res = (NameBindingPattern)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 137

    public NameBindingPattern() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 137
    public NameBindingPattern(Pattern p0) {
        setChild(p0, 0);
    }

    // Declared in AspectJ.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in AspectJ.ast at line 17

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 137
    public void setPattern(Pattern node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public Pattern getPattern() {
        return (Pattern)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public Pattern getPatternNoTransform() {
        return (Pattern)getChildNoTransform(0);
    }

    // Declared in Pointcuts.jrag at line 593
 @SuppressWarnings({"unchecked", "cast"})     public boolean binds(String var) {
        boolean binds_String_value = binds_compute(var);
        return binds_String_value;
    }

    private boolean binds_compute(String var) {  return getPattern().isVariable() && getPattern().variableName().equals(var);  }

    // Declared in PointcutsCodegen.jrag at line 207
 @SuppressWarnings({"unchecked", "cast"})     public ArgPattern argPattern() {
        ArgPattern argPattern_value = argPattern_compute();
        return argPattern_value;
    }

    private ArgPattern argPattern_compute() {
        Pattern pat = getPattern();
        if (pat.isVariable())
            return new ArgVar(new Var(pat.variableName(), pos()), pos());
        else
            return new ArgType(AbcFactory.AbcType(pat.type().getSootType()),
                               pos());
    }

    // Declared in PointcutsCodegen.jrag at line 225
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut targetPointcut() {
        Pointcut targetPointcut_value = targetPointcut_compute();
        return targetPointcut_value;
    }

    private Pointcut targetPointcut_compute() {
        Pattern pat = getPattern();
        if (pat.isVariable())
            return new TargetVar(new Var(pat.variableName(), pos()), pos());
        return new TargetType(AbcFactory.AbcType(pat.type().getSootType()),
                              pos());
    }

    // Declared in PointcutsCodegen.jrag at line 242
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut thisPointcut() {
        Pointcut thisPointcut_value = thisPointcut_compute();
        return thisPointcut_value;
    }

    private Pointcut thisPointcut_compute() {
        Pattern pat = getPattern();
        if (pat.isVariable())
            return new ThisVar(new Var(pat.variableName(), pos()), pos());
        return new ThisType(AbcFactory.AbcType(pat.type().getSootType()),
                            pos());
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
