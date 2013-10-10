<?xml version="1.0" encoding="iso-8859-1"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
<xsl:strip-space elements="*"/>
<xsl:output method="html" indent="no" omit-xml-declaration="yes" encoding="UTF-8"/>

<xsl:include href="templateHTML.xsl"/>
<xsl:include href="paging.xsl"/>

<xsl:param name="xp_xmlfile"/>

<xsl:param name="xp_maxpage" select="10000"/>

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
us back, first with mode "heade" and then with mode "body" --> 
<xsl:template match="SQServlet">
	<xsl:call-template name="html"/>
</xsl:template>


<!--  the HEAD content - this is called by the template --> 
<xsl:template match="SQServlet" mode="head">
  <title>FIGIS - <xsl:value-of select="Head/Dataset"/></title>
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
<xsl:template match="SQServlet" mode="body">
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td rowspan="3">
      <img src="/fi/figis/assets/images/invidot.gif" border="0" alt="" width="10"/></td>
    <td class="key">
      <xsl:call-template name="pagehead"/>
    </td>
  </tr>
  <tr>
    <td>
      <img src="/fi/figis/assets/images/invidot.gif" border="0" alt="" height="10" width="10"/>
    </td>
  </tr>
  <tr>
    <td>
      <xsl:apply-templates select="Table"/>
    </td>
  </tr>
</table>
</xsl:template>

<!-- useful stuff -->

<!-- URL with server name and source xml file -->
<xsl:variable name="baseurl" select="concat(//Servlet,'?xmlfile=',$xp_xmlfile)"/>

<!-- link to CSV page - all current page options -->
<xsl:variable name="csvlink">
<xsl:value-of select="concat($baseurl,'&amp;outtype=csv&amp;ftp=1')"/>
<xsl:call-template name="pageparams"/>
</xsl:variable>

<!-- link to graph generation - all current page options -->
<xsl:variable name="graphlink">
<xsl:value-of select="concat($baseurl,'&amp;outtype=graph')"/>
<xsl:call-template name="pageparams"/>
</xsl:variable>

<!-- shortcut to HTML - base URL plus outtype -->
<xsl:variable name="htmlcall" select="concat($baseurl,'&amp;outtype=html')"/>

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

<!-- else display a list of keys on the next level -->
<xsl:template match="RowSet[RowSet]" mode="navigation">
	<xsl:variable name="level" select="@k + 1"/>
	<xsl:variable name="lname" select="ancestor::Table/Head/Columns/Column[@k=$level]"/>
	<xsl:variable name="page"><xsl:call-template name="curpage"/></xsl:variable>

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
			<input type="hidden" name="outtype" value="html"/>

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
        		<input type="submit" name="refresh" value="ok"/>
		</form>
	</td>
	</tr>

	<!-- if there is current page on this level, we drill down -->
	<xsl:if test="number($page) != 0">
		<xsl:apply-templates select="RowSet[position()=$page]" mode="navigation"/>
	</xsl:if>

</xsl:template>

<!-- links above the table -->
<xsl:template name="tablehead">
<table cellspacing="2" cellpadding="0">
  <tr valign="bottom">
    <td>
      <a href="/fi/website/FIRetrieveAction.do?dom=topic&amp;fid=16003" border="0">
        <img src="/fi/figis/assets/images/toolbar_text/query.gif" 
         width="55" height="17" alt="new query" 
         name="newquery" border="0"/></a>
    </td>
    <td>
      <a href="{$csvlink}&amp;xp_tab={position()}">
        <img src="/fi/figis/assets/images/toolbar_text/export.gif" 
         width="55" height="17" alt="export results in CSV format" 
         border="0" name="export"/></a>
    </td>
    <td>
      <a href="{$graphlink}&amp;xp_tab={position()}">
        <img src="/fi/figis/assets/images/toolbar_text/graph.gif" 
         width="55" height="17" alt="display table as a graph" 
         name="graph" border="0"/>
      </a>
    </td>
  </tr>
</table>
<!-- end of the toolbar table -->
</xsl:template>

