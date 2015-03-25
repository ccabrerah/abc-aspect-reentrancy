package abc.reentrant.weaving.aspectinfo;

/* based on GlobalCflowFactory.java */
import java.util.*;
import abc.main.*;
import abc.weaving.aspectinfo.*;
import polyglot.util.Position;

public class GlobalLCflowSetupFactory extends GlobalCflowSetupFactory{

    public static class CfsStore extends GlobalCflowSetupFactory.CfsStore{

        /* based on GlobalCflowSetupFactory::CfsStore::createNewUnificationCfs */
        public static CflowSetup createNewUnificationCfs(
                Aspect a, boolean isBelow,
                Hashtable origTypeMap /*The typemap of the NEW pc*/,
                Hashtable newTypeMap /*To reveive the new type map*/,
                CflowSetup oldcfs /*The existing instance of Cfs*/,
                Position pos, int depth,
                Unification unification) {

            // Creating a new typemap for the renamed vars
            Set/*<String>*/ fvs = new HashSet();
            unification.getPointcut().getFreeVars(fvs);
            Iterator it = fvs.iterator();

            while (it.hasNext()) {
                String fv = (String) it.next();
                // Need to find the type of fv
                // It is mapped to a var in the new pc, to a var in the old (cfs) pc
                // or to both
                VarBox ve1 =
                        unification.getFromString1(fv);
                VarBox ve2 =
                        unification.getFromString2(fv);

                if (ve1.hasVar()) {
                    Formal f1 = findFormal(ve1.getVar().getName(), oldcfs.getFormals());
                    if (ve2.hasVar()) {
                        // fv is mapped to a var in BOTH
                        AbcType tp2 = (AbcType) origTypeMap.get(ve2.getVar().getName());
                        if (!tp2.equals(f1.getType())) {
                            throw new RuntimeException("Error: types are not equal after unification:\n" +
                                    fv + "->" + f1.getName() + " : " + f1.getType() + "\n" +
                                    fv + "->" + ve2.getVar() + " : " + tp2);
                        }
                        newTypeMap.put(fv, tp2);
                    } else {
                        // fv is mapped to a var in 1 only
                        newTypeMap.put(fv, f1.getType());
                    }
                } else if (ve2.hasVar()) {
                    // fv is mapped to a var in 2 only
                    AbcType tp2 = (AbcType) origTypeMap.get(ve2.getVar().getName());
                    newTypeMap.put(fv, tp2);
                } else // Could not find it in either pc: this is a unification bug
                {
                    throw new RuntimeException("Could not find variable " + fv +
                            " in either of the unified pointcuts:");
                }

            }

            // Create the cfs
            CflowSetup newcfs = LCflowSetup.construct(a, unification.getPointcut(), isBelow, newTypeMap, pos, depth);
            abc.main.Main.v().getAbcExtension().getGlobalAspectInfo().addAdviceDecl(newcfs);

            // Return the new cfs
            return newcfs;

        }

    }

    /* based on GlobalCflowSetupFactory::buildNewLCfs */
    public static GlobalCflowSetupFactory.CfsContainer buildNewCfs(
            Aspect a, Pointcut pc, boolean isBelow, Hashtable typeMap, Position pos, int depth) {

        debug("Creating a new CFS for (depth " + depth + "," + (isBelow ? "cflowbelow" : "cflow") + "):\n" + pc);
        CflowSetup cfs = LCflowSetup.construct(a, pc, isBelow, typeMap, pos, depth);

        // Need to create the identity renaming on pc
        Hashtable/*<Var,PointcutVarEntry>*/ renaming = new Hashtable();
        Iterator it = cfs.getActuals().iterator();
        while (it.hasNext()) {
            Var v = (Var) it.next();
            VarBox ve =
                    new VarBox(v);
            renaming.put(v, ve);
        }

        // Need to register the new CFS as an abstract advice decl
        abc.main.Main.v().getAbcExtension().getGlobalAspectInfo().addAdviceDecl(cfs);

        // Need to remember that we've created cfs
        // IF THE DEBUG FLAG IS NOT SET (o/w no point)
        if (abc.main.options.OptionsParser.v().cflow_use_sharing()) {
            CfsStore.put(pc, typeMap, cfs);
        }

        // Now return it
        return new CfsContainer(cfs, renaming);
    }

    /* based on GlobalCflowSetupFactory::construct */
    public static CfsContainer construct(
            Aspect a, Pointcut pc, boolean isBelow, Hashtable typeMap, Position pos, int depth) {

        if (!abc.main.options.OptionsParser.v().cflow_use_sharing()) {
            return buildNewCfs(a, pc, isBelow, typeMap, pos, depth);
        }

        CfsContainer cfsc = CfsStore.findExisting(a, pc, isBelow, typeMap, pos, depth); //TODO: check that this still works

        if (cfsc == null) {
            // We need to create a new one
            return buildNewCfs(a, pc, isBelow, typeMap, pos, depth);

        } else {
            return cfsc;
        }

    }

}