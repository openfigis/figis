package org.fao.dataloading.countryrfb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.fao.dataloading.countryrfb.jaxb.JaxbUnmarshal;
import org.fao.fi.figis.devcon.FIGISDoc;
import org.fao.fi.figis.devcon.OrgsInvolved;
import org.junit.Test;

public class FIGISDocDiggerTest {

	JaxbUnmarshal u = new JaxbUnmarshal();
	FIGISDocDigger d = new FIGISDocDigger();

	@Test
	public void testFindObject() throws IOException {
		String rfb = String.join("\n",
				Files.readAllLines(Paths.get("src/test/resources/org.fao.dataloading.countryrfb/IATTTC_inputxml.xml")));
		FIGISDoc doc = u.parse(rfb);
		OrgsInvolved found = (OrgsInvolved) d.findObject(doc.getOrg(), OrgsInvolved.class);

		assertNotNull(found);
		assertEquals(3, found.getTextsAndImagesAndTables().size());

	}
}
