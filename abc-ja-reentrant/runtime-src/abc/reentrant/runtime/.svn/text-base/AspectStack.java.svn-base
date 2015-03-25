package abc.reentrant.runtime;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 *
 * @author carlos
 *
 * Part of the reentrancy control implementation
 *
 * HUGE NOTE: here we make the assumption that, at most, there will be one copy of a reference in the stack
 * that's because we are implementing reentrancy control, so there will never be two copies of a reference.
 * Note that this won't hold in case where you are debugging. In case that the assumption doesn't hold,
 * please reimplement this class without the queries to the hashtable.
 */
public class AspectStack {

    static private boolean DEBUG = false;

    private AspectStack() {
    }
    private static AspectStack _instance = null;

    static protected AspectStack getInstance() {
        if (AspectStack._instance == null) {
            AspectStack._instance = new AspectStack();
        }

        return AspectStack._instance;
    }
    private ThreadLocal<HashSet> tl_hashSet = new ThreadLocal() {

        @Override
        protected synchronized Object initialValue() {
            return new HashSet();
        }
    };
    private ThreadLocal<LinkedList> tl_stack = new ThreadLocal() {

        @Override
        protected synchronized Object initialValue() {
            return new LinkedList();
        }
    };

    public static Object pop() {
        if(DEBUG){
            System.out.println("(-)AspectStack: starting pop(). There are "+AspectStack.getInstance().tl_stack.get().size()+" element(s) in the stack");
        }

        try{
            Object top_element = AspectStack.getInstance().tl_stack.get().pop();

            AspectStack.getInstance().tl_hashSet.get().remove(top_element);

            if(DEBUG){
                System.out.println("(-)AspectStack pop():"+top_element+")");
            }

            return top_element;
        } catch(NoSuchElementException e){
            if(DEBUG){
                System.out.println("(-)AspectStack pop(): operation failed!! this must be an error in the compiler generated code. Continuing execution.");
            }

            return null;
        }


    }

    public static boolean push(Object element) {
        if(DEBUG){
            System.out.println("(+)AspectStack: push("+element+")");
        }

        if (AspectStack.getInstance().tl_hashSet.get().add(element)) {
            AspectStack.getInstance().tl_stack.get().push(element);
            return true;
        } else {
            if(DEBUG){
                throw new RuntimeException("Critical error: now reentering aspect");
            }
            return false; //this should be a critical error. It means that we are reentering an advice which is precisely what we are trying to avoid with our reentrancy control
        }

    }

    //we'll just use intergers
    //1 means that the aspect was found on the stack!
    public static int contains(Object element) {
        if(DEBUG){
            System.out.print("(?)AspectStack: contains("+element+") ?");
        }
        if( AspectStack.getInstance().tl_hashSet.get().contains(element) ){
            if(DEBUG){
                System.out.println("... got true");
            }
            return 1;
        }
        else{
            if(DEBUG){
                System.out.println("... got false");
            }
            return 0;
        }

    }

    public static Object peek(){
        return AspectStack.getInstance().tl_stack.get().peek();
    }


    public static void activateDebugMessages( ){
        DEBUG = true;
    }
}
