
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;

public class CONSTANT_Info extends java.lang.Object {
    // Declared in BytecodeCONSTANT.jrag at line 120

    protected BytecodeParser p;

    // Declared in BytecodeCONSTANT.jrag at line 121

    public CONSTANT_Info(BytecodeParser parser) {
      p = parser;

    }

    // Declared in BytecodeCONSTANT.jrag at line 125

    public Expr expr() {
      throw new Error("CONSTANT_info.expr() should not be computed for " + getClass().getName());
    }

    // Declared in BytecodeCONSTANT.jrag at line 128

    public Expr exprAsBoolean() {
      return expr();
    }


}
