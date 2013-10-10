package org.fao.fi.figis.fs.common.data.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.junit.Test;

public class FsLanguageXmlFactoryTest extends BaseTest {

	@Test
	public final void testGetAvailableLanguageCodes() throws Exception {
		FsLanguageXmlFactory f = new FsLanguageXmlFactory();

		// int parentId = 6010;
		int parentId = 163502;

		boolean live = true;

		List<String> languages = f.getAvailableLanguageCodes(parentId, live);
		assertTrue(languages.size() > 0);
		assertEquals("en", languages.get(0));

	}
}
