package org.fao.fi.figis.fs.common.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.common.data.object.FsObservableObject;
import org.fao.fi.figis.fs.common.data.object.FsObservation;
import org.fao.fi.figis.fs.common.data.xml.FsLanguageXml;
import org.fao.fi.figis.fs.dataset.countryprofile.FsCountryprofileObservation;
import org.fao.fi.figis.fs.dataset.resource.FsResourceObservation;
import org.fao.fi.figis.util.common.FAOContextFactory;
import org.fao.fi.figis.util.context.FAOUser;
import org.fao.fi.figis.util.db.HibernateAccess;
import org.hibernate.ReplicationMode;
import org.junit.Test;

public class FsObsManagementServiceTest extends BaseTest {

	FsObsManagementService fsObsManagementService = new FsObsManagementService();;

	/**
	 * 
	 * 05-07-12 15:44:18 [INFO ][FiRefObjectImplFactory ]: finding object:
	 * meta=13030, fid=7 05-07-12 15:44:18 [INFO ][FiRefObjectImplFactory ]:
	 * located object using [7] 05-07-12 15:44:18 [ERROR][FsXmlBasedObjectImpl
	 * ]: Problem getting XPath value
	 * "//fi:FIGISDoc/fi:CountrySector/fi:CountrySectorIdent|fi:FacpRef/fi:ReportingYear"
	 * for
	 * org.fao.fi.figis.fs.dataset.countryprofile.FsCountryprofileObservation
	 * 05-07-12 15:44:18 [ERROR][FsXmlBasedObjectImpl ]:
	 * java.lang.NullPointerException
	 * 
	 * 
	 * http://figis02:8080/fishery/inputxml/facp/8/163362/es
	 * 
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testMakePrimary1() throws Exception {
		// int countryProfile = 7;
		int oid1 = 163362;
		FsObservation o1 = (FsObservation) HibernateAccess.getObject(FsObservation.class, oid1);

		FsObservableObject oo = o1.getParent();
		assertNotNull(oo);

		int oid2 = 163003;
		FsObservation o2 = (FsObservation) HibernateAccess.getObject(FsObservation.class, oid2);

		assertEquals(oid1, o1.getId());

		// List<FsObservation> list =
		// HibernateAccess.runNamedQuery(FsCountryprofileObservation.class,
		// name, true, params);
		// for (FsObservation fsObservation : list) {
		// System.out.println("------" + fsObservation.getParentId() +
		// fsObservation.getId());
		// }

		fsObsManagementService.makePrimary(o1);
	}

	@Test
	public final void testMakePrimary2() throws Exception {
		// int countryProfile = 7;
		int oid1 = 163362;
		FsCountryprofileObservation o1 = (FsCountryprofileObservation) HibernateAccess.getObject(
				FsCountryprofileObservation.class, oid1);

		int oid2 = 163003;
		FsCountryprofileObservation o2 = (FsCountryprofileObservation) HibernateAccess.getObject(
				FsCountryprofileObservation.class, oid2);

		assertEquals(oid1, o1.getId());

		// List<FsObservation> list =
		// HibernateAccess.runNamedQuery(FsCountryprofileObservation.class,
		// name, true, params);
		// for (FsObservation fsObservation : list) {
		// System.out.println("------" + fsObservation.getParentId() +
		// fsObservation.getId());
		// }

		fsObsManagementService.makePrimary(o1);
	}

	@Test
	public final void testLock() throws Exception {
		FsObservation obs = null;
		int action = 1;
		fsObsManagementService.lock(obs, action);
	}

	@Test
	public final void testCopyMoveObject() throws Exception {
		FsLanguageXml to_be_moved = null;
		boolean copy = true;
		ReplicationMode mode = null;
		fsObsManagementService.copyMoveObject(to_be_moved, copy, mode);
	}

	@Test
	public final void testApprove() throws Exception {
		FsObservation fsObservation = null;
		fsObsManagementService.approve(fsObservation);
	}

	// finding object: meta=195000, fid=13598

	@Test
	public final void testmakeReference() throws Exception {

		// http://figis02:8282/firms/resource/13391/156984/en/draft/data
		// resource: FsResourceObservation: 195000:13391:163284

		// ation: 195000:6010:163443
		// http://figis02:8080/fishery/inputxml/resource/6010/163443/en
		// oid = 156984
		String xMeta = "195000";
		String xFid = "6010";
		String xLive = "true";
		List<FsObservation> observations = fsObsManagementService.getObservations(xMeta, xFid, xLive);
		for (FsObservation o : observations) {
			System.out.println("------------");
			System.out.println("------------");
			if (!o.isReference() && !o.isPrimary()) {
				fsObsManagementService.makeReference(o);
			}
		}
	}

	@Test
	public final void testmakePrimary1() throws Exception {
		String id = "vaningen";
		FAOContextFactory contextFact = FAOContextFactory.getInstance();
		contextFact.getOrCreateContext(id);
		FAOContextFactory.getCurrentContext().getUser().setLogon(id);
		FAOUser foundUser = FAOContextFactory.getCurrentContext().getUser();
		assertEquals(id, foundUser.getLogon());
		String xMeta = "195000";
		String xFid = "6010";
		String xLive = "true";
		// Integer oid = new Integer(865);
		Integer oid = new Integer(163443);

		List<FsObservation> observations = fsObsManagementService.getObservations(xMeta, xFid, xLive);
		assertEquals(1, countNumberOfPrimaries(observations));
		FsObservation obs = null;
		for (FsObservation o : observations) {
			System.out.println("------------");
			System.out.println("------------");
			System.out.println(o.getId());
			if (o.getId().equals(oid)) {
				System.out.println("----goal!!-------");
				obs = o;
			}
		}
		assertNotNull(obs);
		fsObsManagementService.makePrimary(obs);
		List<FsObservation> observationsNew = fsObsManagementService.getObservations(xMeta, xFid, xLive);

		assertEquals(1, countNumberOfPrimaries(observationsNew));
	}

	@Test
	public final void testmakePrimary2() throws Exception {
		String xMeta = "195000";
		String xFid = "6010";
		String xLive = "true";
		Integer oid865 = new Integer(865);
		Integer oid163443 = new Integer(163443);
		// String oid865 = "865";
		// String oid163443 = "163443";

		List<FsObservation> observations = fsObsManagementService.getObservations(xMeta, xFid, xLive);
		assertEquals(3, observations.size());
		assertEquals(1, countNumberOfPrimaries(observations));
		FsObservation obs865 = null;
		FsObservation obs163443 = null;
		for (FsObservation o : observations) {
			System.out.println(o.getId() + " - " + o.getIdentity() + " - " + o.getOid());
		}
		for (FsObservation o : observations) {
			if (o.getOid().equals(oid865)) {
				System.out.println(o.getId());
				System.out.println("found oid865");
				obs865 = o;
			}
			if (o.getOid().equals(oid163443)) {
				System.out.println(o.getId());
				System.out.println("found oid163443");
				obs163443 = o;
			}
		}
		assertFalse(obs865.getOid().equals(obs163443.getOid()));

		if (obs865.isPrimary()) {
			System.out.println("#############################obs865.isPrimary(), moving to the other");
			moveFlag(obs865, obs163443);
		}
		if (obs163443.isPrimary()) {
			System.out.println("#############################obs163443.isPrimary(), moving to the other");
			moveFlag(obs163443, obs865);
		}
		assertEquals(1, countNumberOfPrimaries(observations));
	}

	private void moveFlag(FsObservation one, FsObservation other) throws Exception {
		// Session s = HibernateUtil.getSessionNew();
		// Transaction tx = s.beginTransaction();
		//
		// FsObservation one1 = (FsObservation) s.load(one.getClass(),
		// one.getOid());
		// FsObservation other1 = (FsObservation) s.load(other.getClass(),
		// other.getOid());
		// System.out.println("1s.isOpen() " + s.isOpen());
		// one1.setXmlPrimary(false);
		// other1.setXmlPrimary(true);
		// System.out.println("2s.isOpen() " + s.isOpen());
		// s.merge(one1);
		// System.out.println("3s.isOpen() " + s.isOpen());
		// s.merge(other1);
		// System.out.println("4s.isOpen() " + s.isOpen());
		// tx.commit();
		// s.close();
		// System.out.println("5s.isOpen() " + s.isOpen());
	}

	private int countNumberOfPrimaries(List<FsObservation> observations) {
		int c = 0;
		for (FsObservation fsObservation : observations) {
			if (fsObservation.isPrimary()) {
				c++;
			}
		}
		return c;
	}

	@Test
	public final void testNotAbleToObtainConnection() throws Exception {

		// http://figis02:8282/firms/resource/13391/156984/en/draft/data
		// resource: FsResourceObservation: 195000:13391:163284

		// 163283:en INFO HibernateAccess - Object not found:
		// org.fao.fi.figis.fs.common.data.xml.FsLanguageXml id=163283:en(true)
		// FiRefResource: 195000/13391(live)

		// oid = 156984
		String xMeta = "195000";
		String xFid = "13391";
		String xLive = "false";
		List<FsObservation> observations = fsObsManagementService.getObservations(xMeta, xFid, xLive);
		for (FsObservation o : observations) {
			System.out.println("------------");
			System.out.println("-----------getId-" + o.getId() + "-----------getIdentity-" + o.getIdentity()
					+ "-----------getOid-" + o.getOid() + " getWorkingLanguage" + o.getWorkingLanguage());
			if (!o.isReference() && !o.isPrimary()) {
				// o.setReference(true);
				HibernateAccess.saveOrUpdateObject(o, true);
			}
		}
	}

	@Test
	public final void testNotAbleToObtainConnection2() throws Exception {

		// http://figis02:8282/firms/resource/13391/156984/en/draft/data
		// resource: FsResourceObservation: 195000:13391:163284

		// 163283:en INFO HibernateAccess - Object not found:
		// org.fao.fi.figis.fs.common.data.xml.FsLanguageXml id=163283:en(true)
		// FiRefResource: 195000/13391(live)
		// http://figis02:8282/fishery/inputxml/resource/13598/163282/en
		// oid = 156984
		String xMeta = "195000";
		String xFid = "13598";
		String xLive = "false";
		List<FsObservation> observations = fsObsManagementService.getObservations(xMeta, xFid, xLive);
		for (FsObservation o : observations) {
			System.out.println("------------");
			System.out.println("-----------getId-" + o.getId() + "-----------getIdentity-" + o.getIdentity()
					+ "-----------getOid-" + o.getOid() + " getWorkingLanguage " + o.getWorkingLanguage()
					+ " getWorkingLocale " + o.getWorkingLocale());
			if (!o.isReference() && !o.isPrimary()) {
				// o.setReference(true);
				HibernateAccess.saveOrUpdateObject(o, true);
			}
		}
	}

	@Test
	public final void testSave() throws Exception {

		// http://figis02:8282/firms/resource/13391/156984/en/draft/data
		// resource: FsResourceObservation: 195000:13391:163284

		// oid = 156984

		FsObservation poging = new FsResourceObservation();
		poging.setId(new Integer(5000));
		HibernateAccess.saveObject(poging, true);
		System.out.println(poging.getIdentity());

	}

	@Test
	public final void testHibernateAccessgetObject() throws Exception {
		String id = "163342:es";
		boolean live = true;
		FsLanguageXml xml = (FsLanguageXml) HibernateAccess.getObject(FsLanguageXml.class, id, live);
		assertNotNull(xml);
	}
}
