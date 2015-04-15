package org.fao.dataloading.countryrfb;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.fao.dataloading.countryrfb.jaxb.JaxbUnmarshal;
import org.fao.fi.figis.devcon.FIGISDoc;
import org.fao.figis.db.FigisDB;

import com.opencsv.CSVWriter;

/**
 * <fi:FigisID>22090</fi:FigisID> <fi:OrgRef> <fi:FigisID MetaID="104000">22090</fi:FigisID> </fi:OrgRef>
 * 
 * @author Erik van Ingen
 *
 */
public class CountryRfbProcess {

	String userName = "";
	String password = "";

	@FigisDB
	@Inject
	EntityManager em;

	@Inject
	JaxbUnmarshal jaxbUnmarshal;

	@Inject
	RfmoCountry rfmoCountry;

	static String CVS = "src/test/resources/org.fao.dataloading.countryrfb/RfbCountry.csv";

	static String QUERY = " select xml.* from  figis.fs_organization_observation o, figis.fs_observation obs, figis.fs_observation_xml xml "
			+ " where o.cd_observation = obs.cd_observation                                                "
			+ " and cd_collection = 2290                                                                   "
			+ " and obs.cd_observation = xml.cd_observation                                                "
			+ " and cd_language = 1                                                                        "
			+ " and fg_status = 2 ";

	void run() {
		@SuppressWarnings("unchecked")
		List<ObservationXml> list = (List<ObservationXml>) em.createNativeQuery(QUERY, ObservationXml.class)
				.getResultList();

		try {
			CSVWriter writer = new CSVWriter(new FileWriter(CVS), ',');
			for (ObservationXml observationXml : list) {
				FIGISDoc doc = jaxbUnmarshal.parse(observationXml.getXml());
				String[][] entries = rfmoCountry.getRfmoCountry(doc);
				for (String[] record : entries) {
					writer.writeNext(record);
				}
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
