/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abc.reentrant.weaving.aspectinfo;

import abc.weaving.matching.MatchingContext;
import abc.weaving.residues.AlwaysMatch;
import abc.weaving.residues.NeverMatch;
import abc.weaving.aspectinfo.Aspect;
import abc.weaving.aspectinfo.Pointcut;
import abc.reentrant.weaving.residues.ReentrancyCheckAddOnResidue;
import abc.weaving.residues.Residue;
import java.util.Hashtable;
import java.util.Set;
import polyglot.types.SemanticException;
import polyglot.util.Position;

/**
 *
 * @author carlos
 */
public class ReentrancyCheckPointcutAddOn extends Pointcut {

    private Aspect aspect;

    @Override
    public String toString() {
        return "Reentrancy Check pointcut()";
    }

    public ReentrancyCheckPointcutAddOn(Aspect _aspect, Position pos) {
        super(pos);

        this.aspect = _aspect;

    }

    @Override
    public Residue matchesAt(MatchingContext mc) throws SemanticException {

        //if the pointcut always matches, we still need to avoid advice triggered reentrancy
        return new ReentrancyCheckAddOnResidue( this.aspect );
    }

    @Override
    public Pointcut inline(Hashtable renameEnv, Hashtable typeEnv, Aspect context, int cflowdepth) {
            return this;
    }

    @Override
    public void registerSetupAdvice(Aspect context, Hashtable typeEnv) {
    }

    @Override
    public void getFreeVars(Set result) {
    }
}
