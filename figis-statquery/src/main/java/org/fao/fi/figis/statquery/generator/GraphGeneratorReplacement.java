package org.fao.fi.figis.statquery.generator;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.fao.fi.figis.refservice.entity.RefObject;
import org.fao.fi.figis.refservice.entity.criterion.SelectAll;
import org.fao.fi.figis.refservice.entity.criterion.SelectionCriterion;
import org.fao.fi.figis.refservice.entity.list.FigisEntityList;
import org.fao.fi.figis.refservice.reftable.RefService;
import org.fao.fi.figis.refservice.reftable.RefServiceFactory;
import org.fao.fi.figis.statquery.SQKeyParams;

public class GraphGeneratorReplacement {

	ParameterParser p = new ParameterParser();

	private String storeDir;

	private String server;

	private RefService refCountry;

	private RefService refSpecies;

	private RefService refFleet;

	private static final String COUNTRY_DIR = "/countrysector";

	private static final String SPECIES_DIR = "/species";

	private static final String AQUACULTURE_DIR = "/Aquaculture";

	private static final String PRODUCTION_DIR = "/Production";

	private static final String TRADE_DIR = "/Trade";

	private static final String FLEET_DIR = "/Fleet";

	private static final String CAPTURE_DIR = "/Capture";

	private static final String VESSELTYPE_DIR = "/vesseltype";

	private static final String TONNAGE_DIR = "/Tonnage";

	private static final String NUMBER_DIR = "/Number";

	private static final String SQS_BASE_URL = "/figis/servlet/SQServlet?";

	// private static final String SQS_COUNTRY_AQUACULTURE =
	// "ds=Aquaculture&k1=COUNTRY&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";
	private static final String SQS_COUNTRY_AQUACULTURE = "ds=Aquaculture&k1=COUNTRY&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";

	private static final String SQS_COUNTRY_PRODUCTION = "ds=Production&k1=COUNTRY&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";

	private static final String SQS_COUNTRY_CAPTURE = "ds=Capture&k1=COUNTRY&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";

	private static final String SQS_COUNTRY_TRADE = "ds=Commodities&k1=COUNTRY&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";

	private static final String SQS_COUNTRY_FLEET = "ds=Fleet&k1=COUNTRY&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";

	// private static final String SQS_SPECIES_AQUACULTURE =
	// "ds=Aquaculture&k1=SPECIES&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";
	private static final String SQS_SPECIES_AQUACULTURE = "ds=Aquaculture&k1=SPECIES&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&SPECIES_s=";

	private static final String SQS_SPECIES_CAPTURE = "ds=Capture&k1=SPECIES&outtype=gif&gr_props=webapps/figis/species/format/gform_small.txt&k1v=1&k1s=";

	private static final String SQS_VESSELS_TONNAGE = "ds=Fleet&ser=Tonnage&k1=Vesseltype&k1h=64204&outtype=gif&gr_props=webapps/figis/vesseltype/format/gform_grt_small.txt&k1s=";

	private static final String SQS_VESSELS_NUMBER = "ds=Fleet&ser=Number&k1=Vesseltype&k1h=64204&outtype=gif&gr_props=webapps/figis/vesseltype/format/gform_number_small.txt&k1s=";

	private static final int COUNTRY_MID = 13001;

	private static final int SPECIES_MID = 31005;

	private static final int VESSELS_MID = 64200;

	private static final int SPECIES_AQUACULTURE_FILTER = 31102;

	private static final int SPECIES_CAPTURE_FILTER = 31101;

	private static final Logger logger = Logger.getLogger(GraphGenerator.class);

	public GraphGeneratorReplacement(String srv, String store) throws Exception {
		super();
		storeDir = store;
		server = srv;
		refCountry = RefServiceFactory.getService(COUNTRY_MID);
		refSpecies = RefServiceFactory.getService(SPECIES_MID);
		refFleet = RefServiceFactory.getService(VESSELS_MID);
	}

