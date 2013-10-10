package org.fao.figis.test;

import org.fao.fi.figis.util.data.FiConstants;
import org.junit.Before;

public class BaseTest {

	String storeDir = "target/";

	@Before
	public void before() throws Exception {
		FiConstants.setPropertiesDir("../figis-properties/properties");
		FiConstants.setDbProperties("../../figis-properties/properties/DatabaseScan.properties");

	}
}
