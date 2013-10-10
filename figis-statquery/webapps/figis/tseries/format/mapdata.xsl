<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
<xsl:output indent="no" omit-xml-declaration="yes" method="text"/>
<xsl:strip-space elements="*"/>
<xsl:include href="paging.xsl"/>

<xsl:param name="xp_tab" select="1"/>
<xsl:param name="xp_maxpage" select="10000000"/>
<xsl:param name="xp_col" select="round(count
    (//Table[1]/Head/Columns/Column[@type='calc' or @type='year']) div 2)"/>

<xsl:param name="colNo" select="number($xp_col)"/>

<xsl:variable name="AreaKey" select="number(//Map/Column)"/>

<xsl:template match="SQServlet">
  <xsl:apply-templates select="Table[position()=$xp_tab]"/>
</xsl:template>

<xsl:template match="Table">
  <xsl:apply-templates select="Body"/>
</xsl:template>

<xsl:template match="Body">
  <xsl:apply-templates select="RowSet" mode="selectrows"/>
</xsl:template>

<xsl:template name="calcsum">
<xsl:param name="keys"/>
<xsl:text>"</xsl:text>
<xsl:value-of select="substring-after(substring-after($keys[1],':'),':')"/>
<xsl:text>","</xsl:text>
<xsl:value-of select="sum($keys/../../V[$colNo]/@n)"/>
<xsl:text>"
</xsl:text>
</xsl:template>

<xsl:template name="sumunique">
  <!-- we prefer to work directly on keys rather than on nodes, as this is faster -->
  <xsl:param name="keys"/>
  <xsl:if test="count($keys)!=0">
    <!-- select any key from the middle -->
    <xsl:variable name="middlekey" select="$keys[round(count($keys) div 2)]"/>
    <!-- select its id -->
    <xsl:variable name="middlekeytr" select="translate($middlekey,':','')"/>
    <!-- calculate and output sum for all nodes with the same ID -->
    <xsl:call-template name="calcsum">
      <xsl:with-param name="keys" 
         select="$keys[. = $middlekey]"/>
    </xsl:call-template>
    <!-- recurse for the nodes whose IDs are less than selected one -->
    <!-- we started from middle node avoiding the case when they are already sorted -->
    <!-- this way recursion is not so deep -->
    <xsl:call-template name="sumunique">
      <xsl:with-param name="keys" 
         select="$keys[translate(.,':','') &lt; $middlekeytr]"/>
    </xsl:call-template>
    <!-- and for those whose IDs are greater than selected one -->
    <xsl:call-template name="sumunique">
      <xsl:with-param name="keys" 
         select="$keys[translate(.,':','') &gt; $middlekeytr]"/>
    </xsl:call-template>
  </xsl:if>
</xsl:template>

<xsl:template match="RowSet" mode="displayrows">
  <xsl:call-template name="sumunique">
    <xsl:with-param name="keys" select=".//Row[@leaf and number(V[number($colNo)]/@n) &gt; 0]/Key[$AreaKey]/@gid"/>
  </xsl:call-template>
</xsl:template>

</xsl:stylesheet>
