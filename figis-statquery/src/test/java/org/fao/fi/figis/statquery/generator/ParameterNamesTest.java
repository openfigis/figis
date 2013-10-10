package org.fao.fi.figis.statquery.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Enumeration;

import org.junit.Test;

public class ParameterNamesTest {

	@Test
	public void testHasMoreElements() {
		ParameterNames n = new ParameterNames();
		assertTrue(n.hasMoreElements());

		boolean found = false;

		for (Enumeration<String> e = new ParameterNames(); e.hasMoreElements();) {
			String pName = (String) e.nextElement();
			if (pName.startsWith("gr_")) {
				found = true;
			}
		}
		assertTrue(found);

	}

	@Test
	public void testNextElement() {
		ParameterNames n = new ParameterNames();
		assertNotNull(n.nextElement());
	}

}