<!-- adds a link to the legend explaining the various special symbols used in tables -->
<xsl:template name="pagehead">
    <xsl:if test="//V[@s!='']">
      Special values explanation&#160;: 
      <a href="javascript:new_window('/fi/figis/help/tseries/special_.htm',
        'help',1,0,0,0,0,1,1,450,400)">click here</a>.
    </xsl:if>
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
    <xsl:apply-templates select="Body/RowSet" mode="navigation"/>
  </table>

  <table border="1" cellspacing="0" cellpadding="2" bordercolor="#0099ff">
    <!-- table head -->
    <xsl:apply-templates select="Head"/>
    <!-- table body-->
    <xsl:apply-templates select="Body"/>
  </table>
</xsl:template>

<!-- column header -->
<xsl:template match="Column[@type='key']">
	<xsl:variable name="page">
	<xsl:call-template name="curpage">
		<xsl:with-param name="level" select="@k+1"/>
	</xsl:call-template>
	</xsl:variable>
	<xsl:if test="number($page) = 0">
		<th><xsl:value-of select="."/></th>
	</xsl:if>
</xsl:template>

<xsl:template match="Column">
	<th><xsl:value-of select="."/></th>
</xsl:template>


<!-- table head: columns -->
<xsl:template match="Head">
  <tr class="colhead">
    <xsl:apply-templates select="Columns/Column"/>
  </tr>
</xsl:template>

