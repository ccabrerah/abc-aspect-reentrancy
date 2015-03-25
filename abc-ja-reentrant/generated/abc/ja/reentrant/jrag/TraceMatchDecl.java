
package abc.ja.reentrant.jrag;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import java.util.Iterator;import java.util.Collections;import java.util.HashMap;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import polyglot.util.InternalCompilerError;import abc.weaving.aspectinfo.*;import abc.weaving.aspectinfo.MethodCategory;import abc.weaving.aspectinfo.DeclareMessage;import abc.eaj.weaving.aspectinfo.*;import abc.tm.weaving.aspectinfo.*;import abc.tm.weaving.matching.*;import abc.reentrant.weaving.aspectinfo.*;

public class TraceMatchDecl extends AdviceDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        traceMatchName_computed = false;
        traceMatchName_value = null;
        name_computed = false;
        name_value = null;
        syncAdviceName_computed = false;
        syncAdviceName_value = null;
        someAdviceName_computed = false;
        someAdviceName_value = null;
        syncSootMethod_computed = false;
        syncSootMethod_value = null;
        someSootMethod_computed = false;
        someSootMethod_value = null;
        proceedName_computed = false;
        proceedName_value = null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TraceMatchDecl clone() throws CloneNotSupportedException {
        TraceMatchDecl node = (TraceMatchDecl)super.clone();
        node.traceMatchName_computed = false;
        node.traceMatchName_value = null;
        node.name_computed = false;
        node.name_value = null;
        node.syncAdviceName_computed = false;
        node.syncAdviceName_value = null;
        node.someAdviceName_computed = false;
        node.someAdviceName_value = null;
        node.syncSootMethod_computed = false;
        node.syncSootMethod_value = null;
        node.someSootMethod_computed = false;
        node.someSootMethod_value = null;
        node.proceedName_computed = false;
        node.proceedName_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TraceMatchDecl copy() {
      try {
          TraceMatchDecl node = (TraceMatchDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TraceMatchDecl fullCopy() {
        TraceMatchDecl res = (TraceMatchDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in TMCodegen.jrag at line 48


    public void jimplify1phase2()
    {
        super.jimplify1phase2();
        for (int i = 0; i < getNumSymbolDecl(); i++)
            getSymbolDecl(i).jimplify1phase2();
        hostType().getSootClassDecl().addMethod(syncSootMethod());
        hostType().getSootClassDecl().addMethod(someSootMethod());
    }

    // Declared in TMCodegen.jrag at line 77


    public void createAspectInfo()
    {
        // TODO: create AI for sync/some/body advice
        int[] jp_params = implicitParameters();
        LinkedList methods = new LinkedList();
        getBlock().addAllEnclosedMethodSigs(methods);

        if (aroundSymbol() != null) {
            createAdviceDecl(getAdviceSpec().adviceSpec(),
            aroundSymbolPointcut(), methodSig(), jp_params,
            methods, abc.tm.ast.TMAdviceDecl.BODY);
        } else {
            if (beforeSymbolsPointcut() != null)
                createAdviceDecl(new BeforeAdvice(pos()),
                    beforeSymbolsPointcut(), methodSig(), jp_params,
                    methods, abc.tm.ast.TMAdviceDecl.BODY);
        
            if (afterSymbolsPointcut() != null)
                createAdviceDecl(new AfterAdvice(pos()),
                afterSymbolsPointcut(), methodSig(), jp_params,
                methods, abc.tm.ast.TMAdviceDecl.BODY);
        }

        jp_params = symbolsImplicitParameters();

        if (beforeSymbolsPointcut() != null)
            createAdviceDecl(new BeforeAdvice(pos()), beforeSymbolsPointcut(),
                syncMethodSig(), jp_params, methods,
                abc.tm.ast.TMAdviceDecl.SYNCH);

        if (afterSymbolsPointcut() != null)
            createAdviceDecl(new AfterAdvice(pos()), afterSymbolsPointcut(),
                syncMethodSig(), jp_params, methods,
                abc.tm.ast.TMAdviceDecl.SYNCH);

        if (beforeSymbolsPointcut() != null)
            createAdviceDecl(new BeforeAdvice(pos()), beforeSymbolsPointcut(),
                someMethodSig(), jp_params, methods,
                abc.tm.ast.TMAdviceDecl.SOME);

        if (afterSymbolsPointcut() != null)
            createAdviceDecl(new AfterAdvice(pos()), afterSymbolsPointcut(),
                someMethodSig(), jp_params, methods,
                abc.tm.ast.TMAdviceDecl.SOME);
    }

    // Declared in TMCodegen.jrag at line 123


    public void createAdviceDecl(
        abc.weaving.aspectinfo.AdviceSpec spec, Pointcut pc,
        MethodSig sig, int[] jp_params, java.util.List methods, int kind)
    {
        polyglot.util.Position pos;
        if (kind == abc.tm.ast.TMAdviceDecl.BODY)
            pos = pos();
        else
            pos = polyglot.util.Position.COMPILER_GENERATED;

        globalAspectInfo().addAdviceDecl(new TMAdviceDecl(
            spec, pc, sig, aspectClass(), jp_params[0], jp_params[1],
            jp_params[2], methods, pos(), traceMatchName(), pos, kind));
    }

    // Declared in TMCodegen.jrag at line 205


    public void jimplify2()
    {
        super.jimplify2();

        // jimplify children
        for (int i = 0; i < getNumSymbolDecl(); i++)
            getSymbolDecl(i).jimplify2();

        jimplify2method(syncSootMethod());
        jimplify2method(someSootMethod());

        TraceMatch tm =
            new TraceMatch(traceMatchName(), traceMatchFormals(),
                           bodyAdviceFormals(),
                           getPointcutExpr().regex().stateMachine(),
                           isPerThread(), symbolToFormalNameList(),
                           frequentSymbolNames(), symbolToAdviceName(),
                           syncAdviceName(), someAdviceName(),
                           proceedName(), aspectClass(), pos());
        ((TMGlobalAspectInfo) globalAspectInfo()).addTraceMatch(tm);
    }

    // Declared in TMCodegen.jrag at line 227


    public void jimplify2method(SootMethod method)
    {
        JimpleBody body = Jimple.v().newBody(method);
        method.setActiveBody(body);
        Body b = new Body(hostType(), body, this);
        b.add(Jimple.v().newReturnVoidStmt());
        MethodCategory.register(method, MethodCategory.NO_EFFECTS_ON_BASE_CODE);
    }

    // Declared in TMErrorCheck.jrag at line 24

    public void typeCheck()
    {
        // TODO:
        // -----

        // check that the regex must bind all variables

        // check that an around-TM returns a value
    }

    // Declared in TMInfo.jrag at line 60


    void addAroundFormals(java.util.List<Formal> formals)
    {
        for (int i = 0; i < getNumSymbolDecl(); i++)
            getSymbolDecl(i).getSymbolKind().addAroundFormals(formals);
    }

    // Declared in TMPointcutCodegen.jrag at line 65


    protected Pointcut pcOr(Pointcut current, Pointcut next)
    {
        if (current == null)
            return next;
        return OrPointcut.construct(current, next, pos());
    }

    // Declared in tm.ast at line 3
    // Declared in tm.ast line 1

    public TraceMatchDecl() {
        super();

        setChild(new List(), 3);
        setChild(new List(), 5);
        setChild(new List(), 6);

    }

    // Declared in tm.ast at line 13


    // Declared in tm.ast line 1
    public TraceMatchDecl(Modifiers p0, AdviceSpec p1, PointcutExpr p2, List<Access> p3, Block p4, List<SymbolDecl> p5, List<SymbolAccess> p6) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
        setChild(p3, 3);
        setChild(p4, 4);
        setChild(p5, 5);
        setChild(p6, 6);
    }

    // Declared in tm.ast at line 23


  protected int numChildren() {
    return 7;
  }

    // Declared in tm.ast at line 26

  public boolean mayHaveRewrite() { return true; }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setModifiers(Modifiers node) {
        setChild(node, 0);
    }

    // Declared in AspectJ.ast at line 5

    public Modifiers getModifiers() {
        return (Modifiers)getChild(0);
    }

    // Declared in AspectJ.ast at line 9


    public Modifiers getModifiersNoTransform() {
        return (Modifiers)getChildNoTransform(0);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setAdviceSpec(AdviceSpec node) {
        setChild(node, 1);
    }

    // Declared in AspectJ.ast at line 5

    public AdviceSpec getAdviceSpec() {
        return (AdviceSpec)getChild(1);
    }

    // Declared in AspectJ.ast at line 9


    public AdviceSpec getAdviceSpecNoTransform() {
        return (AdviceSpec)getChildNoTransform(1);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setPointcutExpr(PointcutExpr node) {
        setChild(node, 2);
    }

    // Declared in AspectJ.ast at line 5

    public PointcutExpr getPointcutExpr() {
        return (PointcutExpr)getChild(2);
    }

    // Declared in AspectJ.ast at line 9


    public PointcutExpr getPointcutExprNoTransform() {
        return (PointcutExpr)getChildNoTransform(2);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setExceptionList(List<Access> list) {
        setChild(list, 3);
    }

    // Declared in AspectJ.ast at line 6


    private int getNumException = 0;

    // Declared in AspectJ.ast at line 7

    public int getNumException() {
        return getExceptionList().getNumChild();
    }

    // Declared in AspectJ.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public Access getException(int i) {
        return (Access)getExceptionList().getChild(i);
    }

    // Declared in AspectJ.ast at line 15


    public void addException(Access node) {
        List<Access> list = getExceptionList();
        list.addChild(node);
    }

    // Declared in AspectJ.ast at line 20


    public void setException(Access node, int i) {
        List<Access> list = getExceptionList();
        list.setChild(node, i);
    }

    // Declared in AspectJ.ast at line 24

    public List<Access> getExceptions() {
        return getExceptionList();
    }

    // Declared in AspectJ.ast at line 27

    public List<Access> getExceptionsNoTransform() {
        return getExceptionListNoTransform();
    }

    // Declared in AspectJ.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionList() {
        return (List<Access>)getChild(3);
    }

    // Declared in AspectJ.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Access> getExceptionListNoTransform() {
        return (List<Access>)getChildNoTransform(3);
    }

    // Declared in AspectJ.ast at line 2
    // Declared in AspectJ.ast line 13
    public void setBlock(Block node) {
        setChild(node, 4);
    }

    // Declared in AspectJ.ast at line 5

    public Block getBlock() {
        return (Block)getChild(4);
    }

    // Declared in AspectJ.ast at line 9


    public Block getBlockNoTransform() {
        return (Block)getChildNoTransform(4);
    }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 1
    public void setSymbolDeclList(List<SymbolDecl> list) {
        setChild(list, 5);
    }

    // Declared in tm.ast at line 6


    private int getNumSymbolDecl = 0;

    // Declared in tm.ast at line 7

    public int getNumSymbolDecl() {
        return getSymbolDeclList().getNumChild();
    }

    // Declared in tm.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public SymbolDecl getSymbolDecl(int i) {
        return (SymbolDecl)getSymbolDeclList().getChild(i);
    }

    // Declared in tm.ast at line 15


    public void addSymbolDecl(SymbolDecl node) {
        List<SymbolDecl> list = getSymbolDeclList();
        list.addChild(node);
    }

    // Declared in tm.ast at line 20


    public void setSymbolDecl(SymbolDecl node, int i) {
        List<SymbolDecl> list = getSymbolDeclList();
        list.setChild(node, i);
    }

    // Declared in tm.ast at line 24

    public List<SymbolDecl> getSymbolDecls() {
        return getSymbolDeclList();
    }

    // Declared in tm.ast at line 27

    public List<SymbolDecl> getSymbolDeclsNoTransform() {
        return getSymbolDeclListNoTransform();
    }

    // Declared in tm.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<SymbolDecl> getSymbolDeclList() {
        return (List<SymbolDecl>)getChild(5);
    }

    // Declared in tm.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<SymbolDecl> getSymbolDeclListNoTransform() {
        return (List<SymbolDecl>)getChildNoTransform(5);
    }

    // Declared in tm.ast at line 2
    // Declared in tm.ast line 1
    public void setFrequentList(List<SymbolAccess> list) {
        setChild(list, 6);
    }

    // Declared in tm.ast at line 6


    private int getNumFrequent = 0;

    // Declared in tm.ast at line 7

    public int getNumFrequent() {
        return getFrequentList().getNumChild();
    }

    // Declared in tm.ast at line 11


     @SuppressWarnings({"unchecked", "cast"})  public SymbolAccess getFrequent(int i) {
        return (SymbolAccess)getFrequentList().getChild(i);
    }

    // Declared in tm.ast at line 15


    public void addFrequent(SymbolAccess node) {
        List<SymbolAccess> list = getFrequentList();
        list.addChild(node);
    }

    // Declared in tm.ast at line 20


    public void setFrequent(SymbolAccess node, int i) {
        List<SymbolAccess> list = getFrequentList();
        list.setChild(node, i);
    }

    // Declared in tm.ast at line 24

    public List<SymbolAccess> getFrequents() {
        return getFrequentList();
    }

    // Declared in tm.ast at line 27

    public List<SymbolAccess> getFrequentsNoTransform() {
        return getFrequentListNoTransform();
    }

    // Declared in tm.ast at line 31


     @SuppressWarnings({"unchecked", "cast"})  public List<SymbolAccess> getFrequentList() {
        return (List<SymbolAccess>)getChild(6);
    }

    // Declared in tm.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<SymbolAccess> getFrequentListNoTransform() {
        return (List<SymbolAccess>)getChildNoTransform(6);
    }

    // Declared in PerThread.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPerThread() {
        boolean isPerThread_value = isPerThread_compute();
        return isPerThread_value;
    }

    private boolean isPerThread_compute() {  return getModifiers().isPerThread();  }

    protected boolean traceMatchName_computed = false;
    protected String traceMatchName_value;
    // Declared in TMCodegen.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public String traceMatchName() {
        if(traceMatchName_computed)
            return traceMatchName_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        traceMatchName_value = traceMatchName_compute();
        if(isFinal && num == boundariesCrossed)
            traceMatchName_computed = true;
        return traceMatchName_value;
    }

    private String traceMatchName_compute() {  return "tracematch$" + hostType().adviceCounter++;  }

    // Declared in TMCodegen.jrag at line 35
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        if(name_computed)
            return name_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        name_value = name_compute();
        if(isFinal && num == boundariesCrossed)
            name_computed = true;
        return name_value;
    }

    private String name_compute() {  return traceMatchName() + "$body";  }

    protected boolean syncAdviceName_computed = false;
    protected String syncAdviceName_value;
    // Declared in TMCodegen.jrag at line 37
 @SuppressWarnings({"unchecked", "cast"})     public String syncAdviceName() {
        if(syncAdviceName_computed)
            return syncAdviceName_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        syncAdviceName_value = syncAdviceName_compute();
        if(isFinal && num == boundariesCrossed)
            syncAdviceName_computed = true;
        return syncAdviceName_value;
    }

    private String syncAdviceName_compute() {  return "beforeafter$" + hostType().adviceCounter++;  }

    protected boolean someAdviceName_computed = false;
    protected String someAdviceName_value;
    // Declared in TMCodegen.jrag at line 40
 @SuppressWarnings({"unchecked", "cast"})     public String someAdviceName() {
        if(someAdviceName_computed)
            return someAdviceName_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        someAdviceName_value = someAdviceName_compute();
        if(isFinal && num == boundariesCrossed)
            someAdviceName_computed = true;
        return someAdviceName_value;
    }

    private String someAdviceName_compute() {  return "beforeafter$" + hostType().adviceCounter++;  }

    protected boolean syncSootMethod_computed = false;
    protected SootMethod syncSootMethod_value;
    // Declared in TMCodegen.jrag at line 57
 @SuppressWarnings({"unchecked", "cast"})     public SootMethod syncSootMethod() {
        if(syncSootMethod_computed)
            return syncSootMethod_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        syncSootMethod_value = syncSootMethod_compute();
        if(isFinal && num == boundariesCrossed)
            syncSootMethod_computed = true;
        return syncSootMethod_value;
    }

    private SootMethod syncSootMethod_compute() {  return perEventSootMethod(syncAdviceName());  }

    protected boolean someSootMethod_computed = false;
    protected SootMethod someSootMethod_value;
    // Declared in TMCodegen.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public SootMethod someSootMethod() {
        if(someSootMethod_computed)
            return someSootMethod_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        someSootMethod_value = someSootMethod_compute();
        if(isFinal && num == boundariesCrossed)
            someSootMethod_computed = true;
        return someSootMethod_value;
    }

    private SootMethod someSootMethod_compute() {  return perEventSootMethod(someAdviceName());  }

    // Declared in TMCodegen.jrag at line 62
 @SuppressWarnings({"unchecked", "cast"})     public SootMethod perEventSootMethod(String name) {
        SootMethod perEventSootMethod_String_value = perEventSootMethod_compute(name);
        return perEventSootMethod_String_value;
    }

    private SootMethod perEventSootMethod_compute(String name) {  return new SootMethod(name, perEventFormalTypes(), soot.VoidType.v(),
                        soot.Modifier.PUBLIC, new ArrayList());  }

    // Declared in TMCodegen.jrag at line 66
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List perEventFormalTypes() {
        java.util.List perEventFormalTypes_value = perEventFormalTypes_compute();
        return perEventFormalTypes_value;
    }

    private java.util.List perEventFormalTypes_compute() {
        ArrayList types = new ArrayList();
        AdviceSpec spec = getAdviceSpec();
        for (int i = 0; i < spec.getNumParameter(); i++)
            if (spec.getParameter(i).isImplicit() &&
                getSymbolDeclList().refersTo(spec.getParameter(i)))
                    types.add(spec.getParameter(i).type().getSootType());
        return types;
    }

    // Declared in TMCodegen.jrag at line 138
 @SuppressWarnings({"unchecked", "cast"})     public MethodSig syncMethodSig() {
        MethodSig syncMethodSig_value = syncMethodSig_compute();
        return syncMethodSig_value;
    }

    private MethodSig syncMethodSig_compute() {  return perEventMethodSig(syncAdviceName());  }

    // Declared in TMCodegen.jrag at line 141
 @SuppressWarnings({"unchecked", "cast"})     public MethodSig someMethodSig() {
        MethodSig someMethodSig_value = someMethodSig_compute();
        return someMethodSig_value;
    }

    private MethodSig someMethodSig_compute() {  return perEventMethodSig(someAdviceName());  }

    // Declared in TMCodegen.jrag at line 144
 @SuppressWarnings({"unchecked", "cast"})     public MethodSig perEventMethodSig(String name) {
        MethodSig perEventMethodSig_String_value = perEventMethodSig_compute(name);
        return perEventMethodSig_String_value;
    }

    private MethodSig perEventMethodSig_compute(String name) {
        return new MethodSig(soot.Modifier.PUBLIC,
            AbcFactory.AbcClass(hostType().getSootClassDecl()),
            AbcFactory.AbcType(getAdviceSpec().typeVoid().getSootType()),
            name,
            symbolsImplicitFormals(),
            new ArrayList(),
            pos()
        );
    }

    // Declared in TMCodegen.jrag at line 156
 @SuppressWarnings({"unchecked", "cast"})     public int[] implicitParameters() {
        int[] implicitParameters_value = implicitParameters_compute();
        return implicitParameters_value;
    }

    private int[] implicitParameters_compute() {
        int ignored =
            getAdviceSpec().getNumParameter() - bodyAdviceFormals().size();
        int[] jp_params = super.implicitParameters();
        for (int i = 0; i < 3; i++)
            if (jp_params[i] != -1)
                jp_params[i] -= ignored;
        return jp_params;
    }

    // Declared in TMCodegen.jrag at line 167
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List symbolsImplicitFormals() {
        java.util.List symbolsImplicitFormals_value = symbolsImplicitFormals_compute();
        return symbolsImplicitFormals_value;
    }

    private java.util.List symbolsImplicitFormals_compute() {
        ArrayList<Formal> formals = new ArrayList<Formal>();
        AdviceSpec spec = getAdviceSpec();
        for (int i = 0; i < spec.getNumParameter(); i++)
            if (spec.getParameter(i).isImplicit()
                && getSymbolDeclList().refersTo(spec.getParameter(i)))
                    formals.add(spec.getParameter(i).formal());
        return formals;
    }

    // Declared in TMCodegen.jrag at line 178
 @SuppressWarnings({"unchecked", "cast"})     public int[] symbolsImplicitParameters() {
        int[] symbolsImplicitParameters_value = symbolsImplicitParameters_compute();
        return symbolsImplicitParameters_value;
    }

    private int[] symbolsImplicitParameters_compute() {
        int[] jp_params = new int[] { -1, -1, -1 };
        AdviceSpec spec = getAdviceSpec();
        for (int i = 0, pos = 0; i < spec.getNumParameter(); i++) {
            ParameterDeclaration param = spec.getParameter(i);
            if (!param.isImplicit())
                continue;
            if (!getSymbolDeclList().refersTo(spec.getParameter(i)))
                continue;
            for (int j = 0; j < 3; j++)
                if (param.name().equals(implicitVarName(j)))
                    jp_params[j] = pos++;
        }
        return jp_params;
    }

    // Declared in TMCodegen.jrag at line 239
 @SuppressWarnings({"unchecked", "cast"})     public String proceedName() {
        if(proceedName_computed)
            return proceedName_value;
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        proceedName_value = proceedName_compute();
        if(isFinal && num == boundariesCrossed)
            proceedName_computed = true;
        return proceedName_value;
    }

    private String proceedName_compute() {
        if (aroundSymbol() == null)
            return null;
        return "proceed" + traceMatchName().substring(10);
    }

    // Declared in TMCodegen.jrag at line 249
 @SuppressWarnings({"unchecked", "cast"})     public AroundSymbol aroundSymbol() {
        AroundSymbol aroundSymbol_value = aroundSymbol_compute();
        return aroundSymbol_value;
    }

    private AroundSymbol aroundSymbol_compute() {
        AroundSymbol result = null;
        for (int i = 0; i < getNumSymbolDecl() && result == null; i++)
            result = getSymbolDecl(i).getSymbolKind().aroundSymbol();
        return result;
    }

    // Declared in TMInfo.jrag at line 31
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List<Formal> traceMatchFormals() {
        java.util.List<Formal> traceMatchFormals_value = traceMatchFormals_compute();
        return traceMatchFormals_value;
    }

    private java.util.List<Formal> traceMatchFormals_compute() {
        ArrayList<Formal> formals = new ArrayList<Formal>();
        for (int i = 0; i < getAdviceSpec().getNumParameter(); i++)
        {
            ParameterDeclaration param = getAdviceSpec().getParameter(i);
            if (!param.isImplicit())
                formals.add(param.formal());
        }
        return formals;
    }

    // Declared in TMInfo.jrag at line 47
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List<Formal> bodyAdviceFormals() {
        java.util.List<Formal> bodyAdviceFormals_value = bodyAdviceFormals_compute();
        return bodyAdviceFormals_value;
    }

    private java.util.List<Formal> bodyAdviceFormals_compute() {
        ArrayList<Formal> formals = new ArrayList<Formal>();
        addAroundFormals(formals);
        for (int i = 0; i < getAdviceSpec().getNumParameter(); i++)
        {
            ParameterDeclaration param = getAdviceSpec().getParameter(i);
            if (param.isImplicit())
                formals.add(param.formal());
        }
        return formals;
    }

    // Declared in TMInfo.jrag at line 77
 @SuppressWarnings({"unchecked", "cast"})     public java.util.List<String> frequentSymbolNames() {
        java.util.List<String> frequentSymbolNames_value = frequentSymbolNames_compute();
        return frequentSymbolNames_value;
    }

    private java.util.List<String> frequentSymbolNames_compute() {
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < getNumFrequent(); i++)
            names.add(getFrequent(i).name());
        return names;
    }

    // Declared in TMInfo.jrag at line 86
 @SuppressWarnings({"unchecked", "cast"})     public Map symbolToFormalNameList() {
        Map symbolToFormalNameList_value = symbolToFormalNameList_compute();
        return symbolToFormalNameList_value;
    }

    private Map symbolToFormalNameList_compute() {
        Map sym_to_vars = new HashMap();
        for (int i = 0; i < getNumSymbolDecl(); i++) {
            SymbolDecl symbol = getSymbolDecl(i);
            sym_to_vars.put(symbol.name(), symbol.formalNameList());
        }
        return sym_to_vars;
    }

    // Declared in TMInfo.jrag at line 106
 @SuppressWarnings({"unchecked", "cast"})     public Map symbolToAdviceName() {
        Map symbolToAdviceName_value = symbolToAdviceName_compute();
        return symbolToAdviceName_value;
    }

    private Map symbolToAdviceName_compute() {
        Map sym_to_advice = new HashMap();
        for (int i = 0; i < getNumSymbolDecl(); i++) {
            sym_to_advice.put(getSymbolDecl(i).name(),
                            getSymbolDecl(i).perSymbolAdviceName());
        }
        return sym_to_advice;
    }

    // Declared in TMPointcutCodegen.jrag at line 44
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut symbolsPointcut(boolean matchbefore) {
        Pointcut symbolsPointcut_boolean_value = symbolsPointcut_compute(matchbefore);
        return symbolsPointcut_boolean_value;
    }

    private Pointcut symbolsPointcut_compute(boolean matchbefore) {
        Pointcut result = null;
        for (int i = 0; i < getNumSymbolDecl(); i++)
            if (getSymbolDecl(i).getSymbolKind().matchesBefore() == matchbefore)
                result = pcOr(result, getSymbolDecl(i).closedPointcut());
        return result;
    }

    // Declared in TMPointcutCodegen.jrag at line 53
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut beforeSymbolsPointcut() {
        Pointcut beforeSymbolsPointcut_value = beforeSymbolsPointcut_compute();
        return beforeSymbolsPointcut_value;
    }

    private Pointcut beforeSymbolsPointcut_compute() {  return symbolsPointcut(true);  }

    // Declared in TMPointcutCodegen.jrag at line 55
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut afterSymbolsPointcut() {
        Pointcut afterSymbolsPointcut_value = afterSymbolsPointcut_compute();
        return afterSymbolsPointcut_value;
    }

    private Pointcut afterSymbolsPointcut_compute() {  return symbolsPointcut(false);  }

    // Declared in TMPointcutCodegen.jrag at line 57
 @SuppressWarnings({"unchecked", "cast"})     public Pointcut aroundSymbolPointcut() {
        Pointcut aroundSymbolPointcut_value = aroundSymbolPointcut_compute();
        return aroundSymbolPointcut_value;
    }

    private Pointcut aroundSymbolPointcut_compute() {
        for (int i = 0; i < getNumSymbolDecl(); i++)
            if (getSymbolDecl(i).getSymbolKind().aroundSymbol() != null)
                return getSymbolDecl(i).getPointcutExpr().pointcut();
        return null;
    }

    // Declared in TMCodegen.jrag at line 259
    public AroundSymbol Define_AroundSymbol_aroundSymbol(ASTNode caller, ASTNode child) {
        if(caller == getAdviceSpecNoTransform()) {
            return aroundSymbol();
        }
        return getParent().Define_AroundSymbol_aroundSymbol(this, caller);
    }

    // Declared in SymbolNameAnalysis.jrag at line 34
    public ParameterDeclaration Define_ParameterDeclaration_traceMatchParameter(ASTNode caller, ASTNode child, int i) {
        if(caller == getSymbolDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return getAdviceSpec().getParameter(i);
        }
        return getParent().Define_ParameterDeclaration_traceMatchParameter(this, caller, i);
    }

    // Declared in SymbolCodegen.jrag at line 30
    public polyglot.util.Position Define_polyglot_util_Position_traceMatchPosition(ASTNode caller, ASTNode child) {
        if(caller == getSymbolDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return pos();
        }
        return getParent().Define_polyglot_util_Position_traceMatchPosition(this, caller);
    }

    // Declared in SymbolCodegen.jrag at line 27
    public String Define_String_traceMatchName(ASTNode caller, ASTNode child) {
        if(caller == getSymbolDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return traceMatchName();
        }
        return getParent().Define_String_traceMatchName(this, caller);
    }

    // Declared in SymbolCodegen.jrag at line 63
    public int[] Define_int_a_symbolsImplicitParameters(ASTNode caller, ASTNode child) {
        if(caller == getSymbolDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return symbolsImplicitParameters();
        }
        return getParent().Define_int_a_symbolsImplicitParameters(this, caller);
    }

    // Declared in SymbolCodegen.jrag at line 33
    public TypeDecl Define_TypeDecl_returnType(ASTNode caller, ASTNode child) {
        if(caller == getSymbolDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return returnType();
        }
        return super.Define_TypeDecl_returnType(caller, child);
    }

    // Declared in TMNameAnalysis.jrag at line 24
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getSymbolDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
        SimpleSet result = getAdviceSpec().localLookupVariable(name);
        if (!result.isEmpty())
            return result;
        return lookupVariable(name);
    }
}
        return super.Define_SimpleSet_lookupVariable(caller, child, name);
    }

    // Declared in MakeStateMachine.jrag at line 118
    public int Define_int_numSymbols(ASTNode caller, ASTNode child) {
        if(caller == getPointcutExprNoTransform()) {
            return getNumSymbolDecl();
        }
        return getParent().Define_int_numSymbols(this, caller);
    }

    // Declared in MakeStateMachine.jrag at line 125
    public SymbolDecl Define_SymbolDecl_symbol(ASTNode caller, ASTNode child, int i) {
        if(caller == getPointcutExprNoTransform()) {
            return getSymbolDecl(i);
        }
        return getParent().Define_SymbolDecl_symbol(this, caller, i);
    }

    // Declared in SymbolNameAnalysis.jrag at line 30
    public int Define_int_numTraceMatchParameter(ASTNode caller, ASTNode child) {
        if(caller == getSymbolDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return getAdviceSpec().getNumParameter();
        }
        return getParent().Define_int_numTraceMatchParameter(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
