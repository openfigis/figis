package org.fao.fi.figis.fs.sitemap;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fao.fi.figis.fs.common.data.BaseTest;

public class WebResourceGeneratorTest extends BaseTest {

	WebResourceGenerator g = new FigisResourceGenerator();
	FigisSiteDomain f = new FigisSiteDomain();

	// String resource = "topic/13533/en";

	/**
	 * 
	 *
	 */
	// @Test
	public void testGenerateWebResourceList4Resource() {
		String oneResource = "firms/resource/11843/en";

		String site2 = "firms";
		String site2dom1 = "resource";
		List<String> webResources = g.generateWebResourceList(site2, site2dom1);
		boolean found = false;
		for (String webResource : webResources) {
			System.out.println(webResource);
			if (oneResource.equals(webResource)) {
				found = true;
			}
		}
		assertTrue(found);
	}

	/**
	 */
	// @Test
	public void testGenerateWebResourceList4All() {
		List<String> sites = f.getSites();
		for (String site : sites) {
			List<String> domains = f.getDomainsPer(site);
			for (String domain : domains) {
				List<String> resources = g.generateWebResourceList(site, domain);
				// assertTrue(resources.size() > 0);
				for (String resource : resources) {
					System.out.println(resource);
					// assertTrue(resource.length() > 0);
					// assertTrue(resource.contains(domain));
					// assertFalse(resource.startsWith("/"));
					// assertTrue(resource.startsWith(domain));
					// assertFalse(resource.endsWith("/"));
					// String parts[] = resource.split("/");
					// assertEquals(4, parts.length);
				}
			}
		}
	}
}
