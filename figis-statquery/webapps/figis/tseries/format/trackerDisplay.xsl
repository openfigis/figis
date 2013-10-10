<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:ags="http://www.purl.org/agmes/1.1/"  xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:fi="http://www.fao.org/fi/figis/devcon/"  xmlns:fint="http://www.fao.org/fi/figis/internal/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"   exclude-result-prefixes="ags  dc fi  fint rdf xsl ">
	<xsl:template name="trackerDisplay.trackerDisplayContent">
		<div style="display: none;" id="tracker">
			<table cellspacing="0" cellpadding="5">
				<xsl:for-each select="$trackerName/SQParams/SQKeyParams">
					<tr>
						<td class="trackerLabel" width="35%">
							<xsl:value-of select="@Label"/>
						</td>
						<td class="trackerSelection" width="65%">
							<xsl:choose>
								<xsl:when test="Selection/ID">
									<xsl:for-each select="Selection/ID">
										<xsl:value-of select="@Name"/>
										<br/>
									</xsl:for-each>
								</xsl:when>
								<xsl:otherwise>All</xsl:otherwise>
							</xsl:choose>
						</td>
					</tr>
				</xsl:for-each>
				<tr>
					<td class="trackerLabel">Years selected</td>
					<td class="trackerSelection">
						<xsl:choose>
							<xsl:when test="$trackerName/SQParams/Year">
								<xsl:for-each select="$trackerName/SQParams/Year">
									<xsl:value-of select="."/>&#160;
														</xsl:for-each>
							</xsl:when>
							<xsl:otherwise>All</xsl:otherwise>
						</xsl:choose>
					</td>
				</tr>
			</table>
		</div>
	</xsl:template>
</xsl:stylesheet>
