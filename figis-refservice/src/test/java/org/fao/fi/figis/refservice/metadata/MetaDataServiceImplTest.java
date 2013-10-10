package org.fao.fi.figis.refservice.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.fao.fi.figis.refservice.entity.FigisEntityTreeNode;
import org.fao.fi.figis.refservice.reftable.BaseTest;
import org.fao.fi.figis.util.common.FAOException;
import org.junit.Test;

public class MetaDataServiceImplTest extends BaseTest {

	/**
	 * MetaDataServiceImplTest
	 * 
	 * @throws FAOException
	 */
	@Test
	public final void testGetTreeNode() throws FAOException {
		int metaID = 172000;
		MetaDataService mMetaServ = MetaDataServiceFactory.getService();

		FigisEntityTreeNode metaNode = mMetaServ.getTreeNode(metaID);
		assertNotNull(metaNode);

		MetaObject o = (MetaObject) metaNode.getNode();

		assertEquals(metaID, o.mAccessorID);

	}

	@Test
	public final void testGetEntity() throws FAOException {
		int metaID = 172000;
		// int metaID = 185000;
		MetaDataService mMetaServ = MetaDataServiceFactory.getService();

		MetaObject meta = (MetaObject) mMetaServ.getEntity(MetaObject.makeID(metaID));
		System.out.println(meta.getAttrValue(1007));

	}
}
