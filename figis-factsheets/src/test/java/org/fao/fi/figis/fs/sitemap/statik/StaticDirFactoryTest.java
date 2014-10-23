package org.fao.fi.figis.fs.sitemap.statik;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StaticDirFactoryTest {

	@Test
	public void testGetStaticDir() {
		assertEquals("webapps/figis/wwwroot/fi/figis/", StaticDirFactory.getStaticDir());
	}

}
