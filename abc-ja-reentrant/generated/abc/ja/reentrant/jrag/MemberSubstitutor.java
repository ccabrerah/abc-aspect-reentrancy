
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;

public interface MemberSubstitutor extends Parameterization {
    // Declared in Generics.jrag at line 676

    TypeDecl original();

    // Declared in Generics.jrag at line 677

    void addBodyDecl(BodyDecl b);

    // Declared in Generics.jrag at line 678

    TypeDecl substitute(TypeVariable typeVariable);

    // Declared in Generics.jrag at line 922
 @SuppressWarnings({"unchecked", "cast"})     public HashMap localMethodsSignatureMap();
    // Declared in Generics.jrag at line 937
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localFields(String name);
    // Declared in Generics.jrag at line 952
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localTypeDecls(String name);
    // Declared in Generics.jrag at line 982
 @SuppressWarnings({"unchecked", "cast"})     public Collection constructors();
}
