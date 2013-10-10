package org.fao.fi.figis.util.db;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.fao.fi.figis.base.BaseTest;
import org.junit.Test;

public class HibernateAccessTest extends BaseTest {

	/**
	 * 
	 * http://193.43.36.238:9090/browse/FIGIS-627
	 * 
	 * from org.fao.fi.figis.fs.dataset.resource.FsResourceObservation obs where
	 * obs.primary=1 and obs.parentId=?]
	 * 
	 * problem here is the resource is 13537 (fid) and the observation is 163394
	 * (oid)
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testRunNamedQuery() throws Exception {
		// runNamedQuery
		Class<?> clazz = Class.forName("org.fao.fi.figis.fs.dataset.resource.FsResourceObservation");
		String name = "getPrimaryObservation";
		// Object[] params = { 163394 };
		Object[] params = { 13537 };

		List list = HibernateAccess.runNamedQuery(clazz, name, true, params);
		assertEquals(1, list.size());
	}
}
