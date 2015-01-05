package org.fao.fi.figis.fs.dataset.naso;

import static org.junit.Assert.assertEquals;

import org.fao.fi.figis.util.xml.ReferenceElement;
import org.junit.Test;

public class FsNasoRefElemTest {

	@Test
	public void testGetRootElementName() throws InstantiationException, IllegalAccessException {
		ReferenceElement r = FsNasoRefElem.class.newInstance();

		assertEquals("fi:Naso", r.getRootElementName());

	}

}
