<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">

<xsl:output method="xml" encoding="UTF-8" indent="yes"/>

<xsl:param name="xp_limit" select="60000"/>

<xsl:include href="aqybk_lib.xsl"/>
<xsl:include href="aq_total_other.xsl"/>

<xsl:variable name="TableID" select="'A-4'"/>
<xsl:variable name="TableCode" select="'A-4'"/>

<xsl:template match="SQServlet" mode="Footnotes">
	<figis:Footnote LANG="EN">These countries are those with production of <xsl:value-of select="$limit_str"/> tonnes or more in 1999</figis:Footnote>
	<figis:Footnote LANG="FR">Ces pays sont ceux dont la production a été de <xsl:value-of select="$limit_str"/> tonnes ou plus en 1999</figis:Footnote>
	<figis:Footnote LANG="ES">Estos países referentes a producción totalizando <xsl:value-of select="$limit_str"/> toneladas o más en 1999</figis:Footnote>
</xsl:template>

<xsl:template match="Body/RowSet">
	<xsl:call-template name="total_selected"/>
</xsl:template>

<xsl:template match="RowSet" mode="others">
	<xsl:call-template name="totalrows">
		<xsl:with-param name="RowSet" select="$OtherRows"/>
		<xsl:with-param name="KeyE">Other countries</xsl:with-param>
		<xsl:with-param name="KeyF">Autres pays</xsl:with-param>
		<xsl:with-param name="KeyS">Otros países</xsl:with-param>
	</xsl:call-template>
</xsl:template>

</xsl:stylesheet>
