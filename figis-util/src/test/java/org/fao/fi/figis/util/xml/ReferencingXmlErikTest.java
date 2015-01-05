package org.fao.fi.figis.util.xml;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class ReferencingXmlErikTest {

	@Test
	public void testGetRefElemClassList() throws Exception {
		ReferencingXmlErik r = new ReferencingXmlErik();

		Map<String, Class<? extends ReferenceElement>> m = r.getRefElemClassList();

		Object[] keys = m.keySet().toArray();
		for (Object object : keys) {
			System.out.print(object + " - ");
			System.out.println(m.get(object));
		}

		assertEquals("fi:CountrySector", m.get("fi:CountrySector"));

	}
}
