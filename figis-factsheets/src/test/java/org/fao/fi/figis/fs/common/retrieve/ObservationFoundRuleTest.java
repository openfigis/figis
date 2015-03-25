package org.fao.fi.figis.fs.common.retrieve;

import static org.junit.Assert.fail;

import org.fao.fi.figis.fs.dataset.vme.FsVmeObservation;
import org.junit.Test;

public class ObservationFoundRuleTest {

	ObservationFoundRule r = new ObservationFoundRule();

	@Test
	public void testApply() {
		try {
			r.apply(null);
			fail();
		} catch (Exception e) {
		}

		r.apply(new FsVmeObservation());

	}

}
