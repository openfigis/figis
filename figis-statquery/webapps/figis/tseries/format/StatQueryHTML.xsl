<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
	<xsl:strip-space elements="*"/>
<xsl:import href="../../common/fs_common.xsl"/>
	<!-- ********************************************************************************************-->
	<!-- added by yves for new template mechanism -->
<xsl:include href="../../common/wrapper.xsl"/>
	<!--end added by yves -->
	<!-- ********************************************************************************************-->

	<xsl:variable name="graphlink" select="$content-doc//graphlink"/>
	<xsl:variable name="csvlink" select="$content-doc//csvlink"/>
	<!--  NOTE : these two templates are used together with the matching [topic]_template.html -->
	<!--  document and transformed by org.fao.fi.common.data.utils.FiXslUtils.java           -->
	<!--  to display the result of the XSL formatting applied to the XML data in the layout  -->
	<!--  defined in the [topic]_template.html                                               -->
	<!--  the HEAD tag specific content -->
	<xsl:template name="insert-head">
		<title>
			FIGIS - <xsl:value-of select="$content-doc//queryresult/description"/>
		</title>
		<link rel="stylesheet" type="text/css" href="/fi/figis/tseries/format/tables.css"/>
		<script type="text/JavaScript">
			<xsl:comment>
      self.focus();
      function newWin(url)
	{
	openWindow = window.open(url,'DownloadWindow','status,width=200,height=100');
	}
    //</xsl:comment>
		</script>
		<script type="text/JavaScript" src="/fi/figis/scripts/popup.js"/>
		<!-- defines the context of the page : used for setting the HELP link-->
		<script>
		helpurl = '/fi/figis/help/tseries/helpset.htm';
		helpwindow = 'new';
	</script>
	</xsl:template>
	<!--  the PAGE specific content -->
	<xsl:template name="insert-body-content">
		<xsl:call-template name="queryheader"/>
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<img src="/fi/figis/assets/images/invidot.gif" border="0" alt="" width="10"/>
				</td>
				<td>
					<xsl:apply-templates select="$content-doc//stattable"/>
				</td>
			</tr>
		</table>
	</xsl:template>
	<xsl:template name="queryheader">
				<xsl:variable name="csvlink">javascript:newWin('<xsl:value-of select="$csvlink"/>')</xsl:variable>

		<table border="0" cellspacing="0" cellpadding="0">
			<tr valign="bottom">
				<td>
					<a href="/fi/website/FIRetrieveAction.do?dom=topic&amp;fid=16003" border="0">
						<img src="/fi/figis/assets/images/toolbar_text/query.gif" width="55" height="17" alt="new query" name="newquery" border="0"/>
					</a>&#160;</td>
				<td>
					<a href="{$csvlink}">
						<img src="/fi/figis/assets/images/toolbar_text/export.gif" width="55" height="17" alt="export results in CSV format" border="0" name="export"/>
					</a>&#160;</td>
			</tr>
		</table>
		<!-- end of the toolbar table -->
	</xsl:template>
	<!-- for isnerting the GRAPH button -->
	<!-- adds also a link to the legend explaining the various special symbols used in tables -->
	<xsl:template match="graphlink">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr valign="bottom">
				<td>
					<a href="{.}">
						<img src="/fi/figis/assets/images/toolbar_text/graph.gif" width="55" height="17" alt="display table as a graph" name="graph" border="0"/>
					</a>
				</td>
				<td valign="bottom" class="key">
					<xsl:if test="(//value='...' or //value='F' or //value='&lt;0.5'  or //value='R' or //value='W')">&#160;&#160;&#160;&#160;&#160;&#160;Special values explanation&#160;: <a href="javascript:new_window('/fi/figis/help/tseries/special_.htm','help',1,0,0,0,0,1,1,450,400)">click here</a>.</xsl:if>&#160;
</td>
			</tr>
			<tr>
				<td colspan="2">
					<img src="/fi/figis/assets/images/invidot.gif" border="0" alt="" width="55" height="5"/>
				</td>
			</tr>
		</table>
	</xsl:template>
	<xsl:template match="stattable">
		<h4 class="tabtitle">
			<xsl:value-of select="../name"/> : <xsl:value-of select="series"/>
			<xsl:if test="unit">&#32;(<xsl:value-of select="unit"/>)
</xsl:if>
		</h4>
		<xsl:apply-templates select="graphlink"/>
		<table border="1" cellspacing="0" cellpadding="2" bordercolor="#0099ff">
			<xsl:apply-templates select="tableheader"/>
			<xsl:apply-templates select="tablerow"/>
		</table>
	</xsl:template>
	<xsl:template match="keyhead">
		<th>
			<xsl:value-of select="."/>
		</th>
	</xsl:template>
	<xsl:template match="valuehead">
		<th>
			<xsl:value-of select="."/>
		</th>
	</xsl:template>
	<xsl:template match="tableheader">
		<tr class="colhead">
			<xsl:apply-templates select="keyhead"/>
			<xsl:apply-templates select="valuehead"/>
		</tr>
	</xsl:template>
	<xsl:template match="@rowspan">
		<xsl:attribute name="rowspan"><xsl:value-of select="."/></xsl:attribute>
	</xsl:template>
	<xsl:template match="@colspan">
		<xsl:attribute name="colspan"><xsl:value-of select="."/></xsl:attribute>
	</xsl:template>
	<xsl:template match="key" mode="print">
		<xsl:value-of select="."/>
	</xsl:template>
	<xsl:template match="key[@grouptot and not(@subtotal)]" mode="print">
		<span class="total">Total<xsl:text> </xsl:text>
			<xsl:value-of select="."/>
		</span>
	</xsl:template>
	<xsl:template match="key[@subtotal = '0']" mode="print">
		<span class="grandtotal">Grand total</span>
	</xsl:template>
	<xsl:template match="key[@subtotal = '1']" mode="print">
		<span class="total">Total<xsl:text> </xsl:text>
			<xsl:value-of select="."/>
		</span>
	</xsl:template>
	<xsl:template match="key[@subtotal > '1']" mode="print">
		<span class="subtotal">Sub-total<xsl:text> </xsl:text>
			<xsl:value-of select="."/>
		</span>
	</xsl:template>
	<xsl:template match="key[@sub]" mode="indent">
		<div style="margin-left: {10 * @sub}px">
			<xsl:apply-templates select="." mode="print"/>
		</div>
	</xsl:template>
	<xsl:template match="key" mode="indent">
		<xsl:apply-templates select="." mode="print"/>
	</xsl:template>
	<xsl:template match="key">
		<xsl:element name="td">
			<xsl:attribute name="class">key</xsl:attribute>
			<xsl:apply-templates select="@rowspan"/>
			<xsl:apply-templates select="@colspan"/>
			<xsl:if test="../@header = '1' and not(@rowspan)">
				<xsl:attribute name="class">hdrrow</xsl:attribute>
			</xsl:if>
			<xsl:apply-templates select="." mode="indent"/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="value">
		<td class="val">
			<xsl:value-of select="."/>
		</td>
	</xsl:template>
	<xsl:template match="tablerow[@header='1']">
		<tr>
			<xsl:apply-templates select="key[not(@same = '1')]"/>
			<td class="hdrrow" colspan="{count(value)}"/>
		</tr>
	</xsl:template>
	<xsl:template match="tablerow[@header!='1']">
		<tr>
			<xsl:apply-templates select="key[not(@same = '1')]"/>
			<xsl:apply-templates select="value"/>
		</tr>
	</xsl:template>
</xsl:stylesheet>
