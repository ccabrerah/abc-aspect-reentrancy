/* abc - The AspectBench Compiler
 * Copyright (C) 2007 Eric Bodden
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
package abc.tmwpopt.fsanalysis.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import soot.SootMethod;
import abc.da.weaving.weaver.depadviceopt.ds.Shadow;
import abc.main.Main;
import abc.tm.weaving.aspectinfo.PerSymbolTMAdviceDecl;
import abc.tm.weaving.aspectinfo.TMGlobalAspectInfo;
import abc.tm.weaving.aspectinfo.TraceMatch;

/**
 * This class splits a given set of shadows into multiple sets so that each set holds only shadows of one tracematch. 
 *
 * @author Eric Bodden
 */
public class ShadowsPerTMSplitter {
	
	/**
	 * Splits the shadows in the given set per tracematch.
	 * Returns symbol shadows only, i.e. shadows generated by a {@link PerSymbolTMAdviceDecl}. 
	 * @param shadows a set of {@link Shadow}s
	 * @return a mapping from tracematch to a {@link Set} of {@link Shadow}s of that tracematch
	 */
	public static Map<TraceMatch,Set<Shadow>> splitSymbolShadows(Collection<Shadow> shadows) {
		
		HashMap<SootMethod, TraceMatch> adviceMethodToTraceMatch = new HashMap<SootMethod, TraceMatch>();
		
		TMGlobalAspectInfo gai = (TMGlobalAspectInfo) Main.v().getAbcExtension().getGlobalAspectInfo();
		for (TraceMatch tm : gai.getTraceMatches()) {
			for (String tmSymbol : tm.getSymbols()) {
				adviceMethodToTraceMatch.put(tm.getSymbolAdviceMethod(tmSymbol), tm);
			}
		}
		
		Map<TraceMatch,Set<Shadow>> tmToShadows = new HashMap<TraceMatch,Set<Shadow>>();

		for (Shadow shadow : shadows) {
			SootMethod adviceMethod = shadow.getAdviceDecl().getImpl().getSootMethod();
			TraceMatch tm = adviceMethodToTraceMatch.get(adviceMethod);
			if(tm!=null) {
				Set<Shadow> tmShadows = tmToShadows.get(tm);
				if(tmShadows==null) {
					tmShadows = new HashSet<Shadow>();
					tmToShadows.put(tm, tmShadows);
				}
				tmShadows.add(shadow);
			}
		}

		return tmToShadows;		
	}

}
