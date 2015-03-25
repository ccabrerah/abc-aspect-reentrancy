package abc.reentrant.test.tests;

/**
 *
 * @author carlos
 */
public class RunAll {

    static public void main(String[] args){

//        abc.reentrant.runtime.AspectStack.activateDebugMessages();
       abc.reentrant.runtime.Level.activateDebugMessages();
//        org.aspectbench.runtime.internal.lcflowinternal.StackCommon.activateDebugMessages();

        System.out.println("Starting tests...");
        org.junit.runner.JUnitCore.main(
                "abc.reentrant.test.tests.BasicLevel",
                "abc.reentrant.test.tests.BasicReentrancyControl",
                "abc.reentrant.test.tests.AspectStackUpdating",
                "abc.reentrant.test.tests.BasicLCflow",
                "abc.reentrant.test.tests.ManagedLevelChange",
                "abc.reentrant.test.tests.DynamicProceed",
                "abc.reentrant.test.tests.BasicLevelDeploy");

    }

}
