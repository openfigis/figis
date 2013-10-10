<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">

	<xsl:strip-space elements="*"/>
    <xsl:output method="html" indent="no" omit-xml-declaration="yes"/>

<xsl:template match="/">
	<xsl:apply-templates/>
</xsl:template>

<xsl:template match="graphpanel">

<html xmlns:figis="http://www.fao.org/figis">
<head>
<title>FIGIS - Time Series Query Result - Graph </title></head>
<!-- BELOW SHOULD BE -->
 		<!--   below this line the meta tags include command-->
		<!--#INCLUDE VIRTUAL="/fi/figis/meta.inc"-->
		<!-- REPLACED BY ITS CONTENT -->
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<meta name="" content="FIGIS"/>
		<meta name="keywords" content="FAO, F.A.O., fao, f.a.o., FIGIS, figis, fisheries, fisheries global information system, food and agriculture organisation"/>
		<script language="JavaScript" src="/fi/figis/scripts/rollover.js" type="text/javascript"></script>
		<!-- END OF REPLACED BY ITS CONTENT -->
		<!-- END OF BELOW SHOULD BE -->
<script language="JavaScript">

<xsl:comment>
//** the Javascript used for the pop-up window **//
function errorSafe() {return true;}
window.onerror = errorSafe;
var newWindow = null

function newWinReload(url,name,rs,sc,mn,tl,st,lo,wd,hi)
{
// check if window already exists
	if (!newWindow || newWindow.closed) {
		// store new window object in global variable

openWindow = window.open(url,name,'resizable='+rs+',scrollbars='+sc+',menubar='+mn+',toolbar='+tl+',status,+st+,location='+lo+',width='+wd+',height='+hi);

openWindow.location.reload(true);}
	else {
	// window already exists, so bring it forward
	newWindow.focus()
	}

}
//</xsl:comment>

</script>

<!-- pop-up window definition -->
<SCRIPT LANGUAGE="JavaScript">

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

<script language="JavaScript">
<xsl:comment>
var wd = 630;
var hi = 440;
var refr = "yes";
window.name="fig_stat"
//</xsl:comment>
</script>

<!-- BELOW SHOULD BE -->
<!-- Place  below this line the top include command-->
<!--#INCLUDE VIRTUAL="/fi/figis/topstats.inc"-->
<!-- REPLACED BY ITS CONTENT -->

<body bgcolor="#FFFFFF" text="#000000" link="#0000FF" alink="#FF0000" vlink="#FF0000" onLoad="MM_preloadImages('/fi/figis/assets/images/sb_sp_on.gif','/fi/figis/assets/images/sb_sr_on.gif','/fi/figis/assets/images/sb_mf_on.gif','/fi/figis/assets/images/sb_fv_on.gif','/fi/figis/assets/images/tb_fh_on.gif','/fi/figis/assets/images/tb_s_on.gif','/fi/figis/assets/images/tb_r_on.gif','/fi/figis/assets/images/tb_r_on.gif','/fi/figis/assets/images/tb_g_on.gif','/fi/figis/assets/images/tb_p_on.gif','/fi/figis/assets/images/tb_st_on.gif','/fi/figis/assets/images/tb_l_on.gif','/fi/figis/assets/images/sb_la_on.gif','/fi/figis/assets/images/sb_fa_on.gif','/fi/figis/assets/images/sb_fi_on.gif','/fi/figis/assets/images/sb_cous_on.gif')">
<table border="0"  cellspacing="0" cellpadding="0" width="750">
  <tr>
