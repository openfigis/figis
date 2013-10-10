package org.fao.fi.figis.fs.sitemap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ResourceGeneratorFactoryTest {

	@Test
	public void testGetImpl() {
		ResourceGenerator g = ResourceGeneratorFactory.getImpl();
		assertNotNull(g);
		assertTrue(g instanceof ResourceGeneratorDummy);
		assertTrue(g instanceof ResourceGenerator);

	}

	@Test
	public void testetSitemapDir() {
		String g = ResourceGeneratorFactory.getSitemapDir();
		assertNotNull(g);

	}

}
