package org.fao.fi.figis.fs.common.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FiRefObjectTest extends BaseTest {

	// @Before
	// public void before() {
	// FiConstants.setPropertiesDir("src/test/resources/properties");
	// }

	@Test
	public final void testStatic() {
		int meta = FiRefObject.getMetaForDataset("topic");
		assertEquals(166000, meta);
	}

	@Test
	public final void testGetExactDatasetName() {
		FiRefObject l;
		int refType = 166000;

		String datasetName = FiRefObject.getExactDatasetName(refType);
		assertEquals("kut", datasetName);

	}

	@Test
	public final void testGetMetaForDataset() {

		// String ds = "topic/en";
		String ds = "topic";

		int meta = FiRefObject.getMetaForDataset(ds);
		assertEquals(166000, meta);

	}
}
