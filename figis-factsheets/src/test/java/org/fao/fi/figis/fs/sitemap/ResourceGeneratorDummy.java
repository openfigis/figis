package org.fao.fi.figis.fs.sitemap;

import java.util.ArrayList;
import java.util.List;

public class ResourceGeneratorDummy implements WebResourceGenerator {

	public List<String> generateWebResourceList(String site, String domain) {
		List<String> resourceList = new ArrayList<String>();
		resourceList.add("en");
		resourceList.add("capture/en");

		return resourceList;
	}

}
