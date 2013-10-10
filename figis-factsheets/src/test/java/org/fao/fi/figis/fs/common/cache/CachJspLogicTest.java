package org.fao.fi.figis.fs.common.cache;

import javax.servlet.jsp.PageContext;

import org.fao.fi.figis.fs.common.cache.CachJspLogic;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * These tests have not yet been implemented.
 * 
 * @author Erik van Ingen
 * 
 */

public class CachJspLogicTest {

	@Ignore
	@Test
	public void testPerform() {
		// PageContext pageContext = new
		// org.apache.jasper.runtime.PageContextImpl(null);
		PageContext pageContext = null;
		String requestU = "/fiweb/website/FIFacp.do?dom=facp&fid=21&lang=en";
		String requestParams = "?dom=facp&fid=21&lang=en";

		CachJspLogic l1 = new CachJspLogic();
		l1.read(pageContext, requestParams, requestU);
	}

	@Ignore
	@Test
	public void testWrite() {
		PageContext pageContext = null;
		String requestU = null;
		String mq = null;
		CachJspLogic l2 = new CachJspLogic();
		l2.write(pageContext, requestU);
	}

}
