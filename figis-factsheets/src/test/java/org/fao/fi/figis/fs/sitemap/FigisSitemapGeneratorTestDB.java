package org.fao.fi.figis.fs.sitemap;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.fao.fi.figis.fs.common.data.BaseTest;
import org.junit.Test;

public class FigisSitemapGeneratorTestDB extends BaseTest {

	FigisSitemapGenerator g = new FigisSitemapGenerator();
	WebResourceGenerator resourceGenerator = ResourceGeneratorFactory.getImpl();
	private FigisSiteDomain fsd = new FigisSiteDomain();
	FigisSiteDomain f = new FigisSiteDomain();

	@Test
	public void testGenerateSitemap() {
		g.setBaseDir("target");
		String site = "firms";
		String domain = "resource";
		g.generateSitemap(site, domain);
	}

	// @Test
	public void testGenerateSitemapIndex() throws ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();

		int errors = 0;

		List<String> sites = f.getSites();
		for (String site : sites) {
			List<String> domains = f.getDomainsPer(site);
			for (String domain : domains) {
				List<String> urlList = resourceGenerator.generateWebResourceList(site, domain);
				for (String url : urlList) {
					String urlString = fsd.retrieveUrl(site) + "/" + url;
					System.out.println(urlString);
					HttpGet httpGet = new HttpGet(urlString);
					HttpResponse response = httpclient.execute(httpGet);
					assertEquals(200, response.getStatusLine().getStatusCode());
					if (response.getStatusLine().getStatusCode() != 200) {
						errors++;
					}
					httpGet.releaseConnection();
				}
			}
		}
		assertEquals(0, errors);
	}
}
