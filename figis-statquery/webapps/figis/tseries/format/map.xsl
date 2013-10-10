<?xml version="1.0" encoding="iso-8859-1"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
<xsl:strip-space elements="*"/>


<!-- includes the templates common to all the fact sheets -->
<xsl:include href="../../common/fs_common.xsl"/>
	<!-- ********************************************************************************************-->
	<!-- added by yves for new template mechanism -->
<xsl:include href="../../common/wrapper.xsl"/>
	<!--end added by yves -->
	<!-- ********************************************************************************************-->

<!-- the pagination templates -->
<xsl:include href="paging.xsl"/>
<!--Identity transformation
<xsl:template match="@*|*"><xsl:copy><xsl:apply-templates select="@*|node()"/>
</xsl:copy>
</xsl:template>-->
<!--Root transformation
<xsl:template match="/"><xsl:apply-templates select="$layout-doc/*"/>
</xsl:template>-->

<xsl:variable name="years" select="//Table[1]/Head/Columns/Column[@type = 'year' or @type='calc']"/>

<xsl:param name="xp_xmlfile"/>
<xsl:param name="xp_dynaset"/>
<xsl:param name="xp_col" select="round(count($years) div 2)"/>

<xsl:param name="xp_maxpage" select="10000"/>
<xsl:param name="xp_zoom" select="1"/>
<xsl:param name="xp_pan" select="''"/>
<xsl:param name="xp_reuse" select="0"/>

<xsl:variable name="MapConfig" select="document('MapConfig.xml')"/>
<xsl:variable name="ZoomLevel" select="$MapConfig//ZoomLevel"/>

<!-- generate parameter string out of xp_p1..xp_p5 parameters -->
<xsl:template name="pageparams">
	<!-- for each xp_p1..xp_p5 - write page number until we find zero -->
	<xsl:if test="$xp_p1 &gt; 0">
		<xsl:text>&amp;xp_p1=</xsl:text>
		<xsl:value-of select="$xp_p1"/>
		<xsl:if test="$xp_p2 &gt; 0">
			<xsl:text>&amp;xp_p2=</xsl:text>
			<xsl:value-of select="$xp_p2"/>
			<xsl:if test="$xp_p3 &gt; 0">
				<xsl:text>&amp;xp_p3=</xsl:text>
				<xsl:value-of select="$xp_p3"/>
				<xsl:if test="$xp_p4 &gt; 0">
					<xsl:text>&amp;xp_p4=</xsl:text>
					<xsl:value-of select="$xp_p4"/>
					<xsl:if test="$xp_p5 &gt; 0">
						<xsl:text>&amp;xp_p5=</xsl:text>
						<xsl:value-of select="$xp_p5"/>
					</xsl:if>
				</xsl:if>
			</xsl:if>
		</xsl:if>
	</xsl:if>
</xsl:template>

<!--  main template - calls the template. We expect template to call
us back, first with mode "heade" and then with mode "body"--> 

<xsl:template name="insert-body-content">
	<xsl:apply-templates select="$content-doc/*"/>
</xsl:template> 


<!-- the HEAD tag specific content-->
<xsl:template name="insert-head">
<title>FIGIS - Time-series query on: <xsl:value-of select="$content-doc//Head/Dataset"/></title>
  <link rel="stylesheet" type="text/css" href="/fi/figis/tseries/format/tables_new.css"/> 
  <script type="text/JavaScript">
    <xsl:comment>
      self.focus();
      function newWin(url)
	{
	openWindow = window.open(url,'DownloadWindow','status,width=200,height=100');
	}
    //</xsl:comment>
  </script>
  <script type="text/JavaScript" src="/fi/figis/scripts/popup.js"></script>
</xsl:template>



<!--  the BODY content - this is called by the template --> 
<xsl:template match="SQServlet">

<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
      <xsl:apply-templates select="Table"/>
    </td>
  </tr>
</table>
</xsl:template>

<!-- useful stuff -->

<!-- URL with server name and source xml file -->
<xsl:variable name="baseurl">
<xsl:value-of select="concat(//Servlet,'?xmlfile=',$xp_xmlfile)"/>
<xsl:call-template name="pageparams"/>
</xsl:variable>

<!-- shortcut to HTML - base URL plus outtype -->
<xsl:variable name="mapurl" select="concat($baseurl,'&amp;outtype=map')"/>
<xsl:variable name="cur_dset" select="concat('&amp;xp_dynaset=',$xp_dynaset)"/>
<xsl:variable name="cur_zoom" select="concat('&amp;xp_zoom=',$xp_zoom)"/>
<xsl:variable name="cur_col" select="concat('&amp;xp_col=',$xp_col)"/>
<xsl:variable name="reuse" select="'&amp;xp_reuse=1'"/>

