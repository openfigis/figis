package org.fao.fi.figis.fs.sitemap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
	public void testGenerateWebResourceListVme() {
		String site = "fi";
		String domain = "vme";
		assertTrue(g.generateWebResourceList(site, domain).get(0).contains(AreaServiceMock.GERMANY));
	}

	@Test
	public void testGenerateWebResourceList() {
		String site = "fi";
		String domain = "facp";
		assertTrue(g.generateWebResourceList(site, domain).get(0).contains(AreaServiceMock.GERMANY));
	}

	@Test
	public void testTopic() {
		String site = "fi";
		String domain = "topic";
		List<String> l = g.generateWebResourceList(site, domain);
		assertEquals(2, l.size());
	}

}
