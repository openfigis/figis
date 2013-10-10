<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">

	<xsl:strip-space elements="*"/>
    <xsl:output method="html" indent="no" omit-xml-declaration="yes"/>
<xsl:import href="../../common/fs_common.xsl"/>
	<!-- ********************************************************************************************-->
	<!-- added by yves for new template mechanism -->
<xsl:include href="../../common/wrapper.xsl"/>
	<!--end added by yves -->
	<!-- ********************************************************************************************-->

<xsl:template name="insert-head">
<title>FIGIS - Time Series Query Result - Graph</title>
<!-- defines the context of the page : used for setting the HELP link-->
<script>
<!--
helpurl = '/fi/figis/help/tseries/helpset.htm';
helpwindow = 'new';
//-->
</script> 
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

<xsl:template name="insert-body-content">
  <xsl:apply-templates select="$content-doc/graphpanel"/>
</xsl:template>


<xsl:template match="graphpanel">
<!-- main body -->
<xsl:variable name="modservlet" select="modservlet"/>
<xsl:variable name="datfilename" select="datfilename"/>
<xsl:variable name="fileservlet" select="fileservlet"/>
<xsl:variable name="giffilename" select="giffilename"/>
<xsl:variable name="notready" select="notready"/>

<xsl:if test="string-length($notready)=0">
<p>
<br/><a HREF="javascript:newWinReload('{$modservlet}?datfilename={$datfilename}','Graph_Properties',rs,sc,mn,tl,st,lo,wd,hi)"><img src="/fi/figis/assets/images/toolbar_text/editgrph.gif" width="70" height="17" border="0" alt="Edit graph"/></a></p>
<img src="{$fileservlet}?f={$giffilename}" border="0"/>
</xsl:if>

<xsl:if test="string-length($notready)!=0">
<br/><br/><h3><p align="center">
Graph is being prepared - try <strong>Refresh</strong> in a few moments
</p></h3>
</xsl:if>

<xsl:value-of select="text"/>
</xsl:template>

<xsl:template match="modservlet"/>
<xsl:template match="datfilename"/>
<xsl:template match="fileservlet"/>
<xsl:template match="giffilename"/>
<xsl:template match="notready"/>
</xsl:stylesheet>
