package org.fao.fi.figis.fs.common.struts.facp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Iso2PipedTest {

	@Test
	public void testGetFlyOver() {
		Iso2Piped i = new Iso2Piped("es");
		assertEquals("español", i.getFlyOver());

		i = new Iso2Piped("ar");
		assertEquals("لعربية", i.getFlyOver());

		i = new Iso2Piped("zh");
		assertEquals("中文", i.getFlyOver());

	}
}
