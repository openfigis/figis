package org.fao.fi.figis.fs.common.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.common.data.FiWebLink;
import org.fao.fi.figis.fs.common.data.object.Factsheet;
import org.fao.fi.figis.fs.common.data.object.FsObservation;
import org.fao.fi.figis.fs.common.data.xml.FsLanguageXml;
import org.fao.fi.figis.util.data.FiConstants;
import org.junit.Test;

public class FsObsRetrievalServiceTest extends BaseTest {

	FsObsRetrievalService service = FsObsRetrievalService.getService();

	@Test
	public void testGetWebLink1() throws Exception {
		int meta = 165000;
		int fid = 2888;
		String lang = "es";
		FiWebLink wl = service.getWebLink(meta, fid, lang);
		assertNotNull(wl);
	}

	/**
	 * http://193.43.36.238:9090/browse/FIGIS-627
	 * 
	 * 
	 * * problem here is the resource is 13537 (fid) and the observation is
	 * 163394 (oid)
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetWebLink2() throws Exception {
		int meta = 195000;

		int fid = 13537;
		String lang = "fr";
		FiWebLink wl = service.getWebLink(meta, fid, lang);
		assertNotNull(wl);
	}

	@Test
	public void testGet1() throws Exception {
		int meta = 195000;
		int fid = 10531;
		FsObservation obs = (FsObservation) service.get(meta, fid);
		System.out.println(obs.getOid());
		assertNotNull(obs);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGet2() throws Exception {
		Integer domain = 205000;
		Integer fid = 467;
		Integer oid = 164607;

		// 205000:467:164607
		Integer key[] = { domain, fid, oid };

		boolean statusProtected = true;
		boolean logged_in = false;
		boolean draft = true;
		if (!draft && !logged_in) {
			Factsheet obs = (Factsheet) service.get(key);
			if (obs.getLive() && obs.getStatus().equals(FiConstants.STATUS_INTERNET)) {
				statusProtected = false;
			}
			assertNotNull(obs);
			assertTrue(statusProtected);

		}
		assertFalse(service.isLive());

	}

	/**
	 * http://figis02:8080/fishery/asfa/faq/159829/en/draft
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGet3() throws Exception {
		Integer domain = 205000;
		Integer fid = 525;
		Integer oid = 163523;

		Integer key[] = { domain, fid, oid };
		FsObservation obs = (FsObservation) service.get(key);

		boolean statusProtected = true;
		if (obs.getLive() && obs.getStatus().equals(FiConstants.STATUS_INTERNET)) {
			statusProtected = false;
		}

		assertFalse(service.isLive());
		assertTrue(statusProtected);
		assertNotNull(obs);
		System.out.println(obs.getOid());

	}

	/**
	 * 
	 * this object runs for figisapps
	 * 
	 */
	@Test
	public void testGet4() throws Exception {
		Integer domain = 195000;
		Integer fid = 10091;
		// Integer oid = 871;

		FsObservation obs = (FsObservation) service.get(domain, fid);

		if (obs != null) {
			List<FsLanguageXml> xmls = obs.getAllXmls();
			for (FsLanguageXml lXml : xmls) {
				lXml.getXml().expandReferences();
			}
		}

	}

	// [area, Area71.xml]

	@Test
	public void testGetArea() throws Exception {
		Integer domain = 172000;
		Integer fid = 1;
		// Integer oid = 871;

		FsObservation obs = (FsObservation) service.get(domain, fid);
		assertNotNull(obs);
		assertTrue(obs.getAllXmls().size() > 0);

		if (obs != null) {
			List<FsLanguageXml> xmls = obs.getAllXmls();
			for (FsLanguageXml lXml : xmls) {
				lXml.getXml().expandReferences();
			}
		}

	}

	@Test
	public void testGetVme4() throws Exception {
		Integer domain = 172000;
		Integer fid = 1;
		// Integer oid = 871;

		FsObservation obs = (FsObservation) service.get(domain, fid);
		assertNotNull(obs);
		assertTrue(obs.getAllXmls().size() > 0);

		if (obs != null) {
			List<FsLanguageXml> xmls = obs.getAllXmls();
			for (FsLanguageXml lXml : xmls) {
				lXml.getXml().expandReferences();
			}
		}

	}

	/**
	 * An error occurred
	 * 
	 * The following error(s) occurred:
	 * 
	 * The requested factsheet could not be found ([166000, 12356])
	 * 
	 * and m.cd_meta = 166000 and m.cd_topic = 12356 and o.cd_observation =
	 * 163602
	 * 
	 * 
	 * http://figis02:8282/fishery/inputxml/topic/12356/163602/en
	 * 
	 * finds [FsTopicObservation: 166000:12356:152787, FsTopicObservation:
	 * 166000:12356:163602]
	 * 
	 * FiRefResource: 195000/6010(live)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetLucaCase() throws Exception {
		int meta = 165000;
		int fid = 12356;
		// int meta = 165000;
		// int fid = 2888;
		FsObservation obs = (FsObservation) service.get(meta, fid);
		assertNotNull(obs);
		System.out.println(obs.getOid());
	}

	/**
	 * testing a situation where the requested language is not available, but
	 * other languages are.
	 * 
	 * 
	 * About Figis http://figis02:8080/fishery/topic/18043/159410/en/data
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetOtherLanguages() throws Exception {
		// int meta = 166000;
		// int fid = 18043;

		// http://figis02:8080/firms/resource/6010/163502/fr/data
		int meta = 195000;
		int fid = 6010;

		service.setWorkingLanguage("fr");

		FsObservation obs = (FsObservation) service.get(meta, fid);
		boolean statusProtected = true;
		assertTrue(obs.getLive());
		FsObservation parentObservation = obs.getParent().getPrimaryObservation();
		List<FsLanguageXml> langs = parentObservation.getAllXmls();
		boolean primaryIsPublished = false;
		for (FsLanguageXml fsLanguageXml : langs) {
			if (fsLanguageXml.getStatus().equals(FiConstants.STATUS_INTERNET)) {
				primaryIsPublished = true;
			}
		}
		assertTrue(primaryIsPublished);

		if (obs.getLive() && primaryIsPublished) {
			statusProtected = false;
		}
		assertFalse(statusProtected);
		System.out.println(obs.getOid());
	}
}
