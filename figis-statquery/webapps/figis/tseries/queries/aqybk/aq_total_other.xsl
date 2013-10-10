<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"    exclude-result-prefixes="ags  dc fi  fint rdf xsl ">

<xsl:output method="xml" encoding="UTF-8" indent="yes"/>

<!-- <xsl:param name="xp_limit" select="60000"/> defined in main file -->

<xsl:variable name="SelectedRows" select="//Row[@leaf='1' and @ser='Quantity' and number(V[10]/@n) &gt;= number($xp_limit)]"/>
<xsl:variable name="OtherRows" select="//Row[@leaf='1' and @ser='Quantity' and number(V[10]/@n) &lt; number($xp_limit)]"/>

<xsl:variable name="limit_str" select="format-number($xp_limit,'# ##0','spaced')"/>

<xsl:template name="total_selected">
	<xsl:apply-templates select="Row[@tot='0' and @ser='Quantity']" mode="tworows">
		<xsl:with-param name="elementname" select="'figis:aggregaterow'"/>
		<xsl:with-param name="keyE" select="'World Total'"/>
		<xsl:with-param name="keyF" select="'Total mondial'"/>
		<xsl:with-param name="keyS" select="'Total mundial'"/>
	</xsl:apply-templates>
	<xsl:apply-templates select="$SelectedRows" mode="tworows">
		<xsl:sort select="V[10]/@n" data-type="number" order="descending"/>
		<xsl:with-param name="elementname" select="'figis:tablerow'"/>
		<xsl:with-param name="keyNo" select="1"/>
		<xsl:with-param name="keyE" select="'_auto_'"/>
		<xsl:with-param name="keyF" select="'_none_'"/>
		<xsl:with-param name="keyS" select="'_skip_'"/>
	</xsl:apply-templates>
	<xsl:apply-templates select="." mode="others"/>
</xsl:template>

<xsl:template name="totalrows">
	<xsl:param name="RowSet" select="$OtherRows"/>
	<xsl:param name="KeyE" select="'Other countries'"/>
	<xsl:param name="KeyF" select="'Autres pays'"/>
	<xsl:param name="KeyS" select="'Otros países'"/>
	<figis:tablerow>
		<figis:RowTitle LANG="EN"><xsl:value-of select="$KeyE"/></figis:RowTitle>
		<figis:RowType>Q</figis:RowType>
		<xsl:for-each select="//Head/Columns/Column[@type='year']">
			<xsl:variable name="p" select="position()"/>
			<xsl:variable name="s" select="sum($OtherRows/V[$p]/@n)"/>
			<xsl:call-template name="value">
				<xsl:with-param name="n" select="$s"/>
			</xsl:call-template>		
		</xsl:for-each>		
	</figis:tablerow>
	<figis:tablerow>
		<figis:RowTitle LANG="FR"><xsl:value-of select="$KeyF"/></figis:RowTitle>
		<figis:RowType>V</figis:RowType>
		<xsl:for-each select="//Head/Columns/Column[@type='year']">
			<xsl:variable name="p" select="position()"/>
			<xsl:variable name="s" select="sum($OtherRows/following-sibling::Row[1]/V[$p]/@n)"/>
			<xsl:call-template name="value">
				<xsl:with-param name="n" select="$s"/>
				<xsl:with-param name="s" select="''"/>
			</xsl:call-template>		
		</xsl:for-each>		
	</figis:tablerow>
	<figis:tablerow>
		<figis:RowTitle LANG="ES"><xsl:value-of select="$KeyS"/></figis:RowTitle>
		<figis:RowType/>
	</figis:tablerow>
</xsl:template>

</xsl:stylesheet>
