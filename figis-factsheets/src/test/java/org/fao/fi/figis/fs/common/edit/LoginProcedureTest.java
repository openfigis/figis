package org.fao.fi.figis.fs.common.edit;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class LoginProcedureTest {

	@Test
	public final void testParamems2Session() {
		HttpServletRequest request = mock(HttpServletRequest.class);

		// Enumeration<String> attributeNames = new ;

		// Mockito.when(request.getSession().getAttributeNames()).thenReturn(session);

		LoginProcedure lp = new LoginProcedure();
		lp.paramems2Session(request);
	}
}
