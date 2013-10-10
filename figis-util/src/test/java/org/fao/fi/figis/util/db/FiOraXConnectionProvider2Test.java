package org.fao.fi.figis.util.db;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.fao.fi.figis.util.data.FiConstants;
import org.junit.Before;
import org.junit.Test;

public class FiOraXConnectionProvider2Test {

	@Before
	public void before() {
		FiConstants.setPropertiesDir("src/test/resources/properties");
	}

	@Test
	public void testGetConnection() {
		// fail("Not yet implemented");
	}

	@Test
	public void testCreateDataSource() throws Exception {
		FiConnectionProvider f = FiOraXConnectionProvider.getProvider();

		List<Connection> connectionList = new ArrayList<Connection>();

		for (int i = 0; i < 4; i++) {
			System.out.println(i);
			Connection c = f.getConnection();
			connectionList.add(c);
			System.out.println(c.createStatement());
			System.out.println(i);
			assertNotNull(c);
		}
		System.out.println("done");
		// System.in.read();
	}

	@Test
	public void testCreateDataSource2() throws Exception {

		// OracleDataSource ods = new OracleDataSource();
		// ods.setConnectionCachingEnabled(true);
		// ods.setConnectionCacheName(ods.getConnectionCacheName() + (new
		// Random()).nextInt());
		// System.out.println(ods.getConnectionCacheName());
		// ods.setConnectionCacheName("kut");
		// System.out.println(ods.getConnectionCacheName());
		// System.out.println(ods.getConnectionCacheName() + (new
		// Random()).nextInt());
		// ods.setConnectionCacheName(ods.getConnectionCacheName() + (new
		// Random()).nextInt());
		// System.out.println(ods.getConnectionCacheName());

	}

}
