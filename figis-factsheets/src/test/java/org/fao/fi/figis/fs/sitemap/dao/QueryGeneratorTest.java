package org.fao.fi.figis.fs.sitemap.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QueryGeneratorTest {

	QueryGenerator qg = new QueryGenerator();

	@Test
	public void testComposeQueryNansen() {

		String site = "nansen";
		String domain = "topic";
		String domainSqlName = "marine_fishery";
		String sql = qg.composeQuery(site, domain);
		assertTrue(sql.contains("169000"));

	}

	@Test
	public void testComposeQueryFishery() {

		String site = "firms";
		String domain = "fishery";
		String domainSqlName = "marine_fishery";
		String sql = qg.composeQuery(site, domain);
		assertTrue(sql.contains("205000"));
		assertTrue(sql.contains(domainSqlName));

	}

	@Test
	public void testComposeQueryGeartype() {

		String site = "fi";
		String domain = "geartype";
		String domainSqlName = "gear";
		String sql = qg.composeQuery(site, domain);
		assertTrue(sql.contains("54000"));
		assertTrue(sql.contains("54001"));
		assertTrue(sql.contains(domainSqlName));

	}

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
