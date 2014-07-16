package org.fao.fi.figis.fs.sitemap.dao;

import static org.junit.Assert.assertFalse;
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
		assertFalse(sql.contains("cd_sector"));

	}

	@Test
	public void testComposeQueryFacp() {
		assertNotNull(qg);

		String site = "fi";
		String domain = "facp";
		String domainSqlName = "cp";
		String sql = qg.composeQuery(site, domain);
		assertTrue(sql.contains("185000"));
		assertTrue(sql.contains(domainSqlName));
		assertTrue(sql.contains("cd_sector"));

	}

}
