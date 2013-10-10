package org.fao.fi.figis.fs.sitemap;

import java.util.List;

import org.junit.Test;

public class ProduceUrls {

	@Test
	public void testGenerateSitemapIndex() {

		FigisSiteDomain fsd = new FigisSiteDomain();
		List<String> sites = fsd.getSites();
		for (String site : sites) {
			List<String> domains = fsd.getDomainsPer(site);
			String url = fsd.retrieveUrl(site);
			String sitemapUrl = url + "/" + site + "/" + FigisSitemapGenerator.SITEMAP + "new/" + site;
			String sitemapIndexUrl = "'''" + sitemapUrl + "/" + SitemapUrlParser.SITEMAPINDEX + "'''<br>";
			System.out.println(sitemapIndexUrl);

			for (String domain : domains) {
				String domainSitemapUrl = sitemapUrl + "/" + domain + ".xml" + "<br>";
				System.out.println(domainSitemapUrl);
			}
			System.out.println("<br>");

		}
	}

	/**
	 * <a href="sitemapnew/fi/sitemapindex.xml">*</a>
	 */

	@Test
	public void generateLinks() {

		FigisSiteDomain fsd = new FigisSiteDomain();
		List<String> sites = fsd.getSites();
		for (String site : sites) {
			List<String> domains = fsd.getDomainsPer(site);
			String sitemapUrl = "servlet/" + FigisSitemapGenerator.SITEMAP + "/" + site;
			String sitemapIndexUrl = sitemapUrl + "/" + SitemapUrlParser.SITEMAPINDEX;
			printLink(sitemapIndexUrl);

			for (String domain : domains) {
				String domainSitemapUrl = sitemapUrl + "/" + domain + ".xml";
				printLink(domainSitemapUrl);

			}
			System.out.println("</br>");

		}
	}

	private void printLink(String link) {
		System.out.print("<a href=\"");
		System.out.print(link);
		System.out.print("\">");
		System.out.print(link);
		System.out.println("</a></br>");

	}

}
