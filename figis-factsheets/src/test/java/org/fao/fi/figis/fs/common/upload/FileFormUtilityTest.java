package org.fao.fi.figis.fs.common.upload;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.upload.FormFile;
import org.junit.Test;

public class FileFormUtilityTest {

	@Test
	public final void testExtractFormFileList() {
		FileFormUtility u = new FileFormUtility();

		DiskFileItem f1 = new MockDiskFileItem();
		DiskFileItem f2 = new MockDiskFileItem();

		// FormPropertyConfig config1 = new FormPropertyConfig("theFile1",
		// "FormFile", null);
		// FormPropertyConfig config2 = new FormPropertyConfig("theFile1",
		// "FormFile", null);

		FormBeanConfig formBeanConfig1 = new FormBeanConfig();
		FormBeanConfig formBeanConfig2 = new FormBeanConfig();
		formBeanConfig1.setName("theFile1");
		formBeanConfig2.setName("theFile2");

		formBeanConfig1.setType("org.apache.struts.upload.FormFile");
		formBeanConfig2.setType("org.apache.struts.upload.FormFile");

		DynaActionForm daf = new DynaActionForm();

		daf.initialize(formBeanConfig1);

		daf.initialize(formBeanConfig2);

		daf.set("theFile1", f1);
		daf.set("theFile2", f1);
		List<FormFile> list = u.extractFormFileList(daf);
		assertEquals(2, list.size());
		assertEquals(f1, list.get(0));
		assertEquals(f2, list.get(1));
	}
}
