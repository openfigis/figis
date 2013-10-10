package org.fao.fi.figis.fs.common.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.struts.upload.FormFile;

public class MockDiskFileItem extends DiskFileItem implements FormFile {

	/**
	 *
	 */
	private static final long serialVersionUID = -8806968374674371217L;

	public MockDiskFileItem(String fieldName, String contentType, boolean isFormField, String fileName,
			int sizeThreshold, File repository) {
		super(fieldName, contentType, isFormField, fileName, sizeThreshold, repository);

	}

	public MockDiskFileItem() {
		super(null, null, true, null, 0, null);
	}

	public void setContentType(String contentType) {
		// TODO Auto-generated method stub

	}

	public int getFileSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setFileSize(int fileSize) {
		// TODO Auto-generated method stub

	}

	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFileName(String fileName) {
		// TODO Auto-generated method stub

	}

	public byte[] getFileData() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
