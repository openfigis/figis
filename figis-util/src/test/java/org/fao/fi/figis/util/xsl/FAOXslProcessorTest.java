package org.fao.fi.figis.util.xsl;

import java.util.Map;

import org.junit.Test;

public class FAOXslProcessorTest {

	FAOXslProcessor p = new FAOXslProcessor();

	@Test
	public void testProcessXsl() throws Exception {
		Object xml = null;
		Object xsl = null;
		Object out = null;
		Map<String, String> params = null;
		p.processXsl(xml, xsl, out, params);
	}

}
