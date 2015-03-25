package abc.reentrant.weaving.aspectinfo;

import abc.reentrant.weaving.residues.LCflowResidue;
import abc.weaving.aspectinfo.*;

import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import abc.weaving.matching.MatchingContext;
import abc.weaving.matching.WeavingEnv;
import abc.weaving.residues.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LCflow extends Cflow {

    protected LCflow(Pointcut pc, Position pos, int depth) {
        super(pc, pos, depth);
    }

    public LCflow(Pointcut pc, Position pos) {
        super(pc, pos);
    }

    @Override
    public String toString() {
        return "lcflow(" + getPointcut() + ")";
    }

    @Override
    public Pointcut inline(Hashtable renameEnv,
            Hashtable typeEnv,
            Aspect context,
            int cflowdepth) {
        Pointcut pc = this.getPointcut().inline(renameEnv, typeEnv, context, cflowdepth + 1);
        Cflow ret;
        if (pc == this.getPointcut()) {
            ret = this;
        } else {
            ret = new LCflow(pc, getPosition(), depth);
        }
        if (depth == -1) {
            ret.depth = cflowdepth;
        }
        return ret;
    }

    @Override
    public DNF dnf() {
        LCflow ret = new LCflow(getPointcut().dnf().makePointcut(getPointcut().getPosition()), getPosition(), depth);
        return new DNF(ret);
    }

    @Override
    public void registerSetupAdvice(Aspect context, Hashtable typeMap) {
        if (depth == -1) {
            throw new InternalCompilerError("uninlined lcflow registered", getPosition());
        }
        GlobalCflowSetupFactory.CfsContainer cfsCont =
                GlobalLCflowSetupFactory.construct(context, getPointcut(), false, typeMap, getPosition(), depth);
        setCfs(cfsCont.getCfs());
        setRenaming(cfsCont.getRenaming());
        setTypeMap(typeMap);
        getCfs().addUse(this);
    }

    @Override
    public Residue matchesAt(MatchingContext mc) {//this could be replace with a specialconstructor of LCflowResidue that constructs itself using an instance of CflowResidue
        WeavingEnv env = mc.getWeavingEnv();

	List/*<Var>*/ actuals=getCfs().getActuals();
	// List of actuals for the Cflow setup advice
	// These are NOT necessarily the same as the actuals for
	// this (inlined) pointcut, but we have the renaming
	List/*<WeavingVar>*/ weavingActuals=new LinkedList();
	Iterator it=actuals.iterator();
	while(it.hasNext()) {
		Var setupvar = (Var) it.next();
		VarBox inlinedvar =
		   (VarBox) getRenaming().get(setupvar);
		if (inlinedvar == null) {
			throw new RuntimeException("Internal error: Could not find variable "+
					setupvar.getName() + " in lcflow renaming from lcflow:\n"+getPointcut());
		}
		if (inlinedvar.hasVar())
		    weavingActuals.add(env.getWeavingVar(inlinedvar.getVar()));
		else
			weavingActuals.add(null);
	}
	return new LCflowResidue(getCfs(),weavingActuals);
    }
}
