package org.aspectbench.runtime.internal.lcflowinternal;

/**
 *
 * @author carlos
 *
 *
 */
public abstract class StackCommon {

    static public boolean DEBUG = false;

    //TODO: here we could implement some sort of caching to avoid the stack traversal

    public CommonCell getTop(int level) {
        CommonCell currentCell = this.getTop();

        while (true) {
            if(currentCell==null)
                return null;
            if (currentCell.level == level) {
                return currentCell;
            }
            currentCell = currentCell.getPrev();
        }

    }

    static public void activateDebugMessages(){
        StackCommon.DEBUG = true;
    }

    public abstract CommonCell getTop();

    static protected void debug(String string) {
            if(StackCommon.DEBUG){
                System.out.println( "    (LCflow=>StackCommon) " +string);
            }
        }

    public abstract static class CommonCell {

        public int level;

        public int depth() {
            if (this.getPrev() == null) {
                return 1;
            }
            return this.getPrev().depth() + 1;
        }

        public abstract CommonCell getPrev();

        
    }
}

