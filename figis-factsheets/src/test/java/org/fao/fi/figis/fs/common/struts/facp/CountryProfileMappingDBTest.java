package org.fao.fi.figis.fs.common.struts.facp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.dataset.countryprofile.CountryProfileService;
import org.junit.Test;

public class CountryProfileMappingDBTest extends BaseTest {

	CountryProfileMapping m = new CountryProfileMapping();

	@Test
	public void testMap() {
		CountryProfileService s = new CountryProfileService();
		String uiLang = "fr";
		List<AreaPresentation> newList = m.map(s.retrieveCountryProfileAreas(), uiLang);

		assertTrue(newList.size() > 0);

		for (AreaPresentation areaPresentation : newList) {
			List<Iso2Piped> iso2langlist = areaPresentation.getIso2Languages();
			Set<String> languages = new HashSet<String>();
			for (Iso2Piped iso2Piped : iso2langlist) {
				assertNotNull(iso2Piped.getFlyOver());
				assertNotNull(iso2Piped.getIso2());

				System.out.println("language is " + iso2Piped.getFlyOver());
				languages.add(iso2Piped.getIso2());
			}
			assertEquals(iso2langlist.size(), languages.size());
		}
	}

}
