package org.fao.fi.figis.fs.common.cache.servlet;

import java.io.File;
import java.io.IOException;

import org.fao.fi.figis.fs.common.cache.RequestParms;
import org.junit.Before;
import org.junit.Test;

public class FactsheetDomainHtmlCacheDeleterTest {

	FactsheetDomainHtmlCacheDeleter d = new FactsheetDomainHtmlCacheDeleter();

	String domain = "vme";
	Integer fid = new Integer(3);
	Integer oid = new Integer(5);
	RequestParms p = new RequestParms();

	// vme_23591_en_167677.txt

	@Before
	public void before() {
		System.setProperty("user.dir", "src/test/resources");
	}

	@Test
	public void testDeleteCache() throws IOException {
		File file = new File("src/test/resources/vme_456_en_789.txt");
		file.createNewFile();
		// does not work with Java 5 and Windows.
		// assertTrue(d.deleteCache(domain));
		// assertFalse(file.exists());
	}
}
