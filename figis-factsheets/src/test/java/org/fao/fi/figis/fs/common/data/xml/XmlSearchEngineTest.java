package org.fao.fi.figis.fs.common.data.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.fao.fi.figis.fs.common.data.FiSearchTerm;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlSearchEngineTest {

	XmlSearchEngine xmlSearchEngine;

	Document documentEn;
	Document documentEs;
	Document documentFr;
	Document documentRu;

	@Before
	public void testXmlSearchEngine() throws Exception {
		File ctrlFile = new File("src/test/resources/properties/common/SearchTerms.xml");
		String ot = "topic";
		xmlSearchEngine = new XmlSearchEngine(ctrlFile, ot);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		documentEn = builder.parse(new File("src/test/resources/factsheets/2888/en.xml"));
		documentEs = builder.parse(new File("src/test/resources/factsheets/2888/es.xml"));
		documentFr = builder.parse(new File("src/test/resources/factsheets/2888/fr.xml"));
		documentRu = builder.parse(new File("src/test/resources/factsheets/2888/ru.xml"));

	}

	@Test
	public void testFindSearchTermsNode() throws Exception {
		delegateTest("en", documentEn);
		delegateTest("es", documentEs);
		delegateTest("fr", documentFr);
		delegateTest("ru", documentRu);
	}

	private void delegateTest(String lang, Document document) throws Exception {
		Vector<FiSearchTerm> buildVector = new Vector<FiSearchTerm>();
		Node dataRoot = document.getDocumentElement();
		int objectStatusLevel = 0;
		Object key = new Object();
		int result = xmlSearchEngine.findSearchTerms(dataRoot, buildVector, key, objectStatusLevel, lang);
		assertEquals(11, result);
		assertEquals(13, buildVector.size());
		assertTrue(buildVector.get(0).toString().contains(lang));
	}

	// @Test
	public void testGetObjectTypes() {
		fail("Not yet implemented");
	}

}