	private void prepareGIFReplacement(SQKeyParams keyParams, String query, File gifDir, String fid, String extension) {
		UrlReplacement url = new UrlReplacement();
		p.apply(query, url);
		url.setServer(server);
		url.setSqsBaseUrl(SQS_BASE_URL);
		url.setFid(fid);
		url.setKeyParams(keyParams);

		if (url.getDs() == null) {
			throw new FigisStatQueryException("Dataset cannot be null at this point, query is " + query);
		}

		SQServletReplacement r = new SQServletReplacement();
		r.setUrlReplacement(url);
		r.setGifDir(gifDir);

		try {
			r.serviceReplacement(url);
		} catch (IOException e) {
			throw new FigisStatQueryException(e);
		} catch (ServletException e) {
			throw new FigisStatQueryException(e);
		}

	}

	// private void saveGIF(String url, File directory, String name) {
	// logger.info("saving URL: " + url + " as: " + name + " into: " +
	// directory);
	// FileOutputStream out = null;
	// InputStream inp = null;
	// URL source = null;
	// try {
	// source = new URL(url);
	// inp = source.openStream();
	// out = new FileOutputStream(new File(directory, name));
	// FigisUtility.copyStream(inp, out);
	// logger.info("GIF saved");
	// } catch (Exception e) {
	// logger.error("exception caught: " + e.getMessage(), e);
	// } finally {
	// try {
	// if (out != null) {
	// out.close();
	// }
	// if (inp != null) {
	// inp.close();
	// }
	// } catch (Exception ignore) {
	// }
	// }
	// }

	/*
	 * Generates the countrysector related graphs.
	 * 
	 * @return true iff the graphs were successfully generated
	 */
	public boolean generateCountryGraphs() {
		final String country_aquaculture_dir = storeDir + COUNTRY_DIR + AQUACULTURE_DIR;
		final String country_production_dir = storeDir + COUNTRY_DIR + PRODUCTION_DIR;
		final String country_trade_dir = storeDir + COUNTRY_DIR + TRADE_DIR;
		final String country_fleet_dir = storeDir + COUNTRY_DIR + FLEET_DIR;
		final String country_capture_dir = storeDir + COUNTRY_DIR + CAPTURE_DIR;
		try {
			logger.info("creating country directories");
			File dir_aquaculture = (new File(country_aquaculture_dir));
			dir_aquaculture.mkdirs();
			File dir_production = (new File(country_production_dir));
			dir_production.mkdirs();
			File dir_trade = (new File(country_trade_dir));
			dir_trade.mkdirs();
			File dir_fleet = (new File(country_fleet_dir));
			dir_fleet.mkdirs();
			File dir_capture = (new File(country_capture_dir));
			dir_capture.mkdirs();
			logger.info("generating countrysector graphs");
			SelectionCriterion selection = SelectAll.Instance;
			FigisEntityList list = refCountry.getEntities(COUNTRY_MID, selection);
			logger.info("got " + list.size() + " countries");
			for (int i = 0; i < list.size(); i++) {
				RefObject country = (RefObject) list.get(i);
				String fid = country.getFid();
				String name = country.getName();
				logger.info("generating graphs for: " + name);
				SQKeyParams k = new SQKeyParams();

				prepareGIFReplacement(k, SQS_COUNTRY_AQUACULTURE, dir_aquaculture, fid, ".gif");
				prepareGIFReplacement(k, SQS_COUNTRY_PRODUCTION, dir_production, fid, ".gif");
				prepareGIFReplacement(k, SQS_COUNTRY_TRADE, dir_trade, fid, ".gif");
				prepareGIFReplacement(k, SQS_COUNTRY_FLEET, dir_fleet, fid, ".gif");
				prepareGIFReplacement(k, SQS_COUNTRY_CAPTURE, dir_capture, fid, ".gif");
			}
			logger.info("countrysector graphs generated");
		} catch (Exception e) {
			logger.error("exception caught: " + e.getMessage(), e);
			throw new FigisStatQueryException(e);
		}
		return true;
	}

	// private void prepareGIFReplacement(String query, File dir_aquaculture,
	// String fid, String extension) {
	// UrlReplacement url = new UrlReplacement();
	// p.apply(query, url);
	// url.setServer(server);
	// url.setSqsBaseUrl(SQS_BASE_URL);
	// saveGIFReplacement(url, dir_aquaculture, fid + extension);
	// }

