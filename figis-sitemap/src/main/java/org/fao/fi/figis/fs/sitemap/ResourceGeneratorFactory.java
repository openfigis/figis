package org.fao.fi.figis.fs.sitemap;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceGeneratorFactory {

	private final static String KEY = "impl";
	private final static String SITEMAPDIR = "sitemapDir";
	private final static ResourceGenerator resourceGenerator;
	private final static String sitemapDir;

	static {
		try {
			Properties props = new Properties();
			String propFileName = "generator.properties";
			InputStream inputStream = FigisSiteDomain.class.getResourceAsStream(propFileName);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			props.load(inputStream);
			resourceGenerator = (ResourceGenerator) Class.forName(props.getProperty(KEY)).newInstance();
			sitemapDir = props.getProperty(SITEMAPDIR);

		} catch (Exception e) {
			throw new FigisRunException(e);
		}

	}

	static ResourceGenerator getImpl() {
		return resourceGenerator;
	}

	static String getSitemapDir() {
		return sitemapDir;
	}

}
