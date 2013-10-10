package org.fao.fi.figis.statquery.generator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParameterParserTest {

	ParameterParser p = new ParameterParser();

	@Test
	public void testApply() {
		String stringUrl = "ds=Production&k1=COUNTRY&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";
		UrlReplacement url = new UrlReplacement();
		p.apply(stringUrl, url);

		assertEquals(url.getDs(), "Production");
		assertEquals(url.getK1(), "COUNTRY");
		assertEquals(url.getOuttype(), "gif");
		assertEquals(url.getGr_props(), "webapps/figis/species/format/gform_small.txt");
		assertEquals(url.getK1v(), "1");
		assertEquals(url.getK1s(), "");

	}

	@Test
	public void testApplyAquaculture() {
		// String stringUrl =
		// "http://figis02:8080/figis/servlet/SQServlet?ds=Aquaculture&k1=SPECIES&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=2069";
		String stringUrl = "ds=Aquaculture&k1=SPECIES&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=2069";
		UrlReplacement url = new UrlReplacement();
		p.apply(stringUrl, url);
		assertEquals(url.getDs(), "Aquaculture");

	}
}
