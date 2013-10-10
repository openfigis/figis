<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/" xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:date="http://exslt.org/dates-and-times" exclude-result-prefixes="ags date dc fi  fint rdf xsl ">
	<xsl:strip-space elements="*"/>
	<xsl:output method="html" indent="no" omit-xml-declaration="yes" encoding="UTF-8"/>
	<!-- includes the templates common to all the fact sheets -->
	<xsl:include href="../../common/fs_common.xsl"/>
	<!-- ********************************************************************************************-->
	<!-- added by yves for new template mechanism -->
	<xsl:include href="../../common/nowrapper.xsl"/>
	<!--end added by yves -->
	<!-- ********************************************************************************************-->
	<!-- Include contains mecanism to display user parameters selection -->
	<xsl:include href="trackerDisplay.xsl"/>
	<xsl:param name="xp_series"/>
	<xsl:param name="xp_xmlfile"/>
	<xsl:param name="xmlfile"/>
	<!--<xsl:variable name="data-doc-path" select="$xp_xmlfile"/>
	<xsl:variable name="data-doc" select="document($data-doc-path)"/>-->
	<xsl:param name="xp_tracker"/>
	<xsl:variable name="ptracker">
		<xsl:if test="$xp_tracker !=''">
			&amp;xp_tracker=<xsl:value-of select="$xp_tracker"/>
		</xsl:if>
	</xsl:variable>
	<xsl:variable name="tracker" select="concat('../../temp/',$xp_tracker)"/>
	<xsl:variable name="trackerName" select="document($tracker)"/>
	<!-- we extract from return value of file containing table and other information -->
	<xsl:variable name="tableFN-after" select="substring-after(//graphpanel/return,'xmlfile=webapps/figis/temp/')"/>
	<xsl:variable name="tableFN-before" select="substring-before($tableFN-after,'_res.xml')"/>
	<xsl:variable name="tableFileName" select="concat('../../temp/',$tableFN-before,'_res.xml')"/>
	<xsl:variable name="tableFile" select="document($tableFileName)"/>
	<xsl:variable name="HeadDataset" select="$tableFile/SQServlet/Table/Head/Dataset"/>
	<xsl:variable name="HeadSeries" select="$tableFile/SQServlet/Table/Head/Series"/>
	<!--  main template - calls the template. We expect template to call
us back, first with mode "heade" and then with mode "body" -->
	<xsl:template name="insert-body-content">
		<xsl:apply-templates select="$content-doc/*"/>
	</xsl:template>
	<!-- the HEAD tag specific content-->
	<xsl:template name="insert-head">
		<!-- doesn't work :-( it is trying to get the data for the titles FROM the previous XML file containing the results table -->
		<title>FIGIS - Time-series query on: <xsl:value-of select="$HeadDataset"/> - <xsl:value-of select="$xp_series"/>
		</title>
		<link rel="stylesheet" type="text/css" href="/fi/figis/tseries/format/tables_new.css"/>
		<script type="text/JavaScript" src="/fi/figis/tseries/scripts/expandHide.js"/>
		<script type="text/JavaScript">
			<xsl:comment>
//** the Javascript used for the pop-up window **//
function errorSafe() {return true;}
window.onerror = errorSafe;
var newWindow = null

function newWinReload(url,name,rs,sc,mn,tl,st,lo,wd,hi)
{
// check if window already exists
if (!newWindow || newWindow.closed) 
  {
  // store new window object in global variable
  openWindow = window.open(url,name,'resizable='+rs+',scrollbars='+sc+',menubar='+mn+',toolbar='+tl+',status,+st+,location='+lo+',width='+wd+',height='+hi);
  openWindow.location.reload(true);
  }
else  // window already exists, so bring it forward
  {
  newWindow.focus()
  }
}
//</xsl:comment>
		</script>
		<!-- pop-up window definition -->
		<SCRIPT type="text/JavaScript">
			<xsl:comment>
    var started = "0";
	var rs = "yes";
	var sc = "yes";
	var mn = "0";
	var tl = "0";
	var st = "yes";
	var lo = "0";
	var wd = "300";
	var hi = "250";
