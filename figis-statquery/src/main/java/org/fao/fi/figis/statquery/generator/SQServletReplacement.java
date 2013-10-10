package org.fao.fi.figis.statquery.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.fao.fi.figis.statquery.SQFactory;
import org.fao.fi.figis.statquery.SQKeyParams;
import org.fao.fi.figis.statquery.SQParams;
import org.fao.fi.figis.statquery.SQProcessor;
import org.fao.fi.figis.statquery.SQServlet;
import org.fao.fi.figis.statquery.SQTableConvertor;
import org.fao.fi.figis.util.chart.ChartBuilder;
import org.fao.fi.figis.util.common.FAOContextFactory;
import org.fao.fi.figis.util.common.FAOException;
import org.fao.fi.figis.util.common.FAORuntime;
import org.fao.fi.figis.util.common.FigisXmlProperties;
import org.fao.fi.figis.util.context.FAOUser;
import org.fao.fi.figis.util.data.FiConstants;
import org.fao.fi.figis.util.xml.XmlWriter;
import org.fao.fi.figis.util.xsl.XslProcAccess;

import com.klg.jclass.chart.JCChart;

//public class SQServletReplacement extends FAOHttpServlet {
public class SQServletReplacement {

	UrlReplacement urlReplacement;

	File gifDir;

	private static final int CONST_3 = 3;

	/** Default class logger */
	private static Logger mLogger = Logger.getLogger(SQServlet.class);

	// ChartServlet relative path
	private static final String CHART_SERVLET = FigisXmlProperties.getString("ServletBase", FiConstants.SERVLET)
			+ "/ChartServlet";

	// ChartModServlet relative path
	private static final String CHART_MOD_SERVLET = FigisXmlProperties.getString("ServletBase", FiConstants.SERVLET)
			+ "/ChartModServlet";

	// ServerFileServlet relative path
	private static final String FILE_SERVLET = FigisXmlProperties.getString("ServletBase", FiConstants.SERVLET)
			+ "/ServerFileServlet";

	// MissingGif file relative path
	private static final String MISSING_GIF = FigisXmlProperties.getString("MissingGif",
			FiConstants.resolvePath("webapps/figis/assets/images/missing/missing_graph.gif"));

	private int mFlags = // SQTableConvertor.TAB_ALLSERIES|
	SQTableConvertor.TAB_HEADERS | SQTableConvertor.TAB_HEADERS_OF_ONE | SQTableConvertor.TAB_TOTALS
			| SQTableConvertor.TAB_SKIP_NULL_SERIES;

	// Replacement intervention
	// private String timestamp;
	private String timestamp = "ErikToday";

	private SQParams readParams(HttpServletRequest req) throws FAOException {
		SQParams p = new SQParams();
		p.setShowFlags(true);
		p.setShowUnits(true);
		p.fromURL(req);
		return p;
	}

	private void writeParams(SQParams p, String paramFile) throws IOException {
		XmlWriter paramsWriter = new XmlWriter(new FileWriter(paramFile));
		paramsWriter.writeHead();
		p.toXml(paramsWriter);
		paramsWriter.close();
	}

	public void addTempFile(String file) throws FAOException {
		addTempFile(new File(file));
	}

	public void addTempFile(File file) throws FAOException {

		// Replacement intervention
		// getClientContext().getOrCreateSession("SQServlet").addTempFile(file);
	}

	/**
	 * Overrides the getContentType() function Returns the MIME type of the body
	 * of the request, or null if the type is not known. For HTTP servlets, same
	 * as the value of the CGI variable CONTENT_TYPE.
	 * 
	 * @param outtype
	 *            out type
	 * 
	 * @return the content type
	 */
	public String getContentType(String outtype) {
		try {
			String result = SQFactory.getProperties().getString(
					"SQServlet/OutTypes/" + outtype.toLowerCase() + "/outtype", null);
			if (result != null) {
				return result;
			}
		} catch (Exception e) {
			mLogger.error("ContentType error: ", e);
		}
		return "text/html";
	}

	/**
	 * Checke the validity of a map request
	 * 
	 * @param req
	 *            the servlet request
	 * @param p
	 *            the params set of the query
	 * @param w
	 *            an XML writer to write on
	 * 
	 * @throws Exception
	 *             any possible exception
	 */
	protected void checkMap(HttpServletRequest req, SQParams p, XmlWriter w) throws Exception {
		if (!p.getDatasetName().equals("TunaAtlas")) {
			return;
		}
		int k;
		int cols = 0;
		for (k = 0; k < p.getKeyCount(); k++) {
			SQKeyParams key = p.getKey(k);
			if (key.getKeyName().equals("OCEAN_AREA") && (key.getHierID() == 0)) {
				StringBuffer mapLink = HttpUtils.getRequestURL(req);
				mapLink.append("?outtype=map");
				w.openTag("Map");
				w.writeTag("Link", mapLink.toString());
				w.writeTag("Key", "" + k);
				w.writeTag("Column", "" + (cols + 1));
				w.closeTag();
				return;
			} else {
				cols += key.getDisplayedAttributes().length;
			}
		}
	}

