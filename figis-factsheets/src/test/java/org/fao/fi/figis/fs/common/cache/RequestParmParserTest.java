package org.fao.fi.figis.fs.common.cache;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RequestParmParserTest {

	@Test
	public void testRequestUrlParser() {
		String requestUrl = "?dom=topic&fid=18127&oid=159828&lang=en";

		RequestParmParser p = new RequestParmParser(requestUrl);
		assertEquals(p.getDomain(), "topic");
		assertEquals(p.getFid(), "18127");
		assertEquals(p.getOid(), "159828");
		assertEquals(p.getLang(), "en");

	}
}
