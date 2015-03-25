/* abc - The AspectBench Compiler
 * Copyright (C) 2004 Ganesh Sittampalam
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

package abc.weaving.aspectinfo;

import java.util.Hashtable;
import java.util.Set;

import polyglot.util.Position;
import soot.SootClass;
import soot.SootMethod;
import abc.weaving.matching.MatchingContext;
import abc.weaving.residues.Residue;

/** A pointcut designator representing a condition on the
 *  lexical context
 *  @author Ganesh Sittampalam
 *  @date 30-Apr-04
 */
public abstract class LexicalPointcut extends Pointcut {
    public LexicalPointcut(Position pos) {
        super(pos);
    }

    public final Residue matchesAt(MatchingContext mc) {
        return matchesAt(mc.getSootClass(),mc.getSootMethod());
    }

    /** Do we match at a particular class and method? */
    protected abstract Residue matchesAt(SootClass cls,
                                         SootMethod method);

    public Pointcut inline(Hashtable typeEnv,
                              Hashtable renameEnv,
                              Aspect context,
			      int cflowdepth) {
        return this;
    }

    public void registerSetupAdvice
        (Aspect aspct,Hashtable/*<String,AbcType>*/ typeMap) {}

    public void getFreeVars(Set/*<String>*/ result) {}
}