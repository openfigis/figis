package org.fao.fi.figis.crawler;

import org.junit.Test;

public class FigisCrawlerTest {

	FigisCrawler c = new FigisCrawler();

	@Test
	public void testGenerateUrlCsv() {
		c.generateUrlCsv();
	}

}
