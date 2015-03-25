package abc.reentrant.test.tests;

import abc.reentrant.runtime.*;
import java.util.concurrent.Callable;
import junit.framework.TestCase;

/**
 *
 * @author carlos
 */
public class ManagedLevelChange extends TestCase {

    public void test_basicFunctionality() {

        BasicLevel.assertLevelValue(0, "");
        try {
            Level.up(new Callable<Object>() {

                public Object call() throws Exception {
                    BasicLevel.assertLevelValue(1, "");
                    Level.down(new Callable<Object>() {

                        public Object call() throws Exception {
                            BasicLevel.assertLevelValue(0, "");
                            return null;
                        }
                    });
                    BasicLevel.assertLevelValue(1, "");
                    return null;
                }
            });
        } catch (Exception e) {
        }

        BasicLevel.assertLevelValue(0, "");

    }
}
