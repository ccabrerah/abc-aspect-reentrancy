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

package org.aspectbench.runtime.internal;
import org.aspectbench.runtime.internal.lcflowinternal.*;

/**
 * @author Damien Sereni
 */

public class LCFlowStackFactory {

	public static LCFlowStackInterface.Ref makeStackRef() {
// 		if (DecideThreadLocal.ok())
			return new LCFlowStackThreadLocal.Ref();
// 		else
// 			return new LCFlowStackGlobal.LCFlowStackRef();
	}
	public static LCFlowStackInterface.Int makeStackInt() {
// 		if (DecideThreadLocal.ok())
			return new LCFlowStackThreadLocal.Int();
// 		else
// 			return new LCFlowStackGlobal.LCFlowStackInt();
	}
	public static LCFlowStackInterface.Long makeStackLong() {
// 		if (DecideThreadLocal.ok())
			return new LCFlowStackThreadLocal.Long();
// 		else
// 			return new LCFlowStackGlobal.LCFlowStackLong();
	}
	public static LCFlowStackInterface.Float makeStackFloat() {
// 		if (DecideThreadLocal.ok())
			return new LCFlowStackThreadLocal.Float();
// 		else
// 			return new LCFlowStackGlobal.LCFlowStackFloat();
	}
	public static LCFlowStackInterface.Double makeStackDouble() {
// 		if (DecideThreadLocal.ok())
			return new LCFlowStackThreadLocal.Double();
// 		else
// 			return new LCFlowStackGlobal.LCFlowStackDouble();
	}
	
}
