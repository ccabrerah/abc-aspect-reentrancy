package abc.reentrant.weaving.residues;

import abc.weaving.aspectinfo.Pointcut;
import abc.soot.util.LocalGeneratorEx;
import abc.weaving.aspectinfo.Aspect;
import abc.weaving.matching.MatchingContext;
import abc.weaving.weaver.ConstructorInliningMap;
import abc.weaving.weaver.WeavingContext;
import abc.weaving.residues.NeverMatch;
import abc.weaving.residues.AlwaysMatch;
import abc.weaving.residues.Residue;
import polyglot.types.SemanticException;
import soot.BooleanType;
import soot.Local;
import soot.Scene;
import soot.SootMethod;
import soot.jimple.IntConstant;
import soot.jimple.Jimple;
import soot.jimple.Stmt;
import soot.util.Chain;

/**
 *
 * @author carlos
 */
public class ReentrancyCheckAddOnResidue extends Residue {

    Aspect aspect = null;

    public ReentrancyCheckAddOnResidue( Aspect aspect  ) {

        this.aspect = aspect;
        

    }

    @Override
    public Residue inline(ConstructorInliningMap cim) {
        return this;
    }

    @Override
    public Residue optimize() {
        return this; //nothing to optimize
    }

    @Override
    public Stmt codeGen(SootMethod method, LocalGeneratorEx localgen, Chain units, Stmt begin, Stmt fail, boolean sense, WeavingContext wc) {
       if(!sense){
            this.reverseSense(method, localgen, units, begin, fail, sense, wc);
        }

        //prepare stack checking statements
        //obtain reference to this aspect(can i in all cases? there are two options, aspectof() and trying 'this')
        Local aspectRef = localgen.generateLocal(aspect.getInstanceClass().getSootClass().getType(), "aspectRef");

        Stmt getAspectRefStmt = Jimple.v().newAssignStmt(
                aspectRef,
                Jimple.v().newStaticInvokeExpr(Scene.v().getMethod("<"+ aspect.getInstanceClass().getJvmName() +": "+ aspect.getInstanceClass().getJvmName() +" aspectOf()>").makeRef())
                );

        units.insertAfter(getAspectRefStmt, begin);
        
        Local aspectStackCheckResult = localgen.generateLocal(BooleanType.v(), "aspectStackCheckResult" );

        Stmt invokeAspectStackCheck  = Jimple.v().newAssignStmt(
                aspectStackCheckResult,
                Jimple.v().newStaticInvokeExpr(Scene.v().getMethod("<abc.reentrant.runtime.AspectStack: int contains(java.lang.Object)>").makeRef(), aspectRef)
                );

        units.insertAfter(invokeAspectStackCheck, getAspectRefStmt);

        IntConstant valueIndicatingFail = IntConstant.v(1);//ugly, but we'll just use intergers. 1 means that the aspect was found on the stack!

        Stmt comparationJump = Jimple.v().newIfStmt(Jimple.v().newEqExpr(aspectStackCheckResult, valueIndicatingFail), fail); //if "true", jump to fail

        units.insertAfter(comparationJump, invokeAspectStackCheck);
        
        return comparationJump;
        
        
    }

    @Override
    public String toString() {

        return "Reentrancy_check()";

    }

}
