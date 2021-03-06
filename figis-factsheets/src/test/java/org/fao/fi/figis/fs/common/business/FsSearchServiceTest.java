package org.fao.fi.figis.fs.common.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.fao.fi.figis.util.context.FAOContext;
import org.fao.fi.figis.util.data.FiConstants;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public class FsSearchServiceTest {

	FsSearchService fsSearchService;
	FiServiceParamHolder fiServiceParamHolder;

	@Before
	public void before() {
		// creating a fsSearchServiceto like FsSearchAction
		fsSearchService = new FsSearchService(new Properties());
		// File tempDir;
		// // initialize the logger (output to console)
		// BasicConfigurator.configure();
		// tempDir = new File("test/temp");
		// tempDir.mkdirs();
		// FiConstants.setTempDir(tempDir.getAbsolutePath());
		FiConstants.setPropertiesDir("src/test/resources/properties");
		FiConstants.setPropertiesDir("../figis-properties/properties");
		System.setProperty("figis.lucene.store", "webapps/figis/lucene/store");

		// FiConstants.setDbProperties("DEV3");
		// FiConstants.set

	}

	/**
	 * [dslist=fishery., nolimit=., kw0=georeference., kv0=myanmar., kl0=., logop=., lang=., xsl=.,
	 * lixsl=webapps/figis/firms/format/searchfsfirmslist.xsl., refxml=false., octrl=., dom=., syn=., draft=.]
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPerformSearchGeoreportMyanmar() throws Exception {

		FiServiceParamHolder fiServiceParamHolder = new FiServiceParamHolder();
		fiServiceParamHolder.addParam(new FiServiceRequestParam("dslist", "fishery"));
		fiServiceParamHolder.addParam(new FiServiceRequestParam("kw0", "georeference"));
		fiServiceParamHolder.addParam(new FiServiceRequestParam("kv0", "myanmar"));
		fiServiceParamHolder.addParam(new FiServiceRequestParam("refxml", "false"));
		fiServiceParamHolder.addParam(new FiServiceRequestParam("lixsl",
				"webapps/figis/firms/format/searchfsfirmslist.xsl"));

		Document document = fsSearchService.performSearch(fiServiceParamHolder);
		assertNotNull(document);
		document2File(document);
		assertTrue(document2String(document).contains("Myanmar"));
	}

	/**
	 * 
	 * [dslist=., nolimit=., kw0=., kv0=trawl., kl0=., logop=., lang=., xsl=., lixsl=., refxml=false., octrl=., dom=.,
	 * syn=., draft=.]
	 * 
	 * [0] FiServiceRequestParam (id=96) mLog Logger (id=104) mName "dslist" (id=107) mType 0 mValues String[1] (id=112)
	 * 
	 * dslist=.&nolimit=.&kw0=.&kv0=trawl.&kl0=.&logop=.&lang=.&xsl=.&lixsl=.& refxml=false.&octrl=.&dom=.&syn=.&draft=.
	 * 
	 * 
	 */
	@Test
	public void testPerformSearch() throws Exception {
		FiServiceParamHolder fiServiceParamHolder = new FiServiceParamHolder();
		FiServiceRequestParam p = new FiServiceRequestParam(FiServiceRequestParam.TYPE_STRING, "kv0");
		p.setValue("trawl");
		fiServiceParamHolder.setParameter(p);
		Document document = fsSearchService.performSearch(fiServiceParamHolder);
		assertNotNull(document);
		document2File(document);
	}

	@Test
	public void testPerformFullSearchIntrosp() throws Exception {
		List<String> dslist = new ArrayList<String>();
		dslist.add("introsp");
		Document document = fsSearchService.performFullSearch(dslist);

		// JAXBContext jc = JAXBContext.newInstance();
		// // Create unmarshaller
		// Unmarshaller um = jc.createUnmarshaller();
		// Unmarshal XML contents of the file myDoc.xml into your Java object
		// instance.

		// FsSearchResult fsSearchResult = (FsSearchResult)
		// um.unmarshal(document);

		assertNotNull(document);
		document2File(document);

	}

	private void document2File(Document document) throws TransformerException, IOException {
		// set up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		// create string from xml tree

		FileWriter fw = new FileWriter(new File("lf.xml"));

		StreamResult result = new StreamResult(fw);
		DOMSource source = new DOMSource(document);
		trans.transform(source, result);
	}

	private String document2String(Document document) throws TransformerException, IOException {
		// set up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		// create string from xml tree

		StringWriter sw = new StringWriter();

		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(document);
		trans.transform(source, result);
		return sw.getBuffer().toString();
	}

	@Test
	public void testPerformQuery() throws Exception {
		FAOContext context = null;
		Properties props = null;
		String datasetName = null;
		ServletRequest req = null;
		ServletResponse resp = null;

		FiServiceRequest fiServiceRequest = new FiServiceRequest(context, props, datasetName, req);

		// FiServiceStreamProvider fiServiceStreamProvider = new
		// FiServletStreamProvider(resp);

		// FiServiceResult result =
		// fsSearchService.performQuery(fiServiceRequest,
		// fiServiceStreamProvider);
	}
}
