package org.fao.fi.figis.fs.dataset.eims;

import static org.junit.Assert.assertEquals;

import org.fao.fi.figis.fs.dataset.kor.KorFactory;
import org.junit.Test;

public class EimsFactoryTest {

	@Test
	public void getSearchUrl() throws Exception {
		KorFactory f = new EimsFactory();
		assertEquals("http://localhost/figis/moniker/eimskor/456", f.getSearchUrl("456", "en").toString());
		assertEquals("http://localhost/figis/moniker/eimskor/456", f.getSearchUrl("456", "").toString());
	}

	@Test
	public void getUrl() throws Exception {
		KorFactory f = new EimsFactory();
		assertEquals("http://localhost/figis/moniker/eimskor/456", f.getUrl("456", null, "en").toString());
	}

}