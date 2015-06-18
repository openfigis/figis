package org.fao.fi.figis.fs.common.data;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.fao.fi.figis.util.data.FiConstants;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Erik van Ingen
 * 
 */
public class FiKeywordScannerTest {
	String LOG4J_CONFIGURATION = "-Dlog4j.configuration=src/test/resources/log4j.xml";
	String KEYWORD_SCANNER = "org.fao.fi.figis.fs.common.data.FiKeywordScanner";
	String TOMCAT_HOME = "../figis-properties/";
	String FIGIS_PROPERTIES_DIR = TOMCAT_HOME + "properties/";
	public static final String DATABASE_SCAN_PROPERTIES = "../../figis-properties/properties/DatabaseScan.properties";

	String storeDir = "figis-factsheets/target/";

	@Before
	public void before() {
		FiConstants.setPropertiesDir("../figis-properties/properties");
	}

	@Test
	public void vme() {
		String dataset = "vme";
		String id = "23582";
		// String id = null;

		String[] arguments = { "-o", storeDir, "-c", FIGIS_PROPERTIES_DIR + "/common/SearchTerms.xml", "-r", "../",
				"-p", FIGIS_PROPERTIES_DIR, "-db", DATABASE_SCAN_PROPERTIES, "-d", dataset, id };

		FiKeywordScanner.main(arguments);
		File file = new File(storeDir);
		assertTrue(file.exists());
	}

	/**
	 * -o is used for the storedir
	 * 
	 * topic is org.fao.fi.figis.fs.dataset.topic.FiRefTopic
	 * 
	 * The logic generates this error. I do not understand the exact cause of this error but it is not blocking:
	 * 
	 * WARN SessionFactoryObjectFactory - Could not bind factory to JNDI javax.naming.NoInitialContextException: Need to
	 * specify class name in environment or system property, or as an applet parameter, or in an application resource
	 * file: java.naming.factory.initial at javax.naming.spi.NamingManager.getInitialContext(NamingManager.java:645)
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void species() {
		// String COMMAND =
		// "-Xms1024m -Xmx1024m -Dfigis.hibernate.run_cleaner=false $LOG4J_CONFIGURATION -classpath $CP $KEYWORD_SCANNER";
		String dataset = "species";
		// String dataset = "geartype";
		// String id = "9177";// with status
		// String id = "9179"; // with status 2
		// String id = "9180"; // with status 2
		// String id = "2153";
		String id = "";

		String[] arguments = { "-o", storeDir, "-c", FIGIS_PROPERTIES_DIR + "/common/SearchTerms.xml", "-r", "../",
				"-p", FIGIS_PROPERTIES_DIR, "-db", DATABASE_SCAN_PROPERTIES, "-d", dataset, id };

		FiKeywordScanner.main(arguments);
		File file = new File(storeDir);
		assertTrue(file.exists());
	}

}
