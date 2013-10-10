package org.fao.fi.figis.util.common;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArgUtilsTest {

	@Test
	public void testExtractIdListStringArrayIntInt() {

		String argv[] = { "-o", "target", "-c", "src/test/resources/properties//common/SearchTerms.xml", "-r", "../",
				"-p", "src/test/resources/properties/", "-db", "src/test/resources/DatabaseScan.properties", "-d",
				"topic" };

		int firstIndex = -1;
		int[] idList = ArgUtils.extractIdList(argv, firstIndex, argv.length);
		assertTrue(idList.length > 0);

	}
}
