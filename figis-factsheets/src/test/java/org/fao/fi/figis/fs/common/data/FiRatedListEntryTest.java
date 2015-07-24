package org.fao.fi.figis.fs.common.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FiRatedListEntryTest {

	
	String geoReference = "Afghanistan";
	
	
	@Test
	public final void FiRatedListEntryConstructor() {
		new FiRatedListEntry(0, 0, 0, null, null, null, 0, null);
		new FiRatedListEntry(0, 0, 0, null, null, null, 0, "");
		new FiRatedListEntry(0, 0, 0, null, null, null, 0, " ");
		new FiRatedListEntry(0, 0, 0, null, null, null, 0, "  ");
		
	}

	@Test
	public final void testEqualsObject() {
		int meta = 166000;
		int fid = 2888;
		int status = 2;
		String context = "Fisheries and Aquaculture topics";
		String nameEs = "Problemáticas de la utilización de alimentos";
		String nameFr = "Probleme de la utilización de alimentation";

		String urlEs = "/fi/website/FIRetrieveAction.do?dom=topic&fid=2888&lang=es";
		String urlFr = "/fi/website/FIRetrieveAction.do?dom=topic&fid=2888&lang=fr";
		int pct = 100;

		FiRatedListEntry o1 = new FiRatedListEntry(meta, fid, status, context, nameEs, urlEs, pct, geoReference);
		FiRatedListEntry o2 = new FiRatedListEntry(meta, fid, status, context, nameFr, urlFr, pct, geoReference);
		assertTrue(o1.equals(o1));
		assertFalse(o1.equals(o2));
	}

	@Test
	public final void testEqualsObjectStatus() {
		int meta = 166000;
		int fid = 2888;
		int status1 = 1;
		int status2 = 2;
		String context = "Fisheries and Aquaculture topics";
		String nameEs = "Problemáticas de la utilización de alimentos";

		String urlEs = "/fi/website/FIRetrieveAction.do?dom=topic&fid=2888&lang=es";
		int pct = 100;

		FiRatedListEntry o1 = new FiRatedListEntry(meta, fid, status1, context, nameEs, urlEs, pct, geoReference);
		FiRatedListEntry o2 = new FiRatedListEntry(meta, fid, status2, context, nameEs, nameEs, pct, geoReference);
		assertFalse(o1.equals(o2));
	}

}
