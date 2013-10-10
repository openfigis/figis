package factsheet.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.fao.fi.figis.fs.common.data.FiKeywordScannerTest;
import org.fao.fi.figis.fs.common.data.object.FsObservation;
import org.fao.fi.figis.fs.common.data.xml.FsLanguageXml;
import org.fao.fi.figis.fs.dataset.organization.FsOrganizationObservation;
import org.fao.fi.figis.fs.dataset.topic.FsTopicObservation;
import org.fao.fi.figis.util.data.FiConstants;
import org.fao.fi.figis.util.db.HibernateAccess;
import org.junit.Before;
import org.junit.Test;

public class GenerateFactsheets {

	String storeDir = "target/";

	@Before
	public void before() throws Exception {
		File ctrlFile = new File("../figis-properties/properties/common/SearchTerms.xml");
		FiConstants.setPropertiesDir("../figis-properties/properties");
		FiConstants.setDbProperties(FiKeywordScannerTest.DATABASE_SCAN_PROPERTIES);
	}

	@Test
	public void exportFsTopicObservation() throws Exception {
		Object topics[] = { new Integer(18000), new Integer(18001), new Integer(18002), new Integer(18003),
				new Integer(18004), new Integer(18011), new Integer(16074), new Integer(18005), new Integer(18006),
				new Integer(18007), new Integer(18248), new Integer(18249), new Integer(18008), new Integer(18010),
				new Integer(18013), new Integer(166282), new Integer(4450), new Integer(12356), new Integer(4440),
				new Integer(12270), new Integer(166277), new Integer(166279), new Integer(166280) };
		writeXml(topics, FsTopicObservation.class);
	}

	@Test
	public void exportFsOrganizationObservation() throws Exception {
		Object topics[] = { new Integer(17910) };
		writeXml(topics, FsOrganizationObservation.class);
	}

	public void writeXml(Object topics[], Class<?> clazz) throws Exception {
		String name = "getAllObservations";
		for (Object object : topics) {
			Object params[] = { object };
			List<FsObservation> list = HibernateAccess.runNamedQuery(clazz, name, true, params);
			for (FsObservation fsObservation : list) {
				System.out.println("------" + fsObservation.getParentId());
				List<FsLanguageXml> xmls = fsObservation.getAllXmls();
				for (FsLanguageXml fsLanguageXml : xmls) {
					byte[] doc = fsLanguageXml.getXmlAsByteArray();
					String filename = "target/" + object + "_" + fsLanguageXml.getId() + ".xml";
					filename = filename.replaceAll(":", "_");
					System.out.println(filename);
					writeByte2File(filename, doc);
				}
			}
		}

	}

	/**
	 * We spoke, please try to generate all available inputXML for Topic IDs
	 * 16000 and 18037 and for Organization ID 17910.
	 * 
	 * "CD_OBSERVATION" 152662 159262
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	@Test
	public void exportFactsheets2() throws Exception {

		// Object o = FiRefTopicFactory.getInstance(165000);
		// ReferenceFactory ff = (FiRefObjectImplFactory)
		// FiRefTopicFactory.getInstance(165000);
		// FsLanguageXmlFactory f = FsLanguageXmlFactory.getInstance();
		// Object[] key = { new Integer(16000), new Integer(18037) };
		//
		// List<FiRefObject> list = ff.find(key)
		// for (FiRefObject ref : list) {
		// List<FsLanguageXml> l = f.getAllLanguages(ref.getId(), true);
		// assertTrue(l.size() > 0);
		// for (FsLanguageXml fsLanguageXml : l) {
		// byte[] doc = fsLanguageXml.getXmlAsByteArray();
		// String filename = ref.getId() + "_" + fsLanguageXml.getId() + ".xml";
		// System.out.println(filename);
		// writeByte2File(filename, doc);
		// }
		//
		// }
		//
		// Integer ids[] = { new Integer(152662), new Integer(159262), new
		// Integer(17910) };
		// for (Integer id : ids) {
		// List<FsLanguageXml> list = f.getAllLanguages(id, true);
		// assertTrue(list.size() > 0);
		// for (FsLanguageXml fsLanguageXml : list) {
		// byte[] doc = fsLanguageXml.getXmlAsByteArray();
		// String filename = id + "_" + fsLanguageXml.getId() + ".xml";
		// System.out.println(filename);
		// writeByte2File(filename, doc);
		// }
		// }
	}

	void writeByte2File(String filename, byte[] doc) {

		try {
			FileOutputStream fos = new FileOutputStream(filename);

			/*
			 * To write byte array to a file, use void write(byte[] bArray)
			 * method of Java FileOutputStream class.
			 * 
			 * This method writes given byte array to a file.
			 */
			fos.write(doc);
			/*
			 * Close FileOutputStream using, void close() method of Java
			 * FileOutputStream class.
			 */
			fos.close();
		} catch (FileNotFoundException ex) {
			System.out.println("FileNotFoundException : " + ex);
		} catch (IOException ioe) {
			System.out.println("IOException : " + ioe);
		}

	}

}
