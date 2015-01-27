package org.fao.fi.figis.fs.common.cache.delete;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.fao.fi.figis.fs.common.cache.HtmlCacheLogic;
import org.fao.fi.figis.fs.common.cache.RequestParms;
import org.junit.Before;
import org.junit.Test;

public class HtmlCacheDeleteTest {

	HtmlCacheDelete d = new HtmlCacheDelete();

	String domain = "fiets";
	Integer fid = new Integer(3);
	Integer oid = new Integer(5);
	RequestParms p = new RequestParms();

	@Before
	public void before() {
		System.setProperty("user.dir", "src/test/resources");
		p.setDomain(domain);
		p.setFid(fid.toString());
	}

	@Test
	public void testDeletePrimary() throws IOException {
		p.setOid(null);
		HtmlCacheLogic l = new HtmlCacheLogic();
		String fileKey = l.makeCacheKey(p);
		File cachedFile = new File(l.makeFilePath(fileKey));
		cachedFile.createNewFile();
		assertTrue(cachedFile.exists());
		d.deleteObservation(domain, fid.toString(), oid.toString());
	}

	@Test
	public void testDeleteObservation() throws IOException {
		p.setOid(null);
		HtmlCacheLogic l = new HtmlCacheLogic();
		String fileKey = l.makeCacheKey(p);
		File cachedFile = new File(l.makeFilePath(fileKey));
		cachedFile.createNewFile();
		assertTrue(cachedFile.exists());
		d.deleteObservation(domain, fid.toString(), oid.toString());
	}

}
