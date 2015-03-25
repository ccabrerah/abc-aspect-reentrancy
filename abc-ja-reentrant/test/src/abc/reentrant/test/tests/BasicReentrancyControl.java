package abc.reentrant.test.tests;

import junit.framework.TestCase;

/**
 *
 * @author carlos
 *
 * IDEA: trigger reentrancy by:
 *  - using level down inside an advice, and then (indirectly) generating a join point that should be matched by a pointcut inside the same aspect
 *
 * 
 */
public class BasicReentrancyControl extends TestCase{

    public static boolean flag1;

    @Override
    public void setUp() {
        flag1 = false;
    }

    public void test_reentrancyControlLoops(){
        System.out.println( "\n-Testing reentrancy control: pointcut loops" );
        foo();
        //no assert here: if the test doesn't works, it degenerates into a infinite loop
    }

    public void test_reentrancyControlAdviceReentrancy(){
        System.out.println( "\n-Testing reentrancy control: same advice reentering" );
        bar();

        assertFalse(flag1);

    }

    public static void foo(){
        //do nothing
    }

    public static void bar(){
        //do nothing
    }

    public static void bar2(){
        //do nothing
    }

}

aspect ReentrantAspect{

    pointcut matchFoo() : execution(void BasicReentrancyControl.foo());

    after() : matchFoo() {
        //this advice will generate a reentrant join point by setting the level down and calling Foo
        //in fact, this would cause a loop because the execution would be adviced again
        abc.reentrant.runtime.Level.forceUnmanagedDown();

        BasicReentrancyControl.foo(); //this would cause an infinite loop

        abc.reentrant.runtime.Level.forceUnmanagedUp();

    }


    pointcut matchBar() : execution(void BasicReentrancyControl.bar());

    pointcut matchBar2() : execution(void BasicReentrancyControl.bar2());

    before() : matchBar() {
        
        abc.reentrant.runtime.Level.forceUnmanagedDown();

        BasicReentrancyControl.bar2();

        abc.reentrant.runtime.Level.forceUnmanagedUp();
        
    }

    before() : matchBar2() {
        //this shouldn't execute
        BasicReentrancyControl.flag1 = true;
    }


}