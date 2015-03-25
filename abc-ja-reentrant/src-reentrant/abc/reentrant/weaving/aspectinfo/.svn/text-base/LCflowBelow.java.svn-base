package abc.reentrant.weaving.aspectinfo;

import abc.reentrant.weaving.residues.LCflowResidue;
import abc.weaving.aspectinfo.Aspect;
import abc.weaving.aspectinfo.CflowBelow;
import abc.weaving.aspectinfo.Pointcut;

import abc.weaving.aspectinfo.Var;
import abc.weaving.aspectinfo.VarBox;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import abc.weaving.matching.MatchingContext;
import abc.weaving.matching.WeavingEnv;
import abc.weaving.residues.CflowResidue;
import abc.weaving.residues.Residue;

public class LCflowBelow extends CflowBelow {

    public LCflowBelow(Pointcut pc, Position pos) {
        super(pc, pos);
    }

    protected LCflowBelow(Pointcut pc, Position pos, int depth) {
        super(pc, pos, depth);
    }

    @Override
    public DNF dnf() {
        LCflowBelow ret = new LCflowBelow(getPointcut().dnf().makePointcut(getPointcut().getPosition()), getPosition(), depth);
        return new DNF(ret);
    }

    @Override
    public String toString() {
        return "lcflowbelow(" + getPointcut() + ")";
    }

    @Override
    public Pointcut inline(Hashtable renameEnv,
            Hashtable typeEnv,
            Aspect context,
            int cflowdepth) {
        Pointcut pc = this.getPointcut().inline(renameEnv, typeEnv, context, cflowdepth + 1);
        CflowBelow ret;
        if (pc == this.getPointcut()) {
            ret = this;
        } else {
            ret = new LCflowBelow(pc, getPosition(), depth); //this enforces creation of LCflowBelows instead of CflowBelows
        }
        if (depth == -1) {
            ret.depth = cflowdepth;
        }
        return ret;
    }

    @Override
    public void registerSetupAdvice(Aspect context, Hashtable typeMap) {
        if (depth == -1) {
            throw new InternalCompilerError("uninlined lcflowbelow registered", getPosition());
        }
        GlobalLCflowSetupFactory.CfsContainer cfsCont =
                GlobalLCflowSetupFactory.construct(context, getPointcut(), true, typeMap, getPosition(), depth);
        setCfs(cfsCont.getCfs());
        setRenaming(cfsCont.getRenaming());
        setTypeMap(typeMap);
        getCfs().addUse(this);
    }

    @Override
    public Residue matchesAt(MatchingContext mc) {

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
					setupvar.getName() + " in cflow renaming from cflowbelow:\n"+getPointcut()+
					"\nwith CFS pointcut\n:"+getCfs().getPointcut());
		}
		if (inlinedvar.hasVar())
			weavingActuals.add(env.getWeavingVar(inlinedvar.getVar()));
		else
			weavingActuals.add(null);
	}
	return new LCflowResidue(getCfs(),weavingActuals);
    }
    
}
