
package abc.ja.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;

public class CONSTANT_Class_Info extends CONSTANT_Info {
    // Declared in BytecodeCONSTANT.jrag at line 12

    public int name_index;

    // Declared in BytecodeCONSTANT.jrag at line 14


    public CONSTANT_Class_Info(BytecodeParser parser) {
      super(parser);
      name_index = p.u2();
    }

    // Declared in BytecodeCONSTANT.jrag at line 19


    public String toString() {
      return "ClassInfo: " + name();
    }

    // Declared in BytecodeCONSTANT.jrag at line 23


    public String name() {
      String name = ((CONSTANT_Utf8_Info) this.p.constantPool[name_index]).string();
      //name = name.replaceAll("\\/", ".");
      name = name.replace('/', '.');
      return name;
    }

    // Declared in BytecodeCONSTANT.jrag at line 30


    public String simpleName() {
      String name = name();
      name = name.replace('$', '.');
      int pos = name.lastIndexOf('.');
      return name.substring(pos + 1, name.length());
    }

    // Declared in BytecodeCONSTANT.jrag at line 37


    public String packageDecl() {
      String name = name();
      name = name.replace('$', '.');
      int pos = name.lastIndexOf('.');
      if(pos == -1)
        return "";
      return name.substring(0, pos);
    }

    // Declared in BytecodeCONSTANT.jrag at line 46


    public Access access() {
      String name = name();
      name = name.replace('$', '.');
      int index = -1;
      int pos = 0;
      Access result = null;
      do {
        pos = name.indexOf('.', index+1);
        if(pos == -1)
          pos = name.length();
        String s = name.substring(index+1, pos);
        if(index == -1) {
          result = new ParseName(s);
        }
        else {
          result = result.qualifiesAccess(new ParseName(s));
        }
        index = pos;
      } while(pos != name.length());
      return result;
    }


}
