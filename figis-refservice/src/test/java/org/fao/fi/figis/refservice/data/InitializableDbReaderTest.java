package org.fao.fi.figis.refservice.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.fao.fi.figis.refservice.reftable.BaseTest;
import org.junit.Test;

public class InitializableDbReaderTest extends BaseTest {

	@Test
	public void testReadObject() throws Exception {
		int aID[] = { 1001, 1007, 26001 };

		for (int i : aID) {
			AttributeAccessor accessor = (AttributeAccessor) InitializableDbReader.getInstance().readObject(i);
			assertNotNull(accessor);
			String names[] = (String[]) accessor.getColumnName();
			assertEquals("NAME_E", names[0]);
		}

	}

}
