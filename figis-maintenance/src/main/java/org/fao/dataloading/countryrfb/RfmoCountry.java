package org.fao.dataloading.countryrfb;

import java.io.Serializable;
import java.util.List;

import org.fao.fi.figis.devcon.FIGISDoc;
import org.fao.fi.figis.devcon.FigisID;
import org.fao.fi.figis.devcon.LandAreaRef;
import org.fao.fi.figis.devcon.OrgRef;
import org.fao.fi.figis.devcon.OrgsInvolvedEntry;

public class RfmoCountry {

	FIGISDocDigger d = new FIGISDocDigger();

	public String[][] getRfmoCountry(FIGISDoc doc) {

		int rfmoColumn = 0;
		int countryColumn = 1;

		OrgRef orgRef = (OrgRef) d.findObject(doc.getOrg(), OrgRef.class);

		OrgsInvolvedEntry orgsInvolvedEntry = (OrgsInvolvedEntry) d.findObject(doc.getOrg(), OrgsInvolvedEntry.class);

		String[][] bunch = null;

		if (orgsInvolvedEntry != null && orgsInvolvedEntry.getOrgRevesAndLandAreaRevesAndTexts() != null) {

			bunch = new String[orgsInvolvedEntry.getOrgRevesAndLandAreaRevesAndTexts().size()][2];

			String rfmo = ((FigisID) orgRef.getForeignIDsAndFigisIDsAndTitles().get(0)).getContent();

			List<Serializable> list = orgsInvolvedEntry.getOrgRevesAndLandAreaRevesAndTexts();
			for (int record = 0; record < bunch.length; record++) {
				if (list.get(record) instanceof LandAreaRef) {
					LandAreaRef landAreaRef = (LandAreaRef) list.get(record);
					FigisID id = (FigisID) landAreaRef.getFigisIDsAndForeignIDs().get(0);
					bunch[record][rfmoColumn] = rfmo;
					bunch[record][countryColumn] = id.getContent();
				}
			}
		} else {
			bunch = new String[0][0];

		}

		return bunch;
	}

}
