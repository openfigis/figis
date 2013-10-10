<?xml version="1.0" encoding="iso-8859-1"?>
<?figis pref-xsl=XALAN_J?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/" xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:date="http://exslt.org/dates-and-times" exclude-result-prefixes="ags dc date fi  fint rdf xsl ">
	<xsl:output method="html" indent="no" omit-xml-declaration="yes" encoding="UTF-8"/>
	<xsl:strip-space elements="*"/>
	<!-- includes the templates common to all the fact sheets -->
	<xsl:include href="../../common/fs_common.xsl"/>
	<!-- ********************************************************************************************-->
	<!-- added by yves for new template mechanism -->
	<xsl:include href="../../common/nowrapper.xsl"/>
	<!--end added by yves -->
	<!-- ********************************************************************************************-->
	<!-- Include contains mecanism to display user parameters selection -->
	<xsl:include href="trackerDisplay.xsl"/>
	<xsl:param name="debug-me">n</xsl:param>
	<!-- the pagination templates -->
	<xsl:include href="paging.xsl"/>
	<xsl:param name="xp_xmlfile"/>
	<xsl:param name="xp_tracker">
		<xsl:value-of select="//SQServlet/@xp_tracker"/>
	</xsl:param>
	<xsl:variable name="ptracker">
		<xsl:if test="$xp_tracker !=''">
			&amp;xp_tracker=<xsl:value-of select="$xp_tracker"/>
		</xsl:if>
	</xsl:variable>
	<xsl:variable name="tracker" select="concat('../../temp/',$xp_tracker)"/>
	<xsl:variable name="trackerName" select="document($tracker)"/>
	<xsl:param name="xp_maxpage" select="10000"/>
	<!-- useful stuff -->
	<!-- URL with server name and source xml file -->
	<xsl:variable name="baseurl" select="concat(/Servlet,'?xmlfile=',$xp_xmlfile)"/>
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
	<!-- link to graph generation - all current page options -->
	<xsl:variable name="maplink">
		<xsl:value-of select="concat($baseurl,'&amp;outtype=map')"/>
		<xsl:call-template name="pageparams"/>
	</xsl:variable>
	<!-- shortcut to HTML - base URL plus outtype -->
	<xsl:variable name="htmlcall" select="concat($baseurl,'&amp;outtype=html')"/>
	<!-- Get first KeyParam name containing tab name to construct link to modify current search -->
	<xsl:variable name="FirstTabName" select="//SQServlet/SQParams/SQKeyParams[1]/@KeyName"/>
	<xsl:decimal-format name="Number" decimal-separator="." grouping-separator="&#160;" NaN="NC"/>
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
	<xsl:template name="insert-body-content">
		<xsl:apply-templates select="$content-doc/*"/>
	</xsl:template>
	<!-- the HEAD tag specific content-->
	<xsl:template name="insert-head">
		<title>FIGIS - Time-series query on: <xsl:value-of select="$content-doc//Head/Dataset"/>
		</title>
		<!--<link rel="stylesheet" type="text/css" href="/fi/figis/tseries/format/tables_new.css"/>-->
		<style type="text/css">@import "/fi/figis/tseries/format/tables_new.css";</style>
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
		<script type="text/JavaScript" src="/fi/figis/tseries/scripts/expandHide.js"/>
	</xsl:template>
	<!--  the BODY content - this is called by the template -->
	<xsl:template match="SQServlet">
		<xsl:variable name="URLSearch">TabSelector?tb_ds=<xsl:value-of select="SQParams/@DatasetName"/>&amp;tb_mode=TABLE</xsl:variable>
		<table border="0" cellspacing="0" cellpadding="0">
			<tr valign="bottom">
				<td colspan="2" align="left">
					<a href="{$URLSearch}&amp;tb_act=ACTION&amp;tb_grp=RESET" border="0" onmouseover="MM_swapImage('newsearch','','/fi/figis/assets/images/toolbar_text/new_on.gif',1)" onmouseout="MM_swapImgRestore()">
						<img src="/fi/figis/assets/images/toolbar_text/new.gif" width="70" height="19" alt="New Search" name="newsearch" border="0"/>
					</a>&#160;&#160;
					<a href="{$URLSearch}&amp;tb_act=SELECT&amp;tb_grp={$FirstTabName}" border="0" onmouseover="MM_swapImage('modifysearch','','/fi/figis/assets/images/toolbar_text/modify_on.gif',1)" onmouseout="MM_swapImgRestore()">
						<img src="/fi/figis/assets/images/toolbar_text/modify.gif" width="70" height="19" alt="Modify Search" name="modifysearch" border="0"/>
					</a>&#160;&#160;

				</td>
			</tr>
			<tr>
				<td colspan="2" class="sheetitle">Statistical Query Results
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<img src="/fi/figis/assets/images/invidot.gif" border="0" alt="" width="10"/>
				</td>
				<td class="tskey">
					<xsl:call-template name="trackerDisplay"/>
				</td>
			</tr>
			<tr>
				<td>
					<xsl:apply-templates select="Table"/>
				</td>
			</tr>
		</table>
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
		<font class="RowSetTitle">
			<xsl:value-of select="@name"/>
		</font>
		<!-- if there is current page on this level, go down -->
		<xsl:if test="$page != 0">
			<font class="RowSetTitle">&gt;</font>
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
		<xsl:variable name="page">
			<xsl:call-template name="curpage"/>
		</xsl:variable>
		<tr valign="bottom">
			<!-- key name -->
			<td class="tsnavkey" align="right" valign="bottom">
				Display <xsl:value-of select="$lname"/>:&#160;&#160;
			</td>
			<!-- list of values -->
			<td class="tsnavlink" valign="bottom" bgcolor="#CCCCCC">
				<form method="GET" action="{//Servlet}" class="select">
					<!-- fill in the fields that we already know about: xmlfile and outtype -->
					<input type="hidden" name="xmlfile" value="{$xp_xmlfile}"/>
					<input type="hidden" name="outtype" value="html"/>
					<!-- we also know all page numbers on levels ABOVE current -->
					<xsl:if test="$level &gt;= 1">
						<input type="hidden" name="xp_p1" value="{$xp_p1}"/>
					</xsl:if>
					<xsl:if test="$level &gt;= 2">
						<input type="hidden" name="xp_p2" value="{$xp_p2}"/>
					</xsl:if>
					<xsl:if test="$level &gt;= 3">
						<input type="hidden" name="xp_p3" value="{$xp_p3}"/>
					</xsl:if>
					<xsl:if test="$level &gt;= 4">
						<input type="hidden" name="xp_p4" value="{$xp_p4}"/>
					</xsl:if>
					<!-- create a dropdown -->
					<select name="xp_p{$level + 1}" height="1" onchange="javascript:form.submit()">
						<!-- add an option '0' -->
						<option value="0">
							<xsl:if test="$page = 0">
								<xsl:attribute name="selected">selected</xsl:attribute>
							</xsl:if>All
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
		<table cellspacing="0" cellpadding="0" border="0">
			<tr valign="top">
				<td valign="top">
					<a href="{$csvlink}&amp;xp_tab={position()}" border="0" onmouseover="MM_swapImage('export','','/fi/figis/assets/images/toolbar_text/export_on.gif',1)" onmouseout="MM_swapImgRestore()">
						<img src="/fi/figis/assets/images/toolbar_text/export.gif" width="70" height="19" alt="Export results in CSV format" name="export" border="0"/>
					</a>&#160;&#160;
					<a href="{$graphlink}&amp;xp_tab={position()}&amp;xp_tracker={$xp_tracker}&amp;xp_series={Head/Series}" border="0" onmouseover="MM_swapImage('graph','','/fi/figis/assets/images/toolbar_text/graph_on.gif',1)" onmouseout="MM_swapImgRestore()">
						<img src="/fi/figis/assets/images/toolbar_text/graph.gif" width="70" height="19" alt="Display table as a graph" name="graph" border="0"/>
					</a>&#160;&#160;
				</td>
				<xsl:if test="../Map">
					<xsl:variable name="cpage">
						<xsl:call-template name="curpage">
							<xsl:with-param name="level" select="number(../Map/Key) + 1"/>
						</xsl:call-template>
					</xsl:variable>
					<xsl:if test="number($cpage)=0">
						<td>
							<a href="{$maplink}&amp;xp_tab={position()}">
								<img src="/fi/figis/assets/images/toolbar_text/map.gif" width="55" height="17" alt="display map" name="graph" border="0"/>
							</a>
						</td>
					</xsl:if>
				</xsl:if>
			</tr>
		</table>
		<!-- end of the toolbar table -->
	</xsl:template>
	<!-- adds a link to the legend explaining the various special symbols used in tables -->
	<xsl:template name="pagehead">
		<!--      Special values explanation&#160;: 
      <a href="javascript:new_window('/fi/figis/help/tseries/special_.htm',
        'help',1,0,0,0,0,1,1,450,400)">click here</a>.-->
		<!-- content of special_. inserted in the main xsl : same behavior as parameters -->
		<table width="100%" cellspacing="0" cellpadding="2">
			<tr>
				<td class="tracker">
					<b>Special values explanation&#160;</b>
					<a onmouseover="this.style.cursor='hand';" onclick="expandHide('explanation');">
						<img name="tst" alt="Show Selected Parameters" src="/fi/figis/assets/images/addinfo_black.gif"/>
					</a>
				</td>
			</tr>
			<tr>
				<td>
					<div style="display: none;" id="explanation">
						<table cellspacing="0" cellpadding="5">
							<tr>
								<td colspan="2" class="trackerselection">Six types of <b>special values</b> may be displayed.</td>
							</tr>
							<tr>
								<td class="trackerlabel" width="40%">
									<b>...and 0 &#160;&#160;&#160;(unknown)</b>
								</td>
								<td class="trackerselection" width="70%">Data not available.</td>
							</tr>
							<tr>
								<td class="trackerlabel">
									<b>&lt;0.5 and 0 0&#160;&#160;&#160;(negligible)</b>
								</td>
								<td class="trackerselection">More than zero but less than half of the unit used.</td>
							</tr>
							<tr>
								<td class="trackerlabel">
									<b>0 and 0-&#160;&#160;&#160;(zero)</b>
								</td>
								<td class="trackerselection">An actual null value.</td>
							</tr>
							<tr>
								<td class="trackerlabel">
									<b>F&#160;&#160;&#160;(FAO estimate)</b>
								</td>
								<td class="trackerselection">Data estimated from available source of information or calculation based on specific assumptions.</td>
							</tr>
							<tr>
								<td class="trackerlabel">
									<b>R&#160;&#160;&#160;(repetition)</b>
								</td>
								<td class="trackerselection">Repetition of data previously reported.</td>
							</tr>
							<tr>
								<td class="trackerlabel">
									<b>W&#160;&#160;&#160;(wrong unit)</b>
								</td>
								<td class="trackerselection">Data for the record is in a unit that does not match the standard dataset unit.</td>
							</tr>
							<tr>
								<td colspan="2" class="trackerselection">Other explanations</td>
							</tr>
							<tr>
								<td class="trackerlabel">
									<b>no&#160;&#160;&#160;(number)</b>
								</td>
								<td class="trackerselection">Indicates that data unit is number.</td>
							</tr>
							<tr>
								<td class="trackerlabel">
									<b>t&#160;&#160;&#160;(tonnes =1000 kg)</b>
								</td>
								<td class="trackerselection">Indicates that data unit is tonne.</td>
							</tr>
							<tr>
								<td colspan="2" class="trackerselection">
									<font color="#CC3300">
										<b>Tip:</b>
									</font>
					 when calculating, the program will preserve special values to the fullest extent possible, e.g. the sum of two unknown values is an unknown value, the sum of two negligible values a negligible value. In complex cases, all flags are removed and the result of the calculation is considered a &quot;normal&quot; number.
