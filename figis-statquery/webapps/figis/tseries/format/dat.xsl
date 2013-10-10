<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
  <xsl:output method="text" indent="no"/>
  <xsl:strip-space elements="*"/>
	<xsl:variable name="quote">'</xsl:variable>
	<xsl:variable name="bquote">`</xsl:variable>

<xsl:param name="xp_tab" select="1"/>

<xsl:param name="xp_maxpage" select="100"/>
<xsl:include href="paging.xsl"/>

<xsl:variable name="symb" select="//SQParams/ShowFlags = '1'"/>

<xsl:template match="SQServlet">
  <xsl:apply-templates select="Table[position() = $xp_tab]"/>
</xsl:template>

<xsl:template match="RowSet" mode="title">
<!-- append selected keys to page title. If we display all pages 
don't append anything. Else go down the tree and print selected keys
-->
<xsl:apply-templates select="parent::RowSet" mode="title"/> 
<xsl:if test="@name">
	<xsl:text>, </xsl:text><xsl:value-of select="@name"/>
</xsl:if>
</xsl:template>

<xsl:template match="RowSet" mode="displayrows">
<xsl:variable name="firstrow" select=".//Row[@leaf][1]"/>
<xsl:variable name="unit" select="string($firstrow/@unit)"/>
<xsl:variable name="rows" select=".//Row[@leaf][string(@unit)=$unit]"/>

ARRAY <xsl:value-of select="count($rows)"/>
<xsl:text> </xsl:text>
<xsl:value-of select="count(ancestor::Table/Head/Columns/Column[@type='year' or @type='calc'])"/>
<xsl:text>
</xsl:text>
<xsl:apply-templates select="ancestor::Table/Head"/>

<xsl:apply-templates select="$rows">
	<xsl:with-param name="showkeys" select="@k"/>
</xsl:apply-templates>
<xsl:text>

</xsl:text>
# some additional parameters used to format the graph:
#param title="<xsl:value-of select="ancestor::Table/Head/Dataset"/>
<xsl:text> </xsl:text>
<xsl:apply-templates select="." mode="title"/>"
#param ytitle="<xsl:value-of select="ancestor::Table/Head/Series"/>
<xsl:apply-templates select="ancestor::Table/Head/Unit"/>
<xsl:if test="$unit"> (<xsl:value-of select="$unit"/>)</xsl:if>
"
#param xtitle=Years
#param width=600
#param height=600
#param type=line
#param xgrid=none
#param ygrid=#f0f0f0
#param xtick=auto
#param ytick=auto
#param yscale=x1000

<xsl:apply-templates select="$rows" mode="titles">
	<xsl:with-param name="showkeys" select="@k"/>
</xsl:apply-templates>
</xsl:template>

<xsl:template match="Column">
<xsl:value-of select="."/><xsl:text> </xsl:text>
</xsl:template>

<xsl:template match="Head/Unit">&#32;(<xsl:value-of select="."/>)</xsl:template>

<xsl:template match="Head">
<xsl:apply-templates select="Columns/Column[@type='year' or @type='calc']"/>
<xsl:text>
</xsl:text>
</xsl:template>

<xsl:template match="Table">
<xsl:apply-templates select="Body/RowSet" mode="selectrows"/>
</xsl:template>


<xsl:template match="Row">
<xsl:param name="showkeys" select="-1"/>
<xsl:variable name="keystr">
<xsl:apply-templates select="Key[number(@num) &gt; number($showkeys)]"/></xsl:variable>
<xsl:variable name="keystr1" select="translate($keystr,$quote,$bquote)"/>
<xsl:text>'</xsl:text>
<xsl:value-of select="$keystr1"/>
<xsl:text>' </xsl:text>
<xsl:apply-templates select="V"/>
<xsl:text>
</xsl:text>
</xsl:template>

<xsl:template match="Row" mode="titles">
<xsl:param name="showkeys" select="-1"/>
<xsl:variable name="keystr">
<xsl:apply-templates select="Key[number(@num) &gt; number($showkeys)]"/>
</xsl:variable>
<xsl:variable name="keystr1" select="translate($keystr,$quote,$bquote)"/>
#param s<xsl:value-of select="position()"/>_title="<xsl:value-of select="$keystr1"/>"
#param s<xsl:value-of select="position()"/>_show=on
<xsl:text>
</xsl:text>
</xsl:template>

<xsl:template match="Key">
<xsl:value-of select="."/><xsl:if test="position()!=last()">.</xsl:if>
</xsl:template>

<xsl:template match="V">
<xsl:text> </xsl:text>
<xsl:value-of select="round(@n)"/>
</xsl:template>

</xsl:stylesheet>
