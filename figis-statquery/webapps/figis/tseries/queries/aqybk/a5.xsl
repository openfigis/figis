<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">

<xsl:output method="xml" encoding="UTF-8" indent="yes"/>

<xsl:include href="aqybk_lib.xsl"/>

<xsl:variable name="TableID" select="'A-5'"/>
<xsl:variable name="TableCode" select="'A-5'"/>

<xsl:template match="Body/RowSet">
	<xsl:apply-templates select="Row[@tot='0' and @ser='Quantity']" mode="tworows">
		<xsl:with-param name="elementname" select="'figis:aggregaterow'"/>
		<xsl:with-param name="keyE" select="'World Total'"/>
		<xsl:with-param name="keyF" select="'Total mondial'"/>
		<xsl:with-param name="keyS" select="'Total mundial'"/>
	</xsl:apply-templates>
	<xsl:apply-templates select="Row[@leaf='1' and @ser='Quantity']" mode="tworows">
		<xsl:sort select="V[10]/@n" data-type="number" order="descending"/>
		<xsl:with-param name="elementname" select="'figis:tablerow'"/>
		<xsl:with-param name="keyNo" select="1"/>
		<xsl:with-param name="keyE" select="'_auto_'"/>
		<xsl:with-param name="keyF" select="'_none_'"/>
		<xsl:with-param name="keyS" select="'_skip_'"/>
	</xsl:apply-templates>
</xsl:template>

</xsl:stylesheet>
