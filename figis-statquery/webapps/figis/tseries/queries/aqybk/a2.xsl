<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">

<xsl:output method="xml" encoding="UTF-8" indent="yes"/>

<xsl:include href="aqybk_lib.xsl"/>

<xsl:variable name="TableID" select="'A-2'"/>
<xsl:variable name="TableCode" select="'A-2'"/>

<xsl:template match="Body/RowSet/RowSet">
	<xsl:apply-templates select="Row[@tot='3' and @ser='Quantity']" mode="tworows">
		<xsl:with-param name="elementname" select="'figis:aggregaterow'"/>
	</xsl:apply-templates>
	<xsl:apply-templates select="Row[Key/@gid='1:82000:1' and @ser='Quantity']" mode="tworows">
		<xsl:with-param name="elementname" select="'figis:tablerow'"/>
		<xsl:with-param name="keyNo" select="4"/>
	</xsl:apply-templates>
	<xsl:apply-templates select="Row[Key/@gid='1:82000:2' and @ser='Quantity']" mode="tworows">
		<xsl:with-param name="elementname" select="'figis:tablerow'"/>
		<xsl:with-param name="keyNo" select="4"/>
	</xsl:apply-templates>
	<xsl:apply-templates select="Row[Key/@gid='1:82000:3' and @ser='Quantity']" mode="tworows">
		<xsl:with-param name="elementname" select="'figis:tablerow'"/>
		<xsl:with-param name="keyNo" select="4"/>
	</xsl:apply-templates>
</xsl:template>

<xsl:template match="Body/RowSet">
	<xsl:apply-templates select="RowSet[@gid='1:32004:1801']"/>
	<xsl:apply-templates select="RowSet[@gid='1:32004:1805']"/>
	<xsl:apply-templates select="RowSet[@gid='1:32004:1802']"/>
</xsl:template>



</xsl:stylesheet>
