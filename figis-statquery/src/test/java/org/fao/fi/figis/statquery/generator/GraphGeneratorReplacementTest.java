package org.fao.fi.figis.statquery.generator;

import org.fao.figis.test.BaseTest;
import org.junit.Test;

public class GraphGeneratorReplacementTest extends BaseTest {

	GraphGeneratorReplacement g;
	String srv = "http://figis02:8080";
	String store = "target/";

	/**
	 * ERROR XslProcAccess - exception caught:
	 * webapps\figis\tseries\format\dat.xsl (The system cannot find the path
	 * specified) java.io.FileNotFoundException:
	 * 
	 * webapps\figis\tseries\format\dat.xsl (The system cannot find the path
	 * specified)
	 * 
	 */

	@Test
	public void testGenerateCountryGraphs() throws Exception {

		g = new GraphGeneratorReplacement(srv, store);
		g.generateCountryGraphs();
	}

	@Test
	public void testGenerateSpeciesGraphs() throws Exception {
		g = new GraphGeneratorReplacement(srv, store);
		g.generateSpeciesGraphs();
	}

	@Test
	public void testGenerateFleetGraphs() throws Exception {
		g = new GraphGeneratorReplacement(srv, store);
		g.generateFleetGraphs();
	}

}
