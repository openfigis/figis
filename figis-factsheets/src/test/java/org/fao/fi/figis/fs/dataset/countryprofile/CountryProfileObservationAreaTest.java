package org.fao.fi.figis.fs.dataset.countryprofile;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CountryProfileObservationAreaTest {

	@Test
	public void testEquals() {
		CountryProfileObservationArea a1 = new CountryProfileObservationArea();
		a1.setArea(1);
		a1.setLanguage("en");
		CountryProfileObservationArea a2 = new CountryProfileObservationArea();
		a2.setArea(1);
		a2.setLanguage("en");
		assertTrue(a1.equals(a2));
		a2.setLanguage("fr");
		assertFalse(a1.equals(a2));
	}

}
