package org.fao.fi.figis.refservice.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.fao.fi.figis.refservice.metadata.attr.MetaAttrName;
import org.fao.fi.figis.util.common.FAOException;
import org.fao.fi.figis.util.entity.GlobalID;
import org.junit.Test;

public class MetaObjectTest {

	@Test
	public void testMetaObjectIntInt() throws FAOException {
		MetaObject mo = new MetaObject(172000, 1);
		assertNotNull(mo);

	}

	@Test
	public void testSetParent() throws FAOException {

		int accessorId = 1007;

		// the meta object
		MetaObject vme = new MetaObject(172000, 1);
		MetaObject cp = new MetaObject(185000, 1);

		MetaAttrName bad = new MetaAttrName();
		bad.setAccessorID(accessorId);
		// the fact that it has 11 here instead of 1 is the problem.
		GlobalID badID = new GlobalID(2, 172000, 11);
		bad.setID(badID);
		vme.addMetaAttr(bad);

		MetaAttrName good = new MetaAttrName();
		good.setAccessorID(accessorId);
		GlobalID goodID = new GlobalID(2, 185000, 1);
		good.setID(goodID);
		cp.addMetaAttr(good);

		// the parent for the above meta object
		MetaObject parent = new MetaObject(1, 0);
		MetaAttrName longName = new MetaAttrName();
		longName.setAccessorID(0);
		GlobalID longGlobalId = new GlobalID(0, 0, 1);
		longName.setID(longGlobalId);

		parent.setID(longGlobalId);
		parent.addMetaAttr(longName);

		assertEquals(1, vme.getMetaAttrCount());
		assertEquals(1, cp.getMetaAttrCount());
		vme.setParent(parent);
		cp.setParent(parent);
		assertEquals(1, cp.getMetaAttrCount());
		assertEquals(2, vme.getMetaAttrCount());

	}
}
