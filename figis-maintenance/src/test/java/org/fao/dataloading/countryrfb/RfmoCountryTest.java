package org.fao.dataloading.countryrfb;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.fao.dataloading.countryrfb.jaxb.JaxbUnmarshal;
import org.fao.fi.figis.devcon.FIGISDoc;
import org.junit.Test;

public class RfmoCountryTest {

	RfmoCountry rc = new RfmoCountry();
	JaxbUnmarshal u = new JaxbUnmarshal();
	FIGISDocDigger d = new FIGISDocDigger();

	@Test
	public void testGetRfmoCountry() throws IOException {
		String rfb = String.join("\n",
				Files.readAllLines(Paths.get("src/test/resources/org.fao.dataloading.countryrfb/IATTTC_inputxml.xml")));
		FIGISDoc doc = u.parse(rfb);

		String[][] csv = rc.getRfmoCountry(doc);
		for (String[] record : csv) {
			for (String cell : record) {
				System.out.print(cell);
				System.out.print(" ");
			}
			System.out.println();
		}
		assertEquals(21, csv.length);
		assertEquals(2, csv[0].length);

	}
}
