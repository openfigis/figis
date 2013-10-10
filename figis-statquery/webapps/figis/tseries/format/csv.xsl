<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"
  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"
  xmlns:fint="http://www.fao.org/fi/figis/internal/"
  xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
  <xsl:output indent="no" omit-xml-declaration="yes" method="text"/>
  <xsl:strip-space elements="*"/>
  <xsl:include href="paging.xsl"/>

  <xsl:param name="xp_tab" select="1"/>
  <xsl:param name="xp_maxpage" select="100000"/>
  <xsl:param name="xp_title" select="0"/>
  <xsl:param name="xp_hdrrow" select="1"/>

  <!--
   Originally this variable was used in correspondence with a checkbox in the panel, that was suppressed 
   such checkbox determined if the user wants to see the symbols or not, and was in correspondence of 
   one of the SQParams fields. Now the variable is still referenced in the XSLT, but it was placed fix to "1"
  <xsl:variable name="symb" select="//SQParams/ShowFlags = '1'"/>
   -->
  <xsl:variable name="symb" select="1"/>
  

  <xsl:template match="SQServlet">
    <xsl:apply-templates select="Table[position()=$xp_tab]"/>
  </xsl:template>

  <xsl:template match="Head/Unit">&#32;(<xsl:value-of select="."/>)</xsl:template>

  <xsl:template match="Table">
    <xsl:if test="$xp_title='1'">
      <xsl:text>"</xsl:text>
      <xsl:value-of select="Head/Dataset"/> : <xsl:value-of select="Head/Series"/>
      <xsl:apply-templates select="Head/Unit"/>
      <xsl:text>"</xsl:text>
      <xsl:text>
</xsl:text>
    </xsl:if>
    <xsl:apply-templates select="Head"/>
    <xsl:apply-templates select="Body"/>
  </xsl:template>

  <xsl:template match="Body">
    <xsl:apply-templates select="RowSet" mode="selectrows"/>
  </xsl:template>

  <xsl:template match="RowSet" mode="displayrows">
    <xsl:apply-templates select=".//Row[@leaf]"/>
  </xsl:template>

  <xsl:template match="Head">
    <xsl:if test="$xp_hdrrow='1'">
      <xsl:apply-templates select="Columns/Column"/>
      <xsl:text>
</xsl:text>
    </xsl:if>
  </xsl:template>

  <xsl:template match="Row">
    <xsl:apply-templates/>
    <xsl:text>
</xsl:text>
  </xsl:template>


  <xsl:template match="Column[@type!='year' and @type!='calc']">
    <xsl:text>"</xsl:text>
    <xsl:value-of select="."/>
    <xsl:text>",</xsl:text>
  </xsl:template>

  <xsl:template match="Column[@type='year' or @type='calc']">
    <xsl:text>"</xsl:text>
    <xsl:value-of select="."/>
    <xsl:text>"</xsl:text>
    <xsl:if test="$symb">
      <xsl:text>,"S_</xsl:text>
      <xsl:value-of select="."/>
      <xsl:text>"</xsl:text>
    </xsl:if>
    <xsl:call-template name="comma"/>
  </xsl:template>

  <xsl:template name="comma">
    <xsl:if test="position()!=last()">,</xsl:if>
  </xsl:template>

  <xsl:template match="Key|EmptyKey">
    <xsl:text>"</xsl:text>
    <xsl:value-of select="."/>
    <xsl:text>",</xsl:text>
  </xsl:template>

  <xsl:template match="V">
    <xsl:text>"</xsl:text>
    <xsl:value-of select="@n"/>
    <xsl:text>"</xsl:text>
    <xsl:if test="$symb">
      <xsl:text>,"</xsl:text>
      <xsl:value-of select="@s"/>
      <xsl:text>"</xsl:text>
    </xsl:if>
    <xsl:call-template name="comma"/>
  </xsl:template>

</xsl:stylesheet>
