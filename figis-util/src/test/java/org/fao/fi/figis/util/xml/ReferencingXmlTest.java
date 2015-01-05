package org.fao.fi.figis.util.xml;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.InputStream;

import org.fao.fi.figis.base.BaseTest;
import org.junit.Test;
import org.w3c.dom.Document;

import test.UtilTestSetup;

public class ReferencingXmlTest extends BaseTest {

	private static final String TEST_XML = "src/test/resources/upload/facp_chile_es.xml";

	protected UtilTestSetup mTestSetup;

	protected File mTempDir;

	@Test
	public void testGetMainRefElem() throws Exception {
		ReferencingXml testObj;
		ReferenceElement mre;
		testObj = create();
		mre = testObj.getMainRefElem();
		assertNotNull("Get main reference", mre);
	}

	protected ReferencingXml create() throws Exception {
		InputStream is;
		Document doc;
		ReferencingXml testObj;
		is = getClass().getResourceAsStream(TEST_XML);
		doc = FiXmlUtils.parseDoc(is);
		testObj = new ReferencingXml(doc.getDocumentElement(), null);
		return testObj;
	}

}
