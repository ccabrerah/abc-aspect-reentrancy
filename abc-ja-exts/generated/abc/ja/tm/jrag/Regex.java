
package abc.ja.tm.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;



public abstract class Regex extends PointcutExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public Regex clone() throws CloneNotSupportedException {
        Regex node = (Regex)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in MakeStateMachine.jrag at line 35


    public abstract void makeSM(StateMachine sm, SMNode start,
                                      SMNode finish, boolean own_start);

    // Declared in TMPointcutCodegen.jrag at line 30


    public Pointcut pointcut()
    {
        throw new InternalCompilerError("called pointcut() on regex()");
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 21

    public Regex() {
        super();


    }

    // Declared in tm.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in tm.ast at line 12

  public boolean mayHaveRewrite() { return false; }

    // Declared in MakeStateMachine.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public StateMachine stateMachine() {
        StateMachine stateMachine_value = stateMachine_compute();
        return stateMachine_value;
    }

    private StateMachine stateMachine_compute() {
        StateMachine sm = new TMStateMachine();
        SMNode start = (SMNode) sm.newState();
        start.setInitial(true);
        SMNode finish = (SMNode) sm.newState();
        finish.setFinal(true);
        makeSM(sm, start, finish, true);
        return sm;
    }

    // Declared in TMPointcutCodegen.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public Regex regex() {
        Regex regex_value = regex_compute();
        return regex_value;
    }

    private Regex regex_compute() {  return this;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
