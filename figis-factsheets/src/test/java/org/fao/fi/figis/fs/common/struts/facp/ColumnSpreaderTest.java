package org.fao.fi.figis.fs.common.struts.facp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ColumnSpreaderTest {

	ColumnSpreader cs = new ColumnSpreader();

	@Test
	public void testMap2Columns() {

		// 1 element
		List<ColumnSpreaderTest> aList = new ArrayList<ColumnSpreaderTest>();
		aList.add(new ColumnSpreaderTest());
		List<List<?>> columns = cs.map2Columns(aList);
		assertEquals(1, columns.size());
		assertEquals(1, columns.get(0).size());

		// 2 element
		aList.add(new ColumnSpreaderTest());
		assertEquals(2, aList.size());
		columns = cs.map2Columns(aList);
		assertEquals(2, columns.size());
		assertEquals(1, columns.get(0).size());
		assertEquals(1, columns.get(1).size());

		// 3 element
		aList.add(new ColumnSpreaderTest());
		assertEquals(3, aList.size());
		columns = cs.map2Columns(aList);
		assertEquals(2, columns.size());
		assertEquals(2, columns.get(0).size());
		assertEquals(1, columns.get(1).size());

		// 4 element
		aList.add(new ColumnSpreaderTest());
		assertEquals(4, aList.size());
		columns = cs.map2Columns(aList);
		assertEquals(2, columns.size());
		assertEquals(2, columns.get(0).size());
		assertEquals(2, columns.get(1).size());
	}

	@Test
	public void testMap2ColumnsZero() {

		// 1 element
		List<ColumnSpreaderTest> aList = new ArrayList<ColumnSpreaderTest>();
		List<List<?>> columns = cs.map2Columns(aList);
		assertEquals(0, columns.size());

	}

	@Test
	public void testMap2Columns9() {

		int size = 9;

		// 1 element
		List<ColumnSpreaderTest> aList = new ArrayList<ColumnSpreaderTest>();
		for (int i = 0; i < size; i++) {
			aList.add(new ColumnSpreaderTest());
		}
		assertEquals(size, aList.size());
		List<List<?>> columns = cs.map2Columns(aList);
		int counted = 0;
		for (List<?> list : columns) {
			counted += list.size();
		}
		assertEquals(size, counted);

	}
}
