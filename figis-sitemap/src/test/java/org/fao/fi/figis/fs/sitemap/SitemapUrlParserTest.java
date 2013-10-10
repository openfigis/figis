package org.fao.fi.figis.fs.sitemap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SitemapUrlParserTest {

	SitemapUrlParser p = new SitemapUrlParser();

	@Test
	public void testParsePathInfo() {
		assertTrue(p.parsePathInfo("/eaf-nansen/sitemapindex.xml"));
		assertTrue(p.isSitemapIndex());
		assertEquals("eaf-nansen", p.getSite());
		assertNull(p.getDomain());

		assertTrue(p.parsePathInfo("/fi/resource.xml"));
		assertFalse(p.isSitemapIndex());
		assertEquals("fi", p.getSite());
		assertEquals("resource", p.getDomain());

	}
}
