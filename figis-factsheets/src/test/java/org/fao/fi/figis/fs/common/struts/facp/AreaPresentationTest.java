package org.fao.fi.figis.fs.common.struts.facp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class AreaPresentationTest {

	@Test
	public void testGetIso2SlashPreferredLanguage() {
		AreaPresentation a = new AreaPresentation();
		a.getLongName();

	}

	@Test
	public void testCompareTo() {
		List<AreaPresentation> newList = new ArrayList<AreaPresentation>();

		AreaPresentation a1 = new AreaPresentation();
		AreaPresentation a2 = new AreaPresentation();
		a1.setLongName("a");
		a2.setLongName("u");
		newList.add(a2);
		newList.add(a1);
		Collections.sort(newList);
		assertEquals(a1, newList.get(0));
		assertEquals(a2, newList.get(1));

	}

}