</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</xsl:template>
	<xsl:template match="Head/Unit">
&#32;(<xsl:value-of select="."/>)
</xsl:template>
	<!-- Be aware that because of possible very large table all navigation, button etc must be put in td with a fixed width -->
	<xsl:template match="Table">
		<!-- compose table title of dataset name, series name, unit name
   and (if inside a subpage) names of selected keys -->
		<table border="0" width="600" cellpadding="0" cellspacing="0">
			<tr>
				<td class="tstabtitle">
					<xsl:value-of select="Head/Dataset"/>: <xsl:value-of select="Head/Series"/>
					<xsl:apply-templates select="Head/Unit"/>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<img src="/fi/figis/tseries/assets/images/dot-light-blue.gif" alt="" width="100%" height="1"/>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<img src="/fi/figis/assets/images/invidot.gif" border="0" alt="" height="3"/>
				</td>
			</tr>
		</table>
		<!-- buttons above the table -->
		<!-- navigation between the pages -->
		<table border="0" width="600" cellpadding="0" cellspacing="0">
			<tr>
				<td width="3%"/>
				<td width="50%" valign="bottom">
					<!--<font class="tsnavkey">You can choose to restrict data display</font>-->
					<table border="0" cellpadding="0" cellspacing="0">
						<xsl:apply-templates select="Body/RowSet" mode="navigation"/>
					</table>
					<xsl:apply-templates select="Body/RowSet" mode="title"/>
				</td>
				<td width="47%" valign="bottom">
					<xsl:call-template name="tablehead"/>
				</td>
			</tr>
			<tr>
				<td colspan="3" width="100%">
					<img src="/fi/figis/assets/images/invidot.gif" border="0" alt="" height="3"/>
				</td>
			</tr>
		</table>
		<table border="1" cellspacing="0" cellpadding="2" bordercolor="#6699CC">
			<!-- table head -->
			<xsl:apply-templates select="Head"/>
			<!-- table body-->
			<xsl:apply-templates select="Body"/>
		</table>
		<br/>
		© FAO - Fisheries and Aquaculture Information and Statistics Service - 
			<xsl:variable name="myDate" select="date:date()"/>
		<xsl:value-of select="concat(substring($myDate,9,2),'/',substring($myDate,6,2), '/',substring($myDate,1,4))"/>
	</xsl:template>
	<!-- column header -->
	<xsl:template match="Column[@type='key']">
		<xsl:variable name="page">
			<xsl:call-template name="curpage">
				<xsl:with-param name="level" select="@k+1"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:if test="number($page) = 0">
			<th>
				<xsl:value-of select="."/>
			</th>
		</xsl:if>
	</xsl:template>
	<xsl:template match="Column">
		<th>
			<xsl:value-of select="."/>
		</th>
	</xsl:template>
	<!-- table head: columns -->
	<xsl:template match="Head">
		<tr class="tscolhead">
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
	<xsl:template match="Key[count(@num)=0]" mode="rowspan">1</xsl:template>
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
			ts<xsl:apply-templates select="." mode="class"/>
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
			ts<xsl:apply-templates select="." mode="class"/>
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
			<xsl:if test="$page = 0">
				<xsl:value-of select="count($LastKeys)"/>
			</xsl:if>
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
	<xsl:template match="Key[@same]"/>
	<!-- print value -->
	<!-- to do: optionally turn the symbols off/on through a parameter -->
	<xsl:template match="V">
		<td>
			<xsl:choose>
				<xsl:when test="ancestor::Table/Head/Series='Value'">
					<xsl:call-template name="ValueProcess">
						<xsl:with-param name="value">
							<xsl:value-of select="@n"/>
						</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="ancestor::Table/Head/Series='Quantity'">
					<xsl:value-of select="format-number(@n, '###&#160;###&#160;###&#160;##0','Number')"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="."/>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:if test="@s">
				<i>
					<xsl:text> </xsl:text>
					<xsl:value-of select="@s"/>
				</i>
			</xsl:if>
		</td>
	</xsl:template>
	<xsl:template name="ValueProcess">
		<xsl:param name="value"/>
		<xsl:variable name="checkNumber" select="number($value)"/>
		<xsl:choose>
			<xsl:when test="$checkNumber='NaN'">
				<xsl:value-of select="$value"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="format-number(@n, '###&#160;###&#160;###&#160;##0','Number')"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- create a header row: gray background, no data -->
	<xsl:template match="Row[@header='1']">
		<tr class="tsdata">
			<xsl:apply-templates select="Key|EmptyKey"/>
			<td colspan="{count(V)}" class="tshdr">&#160;</td>
		</tr>
	</xsl:template>
	<!-- create a regular row -->
	<xsl:template match="Row[@header!='1']">
		<tr class="tsdata">
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
