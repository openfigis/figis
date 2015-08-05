package org.fao.fi.figis.fs.common.struts;

import static org.junit.Assert.assertNotNull;

import org.apache.struts.action.Action;
import org.apache.struts.chain.commands.util.ClassUtils;
import org.junit.Test;

public class FiActionServletTest {

	@Test
	public void testProcess() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		// . protected Action createAction(ActionContext context, String type)
		// throws Exception {
		// log.info("Initialize action of type: " + type);

		// String type = "org.apache.struts.action.ActionForward";
		String type = "org.apache.struts.actions.ForwardAction";
		Action erikAction = (Action) ClassUtils.getApplicationInstance(type);
		assertNotNull(erikAction);

	}

}
