package org.fao.fi.figis.fs.common.struts.facp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.figis.fs.dataset.countryprofile.CountryProfileObservationArea;
import org.junit.Test;

public class CountryProfileMappingTest {

	CountryProfileMapping m = new CountryProfileMapping();

	@Test
	public void testMap() {
		List<CountryProfileObservationArea> areaList = new ArrayList<CountryProfileObservationArea>();
		CountryProfileObservationArea a1 = new CountryProfileObservationArea();

		String iso2 = "EN";
		String lang1 = "en";
		String lang2 = "es";
		String uiLang = "fr";

		String longNameF = "oui";
		a1.setLanguage(lang1);
		a1.setLongNameF(longNameF);
		a1.setArea(10);
		a1.setIso2code(iso2);

		CountryProfileObservationArea a2 = new CountryProfileObservationArea();
		a2.setLanguage(lang2);
		a2.setLongNameF(longNameF);
		a2.setArea(10);
		a2.setIso2code("FR");

		areaList.add(a1);
		areaList.add(a2);

		List<AreaPresentation> newList = m.map(areaList, uiLang);

		// test the alfphabetic order of the list.

	}

	@Test
	public void testMapSpanish() {
		List<CountryProfileObservationArea> areaList = new ArrayList<CountryProfileObservationArea>();
		CountryProfileObservationArea a1 = new CountryProfileObservationArea();

		String iso2 = "NL";
		String preferredLanguage = "en";
		String lang = "es";

		String longNameE = "The Netherlands";
		a1.setLanguage(lang);
		a1.setLongNameE(longNameE);
		a1.setArea(10);
		a1.setIso2code(iso2);

		areaList.add(a1);

		List<AreaPresentation> newList = m.map(areaList, preferredLanguage);

		for (AreaPresentation areaPresentation : newList) {
			assertEquals(iso2, areaPresentation.getIso2());
		}

		assertEquals(1, newList.size());

		AreaPresentation a = newList.get(0);

		assertEquals(1, a.getIso2Languages().size());
		assertEquals(longNameE, a.getLongName());
		assertEquals(lang, a.getIso2Languages().get(0).getIso2());
		assertEquals(iso2, a.getIso2());

	}

	@Test
	public void testCountryProfileMapping() {
		List<CountryProfileObservationArea> areaList = new ArrayList<CountryProfileObservationArea>();
		CountryProfileObservationArea a1 = new CountryProfileObservationArea();
		String iso2 = "NL";
		String preferredLanguage = "en";
		String lang = "es";

		String longNameE = "The Netherlands";
		a1.setLanguage(lang);
		a1.setLongNameE(longNameE);
		a1.setArea(10);
		a1.setIso2code(iso2);
		areaList.add(a1);
		List<List<AreaPresentation>> colums = m.map2Columns(areaList, preferredLanguage);

		assertEquals(1, colums.size());
		assertEquals(1, colums.get(0).size());

	}

	@Test
	public void testCountryProfileMapping2() {
		List<CountryProfileObservationArea> areaList = new ArrayList<CountryProfileObservationArea>();
		CountryProfileObservationArea a1 = new CountryProfileObservationArea();
		String iso2 = "NL";
		String preferredLanguage = "en";
		String lang = "es";

		String longNameE = "The Netherlands";
		a1.setLanguage(lang);
		a1.setLongNameE(longNameE);
		a1.setArea(10);
		a1.setIso2code(iso2);
		areaList.add(a1);
		List<List<AreaPresentation>> colums = m.map2Columns(areaList, preferredLanguage);

		assertEquals(1, colums.size());
		assertEquals(1, colums.get(0).size());

	}

}
