package org.fao.fi.figis.fs.common.multiquery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MultiquerySystemsTest {

	@Test
	public void testGetSystem() {

		String urlOld = "http://faolex.fao.org/cgi-bin/xml.exe?database=faolex&search_type=query&table=all&format_name=esxml&lang=xmlf&page_header=xmlh&sort_name=sall&query=\"EW=vme$\"*\"E-N\"*\"A-N\"";
		String urlNew = "http://figisapps.fao.org/figis/moniker/faolexfi?text=vme";

		assertEquals(MultiquerySystems.FAOLEX, MultiquerySystems.getSystem(urlOld));
		assertEquals(MultiquerySystems.FAOLEX, MultiquerySystems.getSystem(urlNew));

	}
}