<!-- shortcut to HTML - base URL plus outtype -->
<xsl:variable name="panlink" select="concat($mapurl,$cur_dset,$cur_col,$cur_zoom,$reuse,'&amp;xp_pan=')"/>

<!-- append selected keys to page title. If we display all pages 
don't append anything. Else go down the tree and print selected keys
-->
<xsl:template match="RowSet" mode="title">
  <!-- level of this rowset (we start with the root that's 0) -->
  <xsl:variable name="level" select="@k + 1"/>
  <!-- current page on this level -->
	<xsl:variable name="page">
		<xsl:call-template name="curpage"/>
	</xsl:variable>

	<!-- if we are below level 0 (within a page), we display key name --> 
	<xsl:if test="$level != 0">
		<font size="-{$level}"> <!--each key in a smaller font -->
			<xsl:value-of select="@name"/>
		</font>
	</xsl:if>

	<!-- if there is current page on this level, go down -->
	<xsl:if test="$page != 0">
		<br/>
		<xsl:apply-templates select="RowSet[position()=$page]" mode="title"/>
	</xsl:if>
</xsl:template>

<!-- generating navigation buttons -->

<!-- if current RowSet does not have child RowSets (last level) -->
<!-- don't do anything -->
<xsl:template match="RowSet[not(RowSet)]" mode="navigation">
</xsl:template>

<xsl:template name="Years">
    <form method="GET" action="{//Servlet}">
       <input type="hidden" name="xmlfile" value="{$xp_xmlfile}"/>
       <input type="hidden" name="outtype" value="map"/>
       <input type="hidden" name="xp_zoom" value="{$xp_zoom}"/>

       <xsl:if test="$xp_p1 &gt;= 0"><input type="hidden" name="xp_p1" value="{$xp_p1}"/></xsl:if>
       <xsl:if test="$xp_p2 &gt;= 0"><input type="hidden" name="xp_p2" value="{$xp_p2}"/></xsl:if>
       <xsl:if test="$xp_p3 &gt;= 0"><input type="hidden" name="xp_p3" value="{$xp_p3}"/></xsl:if>
       <xsl:if test="$xp_p4 &gt;= 0"><input type="hidden" name="xp_p4" value="{$xp_p4}"/></xsl:if>
       <xsl:if test="$xp_p5 &gt;= 0"><input type="hidden" name="xp_p5" value="{$xp_p5}"/></xsl:if>
       <!-- the YEARS selection-->
       <span style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold;">Year : </span>
	  <select name="xp_col" height="1" onchange="javascript:form.submit()">
	     <xsl:for-each select="$years">
	       <option>
                  <xsl:attribute name="value"><xsl:value-of select="position()"/></xsl:attribute>
		  <xsl:if test="number($xp_col) = position()">
		  <xsl:attribute name="selected"></xsl:attribute>
		  </xsl:if>
                  <xsl:value-of select="."/>
               </option>
             </xsl:for-each>
	  </select>
        &#160;<input type="submit" name="refresh" value="ok"/>
    </form>
</xsl:template>

<!-- else display a list of keys on the next level -->
<xsl:template match="RowSet[RowSet]" mode="navigation">
	<xsl:variable name="level" select="@k + 1"/>
	<xsl:variable name="lname" select="ancestor::Table/Head/Columns/Column[@k=$level]"/>
	<xsl:variable name="page"><xsl:call-template name="curpage"/></xsl:variable>

        <xsl:if test="$level &lt; number(//Map/Key)">

  	<tr valign="top">

	<!-- key name -->
	<td class="navkey" align="right">
	<xsl:value-of select="$lname"/>:
	</td>
	
	<!-- list of values -->
	<td class="navlink">
		<form method="GET" action="{//Servlet}">
			<!-- fill in the fields that we already know about: xmlfile and outtype -->
			<input type="hidden" name="xmlfile" value="{$xp_xmlfile}"/>
			<input type="hidden" name="outtype" value="map"/>
			<input type="hidden" name="xp_zoom" value="{$xp_zoom}"/>
			<input type="hidden" name="xp_col" value="{$xp_col}"/>

			<!-- we also know all page numbers on levels ABOVE current -->
			<xsl:if test="$level &gt;= 1"><input type="hidden" name="xp_p1" value="{$xp_p1}"/></xsl:if>
			<xsl:if test="$level &gt;= 2"><input type="hidden" name="xp_p2" value="{$xp_p2}"/></xsl:if>
			<xsl:if test="$level &gt;= 3"><input type="hidden" name="xp_p3" value="{$xp_p3}"/></xsl:if>
			<xsl:if test="$level &gt;= 4"><input type="hidden" name="xp_p4" value="{$xp_p4}"/></xsl:if>
        		<!-- create a dropdown -->
        		<select name="xp_p{$level + 1}" height="1" onchange="javascript:form.submit()">
          		<!-- add an option '0' -->
				<option value="0">
				<xsl:if test="$page = 0">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				All
          			</option>

			<!-- add all other option -->
				<xsl:for-each select="RowSet">
					<option value="{position()}">
	              		<xsl:if test="$page = position()">
	                			<xsl:attribute name="selected">selected</xsl:attribute>
	              		</xsl:if>
	              		<xsl:value-of select="@name"/>
	            			</option> 
				</xsl:for-each>
			</select>
          &#160;<input type="submit" name="refresh" value="ok"/>

           </form>
	</td>
	</tr>

	<!-- if there is current page on this level, we drill down -->
	<xsl:if test="number($page) != 0">
		<xsl:apply-templates select="RowSet[position()=$page]" mode="navigation"/>
	</xsl:if>
        </xsl:if>
</xsl:template>

<!-- links above the table -->
<xsl:template name="tablehead">
<table cellspacing="2" cellpadding="0">
  <tr valign="bottom">
    <td>
      <a HREF="{$baseurl}&amp;outtype=html">
        <img src="/fi/figis/assets/images/toolbar_text/table.gif" width="55" height="17" border="0" alt="Table"/>
      </a>
    </td>
  </tr>
</table>
<!-- end of the toolbar table -->
</xsl:template>

<xsl:template match="Head/Unit">
&#32;(<xsl:value-of select="."/>)
</xsl:template>

<xsl:template match="Table">
  <!-- compose table title of dataset name, series name, unit name
   and (if inside a subpage) names of selected keys -->
  <h4 class="tabtitle">
    <xsl:value-of select="Head/Dataset"/> : <xsl:value-of select="Head/Series"/>
    <xsl:apply-templates select="Head/Unit"/>
    <xsl:apply-templates select="Body/RowSet" mode="title"/>
  </h4>
  <!-- buttons above the table -->
  <xsl:call-template name="tablehead"/>

  <!-- navigation between the pages -->
  <table border="0">
    <tr>
      <td valign="middle">
        <table border="0">
          <xsl:apply-templates select="Body/RowSet" mode="navigation"/>
        </table>
      </td>
      <td valign="middle">
         <xsl:call-template name="Years"/>
      </td>
     </tr>
   </table>  
  <br/>
  <!-- ************************************************** *-->
<!-- the table displaying the MAP and the navigation tools -->
  <table width="620" border="0">
     <tr>
       <!-- the map -->
	   <td align="center">
	      <xsl:variable name="imagesrc">
          <xsl:text>/figis/kimsmaps/TunaMaps/TunaMaps?</xsl:text>
          <xsl:text>&amp;layer=g5&amp;size=500,250</xsl:text>
          <xsl:if test="$xp_reuse = '0'">
            <xsl:value-of select="concat('&amp;dynaset=',$xp_dynaset,'&amp;query=map')"/>
          </xsl:if>
          <xsl:if test="$xp_reuse != '0'">
            <xsl:value-of select="'&amp;query=map'"/>
          </xsl:if>
          <xsl:value-of select="concat('&amp;zoom=',$xp_zoom)"/>
          <xsl:if test="$xp_pan != ''">
            <xsl:text>&amp;pan=</xsl:text>
            <xsl:value-of select="$xp_pan"/>
          </xsl:if>
        </xsl:variable>
       <img src="{$imagesrc}"
        width="500"
        alt="Please wait. Generating map"
        title="Please wait. Generating map"/>
      </td><td align="center" valign="top" style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold;">TOOLS<br/>
	  <!-- the tools for panning and zooming -->
	  <!-- zooming -->
<table><tr><td align="center" style="font-family: Arial, Helvetica, sans-serif; font-size: 10px; font-weight: bold;">Zoom<br/>
        <xsl:for-each select="$ZoomLevel">
          <xsl:if test="$xp_zoom=@id">
            <img border="0">
			  <xsl:attribute name="src">/fi/figis/maps/assets/images/kims/pix_on.gif</xsl:attribute>
			  <xsl:attribute name="width"><xsl:value-of select="@id*5"/></xsl:attribute>
			   <xsl:attribute name="height"><xsl:value-of select="5"/></xsl:attribute>
			  <xsl:attribute name="alt">zoom <xsl:value-of select="@id"/></xsl:attribute>
			  </img>
          </xsl:if>
          <xsl:if test="$xp_zoom!=@id">
            <a href="{$mapurl}{$cur_dset}{$cur_col}{$reuse}&amp;xp_zoom={@id}">
              <img border="0">
			  <xsl:attribute name="src">/fi/figis/maps/assets/images/kims/pix_off.gif</xsl:attribute>
			  <xsl:attribute name="width"><xsl:value-of select="@id*5"/></xsl:attribute>
			   <xsl:attribute name="height"><xsl:value-of select="5"/></xsl:attribute>
			  <xsl:attribute name="alt">zoom <xsl:value-of select="@id"/></xsl:attribute>
			  </img>
            </a>
          </xsl:if>
          <br/>
        </xsl:for-each></td></tr></table>
		<!-- panning -->
	  <table border="0" cellspacing="0" cellpadding="0">
	  <tr><td colspan="3" align="center" style="font-family: Arial, Helvetica, sans-serif; font-size: 10px; font-weight: bold;">Pan<br/></td></tr>
    <tr>
      <td><a href="{$panlink}NW">
	  <img width="22" height="21" border="0" alt="pan NW" src="/fi/figis/maps/assets/images/kims/pan_nw.gif"/></a></td>
      <td align="center"><a href="{$panlink}N"><img width="22" height="21" border="0" alt="pan N" src="/fi/figis/maps/assets/images/kims/pan_n.gif"/></a></td>
      <td><a href="{$panlink}NE"><img width="22" height="21" border="0" alt="pan NE" src="/fi/figis/maps/assets/images/kims/pan_ne.gif"/></a></td>
    </tr>
    <tr>
      <td valign="middle"><a href="{$panlink}W"><img width="22" height="21" border="0" alt="pan W" src="/fi/figis/maps/assets/images/kims/pan_w.gif"/></a></td>
     <td valign="middle"><img width="22" height="21" border="0" alt="recenter" src="/fi/figis/maps/assets/images/kims/pan_cent.gif"/></td>
      <td valign="middle"><a href="{$panlink}E"><img width="22" height="21" border="0" alt="pan E" src="/fi/figis/maps/assets/images/kims/pan_e.gif"/></a></td>
    </tr>
    <tr>
      <td><a href="{$panlink}SW"><img width="22" height="21" border="0" alt="pan SW" src="/fi/figis/maps/assets/images/kims/pan_sw.gif"/></a></td>
      <td align="center"><a href="{$panlink}&amp;xp_pan=S"><img width="22" height="21" border="0" alt="pan S" src="/fi/figis/maps/assets/images/kims/pan_s.gif"/></a></td>
      <td><a href="{$panlink}SE"><img width="22" height="21" border="0" alt="pan SE" src="/fi/figis/maps/assets/images/kims/pan_se.gif"/></a></td>
    </tr>
  </table>
	  </td>
    </tr>
	<!-- the legend of the map -->
	<tr><td colspan="2"> 
	<span class="navkey">Legend :</span><br/>
	<xsl:variable name="legendsrc">
          <xsl:text>/figis/kimsmaps/TunaMaps/TunaMaps?</xsl:text>
          <xsl:text>&amp;layer=g5</xsl:text>
          <xsl:if test="$xp_reuse = '0'">
            <xsl:value-of select="concat('&amp;dynaset=',$xp_dynaset,'&amp;query=legend')"/>
          </xsl:if>
          <xsl:if test="$xp_reuse != '0'">
            <xsl:value-of select="'&amp;query=legend'"/>
          </xsl:if>
          <xsl:value-of select="concat('&amp;zoom=',$xp_zoom)"/>
          <xsl:if test="$xp_pan != ''">
            <xsl:text>&amp;pan=</xsl:text>
            <xsl:value-of select="$xp_pan"/>
          </xsl:if>
        </xsl:variable>
       <img src="{$legendsrc}"
        alt="Please wait. Generating legend"
        title="Please wait. Generating legend"/></td></tr>
  </table>
  </xsl:template>

</xsl:stylesheet>
