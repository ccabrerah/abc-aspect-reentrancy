
package abc.ja.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;

public class ParserTrace extends java.lang.Object {
    // Declared in Debug.jrag at line 27

    /*PA: Using Debug.parserTrace instead Flag for personalized parser trace. */
    //public static boolean parserTraceOn = false;

    public static void parserTrace (String s)
      { if (abc.main.Debug.v().parserTrace) 
          System.err.println(" REDUCED: " + s); 
      }


}
