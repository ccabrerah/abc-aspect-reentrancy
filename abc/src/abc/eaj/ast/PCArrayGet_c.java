/* abc - The AspectBench Compiler
 * Copyright (C) 2008 Pavel Avgustinov
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

package abc.eaj.ast;

import java.util.Set;
import java.util.HashSet;

import polyglot.ast.Precedence;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;
import abc.weaving.aspectinfo.Pointcut;
import abc.aspectj.ast.Pointcut_c;

/**
 * @author Pavel Avgustinov
 */

public class PCArrayGet_c extends Pointcut_c implements PCArrayGet {
	public PCArrayGet_c(Position pos) {
		super(pos);
	}

	public boolean isDynamic() {
		return false;
	}

    public Set pcRefs() {
        return new HashSet();
    }
	
	public Pointcut makeAIPointcut() {
		return new abc.eaj.weaving.aspectinfo.ArrayGet(position());
	}

	public Precedence precedence() {
		return Precedence.LITERAL;
	}

	public void dump(CodeWriter w) {
		w.write("arrayget()");
	}

	public void prettyPrint(CodeWriter w, PrettyPrinter pp) {
		w.write("arrayget()");
	}

	protected PCArrayGet_c reconstruct() {
		return this;
	}
}
