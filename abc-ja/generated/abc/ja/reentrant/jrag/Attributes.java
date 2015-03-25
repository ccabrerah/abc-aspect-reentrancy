
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;

public class Attributes extends java.lang.Object {
    // Declared in BytecodeAttributes.jrag at line 14

    protected BytecodeParser p;

    // Declared in BytecodeAttributes.jrag at line 15

    protected boolean isSynthetic;

    // Declared in BytecodeAttributes.jrag at line 17


    protected Attributes(BytecodeParser parser) {
      p = parser;
      isSynthetic = false;
    }

    // Declared in BytecodeAttributes.jrag at line 22


    protected void processAttribute(String attribute_name, int attribute_length) {
      if(attribute_name.equals("Synthetic")) {
        isSynthetic = true;
      } else {
        this.p.skip(attribute_length);
      }
    }

    // Declared in BytecodeAttributes.jrag at line 30


    protected void attributes() {
      int attributes_count = p.u2();
      if(BytecodeParser.VERBOSE)
        p.println("    " + attributes_count + " attributes:");
      for (int j = 0; j < attributes_count; j++) {
        int attribute_name_index = p.u2();
        int attribute_length = p.u4();
        String attribute_name = p.getCONSTANT_Utf8_Info(attribute_name_index).string();
        if(BytecodeParser.VERBOSE)
          p.println("    Attribute: " + attribute_name + ", length: "
              + attribute_length);
        processAttribute(attribute_name, attribute_length);
      }
    }

    // Declared in BytecodeAttributes.jrag at line 45


    public boolean isSynthetic() {
      return isSynthetic;
    }

    // Declared in BytecodeAttributes.jrag at line 50


    // 4.8.15.1
    protected ElementValue readElementValue() {
      char c = (char)p.u1();
      switch (c) {
        case 'e':
          int type_name_index = this.p.u2();
          String type_name = this.p.getCONSTANT_Utf8_Info(type_name_index).string();
          // remove inital L and trailing ;
          Access typeAccess = this.p.fromClassName(type_name.substring(1, type_name.length() - 1));
          int const_name_index = this.p.u2();
          String const_name = this.p.getCONSTANT_Utf8_Info(const_name_index).string();
          return new ElementConstantValue(typeAccess.qualifiesAccess(new VarAccess(const_name)));
        case 'B': case 'C': case 'D': case 'F': case 'I': case 'J': case 'S': case 'Z': case 's':
          int const_value_index = p.u2();
          Expr e = this.p.getCONSTANT_Info(const_value_index).expr();
          return new ElementConstantValue(e);
        case 'c':
          int class_info_index = p.u2();
          String descriptor = this.p.getCONSTANT_Utf8_Info(class_info_index).string();
          e = new TypeDescriptor(p, descriptor).type();
          return new ElementConstantValue(e);
        case '@':
          return new ElementAnnotationValue(readAnnotation());
        case '[':
          int index = p.u2();
          List list = new List();
          for(int i = 0; i < index; i++) 
            list.add(readElementValue());
          return new ElementArrayValue(list);
        default:
          throw new Error("AnnotationDefault tag " + c + " not supported");
      }
    }

    // Declared in BytecodeAttributes.jrag at line 84


    // 4.8.15
    protected Annotation readAnnotation() {
      Access typeAccess = new FieldDescriptor(p, "").type();
      int num_element_value_pairs = p.u2();
      List list = new List();
      for(int i = 0; i < num_element_value_pairs; i++) {
        int element_name_index = p.u2();
        String element_name = p.getCONSTANT_Utf8_Info(element_name_index).string();
        ElementValue element_value = readElementValue();
        list.add(new ElementValuePair(element_name, element_value));
      }
      return new Annotation("Annotation", typeAccess, list);
    }

    // Declared in BytecodeAttributes.jrag at line 97


    public static class FieldAttributes extends Attributes {
      protected CONSTANT_Info constantValue;
      public java.util.ArrayList annotations;
      public Signatures.FieldSignature fieldSignature;
      public FieldAttributes(BytecodeParser p) {
        super(p);
        attributes();
      }

      protected void processAttribute(String attribute_name, int attribute_length) {
        if(attribute_name.equals("ConstantValue") && attribute_length == 2) {
          int constantvalue_index = p.u2();
          constantValue = p.getCONSTANT_Info(constantvalue_index);
        }
        else if(attribute_name.equals("RuntimeVisibleAnnotations")) {
          int num_annotations = p.u2();
          if(annotations == null)
            annotations = new java.util.ArrayList();
          for(int j = 0; j < num_annotations; j++)
            annotations.add(readAnnotation());
        }
        else if(attribute_name.equals("RuntimeInvisibleAnnotations")) {
          int num_annotations = p.u2();
          if(annotations == null)
            annotations = new java.util.ArrayList();
          for(int j = 0; j < num_annotations; j++)
            annotations.add(readAnnotation());
        }
        else if(attribute_name.equals("Signature")) {
          int signature_index = p.u2();
          String s = p.getCONSTANT_Utf8_Info(signature_index).string();
          fieldSignature = new Signatures.FieldSignature(s);
        }
        else {
          super.processAttribute(attribute_name, attribute_length);
        }
      }

