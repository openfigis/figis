<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
<xsl:strip-space elements="*"/>

<xsl:param name="xp_p1" select="-1"/>
<xsl:param name="xp_p2" select="-1"/>
<xsl:param name="xp_p3" select="-1"/>
<xsl:param name="xp_p4" select="-1"/>
<xsl:param name="xp_p5" select="-1"/>


<xsl:template name="curpage">
	<xsl:param name="level" select="@k + 2"/>
	<xsl:variable name="pageparam">
		<xsl:if test="$level=1"><xsl:value-of select="$xp_p1"/></xsl:if>
		<xsl:if test="$level=2"><xsl:value-of select="$xp_p2"/></xsl:if>
		<xsl:if test="$level=3"><xsl:value-of select="$xp_p3"/></xsl:if>
		<xsl:if test="$level=4"><xsl:value-of select="$xp_p4"/></xsl:if>
    		<xsl:if test="$level=5"><xsl:value-of select="$xp_p5"/></xsl:if>
  	</xsl:variable>
	<xsl:choose>
    		<xsl:when test="number($pageparam) &gt;= 0"><xsl:value-of select="$pageparam"/></xsl:when>
    		<xsl:when test="count(.//Row[@leaf]) &gt; $xp_maxpage">1</xsl:when>
    		<xsl:otherwise>0</xsl:otherwise>
 	</xsl:choose>
</xsl:template>


<!-- this is to select an appropriate rowset according to paging
 parameters. We do it in all formats: HTML, CSV and DAT
-->
<xsl:template match="RowSet" mode="selectrows">
  	<!-- current page on this level: xp_p1..xp_p5 -->
  	<xsl:variable name="page"><xsl:call-template name="curpage"/></xsl:variable>
       
        <xsl:choose>
	   <!-- if we need to go deeper -->
	   <xsl:when test="($page != 0) and (RowSet[position()=$page])">
		<xsl:apply-templates select="RowSet[position()=$page]" mode="selectrows"/>
	   </xsl:when>
	   <!-- if current page = 0 (all pages), or no children - display rows in this RowSet -->
           <xsl:otherwise>           
		<xsl:apply-templates select="." mode="displayrows"/>
           </xsl:otherwise>
        </xsl:choose>
</xsl:template>

</xsl:stylesheet>
