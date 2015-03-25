
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;



// ---------------------- Member patterns ----------------

public abstract class MemberPattern extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public MemberPattern clone() throws CloneNotSupportedException {
        MemberPattern node = (MemberPattern)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in PatternsCodegen.jrag at line 134


    protected static boolean matchesFormals(java.util.List/*<FormalPattern>*/ fpats, int fpi, java.util.List/*<soot.Type>*/ ftypes, int fti) {
      // FIXME: BRUTE FORCE MATCHING. DO SOMETHING MORE CLEVER!
      while (fpi < fpats.size()) {
        FormalPattern fp = (FormalPattern)fpats.get(fpi);
        if (fp instanceof ConcreteFormalPattern) {
          if (fti >= ftypes.size()) return false;
          Pattern pat = ((ConcreteFormalPattern)fp).getPattern();
          soot.Type ft = (soot.Type)ftypes.get(fti);
          if (!pat.matchesType(ft)) return false;
        } else {
          // DOTDOT
          while (fti <= ftypes.size()) {
            if (matchesFormals(fpats, fpi+1, ftypes, fti)) return true;
            fti++;
          }
          return false;
        }
        fpi++;
        fti++;
      }
      return fti == ftypes.size();
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 121

    public MemberPattern() {
        super();


    }

    // Declared in AspectJ.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in Patterns.jrag at line 163
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodPattern() {
        boolean isMethodPattern_value = isMethodPattern_compute();
        return isMethodPattern_value;
    }

    private boolean isMethodPattern_compute() {  return false;  }

    // Declared in Patterns.jrag at line 164
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstructorPattern() {
        boolean isConstructorPattern_value = isConstructorPattern_compute();
        return isConstructorPattern_value;
    }

    private boolean isConstructorPattern_compute() {  return false;  }

    // Declared in Patterns.jrag at line 165
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFieldPattern() {
        boolean isFieldPattern_value = isFieldPattern_compute();
        return isFieldPattern_value;
    }

    private boolean isFieldPattern_compute() {  return false;  }

    // Declared in PatternsCodegen.jrag at line 63
 @SuppressWarnings({"unchecked", "cast"})     public FieldPattern fieldPattern() {
        FieldPattern fieldPattern_value = fieldPattern_compute();
        return fieldPattern_value;
    }

    private FieldPattern fieldPattern_compute() {
        throw new RuntimeException("Tried get a FieldPattern from a "
                                    + getClass().getName());
    }

    // Declared in PatternsCodegen.jrag at line 68
 @SuppressWarnings({"unchecked", "cast"})     public MethodPattern methodPattern() {
        MethodPattern methodPattern_value = methodPattern_compute();
        return methodPattern_value;
    }

    private MethodPattern methodPattern_compute() {
        throw new RuntimeException("Tried get a MethodPattern from a "
                                    + getClass().getName());
    }

    // Declared in PatternsCodegen.jrag at line 73
 @SuppressWarnings({"unchecked", "cast"})     public ConstructorPattern constructorPattern() {
        ConstructorPattern constructorPattern_value = constructorPattern_compute();
        return constructorPattern_value;
    }

    private ConstructorPattern constructorPattern_compute() {
        throw new RuntimeException("Tried get a ConstructorPattern from a "
                                    + getClass().getName());
    }

    // Declared in Pointcuts.jrag at line 619
 @SuppressWarnings({"unchecked", "cast"})     public boolean refersToInterfaceConstructor() {
        boolean refersToInterfaceConstructor_value = refersToInterfaceConstructor_compute();
        return refersToInterfaceConstructor_value;
    }

    private boolean refersToInterfaceConstructor_compute() {  return false;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
