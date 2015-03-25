/* abc - The AspectBench Compiler
 * Copyright (C) 2006 Damien Sereni
 *
 * This compiler is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This compiler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this compiler, in the file LESSER-GPL;
 * if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
package org.aspectbench.runtime.internal.lcflowinternal;

public class StackDouble extends StackCommon {

    @Override
    public CommonCell getTop() {
        return this.top;
    }

    public static class Cell extends CommonCell {

        public double elem;
        public Cell prev = null;

        public Cell(Cell prev, double elem, int level) {
            StackCommon.debug( "creating (double) cell with level: "+ level +" element: "+ elem);
            this.prev = prev;
            this.elem = elem;
            this.level = level;
        }

        @Override
        public CommonCell getPrev() {
            return this.prev;
        }
    }
    public Cell top = null;
}
