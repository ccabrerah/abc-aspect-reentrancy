/* abc - The AspectBench Compiler
 * Copyright (C) 2004 Ganesh Sittampalam
 * Copyright (C) 2004 Aske Simon Christensen
 * Copyright (C) 2004 Damien Sereni
 * Copyright (C) 2006 Eric Bodden
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

package abc.eaj.weaving.aspectinfo;

import java.util.Hashtable;
import java.util.Set;

import polyglot.util.Position;
import abc.eaj.weaving.matching.LockShadowMatch;
import abc.weaving.aspectinfo.Aspect;
import abc.weaving.aspectinfo.DynamicValuePointcut;
import abc.weaving.aspectinfo.LocalPointcutVars;
import abc.weaving.aspectinfo.Pointcut;
import abc.weaving.aspectinfo.Unification;
import abc.weaving.matching.MatchingContext;
import abc.weaving.matching.ShadowMatch;
import abc.weaving.residues.AlwaysMatch;
import abc.weaving.residues.NeverMatch;
import abc.weaving.residues.Residue;

/** Handler for <code>monitorenter</code> condition pointcut with a
 *  universal pattern argument.
 *  @author Eric Bodden
 */
public class LockPointcut extends DynamicValuePointcut {

    public LockPointcut(Position pos) {
        super(pos);
    }

    public final Residue matchesAt(MatchingContext mc) {
        ShadowMatch sm = mc.getShadowMatch();

        if(sm instanceof LockShadowMatch) {
            return AlwaysMatch.v();
        } else {
        	return NeverMatch.v();
        }        
    }

    public String toString() {
        return "lock()";
    }
    
    public void registerSetupAdvice
        (Aspect aspct,Hashtable/*<String,AbcType>*/ typeMap) {}
    public void getFreeVars(Set/*<String>*/ result) {}

        /* (non-Javadoc)
         * @see abc.weaving.aspectinfo.Pointcut#unify(abc.weaving.aspectinfo.Pointcut, java.util.Hashtable, java.util.Hashtable, abc.weaving.aspectinfo.Pointcut)
         */
        public boolean unify(Pointcut otherpc, Unification unification) {

                if (otherpc.getClass() == this.getClass()) {
                        unification.setPointcut(this);
                        return true;
                } else // Do the right thing if otherpc was a local vars pc
                        return LocalPointcutVars.unifyLocals(this,otherpc,unification);

        }
}
