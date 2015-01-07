package org.fao.fi.figis.fs.common.cache;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import javax.servlet.jsp.PageContext;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * These tests have not yet been implemented.
 * 
 * @author Erik van Ingen
 * 
 */

public class CachJspLogicTest {

	String requestU = "/fiweb/website/FIFacp.do?dom=facp&fid=21&lang=en";
	String requestParams = "?dom=facp&fid=21&lang=en";

	@Before
	public void before() {
		System.setProperty("user.dir", "src/test/resources");

	}

	@Test
	// @Ignore
	public void testWrite() throws IOException {

		PageContext pageContext = new PageContextMock(requestParams);
		CachJspLogic l2 = new CachJspLogic();
		File cacheFile = new File(CachJspLogic.CACHE_FILE);
		cacheFile.delete();
		assertFalse(cacheFile.exists());
		l2.write(pageContext, requestU);
		// For some reason the exists() on java 5 and windows does not work correctly
		// assertTrue(cacheFile.exists());

		// delete test resources (which actually does not work as well on windows with java 5)
		cacheFile.delete();

	}

	@Test
	public void testRead() {

		PageContext pageContext = new PageContextMock(requestU);

		// pageContext.setAttribute(CachJspLogic.QUERY_CACHE, "");

		CachJspLogic l1 = new CachJspLogic();

		// the first read should not find anything. The next
		// l1.read(pageContext, requestParams, requestU);
		// assertNull(pageContext.getAttribute(CachJspLogic.CACHE_FILE, PageContext.REQUEST_SCOPE));
		// assertNull(pageContext.getAttribute(CachJspLogic.TRANS_RESULT, PageContext.REQUEST_SCOPE));

		CachJspLogic l2 = new CachJspLogic();
		PageContext pageContext2 = new PageContextMock(requestU);
		l2.write(pageContext2, requestU);
		l2.read(pageContext2, requestParams, requestU);
		assertNotNull(pageContext2.getAttribute(CachJspLogic.CACHE_FILE, PageContext.REQUEST_SCOPE));
		assertNotNull(pageContext2.getAttribute(CachJspLogic.TRANS_RESULT, PageContext.REQUEST_SCOPE));
		// assertEquals("", pageContext2.getAttribute(CachJspLogic.TRANS_RESULT, PageContext.REQUEST_SCOPE));

	}
}
