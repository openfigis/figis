package org.fao.fi.figis.fs.common.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class HtmlCacheLogicTest {

	HtmlCacheLogic l = new HtmlCacheLogic();

	String domain = "hulk";
	String identity = "166000:18127:159828";
	String lang = "es";
	// String result = "hulk_18127_159828_es";
	String resultFid = "hulk_18127_es";
	String resultOid = "hulk_166000:18127:159828_es";

	@Test
	public void testMakeCacheKeyStringStringString() {
		String key = l.makeCacheKey(domain, identity, lang);
		assertEquals(resultOid, key);
	}

	@Test
	public void testMakeCacheKeyStringStringStringString() {
		String fid = "18127";
		String key = l.makeCacheKey(domain, fid, lang);

		assertEquals(resultFid, key);

		try {
			l.makeCacheKey(null, fid, lang);
			fail();
		} catch (Exception e) {
		}
		try {
			l.makeCacheKey(domain, "", lang);
			fail();
		} catch (Exception e) {
		}

	}

	@Test
	public void testMakeFilePath() {
		assertTrue(l.makeFilePath(l.makeCacheKey(domain, identity, lang)).contains(resultOid));
	}
}
