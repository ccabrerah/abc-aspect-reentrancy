
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;

public class CONSTANT_String_Info extends CONSTANT_Info {
    // Declared in BytecodeCONSTANT.jrag at line 216

    public int string_index;

    // Declared in BytecodeCONSTANT.jrag at line 218


    public CONSTANT_String_Info(BytecodeParser parser) {
      super(parser);
      string_index = p.u2();
    }

    // Declared in BytecodeCONSTANT.jrag at line 223


    public Expr expr() {
      CONSTANT_Utf8_Info i = (CONSTANT_Utf8_Info)p.constantPool[string_index];
      return new StringLiteral(i.string);
    }

    // Declared in BytecodeCONSTANT.jrag at line 228


    public String toString() {
      return "StringInfo: " + p.constantPool[string_index];
    }


}
