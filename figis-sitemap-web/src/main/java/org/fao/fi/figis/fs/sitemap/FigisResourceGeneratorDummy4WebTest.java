package org.fao.fi.figis.fs.sitemap;

import java.util.ArrayList;
import java.util.List;

public class FigisResourceGeneratorDummy4WebTest implements ResourceGenerator {

	public List<String> generateResourceList(String domain) {
		List<String> resourceList = new ArrayList<String>();
		resourceList.add("en");
		resourceList.add("capture/en");

		return resourceList;
	}

}
