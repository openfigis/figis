package erik.java8;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.ModuleUtils;

public class ImportJsp {

	ServletContext application;
	HttpServletRequest request;
	PageContext pageContext;
	CharSequence charSequence;

	void go() {

		ModuleConfig modey = ModuleUtils.getInstance().getModuleConfig(request);
		String modulare = modey.getPrefix().replace("/", "");
		if (modulare == "")
			modulare = "shared";
		// set up some environment variables sent to xsl
		String root = application.getRealPath("/");
		String serv = request.getServerName();
		String port = String.valueOf(request.getServerPort());
		String host = "http://" + serv + ":" + port;
		String ua = request.getHeader("User-Agent");
		String brow = "browser"
				+ (ua.indexOf("MSIE") != -1 ? "IE" : (ua.indexOf("Firefox") != -1 ? "FF"
						: (ua.indexOf("WebKit") != -1 ? "WK" : "Oth")));
		String serverType = System.getProperty("server.type");
		String requestU = pageContext.getAttribute("requestURL", PageContext.SESSION_SCOPE).toString();
		String requestParams = requestU.substring(requestU.indexOf("?"));
		String action = requestU.replaceAll(".+\\/([\\w]+)\\.do\\?.+", "$1");
		pageContext.setAttribute("browserType", brow, PageContext.SESSION_SCOPE);
		pageContext.setAttribute("requestParams", requestParams, PageContext.SESSION_SCOPE);
		pageContext.setAttribute("root", root, PageContext.SESSION_SCOPE);
		pageContext.setAttribute("host", host, PageContext.SESSION_SCOPE);
		pageContext.setAttribute("serverType", serverType, PageContext.SESSION_SCOPE);
		pageContext.setAttribute("action", action, PageContext.REQUEST_SCOPE);
		pageContext.setAttribute("requestU", requestU, PageContext.REQUEST_SCOPE);
		// get the current year so copyright will be correct
		Calendar cal = new GregorianCalendar();
		// Get the components of the date
		// int era = cal.get(Calendar.ERA); // 0=BC, 1=AD
		int year = cal.get(Calendar.YEAR); // 2002
		// int month = cal.get(Calendar.MONTH); // 0=Jan, 1=Feb, ...
		// int day = cal.get(Calendar.DAY_OF_MONTH); // 1...
		// int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1=Sunday, 2=Monday,
		// ...
		pageContext.setAttribute("year", new Integer(year).toString(), PageContext.SESSION_SCOPE);
		pageContext.setAttribute("remoteAddr", request.getRemoteAddr(), PageContext.SESSION_SCOPE);

	}

}
