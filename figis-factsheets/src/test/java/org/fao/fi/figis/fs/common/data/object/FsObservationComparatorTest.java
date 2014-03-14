package org.fao.fi.figis.fs.common.data.object;

import static org.junit.Assert.fail;

import java.util.Locale;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.dataset.resource.FiRefResource;
import org.junit.Test;

public class FsObservationComparatorTest extends BaseTest {

	FsObservationComparator c = new FsObservationComparator(Locale.ENGLISH);

	@Test
	public void testCompare() {

		// FsResourceObservation: 195000:null:1036
		FsObservation a = new FsObservationDummy();
		a.setId(1036);
		a.setFid(1036);
		a.setOid(1036);

		// FsResourceObservation: 195000:10342:980
		FsObservation b = new FsObservationDummy();
		b.setId(980);
		b.setFid(980);
		b.setOid(1036);

		FiRefObject parent = new FiRefResource();
		parent.setFid(10342);
		b.setParent(parent);

		System.out.println(a);
		System.out.println(b);

		c.compare(a, b);
	}

	// @Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
