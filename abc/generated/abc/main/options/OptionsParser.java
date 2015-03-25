
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
import abc.main.*;
import java.util.*;
import java.io.*;

public class OptionsParser {
    private static OptionsParser instance = new OptionsParser();
    public static OptionsParser v() { return instance; }
    public static void reset() { instance = new OptionsParser(); }

    /** Parse a path.separator separated path into the separate directories. */
    private void parsePath(String path, Collection paths) {
        String[] jars = path.split(System.getProperty("path.separator"));
        for(int j = 0; j < jars.length; j++) {
            // Do we need a sanity check here? !jars[j].equals("") or something like that?
            paths.add(jars[j]);
        }
    }


    private boolean help;
    public boolean help() { return help; }
    public void set_help(boolean val) { help = val; }
  
    private boolean version;
    public boolean version() { return version; }
    public void set_version(boolean val) { version = val; }
  
    private boolean verbose;
    public boolean verbose() { return verbose; }
    public void set_verbose(boolean val) { verbose = val; }
  
    private Collection sourceroots = new ArrayList();
    public Collection sourceroots() { return sourceroots; }
    public void set_sourceroots(Collection val) { sourceroots = val; }
  
    private Collection injars = new ArrayList();
    public Collection injars() { return injars; }
    public void set_injars(Collection val) { injars = val; }
  
    private Collection inpath = new ArrayList();
    public Collection inpath() { return inpath; }
    public void set_inpath(Collection val) { inpath = val; }
  
    private String classpath;
    public String classpath() { return classpath; }
    public void set_classpath(String val) { classpath = val; }
  
    private String main_class;
    public String main_class() { return main_class; }
    public void set_main_class(String val) { main_class = val; }
  
    private boolean dava = false;
    public boolean dava() { return dava; }
    public void set_dava(boolean val) { dava = val; }
  
    private String outjar;
    public String outjar() { return outjar; }
    public void set_outjar(String val) { outjar = val; }
  
    private String d;
    public String d() { return d; }
    public void set_d(String val) { d = val; }
  
    private boolean tag_instructions = false;
    public boolean tag_instructions() { return tag_instructions; }
    public void set_tag_instructions(boolean val) { tag_instructions = val; }
  
    private boolean g = false;
    public boolean g() { return g; }
    public void set_g(boolean val) { g = val; }
  
    private boolean warn_unused_advice = true;
    public boolean warn_unused_advice() { return warn_unused_advice; }
    public void set_warn_unused_advice(boolean val) { warn_unused_advice = val; }
  
    private boolean warn_prec_ambiguity = false;
    public boolean warn_prec_ambiguity() { return warn_prec_ambiguity; }
    public void set_warn_prec_ambiguity(boolean val) { warn_prec_ambiguity = val; }
  
    private boolean nested_comments;
    public boolean nested_comments() { return nested_comments; }
    public void set_nested_comments(boolean val) { nested_comments = val; }
  
    private String ext = "abc.main";
    public String ext() { return ext; }
    public void set_ext(String val) { ext = val; }
  
    private String source = "1.4";
    public String source() { return source; }
    public void set_source(String val) { source = val; }
  
    private boolean abc101runtime;
    public boolean abc101runtime() { return abc101runtime; }
    public void set_abc101runtime(boolean val) { abc101runtime = val; }
  
    private int O = 1;
    public int O() { return O; }
    public void set_O(int val) { O = val; }
  
    private boolean w = false;
    public boolean w() { return w; }
    public void set_w(boolean val) { w = val; }
  
    private boolean around_force_closures = false;
    public boolean around_force_closures() { return around_force_closures; }
    public void set_around_force_closures(boolean val) { around_force_closures = val; }
  
    private boolean around_inlining = true;
    public boolean around_inlining() { return around_inlining; }
    public void set_around_inlining(boolean val) { around_inlining = val; }
  
    private boolean around_force_inlining = false;
    public boolean around_force_inlining() { return around_force_inlining; }
    public void set_around_force_inlining(boolean val) { around_force_inlining = val; }
  
    private boolean before_after_inlining = true;
    public boolean before_after_inlining() { return before_after_inlining; }
    public void set_before_after_inlining(boolean val) { before_after_inlining = val; }
  
    private boolean before_after_force_inlining = false;
    public boolean before_after_force_inlining() { return before_after_force_inlining; }
    public void set_before_after_force_inlining(boolean val) { before_after_force_inlining = val; }
  
    private boolean cflow_use_counters = true;
    public boolean cflow_use_counters() { return cflow_use_counters; }
    public void set_cflow_use_counters(boolean val) { cflow_use_counters = val; }
  
