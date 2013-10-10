package org.fao.fi.figis.fs.common.retrieve;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.fao.fi.figis.util.data.FiConstants;
import org.junit.Test;

public class ProtectedRuleTest {

	/**
	 * test in case of live
	 * 
	 */
	@Test
	public final void testLive() {
		boolean loggedIn = false;
		boolean reference = true;
		boolean draft = false;
		int status = FiConstants.STATUS_INTERNET;

		ProtectedRule r = new ProtectedRule(loggedIn, reference, draft, status);
		assertTrue(r.continueFlow4XML());

		r.setReference(false);
		r.setStatus(FiConstants.STATUS_INTRANET);
		assertFalse(r.continueFlow4XML());

		r.setReference(false);
		r.setStatus(FiConstants.STATUS_INTERNET);
		assertTrue(r.continueFlow4XML());

		r.setLoggedIn(true);
		assertTrue(r.continueFlow4XML());

		r.setStatus(FiConstants.STATUS_INTRANET);
		r.setReference(false);
		assertTrue(r.continueFlow4XML());
	}

	/**
	 * test in case of draft
	 * 
	 */
	@Test
	public final void testDraft() {
		boolean loggedIn = false;
		boolean reference = true;
		boolean draft = true;
		int status = ProtectedRule.DRAFT_EDITING;

		ProtectedRule r = new ProtectedRule(loggedIn, reference, draft, status);
		assertFalse(r.continueFlow4XML());
		r.setLoggedIn(true);
		assertTrue(r.continueFlow4XML());

		r.setStatus(ProtectedRule.DRAFT_REVIEW);
		assertTrue(r.continueFlow4XML());

	}

}
