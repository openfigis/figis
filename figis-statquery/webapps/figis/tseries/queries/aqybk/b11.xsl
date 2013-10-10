<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">

<xsl:output method="xml" encoding="UTF-8" indent="yes"/>

<!-- include libraries -->
<xsl:include href="aqybk_lib.xsl"/>

<!-- table ID is needed by a library to retrieve column names etc-->
<!-- It's just B, so that column names are the same for all B- tables -->
<xsl:variable name="TableID" select="'B'"/>
<!-- tableCode is what's actually printed on the page -->
<xsl:variable name="TableCode" select="concat('B-',//Body/RowSet/Row[1]/Key[2])"/>

<!-- makes table titles -->
<!-- Goes to the Root Rowset, and finds header row. This is the 
row for ISSCAAP group, it contains all its names. 
English, french spanish names are in columns 4 through 6-->
<xsl:template match="SQServlet" mode="TableTitles">
	<figis:TableTitle LANG="EN">
		<xsl:value-of select="//Body/RowSet/Row[1]/Key[4]"/>
	</figis:TableTitle>
	<figis:TableTitle LANG="FR">
		<xsl:value-of select="//Body/RowSet/Row[1]/Key[5]"/>
	</figis:TableTitle>
	<figis:TableTitle LANG="SP">
		<xsl:value-of select="//Body/RowSet/Row[1]/Key[6]"/>
	</figis:TableTitle>
</xsl:template>

<!-- overrides default template for column headers -->
<!-- calls library procedure with unit name that's written in the second row -->
<xsl:template match="SQServlet" mode="ColumnHeaders">
	<xsl:call-template name="ColumnHeaders">
		<xsl:with-param name="unit">mt</xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- All table: take the main RowSet: -->
<xsl:template match="Body/RowSet">
	<!-- first, select all RowSets for individual Species-->
	<!-- sub=0 is group, 1 is species -->
	<xsl:apply-templates select="RowSet[@sub='1']">
		<xsl:sort select="@name" data-type="text"/>
	</xsl:apply-templates>
	<!-- Theh print two grand total rows -->
	<xsl:apply-templates select="Row[@tot='0' and @ser='Quantity']" mode="tworows">
		<xsl:with-param name="elementname" select="'figis:aggregaterow'"/>
		<xsl:with-param name="keyE" select="'Group Total'"/>
		<!-- No french name -->
		<xsl:with-param name="keyF" select="'_none_'"/>
		<!-- No empty row for spanish name -->
		<xsl:with-param name="keyS" select="'_skip_'"/>
		<!-- print symbols -->
		<xsl:with-param name="symbolcols" select="1"/>
	</xsl:apply-templates>
</xsl:template>

<!-- Individual species -->
<xsl:template match="Body/RowSet/RowSet">
	<figis:grouphdr>
		<!-- Print six names in Group headers-->
		<figis:RowTitle LANG="EN"><xsl:value-of select="Row[1]/Key[4]"/></figis:RowTitle>
		<figis:RowTitle LANG="FR"><xsl:value-of select="Row[1]/Key[5]"/></figis:RowTitle>
		<figis:RowTitle LANG="ES"><xsl:value-of select="Row[1]/Key[6]"/></figis:RowTitle>
		<!-- Scientific -->
		<figis:RowTitle LANG="SC"><xsl:value-of select="Row[1]/Key[3]"/></figis:RowTitle>
		<!-- format taxonomic code as x.xx(xx)xxx,xx -->
		<figis:RowTitle LANG="TC">
			<xsl:variable name="t" select="Row[1]/Key[1]"/>
			<xsl:value-of select="concat(substring($t,1,1) , ',' , substring($t,2,2), '(', substring($t,4,2), ')', substring($t,6,3), ',', substring($t,8))"/>
		</figis:RowTitle>
		<!-- 3alpha code -->
		<figis:RowTitle LANG="3A"><xsl:value-of select="Row[1]/Key[2]"/></figis:RowTitle>
	</figis:grouphdr>
	<!--  Select quantities for all countries -->
	<!-- @leaf='1' means do not select species total -->
	<xsl:apply-templates select="Row[@leaf='1' and @ser='Quantity']">
		<xsl:with-param name="TitleLang" select="'EN'"/>
		<!-- do not output RowType tag -->
		<xsl:with-param name="RowType" select="'_skip_'"/>
		<!-- take row header from seventh column (first six were species names) -->
		<xsl:with-param name="TitleKey" select="7"/>
		<xsl:with-param name="symbolcols" select="1"/>
	</xsl:apply-templates>
	<!-- Print two total rows - for quantities and values -->
	<!-- We select Quantity row only. template will take its next sibling, 
	which is value row -->
	<xsl:apply-templates select="Row[@tot and @ser='Quantity']" mode="tworows">
		<xsl:with-param name="elementname" select="'figis:subaggregaterow'"/>
		<xsl:with-param name="keyE" select="'Species Total'"/>
		<!-- No french name -->
		<xsl:with-param name="keyF" select="'_none_'"/>
		<!-- No empty row for spanish name -->
		<xsl:with-param name="keyS" select="'_skip_'"/>
		<xsl:with-param name="symbolcols" select="1"/>
	</xsl:apply-templates>
</xsl:template>

</xsl:stylesheet>
