package org.fao.fi.figis.fs.common.data;

//import static org.junit.Assert.fail;

import java.util.Locale;

import org.fao.fi.figis.util.db.ConnectionMaint;
import org.junit.Test;

public class FiKeywordScannerLuceneTest extends BaseTest {

	/**
	 * public void scanObjects(int[] ids, Locale loc) throws Exception {
	 * 
	 * @throws Exception
	 */
	// @Test
	public final void testScanObjects1() throws Exception {
		FiKeywordScannerLucene scanner = FiKeywordScanner.getInstance();
		scanner.setDatasetName("resource");
		scanner.setStoreDir(storeDir);

		int[] ids = { 6010 };
		Locale locl = null;
		scanner.scanObjects(ids, locl);

	}

	/**
	 * 
	 * 
	 * approved
	 * 
	 * http://figis02:8080/firms/resource/10497/1111/en/data
	 * 
	 * 
	 * published
	 * 
	 * http://figis02:8080/firms/resource/10497/592/fr/data
	 * 
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testScanObjects2() throws Exception {
		FiKeywordScannerLucene scanner = FiKeywordScanner.getInstance();
		scanner.setDatasetName("resource");
		scanner.setStoreDir(storeDir);

		ConnectionMaint.getInstance().setCleanupInterval(10000);
		// 13625
		int[] ids = { 6010 };
		// int[] ids = {};

		Locale locl = null;
		scanner.scanObjects(ids, locl);

	}
}
