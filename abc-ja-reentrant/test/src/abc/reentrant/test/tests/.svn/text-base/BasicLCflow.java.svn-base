package abc.reentrant.test.tests;

import junit.framework.TestCase;
import abc.reentrant.runtime.Level;

/**
 *
 * @author carlos
 */
public class BasicLCflow extends TestCase{
    static public boolean flag1 = false;
    static public boolean flag2 = false;

    public void test_works(){
        System.out.println("\n-Testing LCflow: basic level based matching tests");

        m();

        i();

        assertTrue("lcflow activation flag", flag1);
        assertFalse("lcflow activation flag2 should remaing in false: there is a levelUp() inserted that should prevent lcflow from matching", flag2);
    }


    static public void m() {
        n();
    }

    static public void n(){
        
    }

    static public void i() {
        Level.forceUnmanagedUp();

        j();

        Level.forceUnmanagedDown();
    }

    static public void j() {
    }



}
aspect BasicLCflowTestASpect{

    void around() : lcflow( call( void m() ) ) && call( void n() ) {
        System.out.println("aspect1 activated, lvl:" + Level.get());


        BasicLCflow.flag1 = true;

        proceed();



    }

    before() : lcflow( call(void i()) ) && call( void j() ) {
        System.out.println("aspect2 activated, lvl:" + Level.get());
        
        BasicLCflow.flag2 = true;

    }

}
