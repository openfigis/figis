package org.fao.fi.figis.fs.common.data.xml;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.fao.fi.figis.util.xml.FiXmlUtils;
import org.junit.Test;
import org.w3c.dom.Document;

public class FigisXpathTest {
	String XML = "src/test/resources/factsheets/resource/163502/factsheet163502.xml";

	@Test
	public final void testResolveXPath() throws Exception {

		InputStream is = new FileInputStream(new File(XML));
		Document doc = FiXmlUtils.parseDoc(is);
		String exp = "//fi:FIGISDoc/fi:AqRes/fi:AqResIdent/fi:ReferenceYear";
		FigisXpath f = new FigisXpath();

		assertNotNull(f.resolveXPath(doc, exp));

	}
}
