
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;

public class CONSTANT_Long_Info extends CONSTANT_Info {
    // Declared in BytecodeCONSTANT.jrag at line 169

    public long value;

    // Declared in BytecodeCONSTANT.jrag at line 171


    public CONSTANT_Long_Info(BytecodeParser parser) {
      super(parser);
      value = p.readLong();
    }

    // Declared in BytecodeCONSTANT.jrag at line 176


    public String toString() {
      return "LongInfo: " + Long.toString(value);
    }

    // Declared in BytecodeCONSTANT.jrag at line 180


    public Expr expr() {
      //return new LongLiteral(Long.toString(value));
      return new LongLiteral("0x" + Long.toHexString(value));
    }


}