<td colspan="3" align="left"><img src="/fi/figis/assets/images/h_left.gif" alt="Figis logo" border="0" width="250" height="59"/><img src="/fi/figis/assets/images/h_mid.gif" alt="Figis logo" border="0" width="300" height="59"/><img src="/fi/figis/assets/images/h_right.gif" alt="Figis logo" border="0" width="200" height="59"/><br/><img src="/fi/figis/assets/images/olio.gif" width="10" height="2"/></td>
     </tr>
     <tr>   
     <td width="750" colspan="3"> 
       <table border="0"  bordercolor="white" cellspacing="0" cellpadding="0" width="750">
       <tr bgcolor="#333399">
      <td width="200" align="left"><a href="/fi/figis/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('home','','/fi/figis/assets/images/tb_fh_on.gif',1)"><img src="/fi/figis/assets/images/tb_fhome.gif" alt="Figis Home" border="0" width="118" height="23" name="home"/></a></td>
      <td width="58" align="left"><a href="/fi/figis/search/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('search','','/fi/figis/assets/images/tb_s_on.gif',1)"><img src="/fi/figis/assets/images/tb_s.gif" alt="Search" border="0" width="38" height="23" name="search"/></a></td>
      <td width="81" align="left"><a href="/fi/figis/refer/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('refs','','/fi/figis/assets/images/tb_r_on.gif',1)"><img src="/fi/figis/assets/images/tb_r.gif" alt="References" border="0" width="61" height="23" name="refs"/></a></td>
      <td width="45" align="left"><a href="/fi/figis/links/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('links','','/fi/figis/assets/images/tb_l_on.gif',1)"><img src="/fi/figis/assets/images/tb_l.gif" alt="Links" border="0" width="25" height="23" name="links"/></a></td>
      <td width="69" align="left"><a href="/fi/figis/tseries/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('stats','','/fi/figis/assets/images/tb_st_on.gif',1)"><img src="/fi/figis/assets/images/tb_st_on.gif" alt="Statistics" border="0" width="49" height="23" name="stats"/></a></td>
      <td width="51" align="left"><a href="/fi/figis/maps/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('maps','','/fi/figis/assets/images/tb_m_on.gif',1)"><img src="/fi/figis/assets/images/tb_m.gif" alt="Maps" border="0" width="31" height="23" name="maps"/></a></td>
      <td width="65" align="left"><a href="/fi/figis/pictures/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('pics','','/fi/figis/assets/images/tb_p_on.gif',1)"><img src="/fi/figis/assets/images/tb_p.gif" alt="Pictures" border="0" width="45" height="23" name="pics"/></a></td>
      <td width="68" align="left"><a href="/fi/figis/glossary/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('gloss','','/fi/figis/assets/images/tb_g_on.gif',1)"><img src="/fi/figis/assets/images/tb_g.gif" alt="Glossary" border="0" width="48" height="23" name="gloss"/></a></td>
      <td width="113" align="right"><a href="/fi/figis/help/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('help','','/fi/figis/assets/images/tb_h_on.gif',1)"><img src="/fi/figis/assets/images/tb_hlp.gif" alt="Help" border="0" width="24" height="23" name="help"/></a></td></tr>
      </table>
     </td>
   </tr>
  <tr>
  <td width="118" valign="top" bgcolor="#cccccc"> 
      <table border="0" cellpadding="0" cellspacing="0" width="118">
       <tr>
      <td align="left" bgcolor="#ffffff"><img src="/fi/figis/assets/images/olio.gif" width="118" height="2"/></td>      
      </tr><tr>
       <td bgcolor="#6699ff" align="left"><img src="/fi/figis/assets/images/sb_top.gif" alt="" width="118" height="31"  border="0"/></td>
      </tr><tr>
      <td align="left" bgcolor="#ffffff"><img src="/fi/figis/assets/images/olio.gif" width="118" height="2"/></td>      
      </tr><tr>
      <td bgcolor="#6699ff" align="left"><a href="/fi/figis/language.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('lang','','/fi/figis/assets/images/sb_la_on.gif',1)"><img src="/fi/figis/assets/images/sb_lang.gif" alt="Language" width="118" height="13" border="0" name="lang"/></a>
      </td>
      </tr><tr>
        <td bgcolor="#6699ff" align="left"><a href="http://www.fao.org" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('fao','','/fi/figis/assets/images/sb_fa_on.gif',1)"><img src="/fi/figis/assets/images/sb_fao.gif" alt="FAO" width="118" height="13" border="0" name="fao"/></a>
       </td>
      </tr><tr>
        <td bgcolor="#6699ff" align="left"><a href="http://www.fao.org/fi" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('fi','','/fi/figis/assets/images/sb_fi_on.gif',1)"><img src="/fi/figis/assets/images/sb_fi.gif" alt="Fisheries" width="118" height="11" border="0" name="fi"/></a>
       </td>
      </tr><tr>
        <td align="left" bgcolor="#ffffff">
          <img src="/fi/figis/assets/images/sb_arrw.gif" alt="" width="118" height="17" border="0"/></td>
      </tr><tr>
      <td bgcolor="#ffffff"><img src="/fi/figis/assets/images/olio.gif" alt="" width="118" height="12"/></td>
      </tr><tr>
      <td bgcolor="#ffffff" valign="top"><a href="/fi/figis/species/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('species','','/fi/figis/assets/images/sb_sp_on.gif',1)">
<img src="/fi/figis/assets/images/sb_sp.gif" alt="aquatic species" width="118" height="27" border="0" 
name="species"/></a></td>
      </tr><tr>
      <td bgcolor="#ffffff"><img src="/fi/figis/assets/images/olio.gif" alt="" width="118" height="12"/></td>
      </tr><tr>
      <td bgcolor="#ffffff">
      <a href="/fi/figis/resrsc/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('resources','','/fi/figis/assets/images/sb_sr_on.gif',1)">
<img src="/fi/figis/assets/images/sb_sr.gif" alt="marine resources" width="118" height="27" border="0" 
name="resources"/></a>
      </td>
      </tr><tr>
      <td bgcolor="#ffffff"><img src="/fi/figis/assets/images/olio.gif" alt="" width="118" height="12"/></td>
      </tr><tr>
      <td bgcolor="#ffffff">
      <a href="/fi/figis/fishery/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('fishery','','/fi/figis/assets/images/sb_mf_on.gif',1)">
