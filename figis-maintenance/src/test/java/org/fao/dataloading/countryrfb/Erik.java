package org.fao.dataloading.countryrfb;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class Erik {

	@Test
	public void createNewFile() throws IOException {
		for (int i = 1; i < 1000000; i++) {
			File file = new File("C:/backup/9del/" + i + ".txt");
			file.createNewFile();
			System.out.println(i);
		}
	}

}