	/**
	 * This function produces phisically the result, storing an Xml file in a
	 * pre defined directory
	 * 
	 * @param req
	 *            the <code>HttpServletRequest</code> that has executed th call
	 * @param tempFile
	 *            The name of the tempfile, without any extension
	 * 
	 * @return name of the result file
	 * @throws Exception
	 */
	protected String execQueryReplacement(UrlReplacement req, String tempFile) throws Exception {
		SQParams p = new SQParams();
		p.setShowFlags(true);
		p.setShowUnits(true);
		p.setDatasetName(req.getDs());
		p.addKey(req.getKeyParams());

		String paramFile = tempFile + "_param.xml";
		mLogger.info("writing parameters file: " + paramFile);
		writeParams(p, paramFile);
		addTempFile(paramFile);
		String xmlFile = tempFile + "_res.xml";
		mLogger.info("writing xml file: " + xmlFile);
		SQProcessor proc = new SQProcessor(p);
		proc.execQuery(null);
		SQTableConvertor c = proc.getConvertor();
		XmlWriter w = new XmlWriter(new PrintWriter(xmlFile, FiConstants.getEncoding()));
		w.writeHead();
		w.openTagWithAttributes("SQServlet");

		// Replacement intervention
		// int aidx1 = tempFile.indexOf(getCurrentUser());
		int aidx1 = 0;

		String client_uid = tempFile.substring(aidx1);
		w.writeAttribute("client_uid", client_uid);

		FAOUser user;
		Date now = new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		user = FAOContextFactory.getInstance().getCurrentUser();
		w.writeAttribute("user", user.getName());

		w.writeAttribute("today", date.format(now));
		w.writeAttribute("now", time.format(now));
		// String a_file = req.getParameter("file");
		// if (a_file != null) {
		// int aidx2 = a_file.indexOf(File.separatorChar + "temp" +
		// File.separatorChar) + 6;
		// String xp_tracker = a_file.substring(aidx2);
		// w.writeAttribute("xp_tracker", xp_tracker);
		// }
		w.endAttributes();

		// Replacement intervention
		// w.writeTag("Servlet", getBaseURL(req));

		// checkMap(req, p, w);
		int tabFlags = mFlags;
		// if ("1".equals(getParam(req, "joinser"))) {
		// tabFlags |= SQTableConvertor.TAB_ALLSERIES;
		// }
		// if ("1".equals(getParam(req, "alltotals"))) {
		// tabFlags |= SQTableConvertor.TAB_TOTALS_OF_ONE;
		// }
		c.makeTables(tabFlags, FAOContextFactory.getCurrentLanguage(), w);
		w.closeTag();
		w.close();
		return xmlFile;
	}

	/**
	 * This function produces phisically the result, storing an Xml file in a
	 * pre defined directory
	 * 
	 * @param req
	 *            the <code>HttpServletRequest</code> that has executed th call
	 * @param tempFile
	 *            The name of the tempfile, without any extension
	 * 
	 * @return name of the result file
	 * @throws Exception
	 */
	protected String execQuery(HttpServletRequest req, String tempFile) throws Exception {
		SQParams p = readParams(req);
		String paramFile = tempFile + "_param.xml";
		mLogger.info("writing parameters file: " + paramFile);
		writeParams(p, paramFile);
		addTempFile(paramFile);
		String xmlFile = tempFile + "_res.xml";
		mLogger.info("writing xml file: " + xmlFile);
		SQProcessor proc = new SQProcessor(p);
		proc.execQuery(null);
		SQTableConvertor c = proc.getConvertor();
		XmlWriter w = new XmlWriter(new PrintWriter(xmlFile, FiConstants.getEncoding()));
		w.writeHead();
		w.openTagWithAttributes("SQServlet");

		// Replacement intervention
		// int aidx1 = tempFile.indexOf(getCurrentUser());
		int aidx1 = 0;

		String client_uid = tempFile.substring(aidx1);
		w.writeAttribute("client_uid", client_uid);

		FAOUser user;
		Date now = new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		user = FAOContextFactory.getInstance().getCurrentUser();
		w.writeAttribute("user", user.getName());

		// Replacement intervention
		// w.writeAttribute("host", mContext.getHostString());

		w.writeAttribute("today", date.format(now));
		w.writeAttribute("now", time.format(now));
		String a_file = req.getParameter("file");
		if (a_file != null) {
			int aidx2 = a_file.indexOf(File.separatorChar + "temp" + File.separatorChar) + 6;
			String xp_tracker = a_file.substring(aidx2);
			w.writeAttribute("xp_tracker", xp_tracker);
		}
		w.endAttributes();

		// Replacement intervention
		// w.writeTag("Servlet", getBaseURL(req));

		checkMap(req, p, w);
		int tabFlags = mFlags;
		if ("1".equals(getParam(req, "joinser"))) {
			tabFlags |= SQTableConvertor.TAB_ALLSERIES;
		}
		if ("1".equals(getParam(req, "alltotals"))) {
			tabFlags |= SQTableConvertor.TAB_TOTALS_OF_ONE;
		}
		c.makeTables(tabFlags, FAOContextFactory.getCurrentLanguage(), w);
		w.closeTag();
		w.close();
		return xmlFile;
	}

