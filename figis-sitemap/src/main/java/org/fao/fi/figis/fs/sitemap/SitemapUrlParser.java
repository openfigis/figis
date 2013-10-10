package org.fao.fi.figis.fs.sitemap;

/**
 * 
 * Parse the url's into a clear request for a sitemap or sitemapindex, given the
 * site and/or domain.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class SitemapUrlParser {

	public final static String SITEMAPINDEX = "sitemapindex.xml";

	private String site;
	private String domain;
	private boolean sitemapIndex;

	/**
	 * 
	 * pathInfo = /{site}/{domain}.xml or /{site}.xml
	 * 
	 * or
	 * 
	 * @param pathInfo
	 * @return
	 */
	boolean parsePathInfo(String pathInfo) {
		String array[] = pathInfo.split("/");
		boolean valid = false;
		if (array.length == 3) {
			this.site = array[1];
			if (array[2].equals(SITEMAPINDEX)) {
				this.domain = null;
				this.sitemapIndex = true;
			} else {
				this.domain = array[2].substring(0, (array[2].length() - 4));
				this.sitemapIndex = false;
			}
			valid = true;
		}
		return valid;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @return the sitemapIndex
	 */
	public boolean isSitemapIndex() {
		return sitemapIndex;
	}

}
