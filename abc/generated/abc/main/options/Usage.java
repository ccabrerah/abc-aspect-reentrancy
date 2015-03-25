
/* abc - The AspectBench Compiler
 * Copyright (C) 2004 Ondrej Lhotak
 *
 * This compiler is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This compiler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this compiler, in the file LESSER-GPL;
 * if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

/* THIS FILE IS AUTO-GENERATED FROM options.xml. DO NOT MODIFY. */

package abc.main.options;

public class Usage extends UsageBase {
    public String getUsage() {
        return ""

+"\nGeneral Options:\n"
        
+padOpt(" -h -help", "Print the usage screen for abc." )
+padOpt(" -v -version", "Print the abc version number." )
+padOpt(" -verbose", "Verbose output." )
+padOpt(" @<filename>", "" )
+padOpt(" -argfile <filename>", "Read arguments from a file." )
+"\nInput Options:\n"
        
+padOpt(" -sourceroots <path>", "Use .java files in dirs in <path> as source." )
+padOpt(" -injars <jar list>", "Use class files from the jars in  	<jar list> as source." )
+padOpt(" -inpath <dir list>", "Use class files found in the directories in <dir list>  	as source." )
+padOpt(" -cp <classpath>", "" )
+padOpt(" -classpath <classpath>", "Specify the class path to be used when searching for  	libraries." )
+padOpt(" -main-class <class>", "Sets the main class for interprocedural analysis." )
+"\nOutput Options:\n"
        
+padOpt(" -dava", "Decompile instead of outputting classes.       " )
+padOpt(" -outjar <jar>", "Write output class files into a jar file." )
+padOpt(" -d <path>", "Write output class files into a directory." )
+padOpt(" -tag-instructions", "Tag overhead instructions." )
+padOpt(" -g", "Generate debug information" )
+"\nWarning/Error Reporting Options:\n"
        
+padOpt(" -warn-unused-advice", "Warn if a piece of advice doesn't apply anywhere.       " )
+padOpt(" -warn-prec-ambiguity", "Warn if there is a precedence ambiguity       " )
+"\nLanguage Options:\n"
        
+padOpt(" -nested-comments", "Allow nested comments." )
+padOpt(" -ext <package name>", "Load a language extension." )
+padOpt(" -source <release>", "Provide source compatibility with specified release." )
+padOpt(" -abc101runtime", "abc 1.0.1 runtime compliance mode." )
+"\nOptimization Options:\n"
        
+padOpt(" -O<arg>", "Set the general optimization level." )
+padOpt(" -w", "Whole-program mode.       " )
+padOpt(" -around-force-closures", "Force closures for around advice.       " )
+padOpt(" -around-inlining", "Enable inlining of around advice.       " )
+padOpt(" -around-force-inlining", "Inline around advice whenever possible.       " )
+padOpt(" -before-after-inlining", "Enable inlining of before and after advice.       " )
+padOpt(" -before-after-force-inlining", "Inline before and after advice whenever possible.       " )
+padOpt(" -cflow-use-counters", "Use the counter implementation of cflow whenever possible." )
+padOpt(" -cflow-use-sharing", "Share state between cflow pointcuts whenever possible" )
+padOpt(" -cflow-share-thread-locals", "Share thread-local instances of cflow stacks within method bodies" )
+"\nStatic whole-program analysis for dependent advice (abc.da) and tracematches (abc.tmwpopt):\n"
        
+padOpt(" -laststage <stage>", "Specifies the last stage to be executed in the static       whole program analysis for tracematches. (quick|flowins|flowsens)" )
+padOpt(" -warn-about-individual-shadows", "Warn about each individual shadow that gets removed through the analyses." );
    }
}
  