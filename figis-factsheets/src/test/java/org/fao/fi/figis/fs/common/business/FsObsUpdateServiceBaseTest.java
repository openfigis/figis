package org.fao.fi.figis.fs.common.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.fao.fi.figis.fs.common.cache.CacheKeyGenerator;
import org.fao.fi.figis.fs.common.data.BaseTest;
import org.fao.fi.figis.fs.dataset.countryprofile.FsCountryprofileObservation;
import org.fao.fi.figis.util.common.DiskCache;
import org.fao.fi.figis.util.db.HibernateAccess;
import org.junit.Test;

public class FsObsUpdateServiceBaseTest extends BaseTest {

	@Test
	public void getInstance() {
		String dom = "facp";
		DiskCache cache = DiskCache.getInstance(dom);
		assertNotNull(cache);
	}

	/**
	 * Daemon Thread [http-8080-Processor25] (Suspended (breakpoint at line 56
	 * in FsObsUpdateServiceBase))
	 * FsObsUpdateServiceBase.deleteFromCache(FsObservation) line: 56
	 * 
	 * FsObsManagementService$ModifyStatusAction.performAction(FsObservation)
	 * line: 461
	 * 
	 * FsObsManagementService(FsObsUpdateServiceBase).performAction(
	 * FsObservation, FsObsUpdateServiceBase$ActionClass) line: 37
	 * 
	 * FsObsManagementService.changeStatus(FsObservation, Integer) line: 564
	 * 
	 * FsManageAction.executeAction(ActionMapping, ActionForm,
	 * HttpServletRequest, HttpServletResponse) line: 237
	 * 
	 * FsManageAction(FiStrutsActionBase).execute(ActionMapping, ActionForm,
	 * HttpServletRequest, HttpServletResponse) line: 117
	 * 
	 * FsManageAction(FsStrutsAction).execute(ActionMapping, ActionForm,
	 * HttpServletRequest, HttpServletResponse) line: 47
	 * 
	 * ExecuteAction.execute(ActionContext, Action, ActionConfig, ActionForm)
	 * line: 58
	 * 
	 * ExecuteAction(AbstractExecuteAction).execute(ActionContext) line: 67
	 * 
	 * ExecuteAction(ActionCommandBase).execute(Context) line: 51
	 * 
	 * ChainBase.execute(Context) line: 191
	 * 
	 * LookupCommand.execute(Context) line: 305
	 * 
	 * ChainBase.execute(Context) line: 191
	 * 
	 * ComposableRequestProcessor.process(HttpServletRequest,
	 * HttpServletResponse) line: 283
	 * 
	 * FiActionServlet(ActionServlet).process(HttpServletRequest,
	 * HttpServletResponse) line: 1913
	 * 
	 * FiActionServlet.process(HttpServletRequest, HttpServletResponse) line: 32
	 * 
	 * FiActionServlet(ActionServlet).doPost(HttpServletRequest,
	 * HttpServletResponse) line: 462
	 * 
	 * FiActionServlet(HttpServlet).service(HttpServletRequest,
	 * HttpServletResponse) line: 710
	 * 
	 * FiActionServlet(HttpServlet).service(ServletRequest, ServletResponse)
	 * line: 803
	 * 
	 * ApplicationFilterChain.internalDoFilter(ServletRequest, ServletResponse)
	 * line: 269
	 * 
	 * ApplicationFilterChain.doFilter(ServletRequest, ServletResponse) line:
	 * 188
	 * 
	 * ApplicationDispatcher.invoke(ServletRequest, ServletResponse,
	 * ApplicationDispatcher$State) line: 691
	 * 
	 * ApplicationDispatcher.processRequest(ServletRequest, ServletResponse,
	 * ApplicationDispatcher$State) line: 469
	 * 
	 * ApplicationDispatcher.doForward(ServletRequest, ServletResponse) line:
	 * 403
	 * 
	 * ApplicationDispatcher.forward(ServletRequest, ServletResponse) line: 301
	 * 
	 * SmartForward(Forward).process(HttpServletRequest, HttpServletResponse)
	 * line: 31
	 * 
	 * DispatcherController.handleRequest(HttpServletRequest,
	 * HttpServletResponse) line: 113
	 * 
	 * SimpleControllerHandlerAdapter.handle(HttpServletRequest,
	 * HttpServletResponse, Object) line: 45
	 * 
	 * DispatcherServlet.doDispatch(HttpServletRequest, HttpServletResponse)
	 * line: 806
	 * 
	 * DispatcherServlet.doService(HttpServletRequest, HttpServletResponse)
	 * line: 736
	 * 
	 * DispatcherServlet(FrameworkServlet).processRequest(HttpServletRequest,
	 * HttpServletResponse) line: 396
	 * 
	 * DispatcherServlet(FrameworkServlet).doPost(HttpServletRequest,
	 * HttpServletResponse) line: 360
	 * 
	 * DispatcherServlet(HttpServlet).service(HttpServletRequest,
	 * HttpServletResponse) line: 710
	 * 
	 * DispatcherServlet(HttpServlet).service(ServletRequest, ServletResponse)
	 * line: 803
	 * 
	 * ApplicationFilterChain.internalDoFilter(ServletRequest, ServletResponse)
	 * line: 269
	 * 
	 * ApplicationFilterChain.doFilter(ServletRequest, ServletResponse) line:
	 * 188
	 * 
	 * CharacterEncodingFilter.doFilterInternal(HttpServletRequest,
	 * HttpServletResponse, FilterChain) line: 78
	 * 
	 * CharacterEncodingFilter(OncePerRequestFilter).doFilter(ServletRequest,
	 * ServletResponse, FilterChain) line: 77
	 * 
	 * ApplicationFilterChain.internalDoFilter(ServletRequest, ServletResponse)
	 * line: 215
	 * 
	 * ApplicationFilterChain.doFilter(ServletRequest, ServletResponse) line:
	 * 188
	 * 
	 * OpenSessionInViewFilter.doFilterInternal(HttpServletRequest,
	 * HttpServletResponse, FilterChain) line: 174
	 * 
	 * OpenSessionInViewFilter(OncePerRequestFilter).doFilter(ServletRequest,
	 * ServletResponse, FilterChain) line: 77
	 * 
	 * ApplicationFilterChain.internalDoFilter(ServletRequest, ServletResponse)
	 * line: 215
	 * 
	 * ApplicationFilterChain.doFilter(ServletRequest, ServletResponse) line:
	 * 188
	 * 
	 * StandardWrapperValve.invoke(Request, Response) line: 210
	 * 
	 * StandardContextValve.invoke(Request, Response) line: 174
	 * 
	 * StandardHostValve.invoke(Request, Response) line: 127
	 * 
	 * ErrorReportValve.invoke(Request, Response) line: 117
	 * 
	 * FastCommonAccessLogValve.invoke(Request, Response) line: 482
	 * 
	 * StandardEngineValve.invoke(Request, Response) line: 108
	 * 
	 * CoyoteAdapter.service(Request, Response) line: 151
	 * 
	 * Http11Processor.process(InputStream, OutputStream) line: 870
	 * 
	 * Http11Protocol$JmxHttp11ConnectionHandler(
	 * Http11BaseProtocol$Http11ConnectionHandler
	 * ).processConnection(TcpConnection, Object[]) line: 665
	 * 
	 * PoolTcpEndpoint.processSocket(Socket, TcpConnection, Object[]) line: 528
	 * 
	 * LeaderFollowerWorkerThread.runIt(Object[]) line: 81
	 * 
	 * ThreadPool$ControlRunnable.run() line: 685
	 * 
	 * ThreadWithAttributes(Thread).run() line: 595
	 * 
	 * @throws Exception
	 */

