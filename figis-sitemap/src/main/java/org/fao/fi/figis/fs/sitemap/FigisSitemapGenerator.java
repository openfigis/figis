package org.fao.fi.figis.fs.sitemap;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.SitemapIndexGenerator;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

/**
 * Generates the sitemap plus the sitemap indexes.
 * 
 * @author Erik van Ingen
 * 
 */
public class FigisSitemapGenerator {

	private final static long ONE_WEEK = 7 * 24 * 60 * 60 * 1000;
	public final static String SITEMAP = "sitemap";

	private FigisSiteDomain fsd = new FigisSiteDomain();
	private ResourceGenerator resourceGenerator = ResourceGeneratorFactory.getImpl();;

	/**
	 * The base dire where the sitemaps and sitemap indexes will be stored.
	 */
	private String baseDir;

	/**
	 * @param baseDir
	 *            the baseDir to set
	 */
	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	private String composeSitemapBaseUrl(String site) {
		return fsd.retrieveUrl(site) + "/" + SITEMAP;
	}

	public File generateSitemap(String site, String domain) {
		File file = new File(baseDir + composeSitemapFileName(site, domain));
		if (generateFile(file)) {
			try {
				File baseDirFile = new File(baseDir);

				WebSitemapGenerator wsg = WebSitemapGenerator.builder(fsd.retrieveUrl(site), baseDirFile)
						.fileNamePrefix(composeSitemapName(site, domain)).build();

				List<String> urlList = resourceGenerator.generateResourceList(domain);
				for (String resource : urlList) {
					String urlString = fsd.retrieveUrl(site) + "/" + resource;
					// for now, only the changeFreq is taken into account. This
					// can be improved by applying also : lastMod(new
					// Date()).priority(1.0).changeFreq(ChangeFreq.HOURLY).build();
					WebSitemapUrl url = new WebSitemapUrl.Options(urlString).changeFreq(ChangeFreq.WEEKLY).build();
					wsg.addUrl(url);
				}

				wsg.write();

			} catch (MalformedURLException e) {
				throw new FigisRunException(e);
			}
		}
		return file;
	}

	/**
	 * Generate the sitemapIndex. Needs only to be done once.
	 */
	public File generateSitemapIndex(String site) {
		String sitemapIndexFile = baseDir + File.separator + site + ".xml";
		File file = new File(sitemapIndexFile);
		if (generateFile(file)) {
			try {
				SitemapIndexGenerator sig = new SitemapIndexGenerator(fsd.retrieveUrl(site), file);
				List<String> domains = fsd.getDomainsPer(site);
				for (String domain : domains) {
					String url = composeSitemapBaseUrl(site) + "/" + site + "/" + domain + ".xml";
					sig.addUrl(url);
				}
				sig.write();
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new FigisRunException(e);
			}
		}
		return file;

	}

	private boolean generateFile(File file) {
		boolean generate = false;
		long weekAfter = file.lastModified() + ONE_WEEK;
		if (!file.exists() || System.currentTimeMillis() > weekAfter) {
			generate = true;
		}
		return generate;
	}

	public String composeSitemapName(String site, String domain) {
		return site + "_" + domain;
	}

	public String composeSitemapFileName(String site, String domain) {
		return composeSitemapName(site, domain) + ".xml";
	}

}
