<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">

<xsl:output method="xml" encoding="UTF-8" indent="yes"/>

<xsl:decimal-format name = "spaced" grouping-separator = " "/> 

<!-- DOcument with table and column titles 
This is very simple XML. Idea is to allow non-programmers to
edit it
-->
<xsl:variable name="TableTitlesDoc" 
	select="document('TableTitles.xml')"/>
<!--
Select section for current table 
TableID must be defined in calling XSL
-->
<xsl:variable name="TableTitles" 
	select="$TableTitlesDoc//Table[@ID=$TableID]"/>

<xsl:template match="SQServlet">
<report xmlns:figis="http://www.fao.org/figis">
	<figis:queryresult>
		<figis:YEARBOOK ID="AQYB" VOLUME="01" YEAR="1999">

			<xsl:for-each select="$TableTitlesDoc//YearBook/NAME">
				<figis:NAME>
					<xsl:copy-of select="@LANG"/>
					<xsl:copy-of select="text()"/>
				</figis:NAME>
			</xsl:for-each>
			
			<figis:Tableheader>
				<figis:TableHeaderLiterals>
					<figis:TableCode><xsl:value-of select="$TableCode"/></figis:TableCode>
					<xsl:apply-templates select="." mode="TableTitles"/>
					<xsl:apply-templates select="." mode="TableAbbreviations"/>
				</figis:TableHeaderLiterals>
				<xsl:apply-templates select="." mode="Footnotes"/>
				<xsl:apply-templates select="." mode="ColumnHeaders"/>
			</figis:Tableheader>
			<figis:table>
				<xsl:apply-templates select="Table/Body/RowSet"/>
			</figis:table>
		</figis:YEARBOOK>
	</figis:queryresult>
</report>
</xsl:template>

<!-- Abbreviations are the same for all tables -->
<!-- If some table needs other abbreviations,
override by writing
<xsl:template match="SQServlet" mode="TableAbbreviations">
(this and other overridable templates work thanks to priorities)
 -->
<xsl:template match="SQServlet" mode="TableAbbreviations" priority="-1">
	<figis:TableAbbreviations>
		<figis:TableAbrev>Q = mt</figis:TableAbrev>
		<figis:TableAbrev>V = US$'000</figis:TableAbrev>
	</figis:TableAbbreviations>
</xsl:template>

<!-- Default footnotes template. 
override by writing 
<xsl:template match="SQServlet" mode="Footnotes">
 -->
<xsl:template match="SQServlet" mode="Footnotes" priority="-1">
</xsl:template>

<!-- Default column headers template. 
To override it write
<xsl:template match="SQServlet" mode="ColumnHeaders">
 -->
<xsl:template match="SQServlet" mode="ColumnHeaders" priority="-1">
	<xsl:call-template name="ColumnHeaders"/>
</xsl:template>

<!-- Default Table Titles template. Takes
titles from TableTitles.XML file
To override it write
<xsl:template match="SQServlet" mode="TableTitles">
 -->
<xsl:template match="SQServlet" mode="TableTitles" priority="-1">
	<xsl:for-each select="$TableTitles/TableTitle">
		<figis:TableTitle>
			<xsl:copy-of select="@LANG"/>
			<xsl:copy-of select="text()"/>
		</figis:TableTitle>
	</xsl:for-each>
</xsl:template>

<!-- Output a value passed as a parameter
 Parameters: n - a number (taken from V/@n)
 	s - a symbol (taken from V/@s)
 	symbolcol - 1 to create a separate <figis:value> for symbols
