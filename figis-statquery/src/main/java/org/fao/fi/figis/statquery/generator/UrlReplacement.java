package org.fao.fi.figis.statquery.generator;

import org.fao.fi.figis.statquery.SQKeyParams;

public class UrlReplacement {

	private String server;

	private String sqsBaseUrl;

	private String ds;
	private String k1;
	private String outtype;
	private String gr_props;
	private String k1v;
	private String k1s;
	private String fid;

	private SQKeyParams keyParams;

	/**
	 * @return the keyParams
	 */
	public SQKeyParams getKeyParams() {
		return keyParams;
	}

	/**
	 * @param keyParams
	 *            the keyParams to set
	 */
	public void setKeyParams(SQKeyParams keyParams) {
		this.keyParams = keyParams;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server
	 *            the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return the sqsBaseUrl
	 */
	public String getSqsBaseUrl() {
		return sqsBaseUrl;
	}

	/**
	 * @param sqsBaseUrl
	 *            the sqsBaseUrl to set
	 */
	public void setSqsBaseUrl(String sqsBaseUrl) {
		this.sqsBaseUrl = sqsBaseUrl;
	}

	/**
	 * @return the ds
	 */
	public String getDs() {
		return ds;
	}

	/**
	 * @param ds
	 *            the ds to set
	 */
	public void setDs(String ds) {
		this.ds = ds;
	}

	/**
	 * @return the k1
	 */
	public String getK1() {
		return k1;
	}

	/**
	 * @param k1
	 *            the k1 to set
	 */
	public void setK1(String k1) {
		this.k1 = k1;
	}

	/**
	 * @return the outtype
	 */
	public String getOuttype() {
		return outtype;
	}

	/**
	 * @param outtype
	 *            the outtype to set
	 */
	public void setOuttype(String outtype) {
		this.outtype = outtype;
	}

	/**
	 * @return the gr_props
	 */
	public String getGr_props() {
		return gr_props;
	}

	/**
	 * @param gr_props
	 *            the gr_props to set
	 */
	public void setGr_props(String gr_props) {
		this.gr_props = gr_props;
	}

	/**
	 * @return the k1v
	 */
	public String getK1v() {
		return k1v;
	}

	/**
	 * @param k1v
	 *            the k1v to set
	 */
	public void setK1v(String k1v) {
		this.k1v = k1v;
	}

	/**
	 * @return the k1s
	 */
	public String getK1s() {
		return k1s;
	}

	/**
	 * @param k1s
	 *            the k1s to set
	 */
	public void setK1s(String k1s) {
		this.k1s = k1s;
	}

	/**
	 * @return the fid
	 */
	public String getFid() {
		return fid;
	}

	/**
	 * @param fid
	 *            the fid to set
	 */
	public void setFid(String fid) {
		this.fid = fid;
	}

}
