package abc.reentrant.weaving.residues;

import abc.reentrant.weaving.aspectinfo.*;
import abc.reentrant.weaving.weaver.LCflowCodeGenUtils;
import abc.reentrant.weaving.weaver.LCflowCodeGenUtils.LCflowStackGlobalCodeGen;
import java.util.*;
import soot.*;
import soot.jimple.*;
import soot.util.*;
import abc.weaving.aspectinfo.CflowSetup;
import abc.weaving.tagkit.InstructionKindTag;
import abc.weaving.tagkit.Tagger;
import abc.weaving.weaver.WeavingContext;
import abc.soot.util.LocalGeneratorEx;
import abc.soot.util.Restructure;
import abc.weaving.residues.*;
import java.util.*;
import abc.weaving.weaver.*;

public class LCflowResidue extends CflowResidue {

    @Override
    public Residue inline(ConstructorInliningMap cim) {
        return new LCflowResidue(setup, WeavingVar.inline(vars, cim));
    }

    @Override
    public Residue optimize() {
        ArrayList newVars = new ArrayList();
        newVars.addAll(vars);
        return new LCflowResidue(setup, newVars);
    }

    public LCflowResidue(CflowSetup setup, List vars) {
        super(setup, vars);
    }

    @Override
    public Stmt codeGen(SootMethod method, LocalGeneratorEx localgen,
            Chain units, Stmt begin, Stmt fail, boolean sense,
            WeavingContext wc) {
        
        System.out.println(" ...///////////////codegen--- " + setup);

        LCflowCodeGenUtils.LCflowStackGlobalCodeGen codegen = (LCflowStackGlobalCodeGen) setup.codeGen();
        if (wc.getKindTag() == null) {
            // kind is not CFLOW_TEST
            wc.setKindTag(InstructionKindTag.ADVICE_TEST);
        }
        debug("beginning lcflow codegen " + id);

        //Type object = Scene.v().getSootClass("java.lang.Object").getType(); //not necessary

        Stmt last = begin;

        debug("lcflow extra: obtaining current level");
        Local levelValue = localgen.generateLocal(IntType.v(), "levelValue");//here we'll put the actual level

        //statically invoke Level.get() and store result in 'levelValue'
        Stmt getLevelStmt = Jimple.v().newAssignStmt(
                levelValue,
                Jimple.v().newStaticInvokeExpr(Scene.v().getMethod("<abc.reentrant.runtime.Level: int get()>").makeRef())
                );

        units.insertAfter( getLevelStmt, begin );
        last = getLevelStmt;

        debug("getting lcflow state");
        Local cflowStack = setup.getMethodCflowLocal(localgen, method);

        // If once-per-method retrieval is disabled, make a new local for cflowLocal,
        // and use genInitLocal instead of genInitLocalLazily
        Local cflowLocal =
                (abc.main.options.OptionsParser.v().cflow_share_thread_locals() ? setup.getMethodCflowThreadLocal(localgen, method)
                : localgen.generateLocal(setup.codeGen().getCflowInstanceType(), "lcflowThreadLocal"));

        Chain getlocalstack =
                (abc.main.options.OptionsParser.v().cflow_share_thread_locals() ? setup.codeGen().genInitLocalLazily(localgen, cflowLocal, cflowStack)
                : setup.codeGen().genInitLocal(localgen, cflowLocal, cflowStack));
        Tagger.tagChain(getlocalstack, wc);
        last = (Stmt) insertChainAfter(units, getlocalstack, last);

        debug("checking validity");
        // Finding the succeed and fail statements
        // If sense, fail is the fail param, and
        // succeed is the first statement after the abort test (insert a nop for this purpose)
        // Otherwise, reversed
        Stmt failStmt;
        Stmt succeedStmt;
        Stmt afterAbort = Jimple.v().newNopStmt();
        if (sense) {
            failStmt = fail;
            succeedStmt = afterAbort;
        } else {
            failStmt = afterAbort;
            succeedStmt = fail;
        }

        Local isvalid = localgen.generateLocal(BooleanType.v(), "lcflowactive");

        ChainStmtBox isValidCheck = codegen.genIsValidLevelAware(localgen, cflowLocal, levelValue, isvalid, succeedStmt, failStmt);
        Tagger.tagChain(isValidCheck.getChain(), wc);
        last = (Stmt) insertChainAfter(units, isValidCheck.getChain(), last);
        isValidStmt = isValidCheck.getStmt();

        debug("generating abort");
        Expr test;
        if (sense) {
            test = Jimple.v().newEqExpr(isvalid, IntConstant.v(0));
        } else {
            test = Jimple.v().newNeExpr(isvalid, IntConstant.v(0));
        }
        Stmt abort = Jimple.v().newIfStmt(test, fail);
        Tagger.tagStmt(abort, wc);
        units.insertAfter(abort, last);
        units.insertAfter(afterAbort, abort);

        last = afterAbort;


        //note that in lcflow, we always save the level, so vars is at least of size 1
        if (sense && vars.size() > 0) {

            debug("setting up to get bound values");

            List reslocals = new LinkedList();
            List realwvars = new LinkedList();
            Iterator it = vars.iterator();
            while (it.hasNext()) {
                WeavingVar to = (WeavingVar) (it.next());
                if (to != null) {
                    Local newLocal = localgen.generateLocal(to.getType(), "lcflowbound");
                    reslocals.add(newLocal);
                    realwvars.add(to);
                }

            }



            debug("get bound values");
            Chain peekchain = codegen.genPeek(localgen, cflowLocal, reslocals);
            Tagger.tagChain(peekchain, wc);
            last = (Stmt) insertChainAfter(units, peekchain, last);

            debug("Copy bound values into weaving vars");
            it = realwvars.iterator();
            Iterator it2 = reslocals.iterator();
            while (it.hasNext()) {
                WeavingVar to = (WeavingVar) it.next();
                Local res = (Local) it2.next();
                last = to.set(localgen, units, last, wc, res);
                // last.addTag(InstructionKindTag.ADVICE_TEST)
            }

        }

        debug("done with lcflow codegen");
        return last;

    }

    @Override
    public String toString() {
        return "lcflow(" + setup.getPointcut() + ")";
    }
}
