
package abc.ja.eaj.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;

public class CONSTANT_Methodref_Info extends CONSTANT_Info {
    // Declared in BytecodeCONSTANT.jrag at line 186

    public int class_index;

    // Declared in BytecodeCONSTANT.jrag at line 187

    public int name_and_type_index;

    // Declared in BytecodeCONSTANT.jrag at line 189


    public CONSTANT_Methodref_Info(BytecodeParser parser) {
      super(parser);
      class_index = p.u2();
      name_and_type_index = p.u2();
    }

    // Declared in BytecodeCONSTANT.jrag at line 195


    public String toString() {
      return "MethodRefInfo: " + class_index + " " + name_and_type_index;
    }


}
