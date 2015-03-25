
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;


public class PointcutAccess extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public PointcutAccess clone() throws CloneNotSupportedException {
        PointcutAccess node = (PointcutAccess)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PointcutAccess copy() {
      try {
          PointcutAccess node = (PointcutAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PointcutAccess fullCopy() {
        PointcutAccess res = (PointcutAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Pointcuts.jrag at line 219


    public void nameCheck()
    {
        SimpleSet decls = decls();
        if (decls.size() == 0)
            error("Cannot find pointcut " + name());
        else if (decls.size() > 1)
            error("Ambiguous reference to pointcut " + name());
    }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 31

    public PointcutAccess() {
        super();


    }

    // Declared in AspectJ.ast at line 10


    // Declared in AspectJ.ast line 31
    public PointcutAccess(String p0) {
        setID(p0);
    }

    // Declared in AspectJ.ast at line 15


    // Declared in AspectJ.ast line 31
    public PointcutAccess(beaver.Symbol p0) {
        setID(p0);
    }

    // Declared in AspectJ.ast at line 19


  protected int numChildren() {
    return 0;
  }

    // Declared in AspectJ.ast at line 22

  public boolean mayHaveRewrite() { return false; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 31
    private String tokenString_ID;

    // Declared in AspectJ.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in AspectJ.ast at line 6

    public int IDstart;

    // Declared in AspectJ.ast at line 7

    public int IDend;

    // Declared in AspectJ.ast at line 8

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in AspectJ.ast at line 15

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    // Declared in Pointcuts.jrag at line 189
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        String name_value = name_compute();
        return name_value;
    }

    private String name_compute() {  return getID();  }

    // Declared in Pointcuts.jrag at line 190
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        NameType predNameType_value = predNameType_compute();
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.TYPE_NAME;  }

    // Declared in Pointcuts.jrag at line 192
 @SuppressWarnings({"unchecked", "cast"})     public PointcutDecl decl() {
        PointcutDecl decl_value = decl_compute();
        return decl_value;
    }

    private PointcutDecl decl_compute() {
        SimpleSet decls = decls();
        if (decls.size() == 1) 
            return (PointcutDecl) decls.iterator().next();
        return null;
    }

    // Declared in Pointcuts.jrag at line 200
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet decls() {
        SimpleSet decls_value = decls_compute();
        return decls_value;
    }

    private SimpleSet decls_compute() {
        SimpleSet concrete = SimpleSet.emptySet;
        SimpleSet abstr = SimpleSet.emptySet;

        Iterator i = lookupPointcut(name()).iterator();
        while (i.hasNext()) {
            PointcutDecl decl = (PointcutDecl) i.next();
            if (decl.isAbstract())
                abstr = abstr.add(decl);
            else
                concrete = concrete.add(decl);
        }
        if (concrete.isEmpty())
            return abstr;
        else
            return concrete;
    }

    // Declared in Pointcuts.jrag at line 305
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        String dumpString_value = dumpString_compute();
        return dumpString_value;
    }

    private String dumpString_compute() {  return super.dumpString() + " " + decl().name();  }

    // Declared in Pointcuts.jrag at line 228
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupPointcut(String name) {
        SimpleSet lookupPointcut_String_value = getParent().Define_SimpleSet_lookupPointcut(this, null, name);
        return lookupPointcut_String_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
