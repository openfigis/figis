package org.fao.fi.figis.crawler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;

import crawlercommons.sitemaps.AbstractSiteMap;
import crawlercommons.sitemaps.SiteMap;
import crawlercommons.sitemaps.SiteMapIndex;
import crawlercommons.sitemaps.SiteMapParser;
import crawlercommons.sitemaps.SiteMapURL;
import crawlercommons.sitemaps.UnknownFormatException;

public class FigisCrawler {

	private static final String URL_FI = "http://www.fao.org/fishery/sitemapnew/fi/sitemapindex.xml";
	private static final String URL_FIRMS = "http://firms.fao.org/firms/sitemapnew/firms/sitemapindex.xml";
	private static final String URL_GFCM = "http://www.gfcm.org/gfcm/sitemapnew/gfcm/sitemapindex.xml";
	private static final String URL_NANSEN = "http://www.eaf-nansen.org/nansen/sitemapnew/nansen/sitemapindex.xml";

	private static final String DEV[][] = {//
	{ "http://www.fao.org/fishery", "http://hqldvfigis1:8080/fishery" },//
			{ "http://firms.fao.org/firms", "http://hqldvfigis1:8080/firms" },//
			{ "http://www.gfcm.org/gfcm", "http://hqldvfigis1:8080/gfcm" },//
			{ "http://www.eaf-nansen.org/nansen", "http://hqldvfigis1:8080/nansen" },//
	};

	private static final String QA[][] = {//
	{ "http://www.fao.org/fishery", "http://hqldvfigis1:8282/fishery" },//
			{ "http://www.gfcm.org/gfcm", "http://hqldvfigis1:8282/gfcm" },//
			{ "http://www.eaf-nansen.org/nansen", "http://hqldvfigis1:8282/nansen" },//
	};

	private static String URLS[] = { URL_FI, URL_FIRMS, URL_GFCM };
	// private static String URLS[] = { URL_FI, URL_FIRMS, URL_GFCM, URL_NANSEN
	// };

	private PrintWriter prod;
	private PrintWriter dev;
	private PrintWriter qa;

	private SiteMapParser p = new SiteMapParser();

	public FigisCrawler() {
		try {
			prod = new PrintWriter("target/prod.csv");
			dev = new PrintWriter("target/dev.csv");
			qa = new PrintWriter("target/qa.csv");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void generateUrlCsv() {
		try {
			for (String url : URLS) {
				SiteMapIndex index = (SiteMapIndex) p.parseSiteMap(new URL(url));
				Collection<AbstractSiteMap> l = index.getSitemaps();
				System.out.println(l.size());
				for (AbstractSiteMap sitemap : l) {
					System.out.println(sitemap.getUrl());
					SiteMapParser pe = new SiteMapParser(false);
					SiteMap map = (SiteMap) pe.parseSiteMap(sitemap.getUrl());
					Collection<SiteMapURL> mapList = map.getSiteMapUrls();
					System.out.println(mapList.size());
					for (SiteMapURL siteMapURL : mapList) {
						String line = siteMapURL.getUrl().toExternalForm();
						prod.write(line + "\n");
						dev.write(manipulate4Dev(line) + "\n");
						qa.write(manipulate4Qa(line) + "\n");
					}
				}
			}
			prod.close();
			dev.close();
			qa.close();

		} catch (UnknownFormatException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String manipulate4Dev(String line) {
		for (String[] two : DEV) {
			line = line.replace(two[0], two[1]);
		}
		return line;
	}

	private String manipulate4Qa(String line) {
		for (String[] two : QA) {
			line = line.replace(two[0], two[1]);
		}
		return line;
	}

}
