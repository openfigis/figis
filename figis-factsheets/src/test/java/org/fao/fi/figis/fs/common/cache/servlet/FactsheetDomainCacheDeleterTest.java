package org.fao.fi.figis.fs.common.cache.servlet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FactsheetDomainCacheDeleterTest {

	FactsheetDomainCacheDeleter d = new FactsheetDomainCacheDeleter();

	@Test
	public void testDeleteCache() throws IOException {
		String cacheDirName = "src" + File.separatorChar + "test" + File.separatorChar + "resources";
		String domain = "vme";
		String vmeDirName = cacheDirName + File.separatorChar + domain;

		File cacheDir = new File(cacheDirName);
		assertTrue(cacheDir.exists());

		File vmeDir = new File(vmeDirName);
		vmeDir.mkdir();

		assertTrue(vmeDir.exists());
		File file = new File(vmeDirName + File.separatorChar + "justafile.txt");
		file.createNewFile();
		assertTrue(vmeDir.exists());
		assertTrue(file.exists());
		assertTrue(d.deleteCache(cacheDirName, domain));

		assertFalse(file.exists());

		// the logic will delete the directory and then create it again. So we
		// delete it here again in order to be sure to not to leave old test
		// results.
		assertTrue(vmeDir.delete());
		assertFalse(vmeDir.exists());

	}
}
