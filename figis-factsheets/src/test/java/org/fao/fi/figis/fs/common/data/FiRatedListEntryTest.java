package org.fao.fi.figis.fs.common.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FiRatedListEntryTest {

	@Test
	public final void erik() {
		int l = 4;
		int nt = Math.max(1, (l / 10));

		System.out.println(nt);
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

		FiRatedListEntry o1 = new FiRatedListEntry(meta, fid, status, context, nameEs, urlEs, pct);
		FiRatedListEntry o2 = new FiRatedListEntry(meta, fid, status, context, nameFr, urlFr, pct);
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

		FiRatedListEntry o1 = new FiRatedListEntry(meta, fid, status1, context, nameEs, urlEs, pct);
		FiRatedListEntry o2 = new FiRatedListEntry(meta, fid, status2, context, nameEs, nameEs, pct);
		assertFalse(o1.equals(o2));
	}

}
