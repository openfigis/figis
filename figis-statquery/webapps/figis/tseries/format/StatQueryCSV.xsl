<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
<xsl:output indent="no" omit-xml-declaration="yes" method="text"/>
<xsl:strip-space elements="*"/>
<xsl:param name="series" select="1"/>

<xsl:template match="stattable">
  <xsl:value-of select="../name"/>
  <xsl:text>: </xsl:text>
  <xsl:value-of select="series"/>
  <xsl:if test="unit">(<xsl:value-of select="unit"/>)</xsl:if>
<xsl:text> 
</xsl:text>
  <xsl:apply-templates select="tableheader"/>
  <xsl:apply-templates select="tablerow[@header!='1']"/>
</xsl:template>

<xsl:template match="/report/queryresult">
  <xsl:apply-templates select="stattable"/>
</xsl:template>

<xsl:template match="tableheader">
  <xsl:apply-templates/>
<xsl:text>
</xsl:text>
</xsl:template>

<xsl:template match="tablerow">
  <xsl:apply-templates/>
<xsl:text>
</xsl:text>
</xsl:template>

<xsl:template match="keyhead">
<xsl:text>"</xsl:text>
<xsl:value-of select="."/>
<xsl:text>",</xsl:text>
</xsl:template>

<xsl:template match="valuehead">
<xsl:text>"</xsl:text>
<xsl:value-of select="."/>
<xsl:text>","Symb</xsl:text>
<xsl:value-of select="."/>
<xsl:text>"</xsl:text>
<xsl:call-template name="comma"/>
</xsl:template>

<xsl:template name="comma">
<xsl:if test="position()!=last()">,</xsl:if>
</xsl:template>

<xsl:template match="key">
<xsl:text>"</xsl:text>
<xsl:value-of select="."/>
<xsl:text>",</xsl:text>
</xsl:template>

<xsl:template match="value">
<xsl:text>"</xsl:text>
<xsl:value-of select="."/>
<xsl:text>","</xsl:text>
<xsl:value-of select="@symbol"/>
<xsl:text>"</xsl:text>
<xsl:call-template name="comma"/>
</xsl:template>

</xsl:stylesheet>
