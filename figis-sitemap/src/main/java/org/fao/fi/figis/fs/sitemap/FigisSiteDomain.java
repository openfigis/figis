package org.fao.fi.figis.fs.sitemap;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Has all the kind of meta information on what sites are there, what are the
 * url's, what domains are there per site. This class relies heavily on
 * sites.properties.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class FigisSiteDomain {

	private static Logger log = Logger.getLogger(FigisSiteDomain.class);

	private static String SITE = "site";
	private static String DOMAIN = "dom";
	private static String URL = "url";

	private static List<String> sites = new ArrayList<String>();
	private static Map<String, List<String>> domains = new HashMap<String, List<String>>();;
	private static Properties props = new Properties();;
	private static Map<String, String> urlMap = new HashMap<String, String>();;

	// load the language stuff from this property file into the props
	static {
		try {
			String propFileName = "sites.properties";
			InputStream inputStream = FigisSiteDomain.class.getResourceAsStream(propFileName);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			props.load(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		int i = 1;
		boolean hasSite = false;
		// loop through the properties siteX=
		do {
			String siteKey = SITE + i;
			String siteUrlKey = siteKey + URL;
			String siteValue = props.getProperty(siteKey);
			urlMap.put(siteValue, props.getProperty(siteUrlKey));
			hasSite = siteValue != null;
			if (hasSite) {
				sites.add(siteValue.trim());
				int j = 1;
				boolean hasDomain = false;
				List<String> domainList = new ArrayList<String>();
				// loop through the properties domain1siteX=
				do {
					String domainKey = SITE + i + DOMAIN + j++;
					String domainValue = props.getProperty(domainKey.trim());
					hasDomain = domainValue != null;
					if (hasDomain) {
						domainList.add(domainValue.trim());
					}
				} while (hasDomain);
				domains.put(siteValue.trim(), domainList);

			}
			i++;
		} while (hasSite);

	}

	/**
	 * @return the sites
	 */
	public List<String> getSites() {
		return sites;
	}

	public List<String> getDomainsPer(String site) {
		return domains.get(site);
	}

	public String retrieveUrl(String site) {
		return urlMap.get(site);
	}

}
