package org.fao.fi.figis.base;

import org.fao.fi.figis.util.data.FiConstants;
import org.fao.fi.figis.util.xml.FiNamespaceHandler;
import org.junit.Before;

public class BaseTest {

	String storeDir = "target/";

	@Before
	public void before() throws Exception {
		// File ctrlFile = new
		// File("../figis-properties/properties/common/SearchTerms.xml");

		FiConstants.setPropertiesDir("../figis-properties/properties");

		FiNamespaceHandler f = new FiNamespaceHandler();

	}

}
