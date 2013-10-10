package org.fao.fi.figis.fs.common.business;

import static org.junit.Assert.assertEquals;

import org.fao.fi.figis.fs.common.cache.CacheKeyGenerator;
import org.junit.Test;

public class CacheKeyGeneratorTest {

	CacheKeyGenerator g = new CacheKeyGenerator();

	@Test
	public void testMakeCacheKeyStringObject() {
		assertEquals("ID2_org:45", g.makeCacheKey("org", new Integer(45)));
		assertEquals("ID2_topic:33", g.makeCacheKey("topic", new Integer(33)));
	}

	@Test
	public void testMakeCacheKeyStringObjectObject() {
		assertEquals("ID3_facp:2:3", g.makeCacheKey("facp", new Integer(2), new Integer(3)));
	}

	// @Test
	// public void testCountryprofileObservation() {
	// FsCountryprofileObservation c1 = new FsCountryprofileObservation();
	// c1.setFid(new Integer(3));
	// FsCountryprofileObservation c2 = new FsCountryprofileObservation();
	// c2.setFid(new Integer(3));
	// assertEquals(c1, c2);
	//
	// c2.setOid(new Integer(10));
	// assertNotSame(c1, c2);
	//
	// }
}
