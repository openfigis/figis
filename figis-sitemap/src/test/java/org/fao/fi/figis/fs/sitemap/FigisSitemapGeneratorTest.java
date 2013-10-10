package org.fao.fi.figis.fs.sitemap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.fao.fi.figis.fs.sitemap.FigisSitemapGenerator;
import org.junit.Before;
import org.junit.Test;

public class FigisSitemapGeneratorTest {

	FigisSitemapGenerator s = new FigisSitemapGenerator();

	String target = "target/";

	String site = "fi";
	String domain = "org";

	@Before
	public void before() {
		s.setBaseDir(target);
	}

	@Test
	public void testGenerateSitemap() {
		String filename = s.composeSitemapFileName(site, domain);
		File file = new File(target + filename);
		file.delete();
		assertFalse(file.exists());
		File fileFound = s.generateSitemap(site, domain);
		assertEquals(file, fileFound);
		assertTrue(file.exists());
		assertTrue(fileFound.exists());
	}

	@Test
	public void testGenerateSitemapCache1() {
		String filename = s.composeSitemapFileName(site, domain);
		File fileFound1 = new File(target + filename);

		fileFound1.delete();

		fileFound1 = s.generateSitemap(site, domain);
		s.generateSitemap(site, domain);
		s.generateSitemap(site, domain);
		s.generateSitemap(site, domain);
		s.generateSitemap(site, domain);
		s.generateSitemap(site, domain);
		File fileFound2 = s.generateSitemap(site, domain);
		assertEquals(fileFound1.lastModified(), fileFound2.lastModified());

	}

	@Test
	public void testGenerateSitemapCache2() {
		File file = s.generateSitemap(site, domain);

		// playing with time, 8 days back
		long time = System.currentTimeMillis() - 8 * 24 * 60 * 60 * 1000;
		file.setLastModified(time);

		long newTime = s.generateSitemap(site, domain).lastModified();
		assertFalse(time == newTime);

		// playing with time, 6 days back
		time = System.currentTimeMillis() - 6 * 24 * 60 * 60 * 1000;
		file.setLastModified(time);
		newTime = s.generateSitemap(site, domain).lastModified();
		assertTrue(time == newTime);

	}

	@Test
	public void testGenerateSitemapIndex() {
		String sitemapIndexFile = target + File.separator + site + ".xml";
		File sitemapIndex = new File(sitemapIndexFile);
		sitemapIndex.delete();
		assertFalse(sitemapIndex.exists());
		File file = s.generateSitemapIndex(site);
		assertTrue(file.exists());
		assertEquals(sitemapIndex, file);
		sitemapIndex.delete();
	}

}
