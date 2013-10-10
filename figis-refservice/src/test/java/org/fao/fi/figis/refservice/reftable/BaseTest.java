package org.fao.fi.figis.refservice.reftable;

import java.io.File;

import org.fao.fi.figis.util.data.FiConstants;
import org.junit.Before;

public class BaseTest {

	String storeDir = "target/";

	@Before
	public void before() throws Exception {
		File ctrlFile = new File("../figis-properties/properties/common/SearchTerms.xml");
		FiConstants.setPropertiesDir("../figis-properties/properties");
		// FiConstants.setDbProperties(FiKeywordScannerTest.DATABASE_SCAN_PROPERTIES);
	}

}
