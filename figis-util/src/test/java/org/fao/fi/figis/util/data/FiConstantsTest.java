package org.fao.fi.figis.util.data;

import static org.junit.Assert.assertEquals;

import org.fao.fi.figis.base.BaseTest;
import org.junit.Test;

public class FiConstantsTest extends BaseTest {

	String prod = "external";

	@Test
	public final void testInit() {
		assertEquals("server.type", FiConstants.PRN_SERVER_TYPE);

		System.setProperty(FiConstants.PRN_SERVER_TYPE, prod);

		String str = System.getProperty(FiConstants.PRN_SERVER_TYPE);
		assertEquals(prod, str);
	}

}
