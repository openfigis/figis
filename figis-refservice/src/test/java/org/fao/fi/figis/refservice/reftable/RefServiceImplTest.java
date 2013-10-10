package org.fao.fi.figis.refservice.reftable;

import static org.junit.Assert.assertNotNull;

import org.fao.fi.figis.refservice.metadata.MetaObject;
import org.fao.fi.figis.util.common.FAOException;
import org.fao.fi.figis.util.entity.GlobalID;
import org.junit.Test;

public class RefServiceImplTest extends BaseTest {

	/**
	 * 
	 GlobalID gid = new GlobalID(GlobalID.REF, meta, fid); GlobalID trueGid =
	 * mRefServ.findExistingEntity(gid);
	 * 
	 * GlobalID trueGid = mRefServ.findExistingEntity(gid); RefService
	 * org.fao.fi.figis.refservice.reftable.RefServiceImpl@3513126e
	 * 
	 * FsTopicObservation: 166000:null:156522
	 * 
	 * FsTopicObservation: 166000:null:156549
	 * 
	 * 
	 * @throws FAOException
	 */
	@Test
	public final void testFindExistingEntityEafNansen() throws FAOException {

		// int meta = 166000;
		int meta = 169000;
		// int oid = 156549;
		int fid = 18002;

		RefService mRefServ = RefServiceFactory.getService(meta);
		assertNotNull(mRefServ);
		GlobalID gid = new GlobalID(GlobalID.REF, meta, fid);
		assertNotNull(gid);
		GlobalID trueGid = mRefServ.findExistingEntity(gid);
		assertNotNull(trueGid);
		// mRefServ.findExistingEntity(aID)
	}

	@Test
	public final void testFindExistingEntityVme() throws FAOException {
		int meta = 172000;
		int fid = 10;

		GlobalID gid = new GlobalID(GlobalID.REF, meta, fid);
		assertNotNull(gid);

		RefService mRefServ = RefServiceFactory.getService(meta);
		assertNotNull(mRefServ);
		GlobalID trueGid = mRefServ.findExistingEntity(gid);
		assertNotNull(trueGid);
		// mRefServ.findExistingEntity(aID)

	}

	@Test
	public final void testGetEntity() throws FAOException {
		int meta = 172000;
		RefService mRefServ = RefServiceFactory.getService(meta);
		MetaObject mo = (MetaObject) mRefServ.getEntity(MetaObject.makeID(172000));
		assertNotNull(mo);

	}

}
