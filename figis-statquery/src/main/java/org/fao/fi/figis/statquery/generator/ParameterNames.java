package org.fao.fi.figis.statquery.generator;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class ParameterNames implements Enumeration<String> {

	private ParameterName[] localList = ParameterName.values();
	private List<ParameterName> localStringList = Arrays.asList(localList);
	private Iterator<ParameterName> iterator = localStringList.iterator();

	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	public String nextElement() {
		return iterator.next().name();
	}
}
