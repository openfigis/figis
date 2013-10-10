package org.fao.fi.figis.fs.sitemap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fao.fi.figis.fs.sitemap.FigisSiteDomain;
import org.junit.Test;

public class FigisSiteDomainTest {

	FigisSiteDomain f = new FigisSiteDomain();

	@Test
	public void testGetSites() {

		assertEquals(5, f.getSites().size());
	}

	@Test
	public void testGetDomainsPer() {

		List<String> sites = f.getSites();
		for (String site : sites) {
			List<String> domains = f.getDomainsPer(site);
			assertTrue(domains.size() > 0);
		}
		assertNotNull(f.getDomainsPer("fi").size());
		assertEquals(16, f.getDomainsPer("fi").size());
		assertEquals(3, f.getDomainsPer("firms").size());
	}

	@Test
	public void testRetrieveUrl() {
		assertEquals("http://www.fao.org/fishery", f.retrieveUrl("fi"));
	}

}
