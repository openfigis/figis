package org.fao.fi.figis.fs.common.retrieve;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.fao.fi.figis.fs.common.business.MinDisplayStatus;
import org.fao.fi.figis.fs.common.struts.FsCommonStrutsConst;
import org.fao.fi.figis.util.data.FiConstants;
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

	/**
	 * just testing a piece of code, copied from this method.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDoGet() throws Exception {

		FiConstants.setServerType("external");
		// assertEquals(0, FiConstants.minDisplayStatus());

		boolean loggedIn = false;
		Integer obsStatus = new Integer(2);
		String forward = "";
		String forwardMessage = "somethingelse";
		System.out.println(MinDisplayStatus.get().value());

		if ((!loggedIn) && (MinDisplayStatus.get().value().compareTo(obsStatus)) > 0) {
			// forward = FsRetrieveConst.FWD_ERROR;
			forward = FsCommonStrutsConst.FWD_NOUSER;

		} else {
			forward = forwardMessage;
		}
		assertEquals(FsCommonStrutsConst.FWD_NOUSER, forward);
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
