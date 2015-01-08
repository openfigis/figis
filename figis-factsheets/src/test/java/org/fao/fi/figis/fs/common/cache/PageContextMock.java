package org.fao.fi.figis.fs.common.cache;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

public class PageContextMock extends PageContext {

	public static final String TRANS_RESULT = "content string with HTML";

	PageContextMock(String requestParams) {
		instanceData.put(CachJspLogic.QUERY_CACHE, "");
		instanceData.put(CachJspLogic.TRANS_RESULT, TRANS_RESULT);

		RequestParmsParser p = new RequestParmsParser();

		HtmlCacheLogic htmlCacheLogic = new HtmlCacheLogic();
		String cacheKey = htmlCacheLogic.makeCacheKey(p.parse(requestParams));
		instanceData.put(CachJspLogic.CACHE_FILE, htmlCacheLogic.makeFilePath(cacheKey));
	}

	private Map<String, Object> instanceData = new HashMap<String, Object>();

	@Override
	public void forward(String arg0) throws ServletException, IOException {

	}

	@Override
	public Exception getException() {

		return null;
	}

	@Override
	public Object getPage() {

		return "";
	}

	@Override
	public ServletRequest getRequest() {

		return null;
	}

	@Override
	public ServletResponse getResponse() {

		return null;
	}

	@Override
	public ServletConfig getServletConfig() {

		return null;
	}

	@Override
	public ServletContext getServletContext() {

		return null;
	}

	@Override
	public HttpSession getSession() {

		return null;
	}

	@Override
	public void handlePageException(Exception arg0) throws ServletException, IOException {

	}

	@Override
	public void handlePageException(Throwable arg0) throws ServletException, IOException {

	}

	@Override
	public void include(String arg0) throws ServletException, IOException {

	}

	@Override
	public void include(String arg0, boolean arg1) throws ServletException, IOException {

	}

	@Override
	public void initialize(Servlet arg0, ServletRequest arg1, ServletResponse arg2, String arg3, boolean arg4,
			int arg5, boolean arg6) throws IOException, IllegalStateException, IllegalArgumentException {

	}

	@Override
	public void release() {

	}

	@Override
	public Object findAttribute(String arg0) {

		return "";
	}

	@Override
	public Object getAttribute(String key) {

		return instanceData.get(key);
	}

	@Override
	public Object getAttribute(String key, int arg1) {
		return instanceData.get(key);
	}

	@Override
	public Enumeration<?> getAttributeNamesInScope(int arg0) {

		return null;
	}

	@Override
	public int getAttributesScope(String arg0) {

		return 0;
	}

	@Override
	public ExpressionEvaluator getExpressionEvaluator() {

		return null;
	}

	@Override
	public JspWriter getOut() {

		return null;
	}

	@Override
	public VariableResolver getVariableResolver() {

		return null;
	}

	@Override
	public void removeAttribute(String arg0) {

	}

	@Override
	public void removeAttribute(String arg0, int arg1) {

	}

	@Override
	public void setAttribute(String key, Object value) {
		instanceData.put(key, value);
	}

	@Override
	public void setAttribute(String key, Object value, int arg2) {
		instanceData.put(key, value);
	}

}
