/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abc.reentrant.weaving.residues;

import abc.soot.util.LocalGeneratorEx;
import abc.weaving.residues.AlwaysMatch;
import abc.weaving.residues.Residue;
import abc.weaving.tagkit.InstructionKindTag;
import abc.weaving.weaver.ConstructorInliningMap;
import abc.weaving.weaver.WeavingContext;

import soot.*;
import soot.jimple.*;
import soot.util.*;

/**
 *
 * @author carlos
 */
public class LevelResidue extends Residue {

    private int levelNumber;

    public LevelResidue(int _levelnumber){
        this.levelNumber = _levelnumber;
    }

    private LevelResidue(){}

    @Override
    public Residue inline(ConstructorInliningMap cim) {
        return this;
    }

    @Override
    public Residue optimize() {
        return this;
    }

    @Override
    public Stmt codeGen(SootMethod method, LocalGeneratorEx localgen, Chain units, Stmt begin, Stmt fail, boolean sense, WeavingContext wc) {

        Scene.v().loadClassAndSupport("abc.reentrant.runtime.Level");

        if (wc.getKindTag() == null) {
            wc.setKindTag(InstructionKindTag.ADVICE_EXECUTE);
        }

        Stmt currentStmt = begin;

        Local levelValue = localgen.generateLocal(IntType.v(), "levelValue");//here we'll put the actual level(in runtime)

        //statically invoke Level.get() and store result in 'levelValue'
        Stmt getLevelStmt = Jimple.v().newAssignStmt(
                levelValue,
                Jimple.v().newStaticInvokeExpr(Scene.v().getMethod("<abc.reentrant.runtime.Level: int get()>").makeRef())
                );

        units.insertAfter(getLevelStmt, currentStmt);
        currentStmt=getLevelStmt;

        IntConstant pointcutLevelValue = IntConstant.v(this.levelNumber); //expose this pointcut's level as a constant value

        Expr test;

        if (sense) {
            test = Jimple.v().newNeExpr(levelValue, pointcutLevelValue);
        } else {
            test = Jimple.v().newEqExpr(levelValue, pointcutLevelValue);
        }

        Stmt ifTest = Jimple.v().newIfStmt(test, fail);

        units.insertAfter(ifTest, currentStmt);
        currentStmt = ifTest;

        return currentStmt;
    }

    @Override
    public String toString() {
        return "level(" + levelNumber + ")";
    }
}