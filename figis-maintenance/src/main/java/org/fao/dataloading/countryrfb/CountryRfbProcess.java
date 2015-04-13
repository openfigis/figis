package org.fao.dataloading.countryrfb;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.fao.figis.db.FigisDB;

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

	static String QUERY = " select xml from  fs_organization_observation o, fs_observation obs, fs_observation_xml xml"
			+ "where o.cd_observation = obs.cd_observation                                                "
			+ "and cd_collection = 2290                                                                   "
			+ "and obs.cd_observation = xml.cd_observation                                                "
			+ "and cd_language = 1                                                                        "
			+ "and fg_status = 2 ;";

	void run() {
		@SuppressWarnings("unused")
		List<ObservationXml> list = em.createNativeQuery(QUERY, ObservationXml.class).getResultList();
		System.out.println(list.size());
	}

}
