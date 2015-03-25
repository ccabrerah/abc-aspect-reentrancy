/*
 * The JastAdd Extensible Java Compiler (http://jastadd.org) is covered
 * by the modified BSD License. You should have received a copy of the
 * modified BSD license with this compiler.
 * 
 * Copyright (c) 2005-2008, Torbjorn Ekman
 * All rights reserved.
 */

import AST.*;

import java.util.*;
import java.io.*;
import beaver.Symbol;

import soot.*;
import soot.options.*;

class JavaCompiler extends Frontend {

  public static void main(String args[]) {
    final String[] arguments = args;
    Thread t = new Thread() {
      public void run() {
        compile(arguments);
      }
    };
    t.start();
    /*
    if(!compile(args))
      System.exit(1);
    */
  }

  public static boolean compile(String args[]) {
    return new JavaCompiler().process(
       args,
       new BytecodeParser(),
       new JavaParser() {
          parser.JavaParser parser = new parser.JavaParser();
          public CompilationUnit parse(java.io.InputStream is, String fileName) throws java.io.IOException, beaver.Parser.Exception {
            return parser.parse(is, fileName);
          }
       }
    );
  }

  public boolean process(String[] args, BytecodeReader reader, JavaParser parser) {
    if(!super.process(args, reader, parser))
      return false;
    
    soot.G.reset();
    soot.Main main = soot.Main.v();
    ArrayList list = new ArrayList();
    list.add("-d");
    if(Program.hasValueForOption("-d"))
      list.add(Program.getValueForOption("-d"));
    else
      list.add(".");
    if(Program.hasValueForOption("-classpath")) {
      list.add("-soot-class-path");
      list.add(Program.getValueForOption("-classpath"));
    }
    /*if(Program.hasOption("-verbose"))
      list.add("-verbose");*/

    String[] argList = new String[list.size()];
    int index = 0;
    for(Iterator iter = list.iterator(); iter.hasNext(); ) {
      String s = (String)iter.next();
      argList[index++] = s;
    }
    soot.options.Options.v().parse(argList);

    program.jimplify1();
    program.jimplify2();

    //main.run(new String[] { "-verbose", "test.Test" });


    //Scene.v().loadNecessaryClasses();
    Scene.v().loadBasicClasses();
    Scene.v().loadDynamicClasses();

    if(Program.hasOption("-jimple"))
      Options.v().set_output_format(Options.output_format_jimple);

    PhaseOptions.v().setPhaseOption("jop", "enabled");
    PackManager.v().runBodyPacks();
    PackManager.v().writeOutput();
    return true;
  }

  protected void initOptions() {
    super.initOptions();
    program.addKeyOption("-jimple");
  }

  protected void processNoErrors(CompilationUnit unit) {
    unit.transformation();
  }

  protected String name() {
    return "Java1.4Frontend + Java1.5Frontend + SootBackend";
  }

}
