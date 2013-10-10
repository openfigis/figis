package org.fao.fi.figis.fs.sitemap.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SiteMetaTest {

	SiteMeta siteMeta = new SiteMeta();

	@Test
	public void testGetMeta() {
		assertNotNull(siteMeta);

		assertEquals("195000", siteMeta.getMeta("firms", "resource"));

	}
}
