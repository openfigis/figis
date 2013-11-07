package org.fao.fi.figis.fs.dataset.staticxml;

import java.io.File;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.junit.Test;

public class StaticxmlScannerTest extends BaseTest {

	static String FIGIS_PROPERTIES_DIR = "../../figis-properties/properties";
	static String SCAN_CONTROL = FIGIS_PROPERTIES_DIR + "/staticxml/ScanControl.xml";

	@Test
	public void testDoScan() throws Exception {

		String[] argv = { "-r", "../", "-p", FIGIS_PROPERTIES_DIR, SCAN_CONTROL };

		File cFile = new File(SCAN_CONTROL);

		// F:\FIGIS\devel\webapps\figis\wwwroot\fi\figis\area\data

		// hqldvfigis1.hq.un.fao.org/work/FIGIS/devel/webapps/figis/wwwroot
		// File scanDir = new
		// File("//hqldvfigis1.hq.un.fao.org/work/FIGIS/devel/webapps/figis/");
		File scanDir = new File("C:/Users/vaningen/Documents/drek/");
		// C:\Users\vaningen\Documents\drek\legalframework\data

		StaticxmlScanner scanner = new StaticxmlScanner(cFile, scanDir);
		scanner.doScan();

		// PARAMS="-r ../ -p $FIGIS_PROPERTIES_DIR $FIGIS_PROPERTIES_DIR/staticxml/ScanControl.xml"

	}

	/**
	 * This test is not so interesting, go to testDoScan
	 */

	@Test
	public void testMain() {

		String[] argv = { "-r", "../", "-p", FIGIS_PROPERTIES_DIR, SCAN_CONTROL };

		StaticxmlScanner.main(argv);

		// PARAMS="-r ../ -p $FIGIS_PROPERTIES_DIR $FIGIS_PROPERTIES_DIR/staticxml/ScanControl.xml"

	}

}
