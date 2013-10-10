package org.fao.fi.figis.fs.sitemap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Custom servlet in order to process requests for sitemaps and sitemapindexes.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class SitemapServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(SitemapServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -6540868052331486774L;

	/**
			 * 
			 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SitemapUrlParser parser = new SitemapUrlParser();
		FigisSitemapGenerator fsm = new FigisSitemapGenerator();

		// directory setup and configuration
		fsm.setBaseDir(ResourceGeneratorFactory.getSitemapDir());

		try {
			BufferedReader reader;
			if (parser.parsePathInfo(req.getPathInfo())) {
				File file;
				if (parser.isSitemapIndex()) {
					file = fsm.generateSitemapIndex(parser.getSite());
				} else {
					file = fsm.generateSitemap(parser.getSite(), parser.getDomain());
				}
				InputStream is = new FileInputStream(file);
				if (is != null) {
					InputStreamReader isr = new InputStreamReader(is);
					reader = new BufferedReader(isr);
					PrintWriter writer = resp.getWriter();
					String text = "";
					while ((text = reader.readLine()) != null) {
						writer.println(text);
					}
					is.close();
				}
			}
		} catch (Exception e) {
			log.error("Problem in the sitemap file generation", e);
		}

	}
}