	@Test
	public void testDeleteFromCache1() throws Exception {

		// String dom = obs.getDatasetName();
		// Object fid = obs.getParentFid();
		// Object oid = obs.getId();
		// DiskCache cache = DiskCache.getInstance(dom);
		// // delete both direct object cache entry...
		// Object key = makeCacheKey(dom, fid);
		// cache.delete(key);
		// // ...and direct observation cache entry
		// key = makeCacheKey(dom, fid, oid);
		// cache.delete(key);
		// cache.putItem(key, null);
		//
		//
		// ((Factsheet) ro).setWorkingLanguage(service.getWorkingLanguage());
		// if ((!draft) && (cacheKey != null)) {
		// cache.putItem(cacheKey, ro);
		// : 13030:3:163662
		int oid1 = 163662;
		int fid = 3;
		FsCountryprofileObservation obs = (FsCountryprofileObservation) HibernateAccess.getObject(
				FsCountryprofileObservation.class, oid1);
		assertNotNull(obs);
		String dom = "facp";
		DiskCache cache = DiskCache.getInstance(dom);
		// FsObsSaveService s = new FsObsSaveService();
		CacheKeyGenerator g = new CacheKeyGenerator();
		Object key = g.makeCacheKey(dom, fid);
		cache.putItem(key, obs);

		FsCountryprofileObservation cachedObs = (FsCountryprofileObservation) cache.getItem(key);
		assertEquals(obs.getId(), cachedObs.getId());
		cache.delete(key);
		assertNull(cache.get(key));
		cache.putItem(key, obs);
		FsObsUpdateServiceBase.deleteFromCache(obs);
		assertNull(cache.getItem(key));
	}

	/**
	 * 
	 * "CD_OBSERVATION" "CD_AREA" "REPORTING_YEAR"
	 * 
	 * 163622 3 "1999"
	 * 
	 * 163682 3 "2007"
	 * 
	 * 163662 3 "2005"
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteFromCache2() throws Exception {

		int oid1 = 163622;
		int oid2 = 163682;
		int oid3 = 163662;
		int fid = 3;
		FsCountryprofileObservation obs1 = (FsCountryprofileObservation) HibernateAccess.getObject(
				FsCountryprofileObservation.class, oid1);
		FsCountryprofileObservation obs2 = (FsCountryprofileObservation) HibernateAccess.getObject(
				FsCountryprofileObservation.class, oid2);
		FsCountryprofileObservation obs3 = (FsCountryprofileObservation) HibernateAccess.getObject(
				FsCountryprofileObservation.class, oid3);
		assertNotSame(obs1, obs2);
		assertNotSame(obs2, obs3);
		assertNotSame(obs1, obs3);
		String dom = "kriojpsod";
		DiskCache cache = DiskCache.getInstance(dom);
		CacheKeyGenerator g = new CacheKeyGenerator();
		Object key = g.makeCacheKey(dom, fid);
		cache.putItem(key, obs1);
		assertEquals(obs1, cache.getItem(key));
		cache.delete(key);
		assertNull(cache.getItem(key));

	}

}
