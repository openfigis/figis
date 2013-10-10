<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
  <xsl:output indent="no" omit-xml-declaration="yes" method="text"/>
  <xsl:strip-space elements="*"/>

	<xsl:template name="PRINT"><xsl:value-of select="."/></xsl:template>

	<xsl:template match="/report/figis:queryresult">
		<xsl:for-each select="figis:table">
			<xsl:value-of select="../figis:name"/>,<xsl:value-of select="figis:tablevalue"/><xsl:text>
</xsl:text><xsl:apply-templates/>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="figis:tablerow[@LEVEL]"/>
	<xsl:template match="figis:tableheader|figis:tablerow"><xsl:apply-templates/><xsl:text>
</xsl:text></xsl:template>

	<xsl:template match="figis:keyhead"><xsl:call-template name="PRINT"/>,</xsl:template>

	<xsl:template match="figis:valuehead"><xsl:call-template name="PRINT"/>,<xsl:call-template name="PRINT"/> Symb<xsl:if test="position()!=last()">,</xsl:if></xsl:template>

	<xsl:template match="figiskeyvalue[@WIDTH='1']">"<xsl:call-template name="PRINT"/>",</xsl:template>
	<xsl:template match="figiskeyvalue[@WIDTH='2']">"<xsl:call-template name="PRINT"/>",,</xsl:template>
	<xsl:template match="figiskeyvalue[@WIDTH='3']">"<xsl:call-template name="PRINT"/>",,,</xsl:template>
	<xsl:template match="figiskeyvalue[@WIDTH='4']">"<xsl:call-template name="PRINT"/>",,,,</xsl:template>

	<xsl:template match="figis:value|figisvalue"><xsl:call-template name="PRINT"/>,<xsl:value-of select="@SYMB"/><xsl:if test="position()!=last()">,</xsl:if> </xsl:template>

<!-- these just supress unwanted matched to these nodes, which are
	handled by specific clauses -->
	<xsl:template match="figis:graphlink"/>
	<xsl:template match="figis:tablevalue"/>
</xsl:stylesheet>
