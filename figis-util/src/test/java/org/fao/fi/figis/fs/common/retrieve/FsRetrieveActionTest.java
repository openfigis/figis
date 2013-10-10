package org.fao.fi.figis.fs.common.retrieve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.junit.Before;
import org.junit.Test;

public class FsRetrieveActionTest {

	FsRetrieveAction fsRetrieveAction;

	@Before
	public void before() {
		fsRetrieveAction = new FsRetrieveAction();
	}

	@Test
	public void testExecuteAction() throws Exception {
		ActionMapping mapping = null;
		ActionForm form = null;
		HttpServletRequest req = null;
		HttpServletResponse resp = null;
		fsRetrieveAction.executeAction(mapping, form, req, resp);
	}

	@Test
	public void testGetService() {
	}

	@Test
	public void testIsLegacy() {
	}

	@Test
	public void testIsStatic() {
	}

	@Test
	public void testMakeKey() {
	}

	@Test
	public void testMakeCacheKey() {
	}

	@Test
	public void testGetFactsheetCache() {
	}

}
