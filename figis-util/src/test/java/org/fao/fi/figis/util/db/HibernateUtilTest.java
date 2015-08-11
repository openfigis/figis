package org.fao.fi.figis.util.db;

import static org.junit.Assert.fail;

import org.fao.fi.figis.base.BaseTest;
import org.junit.Test;

public class HibernateUtilTest extends BaseTest {

	HibernateUtil o;

	@Test
	public void test() {

		try {
			HibernateUtil.getConnection();
		} catch (Exception e) {
			fail();
		}

	}

}
