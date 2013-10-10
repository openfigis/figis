package org.fao.fi.figis.fs.common.upload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.common.data.object.ValidationResult;
import org.junit.Test;

public class FsUploadProcessorTest extends BaseTest {
	String uploadPath = "src/test/resources/upload";

	@Test
	public final void testGenerateFileListString() throws Exception {
		FsUploadProcessor mProcessor = new FsUploadProcessor(uploadPath);
		List<String> list = mProcessor.generateFileList(uploadPath);
		assertEquals("424.xml", list.get(0));
		assertEquals("426.xml", list.get(1));
		assertEquals("736.xml", list.get(2));

	}

	@Test
	public final void testDoValidate() throws Exception {
		File file = new File("src/test/resources/upload/vme/VMEFactSheet_XML_prototype.xml");
		Integer uselessNumber = new Integer(0);
		FsUploadProcessor mProcessor = new FsUploadProcessor(uploadPath);
		ValidationResult result = mProcessor.doValidate(file, uselessNumber);
		System.out.println("Errors:");

		for (Iterator<String> i = result.getErrors(); i.hasNext();) {
			System.out.println(i.next());
		}

		System.out.println("Serious Errors:");
		for (Iterator<String> i = result.getSeriousErrors(); i.hasNext();) {
			System.out.println(i.next());
		}

		assertFalse(result.getHasSeriousErrors());
		assertEquals(0, result.getErrorCount());
	}
}
