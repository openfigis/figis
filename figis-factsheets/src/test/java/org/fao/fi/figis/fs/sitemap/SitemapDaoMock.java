package org.fao.fi.figis.fs.sitemap;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.figis.fs.sitemap.dao.SitemapDao;
import org.fao.fi.figis.fs.sitemap.dao.SitemapWebResource;

public class SitemapDaoMock implements SitemapDao {

	public List<SitemapWebResource> retrieveWebResources(String site, String domain) {
		SitemapWebResource r = new SitemapWebResource();
		r.setId(10);
		r.setIso2Code("en");
		r.setObservation(403);
		r.setPrimary(true);
		r.setResource(4545);
		List<SitemapWebResource> l = new ArrayList<SitemapWebResource>();
		l.add(r);
		return l;
	}

}
