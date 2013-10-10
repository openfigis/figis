package org.fao.fi.figis.fs.dataset.countryprofile;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.util.db.HibernateAccess;
import org.junit.Test;

public class AreaTest extends BaseTest {

	@Test
	public void testGetArea() throws Exception {

		int areaNumber = 8;

		CountryProfileObservationArea a = (CountryProfileObservationArea) HibernateAccess.getObject(
				CountryProfileObservationArea.class, areaNumber);
		assertEquals(areaNumber, a.getArea());
		assertEquals("AG", a.getIso2code());
		FsCountryprofileObservation o;
		Object params[] = {};

		List areas = HibernateAccess.runNamedQuery(CountryProfileObservationArea.class, "countryProfilesAreas", true,
				params);

		assertEquals(3, areas.size());

	}
}