-->
<xsl:template name="value">
	<xsl:param name="n" select="0"/>
	<xsl:param name="s" select="''"/>
	<xsl:param name="symbolcol" select="0"/>
	<xsl:variable name="val">
		<xsl:choose>
      		<xsl:when test="number($n) != 0">
				<xsl:value-of select="format-number(number($n), '###### ##0','spaced')"/>
			</xsl:when>
			<!-- special values -->
			<xsl:when test="$s = '.'">	...</xsl:when>
			<xsl:when test="$s = '-'">	-</xsl:when>
			<xsl:otherwise>0</xsl:otherwise>
   		</xsl:choose>		
	</xsl:variable>
	<!-- pad it with spaces on the left -->
	<!-- is it actually needed??? It is this way now -->
	<xsl:variable name="padding" select="substring('            ',string-length($val))"/>
	<figis:value>
		<xsl:value-of select="concat($padding,$val)"/>
	</figis:value>
	<!-- Create symbol column if needed -->
	<xsl:if test="$symbolcol=1">
		<figis:value>
			<!-- we've already taken care of dots and - and 0 symbols
			so we only need to output F (and possibly other letters -->
			<xsl:if test="$s!='' and $s!='.' and $s!=' ' and $s!='0' and $s!='-'">
				<xsl:value-of select="$s"/>
			</xsl:if>
		</figis:value>
	</xsl:if>
</xsl:template>

<!-- Creates figis:RowTitle element 
taking value either from parameter or from current element --> 
<xsl:template name="RowTitleKey">
   <!-- LANG attribute -->
	<xsl:param name="TitleLang" select="'EN'"/>
	<!-- If this is 
		_none_ -  empty title
		_auto_ - take title from Key elemnt inside current element. 
			Key Index taken from TitleKey attribute
		anything else - print this param as key string
	-->
	<xsl:param name="Key" select="'_auto_'"/>

	<!-- When $Key=_auto_, this tells from which Key to take value -->
	<xsl:param name="TitleKey" select="1"/>

	<xsl:choose>
   			<xsl:when test="$Key='_auto_'">
				<figis:RowTitle LANG="{$TitleLang}">
					<xsl:value-of select="Key[$TitleKey]"/>
				</figis:RowTitle>
			</xsl:when>
    		<xsl:when test="$Key='_none_'">
				<figis:RowTitle/>
			</xsl:when>
			<xsl:otherwise>
				<figis:RowTitle LANG="{$TitleLang}">
					<xsl:value-of select="$Key"/>
				</figis:RowTitle>
			</xsl:otherwise>
  	</xsl:choose>
</xsl:template>

<!-- Print one Row -->
<xsl:template match="Row">
	<!-- LANG attribute for title - passed to RowTitleKey -->
	<xsl:param name="TitleLang" select="'EN'"/>
	<!-- key value, _auto_ or _none_ - passed to RowTitleKey -->
	<xsl:param name="Key" select="'_auto_'"/>
	<!-- From which Key to take title-->
	<xsl:param name="TitleKey" select="1"/>
	<!-- Row type: 
		_auto_ - determine automatically from @ser attribute
		_skip_ - do not output
		anything else - output as RowType
	-->
	<xsl:param name="RowType" select="'_auto_'"/>
	<!-- element name for this row - tablerow, aggregaterow, etc -->
	<xsl:param name="elementname" select="'figis:tablerow'"/>
	<!-- output symbol columns? 0 or 1 -->
	<xsl:param name="symbolcols" select="0"/>

	<xsl:element name="{$elementname}">
		<!-- Row title -->
		<xsl:call-template name="RowTitleKey">
			<xsl:with-param name="TitleLang" select="$TitleLang"/>
			<xsl:with-param name="TitleKey" select="$TitleKey"/>
			<xsl:with-param name="Key" select="$Key"/>
		</xsl:call-template>
		<!-- Row type -->
		<xsl:if test="$RowType!='_skip_'">
			<figis:RowType><xsl:value-of select="substring(@ser,1,1)"/></figis:RowType>
		</xsl:if>
		<!-- Values -->
		<xsl:for-each select="V">
			<xsl:call-template name="value">
				<xsl:with-param name="n" select="@n"/>
				<xsl:with-param name="s" select="@s"/>
				<xsl:with-param name="symbolcol" select="$symbolcols"/>
			</xsl:call-template>
		</xsl:for-each>
	</xsl:element>
</xsl:template>

<!-- Row with only a title (no data) -->
<xsl:template match="Row" mode="Title">
	<!-- LANG attribute for title - passed to RowTitleKey -->
	<xsl:param name="TitleLang" select="'EN'"/>
	<!-- key value, _auto_ or _none_ - passed to RowTitleKey -->
	<xsl:param name="Key" select="'_auto_'"/>
	<!-- From which Key to take title-->
	<xsl:param name="TitleKey" select="1"/>
	<!-- element name for this row - tablerow, aggregaterow, etc -->
	<xsl:param name="elementname" select="'figis:tablerow'"/>

	<xsl:element name="{$elementname}">
		<xsl:call-template name="RowTitleKey">
			<xsl:with-param name="TitleLang" select="$TitleLang"/>
			<xsl:with-param name="TitleKey" select="$TitleKey"/>
			<xsl:with-param name="Key" select="$Key"/>
		</xsl:call-template>
		<figis:RowType/>
		<figis:value/>
	</xsl:element>
</xsl:template>

<!-- 
 A template for commonly repeated pattern:
  	English name  Q quantity data
	[French name] V value data
	[Spanish name]
-->
<xsl:template match="Row" mode="tworows">
	<!-- element name for this row - tablerow, aggregaterow, etc -->
	<xsl:param name="elementname" select="'figis:tablerow'"/>
	<!-- Names: 
		_auto_ - take from key number keyNo(EN), keyNo+1(FR), keyNo+2(ES)
		text - output this text
		_none_ - no key
		_skip_ - no row at all (last row (Spanish) only)
	-->
	<xsl:param name="keyE" select="'_auto_'"/>
	<xsl:param name="keyF" select="'_auto_'"/>
	<xsl:param name="keyS" select="'_auto_'"/>
	<!-- key number for english name if keyE is _auto_ . 
	French and Spanish are keyNo+1 and keyNo+2 -->
	<xsl:param name="keyNo" select="1"/>
	<!-- print symbol columns? -->
	<xsl:param name="symbolcols" select="0"/>
	
	<!-- Call template "Row" for this Row, pass parameters -->
	<xsl:apply-templates select=".">
		<xsl:with-param name="TitleLang" select="'EN'"/>
		<xsl:with-param name="TitleKey" select="$keyNo"/>
		<xsl:with-param name="Key" select="$keyE"/>
		<xsl:with-param name="elementname" select="$elementname"/>
		<xsl:with-param name="symbolcols" select="$symbolcols"/>
	</xsl:apply-templates>

	<!-- and for next row -->
	<xsl:apply-templates select="following-sibling::Row[1]">
		<xsl:with-param name="TitleLang" select="'FR'"/>
		<xsl:with-param name="TitleKey" select="$keyNo + 1"/>
		<xsl:with-param name="Key" select="$keyF"/>
		<xsl:with-param name="elementname" select="$elementname"/>
		<xsl:with-param name="symbolcols" select="$symbolcols"/>
	</xsl:apply-templates>

	<!-- there is no data fro spanish - just call title-->
	<xsl:if test="$keyS!='_skip_'">
		<xsl:apply-templates select="." mode="Title">
			<xsl:with-param name="TitleLang" select="'ES'"/>
			<xsl:with-param name="TitleKey" select="$keyNo + 2"/>
			<xsl:with-param name="Key" select="$keyS"/>
			<xsl:with-param name="elementname" select="$elementname"/>
		</xsl:apply-templates>
	</xsl:if>
</xsl:template>

<!-- Default column headers -->
<!-- First column headers - from TableTitles.XML -->
<!-- Data columns - from Table/Head in source -->
<xsl:template name="ColumnHeaders">
	<!-- pass something if you need units in second row of each header -->
	<xsl:param name="unit" select="''"/>
	<figis:TableColumnHeaders>
		<figis:TableColumnHeaderRow>
			<figis:ColHeader>
				<!-- English name for first column -->
				<xsl:value-of select="$TableTitles/ColHeader[@LANG='EN']"/>
			</figis:ColHeader>
		</figis:TableColumnHeaderRow>
		<figis:TableColumnHeaderRow>
			<figis:ColHeader>
				<!-- French name for first column -->
				<xsl:value-of select="$TableTitles/ColHeader[@LANG='FR']"/>
			</figis:ColHeader>
			<!-- Source columns (years) -->
			<xsl:for-each select="Table[1]/Head/Columns/Column[@type='year']">
				<figis:ColHeader><xsl:value-of select="."/></figis:ColHeader>
			</xsl:for-each>
		</figis:TableColumnHeaderRow>
		<figis:TableColumnHeaderRow>
			<figis:ColHeader>
				<!-- Spanish name for first column -->
				<xsl:value-of select="$TableTitles/ColHeader[@LANG='ES']"/>
			</figis:ColHeader>
			<!-- names of the unit - we need one for each year column -->
			<xsl:if test="$unit != ''">
				<xsl:for-each select="Table[1]/Head/Columns/Column[@type='year']">
					<figis:ColHeader><xsl:value-of select="$unit"/></figis:ColHeader>
				</xsl:for-each>
			</xsl:if>
		</figis:TableColumnHeaderRow>
	</figis:TableColumnHeaders>
</xsl:template>


</xsl:stylesheet>