//</xsl:comment>
		</SCRIPT>
		<script type="text/JavaScript">
			<xsl:comment>
var wd = 630;
var hi = 440;
var refr = "yes";
window.name="fig_stat"
//</xsl:comment>
		</script>
	</xsl:template>
	<!--  the BODY content - this is called by the template -->
	<xsl:template match="graphpanel">
		<!-- main body -->
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="sheetitle">Statistical Query Graph for <xsl:value-of select="$HeadDataset"/> - <xsl:value-of select="$xp_series"/>
				</td>
			</tr>
			<tr>
				<td rowspan="3">
					<img src="/fi/figis/assets/images/invidot.gif" border="0" alt="" width="10"/>
				</td>
				<td class="tskey">
					<xsl:call-template name="trackerDisplay"/>
				</td>
			</tr>
		</table>
		<table cellpadding="2">
			<tr>
				<td colspan="2" width="100%" valign="middle" align="center">
					<img src="{fileservlet}?f={giffilename}" border="0"/>
				</td>
			</tr>
		</table>
		<br/>
		© FAO - Fisheries and Aquaculture Information and Statistics Service - 
			<xsl:variable name="myDate">
			<xsl:value-of select="date:date(xs:date?)"/>
		</xsl:variable>
		<xsl:value-of select="concat(substring($myDate,9,2),'/',substring($myDate,6,2), '/',substring($myDate,1,4))"/>
	</xsl:template>
	<xsl:template name="trackerDisplay">
		<table border="0">
			<tr>
				<td width="300" valign="top" align="left">
					<table width="100%" cellspacing="0" cellpadding="2">
						<tr>
							<td class="tracker">
								<b>Parameters selected&#160;</b>
								<a onmouseover="this.style.cursor='hand';" onclick="expandHide('tracker');">
									<img name="tst" alt="Show Selected Parameters" src="/fi/figis/assets/images/addinfo_black.gif"/>
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<xsl:call-template name="trackerDisplay.trackerDisplayContent"/>
							</td>
						</tr>
					</table>
				</td>
				<td width="400" valign="top" align="left">
					<xsl:call-template name="pagehead"/>
				</td>
			</tr>
		</table>
	</xsl:template>
	<xsl:template name="pagehead">
		<table>
			<tr>
				<td>
					<a href="javascript:newWinReload('{modservlet}?datfilename={datfilename}','Graph_Properties',rs,sc,mn,tl,st,lo,wd,hi)" border="0" onmouseover="MM_swapImage('editgrph','','/fi/figis/assets/images/toolbar_text/editgrph_on.gif',1)" onmouseout="MM_swapImgRestore()">
						<img src="/fi/figis/assets/images/toolbar_text/editgrph.gif" width="70" height="19" alt="Edit graph" name="editgrph" border="0"/>
					</a>&#160;&#160;

				<!--
					<a HREF="javascript:newWinReload('{modservlet}?datfilename={datfilename}','Graph_Properties',rs,sc,mn,tl,st,lo,wd,hi)">
						<img src="/fi/figis/assets/images/toolbar_text/editgrph.gif" width="70" height="17" border="0" alt="Edit graph"/>
					</a>-->
				</td>
				<td>
					<a href="{return}" border="0" onmouseover="MM_swapImage('table','','/fi/figis/assets/images/toolbar_text/table_on.gif',1)" onmouseout="MM_swapImgRestore()">
						<img src="/fi/figis/assets/images/toolbar_text/table.gif" width="70" height="19" alt="Back to Table" name="table" border="0"/>
					</a>&#160;&#160;

				<!--
					<a HREF="{return}">
						<img src="/fi/figis/assets/images/toolbar_text/table.gif" width="55" height="17" border="0" alt="Table"/>
					</a>-->
				</td>
			</tr>
		</table>
	</xsl:template>
</xsl:stylesheet>
