package org.fao.fi.figis.fs.sitemap.statik;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FigisStaticSiteDomainTest {

	FigisStaticSiteDomain fssd = new FigisStaticSiteDomain();

	@Test
	public void testGetDomainsPer() {
		assertEquals(8, fssd.getDomainsPer("fi").size());
		assertEquals("culturedspecies", fssd.getDomainsPer("fi").get(0));
	}
}
