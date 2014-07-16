package org.fao.fi.figis.fs.sitemap.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.sitemap.FigisSiteDomain;
import org.fao.fi.figis.fs.sitemap.SitemapDaoImpl;
import org.junit.Test;

public class SitemapDaoTest extends BaseTest {

	SitemapDao dao = new SitemapDaoImpl();
	FigisSiteDomain figisSiteDomain = new FigisSiteDomain();

	// @Test
	public void testRetrieveResources() {
		List<String> sites = figisSiteDomain.getSites();
		for (String site : sites) {
			List<String> domains = figisSiteDomain.getDomainsPer(site);
			for (String domain : domains) {
				System.out.println(site + "_" + domain);
				// if (!domain.equals("species") && !domain.equals("introsp")) {
				// if (!domain.equals("species") && !domain.equals("introsp")) {

				delegate(site, domain);
			}
		}

	}

	@Test
	public void testRetrieveResourcesFacp() {
		delegate("fi", "introsp");

	}

	private void delegate(String site, String domain) {
		List<SitemapWebResource> list = dao.retrieveWebResources(site, domain);
		assertTrue(list.size() > 0);
	}

}