      public CONSTANT_Info constantValue() {
        return constantValue;
      }
    }

    // Declared in BytecodeAttributes.jrag at line 140


    public static class MethodAttributes extends Attributes {
      protected List exceptionList;
      protected ElementValue elementValue;
      public Signatures.MethodSignature methodSignature;
      public java.util.ArrayList annotations;
      public java.util.ArrayList[] parameterAnnotations;

      public MethodAttributes(BytecodeParser p) {
        super(p);
        attributes();
      }

      protected void processAttribute(String attribute_name, int attribute_length) {
        if(attribute_name.equals("Exceptions")) {
          parseExceptions();
        } 
        else if(attribute_name.equals("AnnotationDefault")) {
          annotationDefault();
        }
        else if(attribute_name.equals("Signature")) {
          int signature_index = p.u2();
          String s = p.getCONSTANT_Utf8_Info(signature_index).string();
          methodSignature = new Signatures.MethodSignature(s);
        }
        else if(attribute_name.equals("RuntimeVisibleAnnotations")) {
          int num_annotations = p.u2();
          if(annotations == null)
            annotations = new java.util.ArrayList();
          for(int j = 0; j < num_annotations; j++)
            annotations.add(readAnnotation());
        }
        else if(attribute_name.equals("RuntimeInvisibleAnnotations")) {
          int num_annotations = p.u2();
          if(annotations == null)
            annotations = new java.util.ArrayList();
          for(int j = 0; j < num_annotations; j++)
            annotations.add(readAnnotation());
        }
        else if(attribute_name.equals("RuntimeVisibleParameterAnnotations")) {
          int num_parameters = p.u1();
          if(parameterAnnotations == null)
            parameterAnnotations = new java.util.ArrayList[num_parameters];
          for(int i = 0; i < num_parameters; i++) {
            if(parameterAnnotations[i] == null)
              parameterAnnotations[i] = new java.util.ArrayList();
            int num_annotations = p.u2();
            for(int j = 0; j < num_annotations; j++)
              parameterAnnotations[i].add(readAnnotation());
          }
        }
        else if(attribute_name.equals("RuntimeInvisibleParameterAnnotations")) {
          int num_parameters = p.u1();
          if(parameterAnnotations == null)
            parameterAnnotations = new java.util.ArrayList[num_parameters];
          for(int i = 0; i < num_parameters; i++) {
            if(parameterAnnotations[i] == null)
              parameterAnnotations[i] = new java.util.ArrayList();
            int num_annotations = p.u2();
            for(int j = 0; j < num_annotations; j++)
              parameterAnnotations[i].add(readAnnotation());
          }
        }
        /*
        else if(attribute_name.equals("RuntimeVisibleExtendedAnnotations")) {
          int num_annotations = p.u2();
          java.util.ArrayList extendedAnnotations = new java.util.ArrayList();
          for(int j = 0; j < num_annotations; j++) {
            extendedAnnotations.add(readAnnotation());
          }
          int kind = p.u1();
          if(kind == 0x06)
            System.out.println("Found receiver annotation");
          if(kind >= 0x00 && kind <= 0x05) // typecast, instaceof, new
            p.u2(); // skip 2
          else if(kind == 0x08 || kind == 0x09) { // local variable
            int table_length = p.u2();
            for(int i = 0; i < table_length; i++)
              p.skip(6);
          }
          else if(kind >= 0x10 && kind <= 0x13) // bound
            p.skip(2);
          else if(kind >= 0x14 && kind <= 0x15) // extends, implements
            p.skip(1);
          else if(kind >= 0x16 && kind <= 0x17) // throws
            p.skip(1);


          this.p.skip(attribute_length);
        }
        */
        else {
          super.processAttribute(attribute_name, attribute_length);
        }
      }

      private void parseExceptions() {
        int number_of_exceptions = p.u2();
        exceptionList = new List();
        if(BytecodeParser.VERBOSE)
          p.println("      " + number_of_exceptions + " exceptions:");
        for (int i = 0; i < number_of_exceptions; i++) {
          CONSTANT_Class_Info exception = p.getCONSTANT_Class_Info(p.u2());
          if(BytecodeParser.VERBOSE)
            p.println("        exception " + exception.name());
          exceptionList.add(exception.access());
        }
      }

      public List exceptionList() {
        return exceptionList != null ? exceptionList : new List();
      }

      public ElementValue elementValue() {
        return elementValue;
      }

      // 4.8.19
      private void annotationDefault() {
        elementValue = readElementValue();
      }

    }

    // Declared in BytecodeAttributes.jrag at line 263


