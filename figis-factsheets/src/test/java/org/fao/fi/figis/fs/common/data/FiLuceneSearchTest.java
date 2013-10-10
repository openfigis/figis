package org.fao.fi.figis.fs.common.data;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.fao.fi.figis.fs.common.data.xml.XmlSearchEngine;
import org.fao.fi.figis.util.data.FiConstants;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class FiLuceneSearchTest {

	FiLuceneSearch fiLuceneSearch = new FiLuceneSearch();
	String storeDir = "target/";
	XmlSearchEngine xmlSearchEngine;
	Vector<FiSearchTerm> buildVector;
	Document documentEn;
	Document documentEs;
	Document documentFr;
	Document documentRu;

	@Before
	public void before() throws Exception {
		File ctrlFile = new File("../figis-properties/properties/common/SearchTerms.xml");
		FiConstants.setPropertiesDir("../figis-properties/properties");
		FiConstants.setDbProperties(FiKeywordScannerTest.DATABASE_SCAN_PROPERTIES);

		String ot = "topic";
		xmlSearchEngine = new XmlSearchEngine(ctrlFile, ot);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		documentEn = builder.parse(new File("src/test/resources/factsheets/2888/en.xml"));
		documentEs = builder.parse(new File("src/test/resources/factsheets/2888/es.xml"));
		documentFr = builder.parse(new File("src/test/resources/factsheets/2888/fr.xml"));
		documentRu = builder.parse(new File("src/test/resources/factsheets/2888/ru.xml"));

		buildVector = new Vector<FiSearchTerm>();
		process("en", documentEn, buildVector);
		process("es", documentEs, buildVector);
		process("fr", documentFr, buildVector);
		process("ru", documentRu, buildVector);

		// new ConnectionParameters().getProperty("schema");

		// ConnectionParameters connectionParameters = new
		// ConnectionParameters();
		// connectionParameters.setUrl("jdbc:oracle:thin:@ldvdbs01:1521:FIDEV3");

		fiLuceneSearch.setStoreRoot(storeDir);

	}

	private void process(String lang, Document document, Vector<FiSearchTerm> buildVector) throws Exception {
		Node dataRoot = document.getDocumentElement();
		int objectStatusLevel = 0;
		Object key = new Object();
		xmlSearchEngine.findSearchTerms(dataRoot, buildVector, key, objectStatusLevel, lang);
	}

	@Test
	public void testStoreResults() throws Exception {
		int meta = 165000;
		boolean reset = false;

		fiLuceneSearch.storeResults(buildVector, meta, reset);
	}

	@Test
	public void testFind() throws Exception {
		Set<Integer> metas = new HashSet<Integer>();
		metas.add(new Integer(175000));
		// metas.add(new Integer(205000));
		// metas.add(new Integer(31005));

		List<FiQueryTerm> terms = new ArrayList<FiQueryTerm>();
		// String[] vList = { "berend5Edit" };
		String[] vList = { "9179" };
		// String[] vList = { "8414" };
		// String[] vList = { " Southern Bluefin tuna - Global : 2012" };
		String useANDop = "true";
		FiQueryTerm term = new FiQueryTerm(null, vList, null, useANDop);
		terms.add(term);

		boolean useAnd = false;
		boolean useSynonyms = false;
		boolean noLimit = false;

		FiRatedListEntry[] results = fiLuceneSearch.find(metas, terms, useAnd, useSynonyms, noLimit);
		assertEquals(1, results.length);
		assertEquals(2, results[0].getStatus());

	}
}