    private boolean cflow_use_sharing = true;
    public boolean cflow_use_sharing() { return cflow_use_sharing; }
    public void set_cflow_use_sharing(boolean val) { cflow_use_sharing = val; }
  
    private boolean cflow_share_thread_locals = true;
    public boolean cflow_share_thread_locals() { return cflow_share_thread_locals; }
    public void set_cflow_share_thread_locals(boolean val) { cflow_share_thread_locals = val; }
  
    private String laststage = "quick";
    public String laststage() { return laststage; }
    public void set_laststage(String val) { laststage = val; }
  
    private boolean warn_about_individual_shadows = false;
    public boolean warn_about_individual_shadows() { return warn_about_individual_shadows; }
    public void set_warn_about_individual_shadows(boolean val) { warn_about_individual_shadows = val; }
  
    private boolean time;
    public boolean time() { return time; }
    public void set_time(boolean val) { time = val; }
  
    private int time_report_delay = 0;
    public int time_report_delay() { return time_report_delay; }
    public void set_time_report_delay(int val) { time_report_delay = val; }
  
    public boolean parse(ArgList args) {

      if(args.top().equals("-h")
      || args.top().equals("-h:on")
      || args.top().equals("-h:true")
      || args.top().equals("-h:yes")
      ) {
        help = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-h:off")
      || args.top().equals("-h:false")
      || args.top().equals("-h:no")
      ) {
        help = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-help")
      || args.top().equals("-help:on")
      || args.top().equals("-help:true")
      || args.top().equals("-help:yes")
      ) {
        help = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-help:off")
      || args.top().equals("-help:false")
      || args.top().equals("-help:no")
      ) {
        help = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-v")
      || args.top().equals("-v:on")
      || args.top().equals("-v:true")
      || args.top().equals("-v:yes")
      ) {
        version = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-v:off")
      || args.top().equals("-v:false")
      || args.top().equals("-v:no")
      ) {
        version = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-version")
      || args.top().equals("-version:on")
      || args.top().equals("-version:true")
      || args.top().equals("-version:yes")
      ) {
        version = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-version:off")
      || args.top().equals("-version:false")
      || args.top().equals("-version:no")
      ) {
        version = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-verbose")
      || args.top().equals("-verbose:on")
      || args.top().equals("-verbose:true")
      || args.top().equals("-verbose:yes")
      ) {
        verbose = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-verbose:off")
      || args.top().equals("-verbose:false")
      || args.top().equals("-verbose:no")
      ) {
        verbose = false;
        args.shift();
        return true;
      }
    
    if(args.top().startsWith("@")) {
        String fileName = args.top().substring(1);
        args.shift();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch(IOException e) {
            throw new IllegalArgumentException("Couldn't open argfile "+fileName);
        }
        LinkedList newArgs = new LinkedList();
        try {
            while(true) {
                String s = br.readLine();
                if(s == null) break;
                newArgs.addFirst(s);
            }
        } catch(IOException e) {
            throw new IllegalArgumentException("Error reading from argfile "+fileName);
        }
        Iterator argIt = newArgs.iterator();
        while(argIt.hasNext()) args.push((String) argIt.next());
        return true;
    }
    
    if(args.top().equals("-argfile")) {
        String fileName = args.argTo();
        args.shift();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch(IOException e) {
            throw new IllegalArgumentException("Couldn't open argfile "+fileName);
        }
        LinkedList newArgs = new LinkedList();
        try {
            while(true) {
                String s = br.readLine();
                if(s == null) break;
                newArgs.addFirst(s);
            }
        } catch(IOException e) {
            throw new IllegalArgumentException("Error reading from argfile "+fileName);
        }
        Iterator argIt = newArgs.iterator();
        while(argIt.hasNext()) args.push((String) argIt.next());
        return true;
    }
    
      if(args.top().equals("-sourceroots")) {
        parsePath(args.argTo(), sourceroots);
        args.shift();
        return true;
      }
    
      if(args.top().equals("-injars")) {
        parsePath(args.argTo(), injars);
        args.shift();
        return true;
      }
    
      if(args.top().equals("-inpath")) {
        parsePath(args.argTo(), inpath);
        args.shift();
        return true;
      }
    
      if(args.top().equals("-cp")) {
        classpath = args.argTo();
        args.shift();
        return true;
      } else if(args.top().startsWith("-cp:")) {
        classpath = args.argTo().substring("-cp".length());
        args.shift();
        return true;
      }
    
      if(args.top().equals("-classpath")) {
        classpath = args.argTo();
        args.shift();
        return true;
      } else if(args.top().startsWith("-classpath:")) {
        classpath = args.argTo().substring("-classpath".length());
        args.shift();
        return true;
      }
    
      if(args.top().equals("-main-class")) {
        main_class = args.argTo();
        args.shift();
        return true;
      } else if(args.top().startsWith("-main-class:")) {
        main_class = args.argTo().substring("-main-class".length());
        args.shift();
        return true;
      }
    
      if(args.top().equals("-dava")
      || args.top().equals("-dava:on")
      || args.top().equals("-dava:true")
      || args.top().equals("-dava:yes")
      ) {
        dava = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-dava:off")
      || args.top().equals("-dava:false")
      || args.top().equals("-dava:no")
      ) {
        dava = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-outjar")) {
        outjar = args.argTo();
        args.shift();
        return true;
      } else if(args.top().startsWith("-outjar:")) {
        outjar = args.argTo().substring("-outjar".length());
        args.shift();
        return true;
      }
    
      if(args.top().equals("-d")) {
        d = args.argTo();
        args.shift();
        return true;
      } else if(args.top().startsWith("-d:")) {
        d = args.argTo().substring("-d".length());
        args.shift();
        return true;
      }
    
      if(args.top().equals("-tag-instructions")
      || args.top().equals("-tag-instructions:on")
      || args.top().equals("-tag-instructions:true")
      || args.top().equals("-tag-instructions:yes")
      ) {
        tag_instructions = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-tag-instructions:off")
      || args.top().equals("-tag-instructions:false")
      || args.top().equals("-tag-instructions:no")
      ) {
        tag_instructions = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-g")
      || args.top().equals("-g:on")
      || args.top().equals("-g:true")
      || args.top().equals("-g:yes")
      ) {
        g = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-g:off")
      || args.top().equals("-g:false")
      || args.top().equals("-g:no")
      ) {
        g = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-warn-unused-advice")
      || args.top().equals("-warn-unused-advice:on")
      || args.top().equals("-warn-unused-advice:true")
      || args.top().equals("-warn-unused-advice:yes")
      ) {
        warn_unused_advice = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-warn-unused-advice:off")
      || args.top().equals("-warn-unused-advice:false")
      || args.top().equals("-warn-unused-advice:no")
      ) {
        warn_unused_advice = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-warn-prec-ambiguity")
      || args.top().equals("-warn-prec-ambiguity:on")
      || args.top().equals("-warn-prec-ambiguity:true")
      || args.top().equals("-warn-prec-ambiguity:yes")
      ) {
        warn_prec_ambiguity = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-warn-prec-ambiguity:off")
      || args.top().equals("-warn-prec-ambiguity:false")
      || args.top().equals("-warn-prec-ambiguity:no")
      ) {
        warn_prec_ambiguity = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-nested-comments")
      || args.top().equals("-nested-comments:on")
      || args.top().equals("-nested-comments:true")
      || args.top().equals("-nested-comments:yes")
      ) {
        nested_comments = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-nested-comments:off")
      || args.top().equals("-nested-comments:false")
      || args.top().equals("-nested-comments:no")
      ) {
        nested_comments = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-ext")) {
        ext = args.argTo();
        args.shift();
        return true;
      } else if(args.top().startsWith("-ext:")) {
        ext = args.argTo().substring("-ext".length());
        args.shift();
        return true;
      }
    
      if(args.top().equals("-source")) {
        source = args.argTo();
        args.shift();
        return true;
      } else if(args.top().startsWith("-source:")) {
        source = args.argTo().substring("-source".length());
        args.shift();
        return true;
      }
    
      if(args.top().equals("-abc101runtime")
      || args.top().equals("-abc101runtime:on")
      || args.top().equals("-abc101runtime:true")
      || args.top().equals("-abc101runtime:yes")
      ) {
        abc101runtime = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-abc101runtime:off")
      || args.top().equals("-abc101runtime:false")
      || args.top().equals("-abc101runtime:no")
      ) {
        abc101runtime = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-O")) {
        try {
          Integer num = Integer.decode(args.argTo());
          O = num.intValue();
          args.shift();
          return true;
        } catch(NumberFormatException e) {
        }
      } else if(args.top().startsWith("-O")) {
        try {
          String i = args.top().substring("-O".length());
          Integer num = Integer.decode(i);
          O = num.intValue();
          args.shift();
          return true;
        } catch(NumberFormatException e) {
        }
      }
    
      if(args.top().equals("-w")
      || args.top().equals("-w:on")
      || args.top().equals("-w:true")
      || args.top().equals("-w:yes")
      ) {
        w = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-w:off")
      || args.top().equals("-w:false")
      || args.top().equals("-w:no")
      ) {
        w = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-around-force-closures")
      || args.top().equals("-around-force-closures:on")
      || args.top().equals("-around-force-closures:true")
      || args.top().equals("-around-force-closures:yes")
      ) {
        around_force_closures = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-around-force-closures:off")
      || args.top().equals("-around-force-closures:false")
      || args.top().equals("-around-force-closures:no")
      ) {
        around_force_closures = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-around-inlining")
      || args.top().equals("-around-inlining:on")
      || args.top().equals("-around-inlining:true")
      || args.top().equals("-around-inlining:yes")
      ) {
        around_inlining = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-around-inlining:off")
      || args.top().equals("-around-inlining:false")
      || args.top().equals("-around-inlining:no")
      ) {
        around_inlining = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-around-force-inlining")
      || args.top().equals("-around-force-inlining:on")
      || args.top().equals("-around-force-inlining:true")
      || args.top().equals("-around-force-inlining:yes")
      ) {
        around_force_inlining = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-around-force-inlining:off")
      || args.top().equals("-around-force-inlining:false")
      || args.top().equals("-around-force-inlining:no")
      ) {
        around_force_inlining = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-before-after-inlining")
      || args.top().equals("-before-after-inlining:on")
      || args.top().equals("-before-after-inlining:true")
      || args.top().equals("-before-after-inlining:yes")
      ) {
        before_after_inlining = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-before-after-inlining:off")
      || args.top().equals("-before-after-inlining:false")
      || args.top().equals("-before-after-inlining:no")
      ) {
        before_after_inlining = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-before-after-force-inlining")
      || args.top().equals("-before-after-force-inlining:on")
      || args.top().equals("-before-after-force-inlining:true")
      || args.top().equals("-before-after-force-inlining:yes")
      ) {
        before_after_force_inlining = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-before-after-force-inlining:off")
      || args.top().equals("-before-after-force-inlining:false")
      || args.top().equals("-before-after-force-inlining:no")
      ) {
        before_after_force_inlining = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-cflow-use-counters")
      || args.top().equals("-cflow-use-counters:on")
      || args.top().equals("-cflow-use-counters:true")
      || args.top().equals("-cflow-use-counters:yes")
      ) {
        cflow_use_counters = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-cflow-use-counters:off")
      || args.top().equals("-cflow-use-counters:false")
      || args.top().equals("-cflow-use-counters:no")
      ) {
        cflow_use_counters = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-cflow-use-sharing")
      || args.top().equals("-cflow-use-sharing:on")
      || args.top().equals("-cflow-use-sharing:true")
      || args.top().equals("-cflow-use-sharing:yes")
      ) {
        cflow_use_sharing = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-cflow-use-sharing:off")
      || args.top().equals("-cflow-use-sharing:false")
      || args.top().equals("-cflow-use-sharing:no")
      ) {
        cflow_use_sharing = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-cflow-share-thread-locals")
      || args.top().equals("-cflow-share-thread-locals:on")
      || args.top().equals("-cflow-share-thread-locals:true")
      || args.top().equals("-cflow-share-thread-locals:yes")
      ) {
        cflow_share_thread_locals = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-cflow-share-thread-locals:off")
      || args.top().equals("-cflow-share-thread-locals:false")
      || args.top().equals("-cflow-share-thread-locals:no")
      ) {
        cflow_share_thread_locals = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-laststage")) {
        laststage = args.argTo();
        args.shift();
        return true;
      } else if(args.top().startsWith("-laststage:")) {
        laststage = args.argTo().substring("-laststage".length());
        args.shift();
        return true;
      }
    
      if(args.top().equals("-warn-about-individual-shadows")
      || args.top().equals("-warn-about-individual-shadows:on")
      || args.top().equals("-warn-about-individual-shadows:true")
      || args.top().equals("-warn-about-individual-shadows:yes")
      ) {
        warn_about_individual_shadows = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-warn-about-individual-shadows:off")
      || args.top().equals("-warn-about-individual-shadows:false")
      || args.top().equals("-warn-about-individual-shadows:no")
      ) {
        warn_about_individual_shadows = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-time")
      || args.top().equals("-time:on")
      || args.top().equals("-time:true")
      || args.top().equals("-time:yes")
      ) {
        time = true;
        args.shift();
        return true;
      }
      if(args.top().equals("-time:off")
      || args.top().equals("-time:false")
      || args.top().equals("-time:no")
      ) {
        time = false;
        args.shift();
        return true;
      }
    
      if(args.top().equals("-time-report-delay")) {
        try {
          Integer num = Integer.decode(args.argTo());
          time_report_delay = num.intValue();
          args.shift();
          return true;
        } catch(NumberFormatException e) {
        }
      } else if(args.top().startsWith("-time-report-delay")) {
        try {
          String i = args.top().substring("-time-report-delay".length());
          Integer num = Integer.decode(i);
          time_report_delay = num.intValue();
          args.shift();
          return true;
        } catch(NumberFormatException e) {
        }
      }
    
        return false;
    }
}
  