<!-- table body - we select a rowset according to current page 
 (root rowset if there is no current page 
 selectrows template is in paging.xsl
-->
<xsl:template match="Body">
  <xsl:apply-templates select="RowSet" mode="selectrows"/>
</xsl:template>

<!-- this is where the rows are actually written -->
<xsl:template match="RowSet" mode="displayrows">
  <xsl:apply-templates select=".//Row"/>
</xsl:template>

<!-- rowspan -->
<xsl:template match="@rs">
<xsl:attribute name="rowspan"><xsl:value-of select="."/></xsl:attribute>
</xsl:template>


<!-- print a key value -->
<!-- depending whether it's total, header, etc -->
<xsl:template match="Key" mode="print">
<xsl:value-of select="."/>
</xsl:template>

<xsl:template match="Row" mode="totalword">
<xsl:choose>
	<xsl:when test="@tot > '1'">Sub-total </xsl:when>
	<xsl:when test="@tot = '1'">Total </xsl:when>
	<xsl:when test="@tot = '0'">Grand total</xsl:when>
	<!--<xsl:when test="Key/@grouptot">Total </xsl:when>-->
</xsl:choose>
</xsl:template>

<!-- templates for indenting a sub-key (e.g species within a group) 
 called from template without mode and 
 in turn calls template with mode "print" -->
<xsl:template match="Key[@sub]" mode="indent">
<div style="margin-left: {10 * @sub}px">
<xsl:apply-templates select="." mode="print"/>
</div>
</xsl:template>

<!-- template for indenting a regular key (does not indent) -->
<xsl:template match="Key" mode="indent">
<xsl:apply-templates select="." mode="print"/>
</xsl:template>

<xsl:template match="Key[count(@num)=0]" mode="rowspan">
1
</xsl:template>

<xsl:template match="Key[count(@num)!=0]" mode="rowspan">
	<xsl:variable name="num" select="number(@num)"/>
	<xsl:variable name="page">
		<xsl:call-template name="curpage">
			<xsl:with-param name="level" select="$num + 1"/>
		</xsl:call-template>
	</xsl:variable>
	<xsl:choose>
		<xsl:when test="number($page) != 0">0</xsl:when>
		<xsl:when test="following-sibling::EmptyKey[@num &gt; $num] and not(following-sibling::Key[@num &gt; $num])">0</xsl:when>
    		<xsl:when test="@rs">
			<xsl:value-of select="@rs"/>
		</xsl:when>
  		<xsl:otherwise>1</xsl:otherwise>
  	</xsl:choose>
</xsl:template>

<!-- main template for key. Creates a td element and then calls 
 templates for indenting and printing
-->
<xsl:template match="Key">
<xsl:variable name="num" select="number(@num)"/>
<xsl:variable name="rs">
	<xsl:apply-templates select="." mode="rowspan"/>
</xsl:variable>
<xsl:variable name="class">
	<xsl:apply-templates select="." mode="class"/>
</xsl:variable>
<xsl:if test="number($rs) != 0">
	<xsl:element name="td">
		<xsl:attribute name="rowspan"><xsl:value-of select="$rs"/></xsl:attribute>
		<xsl:attribute name="class"><xsl:value-of select="$class"/></xsl:attribute>
	    	<xsl:if test="local-name(following-sibling::*[1])='EmptyKey'">
<!--	     		<xsl:attribute name="colspan">
	       		<xsl:value-of select="1 + count(following-sibling::EmptyKey)"/>
	     		</xsl:attribute>-->
		</xsl:if>
	  	<xsl:apply-templates select="." mode="indent"/>
	</xsl:element>
</xsl:if>
</xsl:template>


<xsl:template match="EmptyKey[not(preceding-sibling::EmptyKey)]">
	<xsl:variable name="num" select="number(@num)"/>
	<xsl:variable name="class">
		<xsl:apply-templates select="." mode="class"/>
	</xsl:variable>
	<xsl:variable name="page">
		<xsl:call-template name="curpage">
			<xsl:with-param name="level" select="$num"/>
		</xsl:call-template>
	</xsl:variable>
	<xsl:variable name="Keys" select="preceding-sibling::Key"/>
	<xsl:variable name="LastKeys" select="$Keys[@num=$num - 1]"/>
	<xsl:variable name="EmptyKeys" select="following-sibling::EmptyKey"/>
	<xsl:variable name="LastKeyCols">
		<xsl:if test="$page = 0"><xsl:value-of select="count($LastKeys)"/></xsl:if>
		<xsl:if test="$page != 0">0</xsl:if>
	</xsl:variable>
	<td class="{$class}" colspan="{1 + number($LastKeyCols) + count($EmptyKeys)}">
		<xsl:apply-templates select=".." mode="totalword"/>
		<xsl:for-each select="$LastKeys">
			<xsl:if test="position()=2">(</xsl:if>
			<xsl:if test="position() &gt; 2">, </xsl:if>
			<xsl:apply-templates select="." mode="print"/>
			<xsl:if test="position()=last() and position()!=1">)</xsl:if>
		</xsl:for-each>
	</td>
</xsl:template>

<!-- print the key that's the same as in the previous row
  does nothing. Does not even create a <td> since previous row
  has created rowspan attribute
-->
<!--<xsl:template match="Key[@tot and @num=preceding-sibling::Key/@num[1]]"></xsl:template>-->

<!-- print the key that's the same as in the previous row
  does nothing. Does not even create a <td> since previous row
  has created rowspan attribute
-->
<xsl:template match="Key[@same]"></xsl:template>

<!-- print value -->
<!-- to do: optionally turn the symbols off/on through a parameter -->
<xsl:template match="V">
<td><xsl:value-of select="."/>
<xsl:if test="@s"><i><xsl:text> </xsl:text><xsl:value-of select="@s"/></i></xsl:if>
</td>
</xsl:template>

<!-- create a header row: gray background, no data -->
<xsl:template match="Row[@header='1']">
<tr class="data">
    <xsl:apply-templates select="Key|EmptyKey"/>
    <td colspan="{count(V)}" class="hdr">&#160;</td>
</tr>
</xsl:template>

<!-- create a regular row -->
<xsl:template match="Row[@header!='1']">
	<tr class="data">
		<xsl:apply-templates select="Key|EmptyKey"/>
		<xsl:apply-templates select="V"/>
	</tr>
</xsl:template>

<!-- row keys. Main purpose is to create a span with appropriate style -->
<xsl:template match="Key|EmptyKey" mode="class">
	<xsl:if test="(not(@rs) and number(../@header))">hdr</xsl:if>
	<xsl:if test="@rs or number(../@header)=0">
		<xsl:if test="count(../Key/@grouptot)">hdr</xsl:if>
		<xsl:choose>
			<xsl:when test="not(../@tot)">key</xsl:when>
			<xsl:when test="number(../@tot) = 0">grandtotal</xsl:when>
			<xsl:when test="number(../@tot) = 1">total</xsl:when>
			<xsl:when test="number(../@tot) > 1">subtotal</xsl:when>
			<xsl:otherwise>key</xsl:otherwise>
		</xsl:choose>
	</xsl:if>
</xsl:template>

</xsl:stylesheet>
