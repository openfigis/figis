package org.fao.fi.figis.fs.common.edit;


public class PlaceFromTest {

	// Action action;
	// ActionMapping mapping;
	// ActionForm form;
	// HttpServletRequest request;
	// HttpSession session;
	//
	// ServletContext servletContext;
	// String forward;
	// PlaceFrom placeFrom;
	//
	// @Before
	// public final void before() {
	// action = mock(Action.class);
	// mapping = mock(ActionMapping.class);
	// form = mock(ActionForm.class);
	// request = mock(HttpServletRequest.class);
	// session = mock(HttpSession.class);
	// Mockito.when(request.getSession()).thenReturn(session);
	// servletContext = mock(ServletContext.class);
	// Mockito.when(request.getServletPath()).thenReturn("/Home.do");
	// String prefixes[] = { "/website" };
	// Mockito.when(servletContext.getAttribute(Globals.MODULE_PREFIXES_KEY)).thenReturn(prefixes);
	// placeFrom = new PlaceFrom();
	// ModuleUtils.getInstance().selectModule("website", request,
	// servletContext);
	// forward = placeFrom.understandForward(action, mapping, form, request,
	// servletContext).getForward();
	// }
	//
	// @Test
	// public final void testUnderstandForward1() {
	// // first time testing
	// assertEquals(forward, PlaceFrom.FORWARD1);
	// assertEquals(ModuleUtils.getInstance().getModuleName(request,
	// servletContext), "");
	//
	// Mockito.when(request.getServletPath()).thenReturn("/website/ErikRegularAction.do");
	// Mockito.when(session.getAttribute(PlaceFrom.PLACE_FROM)).thenReturn(placeFrom);
	//
	// // multiple cycle testing
	// for (int i = 0; i < 10; i++) {
	// ModuleUtils.getInstance().selectModule("website", request,
	// servletContext);
	// forward = placeFrom.understandForward(action, mapping, form, request,
	// servletContext).getForward();
	// assertEquals(forward, PlaceFrom.FORWARD1);
	// Mockito.when(request.getServletPath()).thenReturn("/Home.do");
	// assertEquals("", ModuleUtils.getInstance().getModuleName(request,
	// servletContext));
	// }
	// }
	//
	// /**
	// * testing when coming back over and over again after having done a login
	// *
	// *
	// */
	// @Test
	// public final void testUnderstandForward3() {
	//
	// // hitting the action the first time, need to login
	// PlaceFrom placeFrom = new PlaceFrom();
	// assertEquals(PlaceFrom.PLACE1, placeFrom.getLoginPlace());
	// assertEquals(PlaceFrom.FORWARD1, placeFrom.understandForward(action,
	// mapping, form, request, servletContext)
	// .getForward());
	//
	// // logging in
	// PlaceFrom loginPlaceFrom = new PlaceFrom();
	// Mockito.when(session.getAttribute(PlaceFrom.PLACE_FROM)).thenReturn(placeFrom);
	// assertEquals(PlaceFrom.PLACE1, placeFrom.getLoginPlace());
	// loginPlaceFrom = loginPlaceFrom.loginDone(session, request,
	// servletContext);
	// assertEquals(PlaceFrom.FORWARD2, placeFrom.getForward());
	// assertEquals(PlaceFrom.PLACE2, placeFrom.getLoginPlace());
	//
	// // coming back in the same action again
	// placeFrom = new PlaceFrom();
	// placeFrom = placeFrom.understandForward(action, mapping, form, request,
	// servletContext);
	// assertEquals(PlaceFrom.FORWARD2, placeFrom.getForward());
	// assertEquals(PlaceFrom.PLACE3, placeFrom.getLoginPlace());
	//
	// // going again to the same action
	// placeFrom = new PlaceFrom();
	// placeFrom = placeFrom.understandForward(action, mapping, form, request,
	// servletContext);
	// assertEquals(PlaceFrom.FORWARD3, placeFrom.getForward());
	// assertEquals(PlaceFrom.PLACE3, placeFrom.getLoginPlace());
	//
	// // and again
	// placeFrom = new PlaceFrom();
	// placeFrom = placeFrom.understandForward(action, mapping, form, request,
	// servletContext);
	// assertEquals(PlaceFrom.FORWARD3, placeFrom.getForward());
	// assertEquals(PlaceFrom.PLACE3, placeFrom.getLoginPlace());
	//
	// }
	//
	// @Test
	// public final void testLoginDone1() {
	// Mockito.when(session.getAttribute(PlaceFrom.PLACE_FROM)).thenReturn(placeFrom);
	// PlaceFrom loginPlaceFrom = new PlaceFrom();
	// loginPlaceFrom = loginPlaceFrom.loginDone(session, request,
	// servletContext);
	// assertEquals(PlaceFrom.PLACE2, loginPlaceFrom.getLoginPlace());
	// assertEquals(PlaceFrom.FORWARD2, loginPlaceFrom.getForward());
	// }
	//
	// /**
	// * in case there is no session, the placeFrom object returned in null
	// */
	// @Test
	// public final void testLoginDone2() {
	// Mockito.when(session.getAttribute(PlaceFrom.PLACE_FROM)).thenReturn(null);
	// PlaceFrom loginPlaceFrom = new PlaceFrom();
	// loginPlaceFrom = loginPlaceFrom.loginDone(session, request,
	// servletContext);
	// assertEquals(PlaceFrom.PLACE1, loginPlaceFrom.getLoginPlace());
	// assertEquals(PlaceFrom.FORWARD4, loginPlaceFrom.getForward());
	// }

}
