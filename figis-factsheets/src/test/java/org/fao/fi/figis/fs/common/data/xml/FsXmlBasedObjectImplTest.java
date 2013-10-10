package org.fao.fi.figis.fs.common.data.xml;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.junit.Test;

/**
 * 
 * 
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class FsXmlBasedObjectImplTest extends BaseTest {

	private static final String XML = "src/test/resources/factsheets/resource/163502/factsheet163502.xml";

	private static final String XPATHS = "testdata/FsXmlBasedObject.properties";

	//
	// protected FsTestSetup mTestSetup;
	//
	// protected File mTempDir;

	// @Before
	// public void setUp() throws Exception {
	// mTestSetup = new FsTestSetup();
	// mTempDir = new File(FiConstants.TEMP_DIR);
	// }

	@Test
	public void testResolveXPath() throws Exception {
		String xpath = "//fi:FIGISDoc/fi:AqRes/fi:AqResIdent/fi:ReferenceYear";
		FsXmlBasedObject object = create();
		assertNotNull(object.resolveXPath(object.getRoot(), xpath));
	}

	protected FsXmlBasedObject create() throws Exception {
		FsXmlBasedObject testObj;
		InputStream is;
		is = getClass().getResourceAsStream(XPATHS);
		testObj = new FsXmlBasedObjectImpl() {

			private static final long serialVersionUID = -494427712157777389L;

			public Object getId() {
				return getFid();
			}

			public Integer getFid() {
				return new Integer(9);
			}

			public void setId(Object i) {
				return;
			}
		};
		assertNotNull("Creation failed", testObj);
		testObj.addXPathProperties(is);
		return testObj;
	}

}
