
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;

class Option extends java.lang.Object {
    // Declared in Options.jadd at line 14

    public String name;

    // Declared in Options.jadd at line 15

    public boolean hasValue;

    // Declared in Options.jadd at line 16

    public boolean isCollection;

    // Declared in Options.jadd at line 17

    public Option(String name, boolean hasValue, boolean isCollection) {
      this.name = name;
      this.hasValue = hasValue;
      this.isCollection = isCollection;
    }


}