    public static class TypeAttributes extends Attributes {
      TypeDecl typeDecl;
      TypeDecl outerTypeDecl;
      Program classPath;
      public TypeAttributes(BytecodeParser p, TypeDecl typeDecl, TypeDecl outerTypeDecl, Program classPath) {
        super(p);
        this.typeDecl = typeDecl;
        this.outerTypeDecl = outerTypeDecl;
        this.classPath = classPath;
        attributes();
      }

      protected void processAttribute(String attribute_name, int attribute_length) {
        if(attribute_name.equals("InnerClasses")) {
          innerClasses();
        }
        else if(attribute_name.equals("Signature")) {
          int signature_index = p.u2();
          String s = p.getCONSTANT_Utf8_Info(signature_index).string();
          Signatures.ClassSignature classSignature = new Signatures.ClassSignature(s);
          typeDecl = typeDecl.makeGeneric(classSignature);
        }
        else if(attribute_name.equals("RuntimeVisibleAnnotations")) {
          int num_annotations = p.u2();
          //System.out.println("RuntimeVisibleAnnotations: " + num_annotations);
          for(int j = 0; j < num_annotations; j++) {
            Annotation a = readAnnotation();
            typeDecl.getModifiers().addModifier(a);
          }
        }
        else if(attribute_name.equals("RuntimeInvisibleAnnotation")) {
          int num_annotations = p.u2();
          //System.out.println("RuntimeInvisibleAnnotations: " + num_annotations);
          for(int j = 0; j < num_annotations; j++) {
            Annotation a = readAnnotation();
            typeDecl.getModifiers().addModifier(a);
          }
        }
        else {
          super.processAttribute(attribute_name, attribute_length);
        }
      }

      protected void innerClasses() {
        int number_of_classes = this.p.u2();
        if(BytecodeParser.VERBOSE)
          p.println("    Number of classes: " + number_of_classes);
        for (int i = 0; i < number_of_classes; i++) {
          if(BytecodeParser.VERBOSE)
            p.print("      " + i + "(" + number_of_classes + ")" +  ":");
          int inner_class_info_index = this.p.u2();
          int outer_class_info_index = this.p.u2();
          int inner_name_index = this.p.u2();
          int inner_class_access_flags = this.p.u2();
          String inner_name = "";
          if(inner_class_info_index > 0 && outer_class_info_index > 0 && inner_name_index >  0) {
            CONSTANT_Class_Info inner_class_info = this.p.getCONSTANT_Class_Info(inner_class_info_index);
            CONSTANT_Class_Info outer_class_info = this.p.getCONSTANT_Class_Info(outer_class_info_index);
            if(inner_class_info == null || outer_class_info == null) {
              System.out.println("Null");
            }
            String inner_class_name = inner_class_info.name();
            String outer_class_name = outer_class_info.name();

            if(BytecodeParser.VERBOSE)
              this.p.println("      inner: " + inner_class_name + ", outer: " + outer_class_name);

            if (inner_name_index != 0) {
              inner_name = this.p.getCONSTANT_Utf8_Info(inner_name_index).string();
            } else {
              inner_name = inner_class_info.simpleName();
            }

            if (inner_class_info.name().equals(p.classInfo.name())) {
              if(BytecodeParser.VERBOSE)
                p.println("      Class " + inner_class_name + " is inner (" + inner_name + ")");
              typeDecl.setID(inner_name);
              typeDecl.setModifiers(BytecodeParser.modifiers(inner_class_access_flags & 0x041f));
              if (this.p.outerClassInfo != null && this.p.outerClassInfo.name().equals(outer_class_info.name())) {
                MemberTypeDecl m = null;
                if (typeDecl instanceof ClassDecl) {
                  m = new MemberClassDecl((ClassDecl)typeDecl);
                  outerTypeDecl.addBodyDecl(m);
                } else if (typeDecl instanceof InterfaceDecl) {
                  m = new MemberInterfaceDecl((InterfaceDecl)typeDecl);
                  outerTypeDecl.addBodyDecl(m);
                }
              }
            }
            if (outer_class_info.name().equals(this.p.classInfo.name())) {
              if(BytecodeParser.VERBOSE)
                p.println("      Class " + this.p.classInfo.name()
                    + " has inner class: " + inner_class_name);
              if(BytecodeParser.VERBOSE)
                p.println("Begin processing: " + inner_class_name);
              try {
                java.io.InputStream is = classPath.getInputStream(inner_class_name);
                if(is != null) {
                  BytecodeParser p = new BytecodeParser(is, this.p.name);
                  p.parse(typeDecl, outer_class_info, classPath, (inner_class_access_flags & Flags.ACC_STATIC) == 0);
                  is.close();
                }
                else {
                  System.out.println("Error: ClassFile " + inner_class_name
                      + " not found");
                }
              } catch (FileNotFoundException e) {
                System.out.println("Error: " + inner_class_name
                    + " not found");
              } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
              }
              if(BytecodeParser.VERBOSE)
                p.println("End processing: " + inner_class_name);
            }
          }

        }
        if(BytecodeParser.VERBOSE)
          p.println("    end");
      }




    }


}
