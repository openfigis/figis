package org.fao.fi.figis.util.chart;

import java.util.Properties;

import org.junit.Test;

public class ChartBuilderTest {

	@Test
	public void testChartBuilder() throws Exception {
		Properties grProps = new Properties();
		// for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
		// String pName = (String) e.nextElement();
		// if (pName.startsWith("gr_")) {
		// grProps.setProperty(pName.substring(CONST_3), getParam(req, pName));
		// }
		// }

		new ChartBuilder(grProps);

	}

}
