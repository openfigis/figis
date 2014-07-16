package org.fao.fi.figis.fs.sitemap;

public class AreaServiceMock implements AreaService {

	public static String GERMANY = "DLD";

	public String getIso3For(int area) {
		return GERMANY;

	}

}
