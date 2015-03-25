package abc.reentrant.test.tests;

import abc.reentrant.runtime.Level;
import junit.framework.TestCase;

/**
 *
 * @author carlos
 */
public class BasicLevel extends TestCase {

    public void test_baseLevelAdvicing() {/*Here we'll be testing that the level gets automatically changed*/
        System.out.println("\n-Testing execution Level implementation: correct execution level updating");
        BasicLevel.assertLevelValue(0, "------before all advices------");
        doSomething();
        BasicLevel.assertLevelValue(0, "------after before  advices------");
        doSomethingElse();
        BasicLevel.assertLevelValue(0, "------after around advices------");
        doSomethingMore();
        BasicLevel.assertLevelValue(0, "------after if pointcut------");

    }

    public void test_basicRuntimeChecks() {
        System.out.println("\n-Testing execution Level implementation: runtime functionality");

        BasicLevel.assertLevelValue(0, "--before a manual level up--");
        Level.forceUnmanagedUp();
        BasicLevel.assertLevelValue(1, "--after a manual level up--");
        Level.forceUnmanagedDown();
        BasicLevel.assertLevelValue(0, "--after a manual level down--");

    }

    //base level code
    void doSomething() {

        BasicLevel.assertLevelValue(0, "inside method: doSomething()");

    }

    void doSomethingElse() {

        BasicLevel.assertLevelValue(0, "inside method: doSomethingElse()");

    }

    private void doSomethingMore() {

        BasicLevel.assertLevelValue(0, "inside method: doSomethingMore()");

    }

    static boolean doSomeCheck() {
        //this method serves as a condition for an if() pointcut below
        BasicLevel.assertLevelValue(1, "inside method: doSomeCheck(), (if() poincut condition)");
        return true;

    }

    //helper method, prints a message and asserts the level value
    static public void assertLevelValue(int correct_value, String message) {
        if (message == null) {
            message = "";
        }
        System.out.print(message + " expected level ->" + correct_value + "<-, ");
        System.out.println("current level ->" + Level.get() + "<-");

        assertTrue(Level.get() == correct_value);
    }
}

//Aspects for testing
aspect PcBaseLevelApplication

{

    pointcut match_1() : call(void BasicLevel.doSomething()) ;// && cflow(execution(void BasicLevel.test_baseLevelAdvicing()));

    before(

) : match_1() {

        BasicLevel.assertLevelValue( 1 , "asp1-match1");



}

    pointcut match_2() : call(void BasicLevel.doSomethingElse()) ;



void around() : match_2() {

        System.out.println("*advice start*");

        BasicLevel.

assertLevelValue( 1 , "asp1-match2-preProceed");

        proceed(

);

        BasicLevel.

assertLevelValue( 1 , "asp1-match2-postProceed");

        System.

out.println("*advice end*");



}

    pointcut match_3(): call(void BasicLevel.doSomethingMore()) && if(BasicLevel.doSomeCheck()) ;

    after(

) : match_3() {
        //this advice is of no importance. the important check is made on the if pointcut above
        BasicLevel.assertLevelValue( 1 , "after match_3 advice (it's poincut has an if pointcut)");



}

    void around() : match_3() {
        //this advice is of no importance. the important check is made on the if pointcut above
        BasicLevel.assertLevelValue( 1 , "around match_3 advice (it's poincut has an if pointcut), before proceed()");
        proceed(

);
        BasicLevel.

assertLevelValue( 1 , "around match_3 advice (it's poincut has an if pointcut), after proceed()");


}

    before() : match_3() {
        //this advice is of no importance. the important check is made on the if pointcut above
        BasicLevel.assertLevelValue( 1 , "before match_3 advice (it's poincut has an if pointcut)");



}

}

aspect PcBaseLevelApplicationCopy{
    //this is just a copy of a part of PcBaseLevelApplication

    pointcut match_1() : call(void BasicLevel.doSomething()) ;// && cflow(execution(void BasicLevel.test_baseLevelAdvicing()));

    before(

) : match_1() {

        BasicLevel.assertLevelValue( 1 , "asp2-match1");



}

    pointcut match_2() : call(void BasicLevel.doSomethingElse()) ;



void around() : match_2() {

        System.out.println("*advice start*");

        BasicLevel.

assertLevelValue( 1 , "asp2-match2-preProceed");

        proceed(

);

        BasicLevel.

assertLevelValue( 1, "asp2-match2-postProceed");

        System.

out.println("*advice end*");



}
}


aspect PcBaseLevelApplicationCopy2{
    //this is just a copy of a part of PcBaseLevelApplication

    pointcut match_1() : call(void BasicLevel.doSomething()) ;// && cflow(execution(void BasicLevel.test_baseLevelAdvicing()));

    before(

) : match_1() {

        BasicLevel.assertLevelValue( 1, "asp3-match1");



}

    pointcut match_2() : call(void BasicLevel.doSomethingElse()) ;



void around() : match_2() {

        System.out.println("*advice start*");

        BasicLevel.

assertLevelValue( 1, "asp3-match2-preProceed");

        proceed(

);

        BasicLevel.

assertLevelValue( 1, "asp3-match2-postProceed");

        System.

out.println("*advice end*");


}
}
