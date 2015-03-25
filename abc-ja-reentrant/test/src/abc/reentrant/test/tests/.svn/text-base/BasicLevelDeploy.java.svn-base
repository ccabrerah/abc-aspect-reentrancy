package abc.reentrant.test.tests;

import abc.reentrant.runtime.Level;
import junit.framework.TestCase;

/**
 *  Test correct level matching.Uses 3 identical aspects, except for that they are declared to apply to different levels.
 * 
 * @author carlos
 */
public class BasicLevelDeploy extends TestCase {
    public static boolean flag1 = false;
    public static boolean flag2 = false;
    public static boolean flag3 = false;

    @Override
    public void setUp(){
        flag1 = false;
        flag2 = false;
        flag3 = false;
    }

    public void test_baseLevelAdvicing() {
        System.out.println("\n-Testing level matching when deployed on level 1");
        doNothing();

        assertTrue(flag1);
        assertTrue(flag2);
        assertFalse(flag3);

    }

    public void test_level1Advicing() {
        System.out.println("\n-Testing level matching when deployed on level 2");
        Level.forceUnmanagedUp();

        doNothing();

        assertFalse(flag1);
        assertFalse(flag2);
        assertTrue(flag3);

        Level.forceUnmanagedDown();

    }

    public void doNothing(){}


}

  aspect DeployOnDefaultLevel{ //should work exactly as if it had been declared as 'onlevel 1'
      pointcut match_test() : execution(void BasicLevelDeploy.doNothing());

      before() : match_test() {
          BasicLevelDeploy.flag1 = true;
      }
  }

  onlevel 1 aspect DeployOnLevelOne{
      pointcut match_test() : execution(void BasicLevelDeploy.doNothing());

      before() : match_test() {
          BasicLevelDeploy.flag2 = true;
      }
  }

  onlevel 2 aspect DeployOnLevelTwo{
      pointcut match_test() : execution(void BasicLevelDeploy.doNothing());

      before() : match_test() {
          BasicLevelDeploy.flag3 = true;
      }
  }