	/*
	 * Generates the species related graphs.
	 * 
	 * @return true iff the graphs were successfully generated
	 */
	public boolean generateSpeciesGraphs() {
		final String species_aquaculture_dir = storeDir + SPECIES_DIR + AQUACULTURE_DIR;
		final String species_capture_dir = storeDir + SPECIES_DIR + CAPTURE_DIR;
		try {
			logger.info("creating species directories");
			File dir_aquaculture = (new File(species_aquaculture_dir));
			dir_aquaculture.mkdirs();
			File dir_capture = (new File(species_capture_dir));
			dir_capture.mkdirs();
			logger.info("generating species graphs");
			SelectionCriterion selection = SelectAll.Instance;
			// FigisEntityList species_list =
			// refSpecies.getEntities(SPECIES_WITH_FS_FILTER, selection);
			FigisEntityList aquaculture_list = refSpecies.getEntities(SPECIES_AQUACULTURE_FILTER, selection);
			logger.info("got " + aquaculture_list.size() + " species aquaculture stats");
			// int nrOfGrapsh = aquaculture_list.size();
			int nrOfGrapsh = 1;

			for (int i = 0; i < nrOfGrapsh; i++) {
				RefObject species = (RefObject) aquaculture_list.get(i);
				String fid = species.getFid();
				String name = species.getName();
				logger.info("generating graphs for: " + name);
				// String url = server + SQS_BASE_URL + SQS_SPECIES_AQUACULTURE
				// + fid;

				SQKeyParams k = new SQKeyParams();
				k.setKeyName("SPECIES");
				k.setMetaID(SPECIES_MID);
				String[] sel = { fid };
				k.setSelection(sel);
				prepareGIFReplacement(k, SQS_SPECIES_AQUACULTURE + fid, dir_aquaculture, fid, ".gif");

			}
			// FigisEntityList capture_list =
			// refSpecies.getEntities(SPECIES_CAPTURE_FILTER, selection);
			// logger.info("got " + capture_list.size() +
			// " species with capture stats");
			// for (int i = 0; i < nrOfGrapsh; i++) {
			// RefObject species = (RefObject) capture_list.get(i);
			// String fid = species.getFid();
			// String name = species.getName();
			// logger.info("generating graphs for: " + name);
			// // String url = server + SQS_BASE_URL + SQS_SPECIES_CAPTURE +
			// // fid;
			// prepareGIFReplacement(SQS_SPECIES_CAPTURE, dir_capture, fid,
			// ".gif");
			// }
			logger.info("species graphs generated");
		} catch (Exception e) {
			logger.error("exception caught: " + e.getMessage(), e);
			throw new FigisStatQueryException(e);
		}
		return true;
	}

	/*
	 * Generates the vesseltype related graphs.
	 * 
	 * @return true if the graphs were successfully generated
	 */
	public boolean generateFleetGraphs() {
		final String vessels_tonnage_dir = storeDir + VESSELTYPE_DIR + TONNAGE_DIR;
		final String vessels_number_dir = storeDir + VESSELTYPE_DIR + NUMBER_DIR;
		try {
			logger.info("creating species directories");
			File dir_tonnage = (new File(vessels_tonnage_dir));
			dir_tonnage.mkdirs();
			File dir_number = (new File(vessels_number_dir));
			dir_number.mkdirs();
			logger.info("generating vessels graphs");
			SelectionCriterion selection = SelectAll.Instance;
			FigisEntityList list = refFleet.getEntities(VESSELS_MID, selection);
			logger.info("got " + list.size() + " vessel types");
			for (int i = 0; i < list.size(); i++) {
				RefObject vessel = (RefObject) list.get(i);
				String fid = vessel.getFid();
				String name = vessel.getName();
				logger.info("generating graphs for: " + name);
				// String url = server + SQS_BASE_URL + SQS_VESSELS_NUMBER +
				// fid;
				SQKeyParams k = new SQKeyParams();

				prepareGIFReplacement(k, SQS_VESSELS_NUMBER, dir_number, fid, ".gif");
				// url = server + SQS_BASE_URL + SQS_VESSELS_TONNAGE + fid;
				prepareGIFReplacement(k, SQS_VESSELS_TONNAGE, dir_tonnage, fid, ".gif");
			}
			logger.info("vessels graphs generated");
		} catch (Exception e) {
			logger.error("exception caught: " + e.getMessage(), e);
			return false;
		}
		return true;
	}

}
