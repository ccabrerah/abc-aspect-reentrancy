package abc.ja.reentrant;

import abc.aspectj.parse.AbcLexer;
import abc.aspectj.parse.LexerAction_c;
import abc.aspectj.parse.PerClauseLexerAction_c;
import abc.ja.reentrant.parse.JavaParser.Terminals; // generated code
import abc.reentrant.weaving.weaver.LCflowCodeGenUtils;
import soot.Scene;
import soot.SootClass;
import abc.main.Debug;


public class AbcExtension extends abc.ja.eaj.AbcExtension
{

     @Override
    public void addBasicClassesToSoot()
    {

        super.addBasicClassesToSoot();
        LCflowCodeGenUtils.addBasicClassesToSoot();

        /*These are part of the level's implementation*/
        Scene.v().addBasicClass("java.lang.ThreadLocal$ThreadLocalMap",
                                SootClass.SIGNATURES);
        Scene.v().addBasicClass("java.util.concurrent.atomic.AtomicInteger",
                                SootClass.SIGNATURES);
        Scene.v().addBasicClass("abc.reentrant.runtime.Level",
                                SootClass.SIGNATURES);
        Scene.v().addBasicClass("abc.reentrant.runtime.Level$1",
                                SootClass.SIGNATURES);

        /*This is part of the reentrancy control implementation*/
        Scene.v().addBasicClass("abc.reentrant.runtime.AspectStack",
                                SootClass.SIGNATURES);
        Scene.v().addBasicClass("java.util.LinkedList",
                                SootClass.SIGNATURES);
        Scene.v().addBasicClass("java.util.HashSet",
                                SootClass.SIGNATURES);
        Scene.v().addBasicClass("abc.reentrant.runtime.AspectStack$2",
                                SootClass.SIGNATURES);
        Scene.v().addBasicClass("abc.reentrant.runtime.AspectStack$1",
                                SootClass.SIGNATURES);

//        abc.main.Debug.v().adviceInliner = true;
//        abc.main.Debug.v().aroundWeaver = true;
//        abc.main.Debug.v().aroundInliner = true;
//        abc.main.Debug.v().aspectCodeGen = true;
//        abc.main.Debug.v().weaverDriver = true;
//        abc.main.Debug.v().pointcutCodeGen = true;
//        abc.main.Debug.v().cflowAnalysis = true;
//        abc.main.Debug.v().cflowAnalysisStats = true;
//        abc.main.Debug.v().residueCodeGen = true;
//        abc.main.Debug.v().constructorInliner = true;
//	  abc.main.Debug.v().patternMatches = true;

    }

    @Override
    protected void collectVersions(StringBuffer versions)
    {
        super.collectVersions(versions);
        versions.append(" with execution levels, level pointcut, level aware cflow, reentrancy control " +
                        new abc.ja.reentrant.Version().toString() +
                        "\n");
    }

    
    @Override
    public abc.ja.reentrant.CompileSequence createCompileSequence() {
      return new CompileSequence(this);
    }

    
    @Override
    public void initLexerKeywords(AbcLexer lexer) {

        super.initLexerKeywords(lexer);
        

        lexer.addPointcutKeyword("lcflow", new LexerAction_c(new Integer(Terminals.PC_LCFLOW)));
        lexer.addPointcutKeyword("lcflowbelow", new LexerAction_c(new Integer(Terminals.PC_LCFLOWBELOW)));
	lexer.addPointcutKeyword("level", new LexerAction_c(new Integer(Terminals.PC_LEVEL)));
        lexer.addGlobalKeyword("onlevel", new LexerAction_c(new Integer(Terminals.ONLEVEL)));
        lexer.addPointcutKeyword("gcflow", new LexerAction_c(new Integer(Terminals.PC_GCFLOW)));
        lexer.addPointcutKeyword("gcflowbelow", new LexerAction_c(new Integer(Terminals.PC_GCFLOWBELOW)));

        	
    }

}
