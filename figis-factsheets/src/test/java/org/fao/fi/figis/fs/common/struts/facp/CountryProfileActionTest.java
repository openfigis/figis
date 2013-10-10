package org.fao.fi.figis.fs.common.struts.facp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.dataset.countryprofile.CountryProfileService;
import org.junit.Test;

public class CountryProfileActionTest extends BaseTest {

	@Test
	public void testExecuteActionMappingActionFormHttpServletRequestHttpServletResponse() {
		CountryProfileService s = new CountryProfileService();
		CountryProfileMapping m = new CountryProfileMapping();

		String language = "fr";
		List<AreaPresentation> list = m.map(s.retrieveCountryProfileAreas(), language);

		assertEquals(3, list.size());

	}

}
