<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">

<xsl:output method="xml" encoding="UTF-8" indent="yes"/>

<xsl:include href="aqybk_lib.xsl"/>

<xsl:variable name="TableID" select="'C-1'"/>
<xsl:variable name="TableCode" select="'C-1'"/>

<xsl:template match="SQServlet" mode="ColumnHeaders">
	<xsl:call-template name="ColumnHeaders">
		<xsl:with-param name="unit">mt</xsl:with-param>
	</xsl:call-template>
</xsl:template>

<xsl:template match="Body/RowSet/RowSet">
	<figis:aggregaterow>
		<figis:RowTitle LANG="EN"><xsl:value-of select="@name"/></figis:RowTitle>
	</figis:aggregaterow>
	<xsl:apply-templates select="Row[@leaf='1' and @ser='Quantity']">
		<xsl:with-param name="TitleLang" select="'SC'"/>
		<xsl:with-param name="RowType" select="'_skip_'"/>
		<xsl:with-param name="TitleKey" select="2"/>
		<xsl:with-param name="symbolcols" select="1"/>
	</xsl:apply-templates>
	<xsl:apply-templates select="Row[@tot and @ser='Quantity']" mode="tworows">
		<xsl:with-param name="elementname" select="'figis:subaggregaterow'"/>
		<xsl:with-param name="keyE" select="'Fish, crustaceans and molluscs'"/>
		<xsl:with-param name="keyF" select="'Poissons, crustacés et mollusques'"/>
		<xsl:with-param name="keyS" select="'Peces, crustáceos y moluscos'"/>
		<xsl:with-param name="symbolcols" select="1"/>
	</xsl:apply-templates>
</xsl:template>

<xsl:template match="Body/RowSet">
	<xsl:apply-templates select="RowSet"/>
</xsl:template>

</xsl:stylesheet>
