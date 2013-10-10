<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
  <xsl:output method="text" indent="no"/>
  <xsl:strip-space elements="*"/>
	<xsl:variable name="quote">'</xsl:variable>
	<xsl:variable name="uscore">_</xsl:variable>


<xsl:template match="/report/queryresult">
  <xsl:apply-templates select="stattable"/>
</xsl:template>


<xsl:template match="stattable">
ARRAY <xsl:value-of select="count(tablerow)"/>
<xsl:text> </xsl:text>
<xsl:value-of select="count(tableheader/valuehead)"/>
<xsl:text>
</xsl:text>
<xsl:apply-templates select="tableheader"/>
<xsl:apply-templates select="tablerow"/>
<xsl:text>

</xsl:text>

# some additional parameters used to format the graph:
#param title="<xsl:value-of select='/description'/>"
#param ytitle="<xsl:value-of select='series'/>"
#param xtitle=Years
#param width=600
#param height=600
#param type=line
#param xgrid=none
#param ygrid=#f0f0f0
#param xtick=auto
#param ytick=auto
#param yscale=x1000
</xsl:template>

<xsl:template match="valuehead">
<xsl:value-of select="."/><xsl:text> </xsl:text>
</xsl:template>

<xsl:template match="tableheader">
<xsl:apply-templates select="valuehead"/>
<xsl:text>
</xsl:text>
</xsl:template>

<xsl:template match="tablerow">
<xsl:variable name="keystr"><xsl:apply-templates select="key"/></xsl:variable>

<xsl:text>'</xsl:text>
<xsl:value-of select="translate($keystr,$quote,$uscore)"/>
<xsl:text>' </xsl:text>
<xsl:apply-templates select="value"/>
#param s<xsl:value-of select="position()"/>_title="<xsl:value-of select="$keystr"/>"
#param s<xsl:value-of select="position()"/>_show=on
<xsl:text>
</xsl:text>
</xsl:template>

<xsl:template match="key">
<xsl:value-of select="."/><xsl:if test="position()!=last()">.</xsl:if>
</xsl:template>

<xsl:template match="value">
<xsl:variable name="value" select="."/>
<xsl:text> </xsl:text>
<xsl:if test="string-length($value)=0">0</xsl:if>
<xsl:if test="string-length($value)>0"><xsl:value-of select="."/></xsl:if>
</xsl:template>

</xsl:stylesheet>
