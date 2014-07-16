package org.fao.fi.figis.fs.sitemap;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * 
 * 
 * @author Erik van INgen
 *
 */

public class FigisResourceGeneratorTest {

	SitemapDaoMock m = new SitemapDaoMock();
	AreaService as = new AreaServiceMock();
	FigisResourceGenerator g = new FigisResourceGenerator(m, as);

	@Test
	public void testGenerateWebResourceList() {
		String site = "fi";
		String domain = "facp";
		assertTrue(g.generateWebResourceList(site, domain).get(0).contains(AreaServiceMock.GERMANY));
	}
}
