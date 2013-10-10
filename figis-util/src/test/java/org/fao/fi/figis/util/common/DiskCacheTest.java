package org.fao.fi.figis.util.common;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DiskCacheTest {

	@Test
	public void testGetInstance() throws Exception {
		String doms[] = { "facp", "org" };
		Object key = "fdfsd";
		for (String dom : doms) {
			DiskCache cache;
			cache = DiskCache.getInstance(dom);
			cache.putItem(key, key);
			assertNotNull("Cache get", cache);
			assertNotNull("Object cache fetch fail OK (object deleted)", cache.getItem(key));
			cache.delete(key);
			assertNull("Object cache fetch fail OK (object deleted)", cache.getItem(key));
		}
	}
}
