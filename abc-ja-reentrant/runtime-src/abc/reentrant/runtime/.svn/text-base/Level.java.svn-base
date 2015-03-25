package abc.reentrant.runtime;

import java.util.concurrent.Callable;

/**
 *
 * @author carlos
 */
public class Level {

    static private boolean DEBUG = false;
    private static Level _instance = null;

    private Level() {
    }

    static protected Level getInstance() {
        if (Level._instance == null) {
            Level._instance = new Level();
        }

        return Level._instance;
    }
    private ThreadLocal<Integer> currentLevel = new ThreadLocal() {

        @Override
        protected synchronized Object initialValue() {
            return new Integer(0);
        }
    };

    static public int get() {
	if( DEBUG ){	
		System.out.println(" returning level -- on level "+ Level.getInstance().currentLevel.get().intValue());
	}
        return Level.getInstance().currentLevel.get().intValue();
    }


    static private void set(int _level) {
        Level.getInstance().currentLevel.set(new Integer(_level));
    }

    static /*private*/ public void forceUnmanagedUp() {
        Level.set(Level.get() + 1);
        if (DEBUG) {
            System.out.println("+ level up");
        }
    }

    static public void forceUnmanagedUp1(){
        if (DEBUG) {	System.out.print("+ ProceedInvocation.java: asking level -- "); }
	forceUnmanagedUp();
    }

    static public void forceUnmanagedUp2(){
        if (DEBUG) {	System.out.print("+ AdviceDecl.java: asking level -- "); }
	forceUnmanagedUp();
    }

    static public void forceUnmanagedUp3(){
        if (DEBUG) {	System.out.print("+ IfResidue.java: asking level -- "); }
	forceUnmanagedUp();
    }

    /* Uses java.util.concurrent.callable */
    static public Object up(Callable callable) throws  Exception{
        int levelAtStart = Level.get();
        Object result = null;

        Level.forceUnmanagedUp(); //this should not fail
        try {
            result = callable.call();
        } catch (Exception e) {
            Level.set(levelAtStart);
            throw e;
        }

        Level.set(levelAtStart);
        return result;

    }

//    /* Uses abc.reentrant.runtime.Expr */
//    static public Object up(Expr expr) {
//        int levelAtStart = Level.get();
//        Object result = null;
//
//        Level.forceUnmanagedUp(); //this should not fail
//
//        result = expr.eval();
//
//        Level.set(levelAtStart);
//        return result;
//    }

    static /*private*/ public void forceUnmanagedDown() {
        Level.set(Level.get() - 1);
        if (DEBUG) {
            System.out.println("- level down");
	    if (Level.get() < 0) System.out.println(" !! Execution level would've gone BELOW zero. Serious error.");
        }
	if (Level.get() < 0)
	    Level.set(0);
    }

    static public void forceUnmanagedDown1(){
	        if (DEBUG) { System.out.print("- ProceedInvocation.java:");}
	forceUnmanagedDown();
    }

    static public void forceUnmanagedDown2(){
	        if (DEBUG) { System.out.print("- AdviceDecl.java:");}
	forceUnmanagedDown();
    }

    static public void forceUnmanagedDown3(){
	        if (DEBUG) { System.out.print("- IfResidue.java:");}
	forceUnmanagedDown();
    }

    /* Uses java.util.concurrent.callable*/
    static public Object down(Callable callable) throws Exception{
        int levelAtStart = Level.get();
        Object result = null;

        Level.forceUnmanagedDown(); //this should not fail
        try {
            result = callable.call();
        } catch (Exception e) {
	    Level.set(levelAtStart);
            throw e;
        }

        Level.set(levelAtStart);
        return result;

    }

//        /* Uses abc.reentrant.runtime.Expr */
//    static public Object down(Expr expr) {
//        int levelAtStart = Level.get();
//        Object result = null;
//
//        Level.forceUnmanagedDown(); //this should not fail
//
//        result = expr.eval();
//
//        Level.set(levelAtStart);
//        return result;
//
//    }

    public static void activateDebugMessages() {
        DEBUG = true;
    }
}
