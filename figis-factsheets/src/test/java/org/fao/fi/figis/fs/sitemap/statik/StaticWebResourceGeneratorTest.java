package org.fao.fi.figis.fs.sitemap.statik;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class StaticWebResourceGeneratorTest {

	StaticWebResourceGenerator swrg = new StaticWebResourceGenerator("src/test/resources");

	@Test
	public void testGenerateWebResourceList() {
		String site = "fi";
		String domain = "culturedspecies";

		List<String> list = swrg.generateWebResourceList(site, domain);
		assertEquals(4, list.size());
		assertEquals("culturedspecies/ARG_6/es", list.get(0));
		assertEquals("culturedspecies/fims_cf/en", list.get(2));
	}
}
