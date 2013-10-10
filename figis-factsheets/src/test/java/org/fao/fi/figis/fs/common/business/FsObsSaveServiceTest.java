package org.fao.fi.figis.fs.common.business;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.util.db.Transactionable;
import org.junit.Test;

public class FsObsSaveServiceTest extends BaseTest {

	FsObsSaveService l;

	@Test
	public final void testCreateTransactionable() throws Exception {
		File xmlFile = new File("src/test/resources/luca/12356_DeepSeaEcosystems_en.xml");
		Integer id = null;
		String lang = null;
		Transactionable t = FsObsSaveService.createTransactionable(id, lang, xmlFile);
		assertNotNull(t);
	}
}