<img src="/fi/figis/assets/images/sb_mf.gif" alt="marine fisheries" width="118" height="27" border="0" 
name="fishery"/></a>
	  </td>
      </tr><tr>
      <td bgcolor="#ffffff"><img src="/fi/figis/assets/images/olio.gif" alt="" width="118" height="12"/></td>
      </tr><tr>
      <td bgcolor="#ffffff">
      <a href="/fi/figis/tech/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('tech','','/fi/figis/assets/images/sb_fv_on.gif',1)">
<img src="/fi/figis/assets/images/sb_fv.gif" alt="fishing technologies" width="118" height="27" border="0" 
name="tech"/></a>
	</td>
      </tr><tr>
      <td bgcolor="#ffffff"><img src="/fi/figis/assets/images/olio.gif" alt="" width="118" height="12"/></td>
      </tr><tr>
      <td bgcolor="#ffffff"><img src="/fi/figis/assets/images/sb_cs.gif" alt="coming soon" width="118" height="161"/></td>
      </tr><tr>
      <td bgcolor="#ffffff"><img src="/fi/figis/assets/images/olio.gif" alt="" width="118" height="5"/></td>
     </tr><tr>
      <td bgcolor="#ffffff"><img src="/fi/figis/assets/images/bobo.gif" alt="" width="118" height="5"/></td>
     </tr><tr>
     <td bgcolor="#cccccc"><img src="/fi/figis/assets/images/olio.gif" alt="" width="118" height="12"/></td>
     </tr><tr>
     <td bgcolor="#cccccc">
      <img src="/fi/figis/assets/images/sb_log.gif" alt="advanced users'logon" width="118" height="29"/></td>
     </tr><tr>
           <td valign="top" bgcolor="#CCCCCC">           <center>    
           <form action="http://">         
              <font face="Arial" size="-2"><b>Username:</b></font>
               <br/><input type="text" name="name" size="6" maxlength="10"/>
               <br/>
              <font face="Arial,Arial,Helvetica,sans-serif" size="-2"><b>Password:</b></font>
               <br/><input type="password" name="password" size="6" maxlength="10"/>
               <br/>
              <input type="submit" value="Login"/>
           </form>
           </center>
         </td>
        </tr>
        <tr>
        <td bgcolor="#cccccc"> </td>
        </tr><tr>
        <td bgcolor="#cccccc">
        <img src="/fi/figis/assets/images/sb_map.gif" alt="site map" width="118" height="13" border="0"/></td>
        </tr><tr>
       <td bgcolor="#cccccc"><img src="/fi/figis/assets/images/olio.gif" alt="" width="118" height="12"/></td>
        </tr><tr>
        <td bgcolor="#cccccc">
        <a href="/fi/figis/contact/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('contact','','/fi/figis/assets/images/sb_cous_on.gif',1)">
<img src="/fi/figis/assets/images/sb_cous.gif" alt="contact us" width="118" height="11" border="0" 
name="contact"/></a></td>
        </tr>
      </table>
   
   </td>
    <!-- vertical separator -->
   <td><img src="/fi/figis/assets/images/invidot.gif" width="10" height="1" border="0" alt=""/></td>
    <!-- end vertical separator -->
 <td valign="top"  width="622">
<!-- here above the top blue banner template -->
<!-- END OF REPLACED BY ITS CONTENT -->
<!-- END OF BELOW SHOULD BE -->


<!-- main body -->
<xsl:variable name="modservlet" select="modservlet"/>
<xsl:variable name="datfilename" select="datfilename"/>
<xsl:variable name="fileservlet" select="fileservlet"/>
<xsl:variable name="giffilename" select="giffilename"/>
<xsl:variable name="notready" select="notready"/>
<xsl:if test="string-length($notready)=0">
<p><br/><a HREF="javascript:newWinReload('{$modservlet}?datfilename={$datfilename}','Graph_Properties',rs,sc,mn,tl,st,lo,wd,hi)"><img src="/fi/figis/assets/images/toolbar_text/editgrph.gif" width="70" height="17" border="0" alt="Edit graph"/></a></p>
<img src="{$fileservlet}?f={$giffilename}" border="0"/>
</xsl:if>
<xsl:if test="string-length($notready)!=0">
<br/><br/><h3><p align="center">
Graph is being prepared - try <strong>Refresh</strong> in a few moments
</p></h3>
</xsl:if>

<xsl:value-of select="text"/>
<!-- end of main body -->
<!-- BELOW SHOULD BE -->
 <!--   below this line the meta tags include command-->
<!--#INCLUDE VIRTUAL="/fi/figis/bottom.inc"-->
<!-- REPLACED BY ITS CONTENT -->
</td></tr>
<tr>
	<td align="left" colspan="3" valign="bottom"><img src="/fi/figis/assets/images/f_all.gif" alt="bottom banner" width="750" height="15"/></td>
</tr>
</table>
<p></p>
</body></html>
<!-- END OF REPLACED BY ITS CONTENT -->
<!-- END OF BELOW SHOULD BE -->

</xsl:template>
<xsl:template match="modservlet"/>
<xsl:template match="datfilename"/>
<xsl:template match="fileservlet"/>
<xsl:template match="giffilename"/>
<xsl:template match="notready"/>
</xsl:stylesheet>
