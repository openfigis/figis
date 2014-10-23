package org.fao.fi.figis.fs.sitemap.statik;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.fao.fi.figis.fs.sitemap.WebResourceGenerator;
import org.junit.Test;

public class StaticSitemapGeneratorTest {

	WebResourceGenerator g = new StaticWebResourceGenerator("src/test/resources");

	@Test
	public void testGenerateSitemapIndex() {
		String site = "fi";
		String domain = "aquaculture";

		List<String> l = g.generateWebResourceList(site, domain);
		assertEquals(4, l.size());
	}
}
