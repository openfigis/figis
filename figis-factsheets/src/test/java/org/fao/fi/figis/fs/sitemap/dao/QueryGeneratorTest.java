package org.fao.fi.figis.fs.sitemap.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QueryGeneratorTest {

	QueryGenerator qg = new QueryGenerator();

	@Test
	public void testComposeQuery() {
		assertNotNull(qg);

		String site = "firms";
		String domain = "resource";
		String sql = qg.composeQuery(site, domain);
		System.out.println(sql);
		assertTrue(sql.contains("195000"));
		assertTrue(sql.contains(domain));

	}

	// @Test
	public void testComposeQueryFishTech() {

		String site = "fi";
		String domain = "fishtech";
		String sql = qg.composeQuery(site, domain);
		System.out.println(sql);
		assertTrue(sql.contains("fish_tech"));
		assertTrue(sql.contains(domain));

	}

}
