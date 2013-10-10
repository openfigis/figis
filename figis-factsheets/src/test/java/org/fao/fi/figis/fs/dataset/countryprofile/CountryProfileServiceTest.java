package org.fao.fi.figis.fs.dataset.countryprofile;

import static org.junit.Assert.assertTrue;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.junit.Test;

public class CountryProfileServiceTest extends BaseTest {

	@Test
	public void testRetrieveCountryProfileAreas() {

		CountryProfileService s = new CountryProfileService();
		assertTrue(s.retrieveCountryProfileAreas().size() > 0);
		System.out.println(s.retrieveCountryProfileAreas().size());
	}

}
