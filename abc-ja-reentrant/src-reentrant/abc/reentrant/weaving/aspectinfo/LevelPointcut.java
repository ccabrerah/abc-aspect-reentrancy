/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abc.reentrant.weaving.aspectinfo;

import abc.reentrant.weaving.residues.LevelResidue;
import abc.weaving.aspectinfo.Aspect;
import abc.weaving.aspectinfo.Pointcut;
import abc.weaving.matching.MatchingContext;
import abc.weaving.residues.Residue;
import java.util.Hashtable;
import java.util.Set;
import polyglot.types.SemanticException;
import polyglot.util.Position;

/**
 *
 * @author carlos
 */
public class LevelPointcut extends Pointcut{
    private int levelNumber;

    public LevelPointcut(int l, Position pos){
        super(pos);

        this.levelNumber = l;
    }

    @Override
    public String toString() {
        return "level("+levelNumber+")";
    }

    @Override
    public Residue matchesAt(MatchingContext mc) throws SemanticException {
        return new LevelResidue(this.levelNumber);
    }

    @Override
    public Pointcut inline(Hashtable renameEnv, Hashtable typeEnv, Aspect context, int cflowdepth) {
        //there is nothing that could be renamed in this pointcut
        return this;
    }

    @Override
    public void registerSetupAdvice(Aspect context, Hashtable typeEnv) {  } //doesn't applies

    @Override
    public void getFreeVars(Set result) {   } //nothing to do here

}