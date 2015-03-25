
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;



// ----------------------- Declare (...) -------------------


public class DeclareParentsExtends extends BodyDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclareParentsExtends clone() throws CloneNotSupportedException {
        DeclareParentsExtends node = (DeclareParentsExtends)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclareParentsExtends copy() {
      try {
          DeclareParentsExtends node = (DeclareParentsExtends)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DeclareParentsExtends fullCopy() {
        DeclareParentsExtends res = (DeclareParentsExtends)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in DeclareParentsAnalysis.jrag at line 459


  // add declare precedences to generic inter-type collection phase
  protected void collectIntertypeParentDecls(HashMap map) {
    super.collectIntertypeParentDecls(map);
    String key = "declare_parents_extends";
    if(!map.containsKey(key))
      map.put(key, new ArrayList());
    Collection c = (Collection)map.get(key);
    c.add(this);
  }

    // Declared in DeclareParentsAnalysis.jrag at line 489


  public void generateIntertypeDecls() {
    for(int i = 0; i < getNumTypeAccess(); i++) {
      TypeDecl typeDecl = getTypeAccess(i).type();
      typeDecl.mustBePublic = true;
    }
  }

    // Declared in DeclareParentsPrettyPrint.jrag at line 11

  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("declare parents: ");
    getPattern().toString(s);
    s.append(" extends ");
    for(int i = 0; i < getNumTypeAccess(); i++) {
      if(i != 0) s.append(", ");
      getTypeAccess(i).toString(s);
    }
    s.append(";\n");
  }

    // Declared in AspectJ.ast at line 3
    // Declared in AspectJ.ast line 37

    public DeclareParentsExtends() {
        super();

        setChild(new List(), 1);

    }

    // Declared in AspectJ.ast at line 11


    // Declared in AspectJ.ast line 37
    public DeclareParentsExtends(Pattern p0, List<Access> p1) {
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
    // Declared in AspectJ.ast line 37
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

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 37
    public void setTypeAccessList(List<Access> list) {
        setChild(list, 1);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumTypeAccess = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumTypeAccess() {
        return getTypeAccessList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Access getTypeAccess(int i) {
        return (Access)getTypeAccessList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addTypeAccess(Access node) {
        List<Access> list = getTypeAccessList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setTypeAccess(Access node, int i) {
        List<Access> list = getTypeAccessList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<Access> getTypeAccesss() {
        return getTypeAccessList();
    }

    // Declared in AspectJ.ast at line 27

    public List<Access> getTypeAccesssNoTransform() {
        return getTypeAccessListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getTypeAccessList() {
        return (List<Access>)getChild(1);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getTypeAccessListNoTransform() {
        return (List<Access>)getChildNoTransform(1);
    }

    // Declared in Patterns.jrag at line 141
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.TYPE_NAME;
        }
        if(caller == getPatternNoTransform()) {
            return NameType.TYPE_NAME;
        }
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
