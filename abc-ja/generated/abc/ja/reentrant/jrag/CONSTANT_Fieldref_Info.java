
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;

public class CONSTANT_Fieldref_Info extends CONSTANT_Info {
    // Declared in BytecodeCONSTANT.jrag at line 87

    public int class_index;

    // Declared in BytecodeCONSTANT.jrag at line 88

    public int name_and_type_index;

    // Declared in BytecodeCONSTANT.jrag at line 90


    public CONSTANT_Fieldref_Info(BytecodeParser parser) {
      super(parser);
      class_index = p.u2();
      name_and_type_index = p.u2();
    }

    // Declared in BytecodeCONSTANT.jrag at line 96


    public String toString() {
      return "FieldRefInfo: " + p.constantPool[class_index] + " "
        + p.constantPool[name_and_type_index];
    }


}