	/**
	 * return a particular parameter of the servlet invocation (i.e. by the GET
	 * method)
	 * 
	 * @param req
	 *            the current HTTP request
	 * @param pName
	 *            name of the parameter
	 * 
	 * @return value of the parameter
	 */
	public static String getParam(HttpServletRequest req, String pName) {
		return expandParamValue(req.getParameter(pName));
	}

	/**
	 * return a set of values related to particular parameter of the servlet
	 * invocation (i.e. by the GET method)
	 * 
	 * @param req
	 *            the current HTTP request
	 * @param pName
	 *            name of the parameter
	 * 
	 * @return values of the parameter in form of array
	 */
	public static String[] getParamVals(HttpServletRequest req, String pName) {
		String[] pValues = req.getParameterValues(pName);
		if (pValues == null) {
			return null;
		}
		for (int v = 0; v < pValues.length; v++) {
			pValues[v] = expandParamValue(pValues[v]);
		}
		return pValues;
	}

	public String getDefaultXsl(String type) {
		try {
			if ((type == null) || (type.length() == 0)) {
				return null;
			}
			return SQFactory.getProperties().getString("SQServlet/OutTypes/" + type.toLowerCase() + "/XSL", null);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Expands the a particular parameter value
	 * 
	 * @param p
	 *            a param of the query
	 * 
	 * @return the value of the parameter
	 */
	public static String expandParamValue(String p) {
		if (p == null) {
			return p;
		}
		int pos = 0;
		int endPos = 0;
		while (-1 != (pos = p.indexOf('{', pos))) {
			if (-1 == (endPos = p.indexOf('}', pos))) {
				break;
			}
			String macro = p.substring(pos + 1, endPos).toLowerCase();
			try {
				String macroVal = SQFactory.getProperties().getString("MACRO[@id='" + macro + "']", macro);
				p = p.substring(0, pos) + macroVal + p.substring(endPos + 1);
				pos += macroVal.length();
			} catch (Exception e) {
				break;
			}
		}
		return p;
	}

	public String makeGifReplacement(UrlReplacement url, String xmlFile, String tempFile) throws Exception {
		String gifFile;
		mLogger.info("making a GIF graph");
		String datFile = tempFile + ".dat";
		mLogger.info("writing dat file: " + datFile);
		mLogger.info("writing xml file: " + xmlFile);

		// Replacement Intervention
		// Do we really need the xsl?
		// XslProcAccess.processXsl(xmlFile, getDefaultXsl("dat"), datFile,
		// makeXslParams(req, xmlFile));
		// addTempFile(datFile);
		Properties grProps = new Properties();
		grProps.setProperty("gr_props", url.getGr_props());

		// build chart
		ChartBuilder builder = new ChartBuilder(grProps);
		String grPropFile = "properties/gform_small.txt";
		if (!new File(grPropFile).exists()) {
			throw new FigisStatQueryException("file does not exist:" + grPropFile);
		}

		try {
			JCChart chart = builder.drawChart(datFile, false, grPropFile);
			gifFile = tempFile + ".gif";
			addTempFile(gifFile);
			FileOutputStream out = new FileOutputStream(gifFile);
			builder.streamGif(chart, out);
			mLogger.info("GIF graph produced");
			out.close();
		} catch (Exception e) {
			mLogger.error("GIF graph production failed: " + e.getMessage());
			gifFile = MISSING_GIF;
			throw new FigisStatQueryException(e);
		} finally {
			builder.close();
		}
		return gifFile;
	}

	public String makeGif(HttpServletRequest req, String xmlFile, String tempFile) throws Exception {
		String gifFile;
		mLogger.info("making a GIF graph");
		String datFile = tempFile + ".dat";
		mLogger.info("writing dat file: " + datFile);
		mLogger.info("writing xml file: " + xmlFile);

		// Replacement Intervention
		// Do we really need the xsl?
		XslProcAccess.processXsl(xmlFile, getDefaultXsl("dat"), datFile, makeXslParams(req, xmlFile));
		addTempFile(datFile);
		Properties grProps = new Properties();
		for (Enumeration<String> e = new ParameterNames(); e.hasMoreElements();) {
			String pName = (String) e.nextElement();
			if (pName.startsWith("gr_")) {
				grProps.setProperty(pName.substring(CONST_3), getParam(req, pName));
			}
		}

		// build chart
		ChartBuilder builder = new ChartBuilder(grProps);
		String grPropFile = "properties/gform_small.txt";
		if (!new File(grPropFile).exists()) {
			throw new FigisStatQueryException("file does not exist:" + grPropFile);
		}

		try {
			JCChart chart = builder.drawChart(datFile, false, grPropFile);
			gifFile = tempFile + ".gif";
			addTempFile(gifFile);
			FileOutputStream out = new FileOutputStream(gifFile);
			builder.streamGif(chart, out);
			mLogger.info("GIF graph produced");
			out.close();
		} catch (Exception e) {
			mLogger.error("GIF graph production failed: " + e.getMessage());
			gifFile = MISSING_GIF;
			throw new FigisStatQueryException(e);
		} finally {
			builder.close();
		}
		return gifFile;
	}

	public String produceGraphHtml(HttpServletRequest req, String xmlFile, String tempFile) throws Exception {
		mLogger.info("making a HTML graph");
		String gifFile = makeGif(req, xmlFile, tempFile);
		String graphXml = tempFile + "_gr.xml";
		XmlWriter wr = new XmlWriter(new FileWriter(graphXml));
		wr.writeHead();
		wr.openTag("graphpanel");

		// Replacement intervention
		// wr.writeTag("modservlet", mContext.getHostString() +
		// CHART_MOD_SERVLET);

		wr.writeTag("datfilename", (new File(tempFile + ".dat")).getName());

		// Replacement intervention
		// wr.writeTag("fileservlet", mContext.getHostString() + FILE_SERVLET);

		StringBuffer returnUrl = HttpUtils.getRequestURL(req);
		returnUrl.append("?outtype=html");
		for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
			String pName = (String) e.nextElement();
			if (pName.equals("outtype")) {
				continue;
			}
			if (pName.equals("graphfile")) {
				continue;
			}
			if (pName.startsWith("gr_")) {
				continue;
			}
			String[] pValues = req.getParameterValues(pName);
			for (int p = 0; p < pValues.length; p++) {
				returnUrl.append('&').append(pName).append('=').append(pValues[p]);
			}
		}
		wr.writeTag("return", returnUrl.toString());
		wr.writeTag("giffilename", gifFile);
		wr.closeTag();
		wr.close();
		addTempFile(graphXml);
		mLogger.info("HTML graph produced");
		return transform2File(req, graphXml, "graph", tempFile + "_gr.html");
	}

	public Map<String, String> makeXslParams(HttpServletRequest req, String xmlFile) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("xp_xmlfile", xmlFile);
		for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
			String pName = (String) e.nextElement();
			if (pName.startsWith("xp_")) {
				params.put(pName, getParam(req, pName));
			}
		}
		return params;
	}

	public String transform2File(String xml, String xsl, String dest, Map<String, String> params) throws Exception {
		mLogger.info("starting xslt processing: xml=" + xml + ", xsl=" + xsl + ", out=" + dest + ", params=" + params);
		addTempFile(dest);
		XslProcAccess.processXsl(xml, xsl, dest, params);
		mLogger.info("xsl processed");
		return dest;
	}

	public String transform2File(HttpServletRequest req, String xmlFile, String outType, String result)
			throws Exception {
		String xsl = getParam(req, "xsl");
		if (xsl == null) {
			xsl = getDefaultXsl(outType);
		}
		if ((xsl == null) || !new File(xsl).exists()) {
			return xmlFile;
		} else {
			return transform2File(xmlFile, xsl, result, makeXslParams(req, xmlFile));
		}
	}

	public InputStream transform(HttpServletRequest req, String xmlFile, String outType, String tempFile)
			throws Exception {
		return new FileInputStream(transform2File(req, xmlFile, outType, tempFile + "_res"));
	}

	/**
	 * TODO: Document here
	 * 
	 * @param req
	 *            the servlet request
	 * @param resp
	 *            the servlet response
	 * 
	 * @throws IOException
	 *             any possible I/O exception
	 * @throws ServletException
	 *             any possible servlet exception
	 */
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String xmlFile;
		try {
			// Replacement intervention
			// requestInit(req, resp);

			String outType = urlReplacement.getOuttype();
			if ((mLogger.getLevel() != null) && (mLogger.getLevel().equals(Level.DEBUG))) {
				mLogger.debug("service started: IP=" + req.getRemoteAddr() + ", host=" + req.getRemoteHost()
						+ ", user=" + req.getRemoteUser() + ", protocol=" + req.getProtocol() + ", method.........: "
						+ req.getMethod() + ", URL params=" + req.getQueryString() + ", output type=" + outType);
			}
			if (outType == null) {
				outType = "";
			}
			// timestamp = req.getParameter("timestamp");

			// Replacement intervention
			// String tempFile = getTempFileRoot(false).replace('\\', '/');
			String tempFile = this.getGifDir().getPath() + File.separator + this.urlReplacement.getFid();

			if ((null != (xmlFile = getParam(req, "xmlfile"))) && (new File(xmlFile)).exists()) {
				xmlFile = xmlFile.replace('\\', '/');
			} else {
				xmlFile = execQuery(req, tempFile);
			}

			makeGif(req, xmlFile, tempFile);

			// Replacement intervention
			// in = new FileInputStream(gifFile);
			// resp.setContentType(getContentType(outType));
			// FigisUtility.copyStream(in, resp.getOutputStream());
		} catch (Exception e) {
			mLogger.error("SQServlet error: ", e);
		}
	}

	public void serviceReplacement(UrlReplacement url) throws IOException, ServletException {
		String xmlFile;
		try {
			// Replacement intervention
			// requestInit(req, resp);

			String outType = urlReplacement.getOuttype();
			if (outType == null) {
				outType = "";
			}
			String tempFile = this.getGifDir().getPath() + File.separator + this.urlReplacement.getFid();

			xmlFile = execQueryReplacement(url, tempFile);

			makeGifReplacement(url, xmlFile, tempFile);

			// Replacement intervention
			// in = new FileInputStream(gifFile);
			// resp.setContentType(getContentType(outType));
			// FigisUtility.copyStream(in, resp.getOutputStream());
		} catch (Exception e) {
			throw new FigisStatQueryException(e);
		}
	}

	/**
	 * get prefix for the temp file. Application then gets "ownership" of this
	 * prefix and can append extension etc to create several related temp files
	 * 
	 * @param abs
	 *            if <code>true<code> an absolute path is wanted
	 * 
	 * @return the prefix for the temp file
	 * 
	 * @throws FAOException
	 *             any possible exception
	 */
	public String getTempFileRoot(boolean abs) throws FAOException {
		File dir = null;
		try {
			// Replacement intervention
			// String userName = getClientContext().getUser().getName();
			String userName = "ErikReplacement";

			dir = new File(FAORuntime.getTempDir());
			// File tmp = File.createTempFile(userName, ".tmp", dir);
			File tmp = new File(dir, userName + timestamp + ".tmp");
			String tempRoot = tmp.getPath();
			if (abs) {
				tempRoot = tmp.getAbsolutePath();
			}
			tmp.delete();
			int index = tempRoot.lastIndexOf(".");
			tempRoot = tempRoot.substring(0, index);
			return tempRoot;
		} catch (Exception e) {
			mLogger.error("exception caught creating temp dir " + dir + ": ", e);
			throw new FAOException("FileCreateError", e);
		}
	}

	/**
	 * forces the browser to save the file with a particular extension
	 * 
	 * @param resp
	 *            Servlet HttpResponse
	 * @param file
	 *            the file to save
	 * @param type
	 *            extension of the file
	 */
	public void setExportHeaders(HttpServletResponse resp, File file, String type) {
		resp.setContentType("Application/" + type);
		resp.setHeader("Content-Disposition", "attachement; filename=" + file.getName() + ";");
		resp.setHeader("Content-Length", String.valueOf(file.length()));
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", Long.parseLong("0"));
	}

	public void setUrlReplacement(UrlReplacement url) {
		this.urlReplacement = url;

	}

	/**
	 * @return the gifDir
	 */
	public File getGifDir() {
		return gifDir;
	}

	/**
	 * @param gifDir
	 *            the gifDir to set
	 */
	public void setGifDir(File gifDir) {
		this.gifDir = gifDir;
	}

}
