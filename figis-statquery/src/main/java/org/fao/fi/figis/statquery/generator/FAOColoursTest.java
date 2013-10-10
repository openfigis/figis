package org.fao.fi.figis.statquery.generator;

import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.io.IOException;

import org.fao.fi.figis.util.common.FAOColours;
import org.junit.Test;

public class FAOColoursTest {

	@Test
	public void test() throws IOException {
		FAOColours faoColours = new FAOColours();
		Color color = faoColours.getColour("");
		assertNotNull(color);

	}

}
