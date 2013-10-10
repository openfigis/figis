package org.fao.fi.figis.fs.common.data.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.fao.fi.figis.fs.common.business.FsObsManagementService;
import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.dataset.collection.FiCollection;
import org.fao.fi.figis.util.db.HibernateUtil;
import org.junit.Test;

public class FsObservationFactoryTest extends BaseTest {

	FsObsManagementService s = new FsObsManagementService();;

	/**
	 * mMeta = 267000 Collection:7240
	 * 
	 * @throws Exception
	 */

	@Test
	public final void testGetCollectionObservations() throws Exception {
		FiCollection collection = new FiCollection();
		collection.setFid(new Integer(7240));

		List<FsObservation> oList = new ArrayList<FsObservation>();
		FsObservationFactory[] factories = null;
		try {
			factories = FsObservationFactory.getAll();
			for (int f = 0; f < factories.length; f++) {
				List<FsObservation> mResult = factories[f].getCollectionObservations(collection, true);

				oList.addAll(mResult);
			}
			oList = sortObservationList(oList);
		} finally {
			HibernateUtil.cleanup();
		}
	}

	private List<FsObservation> sortObservationList(List<FsObservation> in) {
		Collections.sort(in, new FsObservationComparator(Locale.ENGLISH));
		return in;
	}

}
