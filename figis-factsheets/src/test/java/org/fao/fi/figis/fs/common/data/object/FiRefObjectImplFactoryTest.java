package org.fao.fi.figis.fs.common.data.object;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.dataset.topic.FiRefTopic;
import org.fao.fi.figis.fs.dataset.topic.FiRefTopicFactory;
import org.junit.Test;

public class FiRefObjectImplFactoryTest extends BaseTest {

	FiRefObjectImplFactory f;

	/**
	 * 195000=org.fao.fi.figis.fs.dataset.resource.FiRefResource
	 * 
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLookupObjectClass() throws Exception {
		Object meta = 195000;
		Class<?> clazz = FiRefObjectImplFactory.lookupObjectClass(meta);
		assertEquals("org.fao.fi.figis.fs.dataset.resource.FiRefResource", clazz.getName());
	}

	// @Test
	public final void test() throws Exception {
		f = new FiRefTopicFactory();
		Object key[] = { new Integer(165000), new Integer(12356) };
		FiRefObject o = f.find(key);
		assertNotNull(o);
	}

	/**
	 *
	 */
	@Test
	public final void testCreateObject() throws Exception {

		Integer meta = new Integer(165000);
		Integer fid = new Integer(13537);

		FiRefObject key = FiRefTopicFactory.createObject(meta, fid);
		assertTrue(key instanceof FiRefObject);
		key.setLive(true);
		FsObservation observation = ((FsObservableObject) key).getPrimaryObservation();
		assertNotNull(observation);
	}

	// @Test
	public final void testFiRefObjectImplFactory() throws Exception {
		FiRefObjectImplFactory f = new FiRefObjectImplFactory(FiRefTopic.class);
		assertEquals(165000, f.getSystemMeta());
	}
}
