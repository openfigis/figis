<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:figis="http:--www.fao.org/figis">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:variable name="xm_totals" select="document('totals.xml')/SQServlet/Table/Body"/>  	<!-- Total rows of fishing area -->
	<xsl:variable name="xm_locals" select="document('LocalNames.xml')/LocalNames"/>	   	<!-- Species local names -->
	<xsl:variable name="xm_ns" select="document('NsCodes.xml')/ns_codes/ns1"/>	  	<!--  ISSCAAP Codes by -->
	<xsl:variable name="cd_un_code" select="/SQServlet/Table/Body/RowSet/RowSet/Row/Key[4]"/> <!-- Country UN code -->	
	<!-- ======================= -->
	<!--  START OF PROCESSING       -->
	<!-- ======================= -->
	<xsl:template match="/">
		<xsl:if test="count(/SQServlet/Table) &gt; 0">  		<!-- Check if rows in <Table> are greater than 0 -->
			<xsl:apply-templates select="SQServlet"/>
		</xsl:if>
	</xsl:template>
	<!-- ================================= -->
	<!-- TEMPLATE OF QUESTIONNAIRE HEADER  -->
	<!-- ================================= --> 
	<xsl:template match="SQServlet">
		<figis:QUESTIONNAIRE ID="NS1" VOLUME=" ">
			<xsl:attribute name="VOLUME"><xsl:value-of select="/SQServlet/Table/Body/RowSet/RowSet/Row/Key[1] "/></xsl:attribute>
			<xsl:attribute name="YEAR"><xsl:value-of select="(concat(/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)-6] ,'-',/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)]))"/></xsl:attribute>
			<figis:PageHeaderGroup>
				<figis:PageHeader ID="UNC">
					<xsl:value-of select="/SQServlet/Table/Body/RowSet/RowSet/RowSet/RowSet/Row/Key[4]"/>
				</figis:PageHeader>
				<figis:PageHeader LANG="EN" ID="CNT">
					<xsl:value-of select="/SQServlet/Table/Body/RowSet/RowSet/RowSet/RowSet/Row/Key[1]"/>
				</figis:PageHeader>
				<figis:PageHeader LANG="FR" ID="CNT">
					<xsl:value-of select="/SQServlet/Table/Body/RowSet/RowSet/RowSet/RowSet/Row/Key[2]"/>
				</figis:PageHeader>
				<figis:PageHeader LANG="ES" ID="CNT">
					<xsl:value-of select="/SQServlet/Table/Body/RowSet/RowSet/RowSet/RowSet/Row/Key[3]"/>
				</figis:PageHeader>
			</figis:PageHeaderGroup>
			<figis:TableTitleGroup>
				<figis:TableTitle>NS-X</figis:TableTitle>
				<figis:TableTitle ID="ORG" LANG="EN">FAO</figis:TableTitle>
				<figis:TableTitle ID="COL" LANG="FR">Fishstat</figis:TableTitle>
				<figis:TableTitle ID="KND" LANG="ES">NS 1</figis:TableTitle>
				<figis:TableTitle ID="TYP" LANG="EN">Fish, crustaceans, mollusc, etc</figis:TableTitle>
				<figis:TableTitle ID="TYP" LANG="FR">Poissons, crustacés, mollusques, etc</figis:TableTitle>
				<figis:TableTitle ID="TYP" LANG="ES">Peces, crustáceos, moluscos, etc</figis:TableTitle>
				<figis:TableTitle ID="MTH" LANG="EN">Aquaculture production by species items and major fishing areas</figis:TableTitle>
				<figis:TableTitle ID="MTH" LANG="FR">Production de l'áquaculture par espèce et zones</figis:TableTitle>
				<figis:TableTitle ID="MTH" LANG="ES">Producción de l'acuicultura por especies y áreas</figis:TableTitle>
			</figis:TableTitleGroup>
			<figis:TableHeaderGroup>
				<!-- ============================ -->
				<!-- HEADER COLUMNS DESCRIPTIONS -->
				<!-- ============================ -->
				<figis:TableHeader ID="SPC" LANG="EN">Species item</figis:TableHeader>
				<figis:TableHeader ID="SPC" LANG="FR">Catégorie d'espèce</figis:TableHeader>
				<figis:TableHeader ID="SPC" LANG="ES">Partida de especie</figis:TableHeader>
				<figis:TableHeader ID="WAR" LANG="EN">Fishing area</figis:TableHeader>
				<figis:TableHeader ID="WAR" LANG="FR">Zone de pêche</figis:TableHeader>
				<figis:TableHeader ID="WAR" LANG="ES">Area de pesca</figis:TableHeader>
				<figis:TableHeader ID="MUN" LANG="EN">Metric tons</figis:TableHeader>
				<figis:TableHeader ID="MUN" LANG="FR">Tonnes métriques</figis:TableHeader>
				<figis:TableHeader ID="MUN" LANG="ES">Toneladas métricas</figis:TableHeader>
				<!-- ====================== -->
				<!--  GET YEARS FOR HEADER -->
				<!-- ====================== -->
				<figis:TableHeader ID="YR1">
					<xsl:value-of select="/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)-6] "/>
				</figis:TableHeader>
				<figis:TableHeader ID="YR2">
					<xsl:value-of select="/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)-5] "/>
				</figis:TableHeader>
				<figis:TableHeader ID="YR3">
					<xsl:value-of select="/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)-4] "/>
				</figis:TableHeader>
				<figis:TableHeader ID="YR4">
					<xsl:value-of select="/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)-3] "/>
				</figis:TableHeader>
				<figis:TableHeader ID="YR5">
					<xsl:value-of select="/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)-2] "/>
				</figis:TableHeader>
				<figis:TableHeader ID="YR6">
					<xsl:value-of select="/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)-1] "/>
				</figis:TableHeader>
				<figis:TableHeader ID="YR7">
					<xsl:value-of select="/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)] "/>
				</figis:TableHeader>
				<figis:TableHeader ID="YR8">
					<xsl:value-of select="/SQServlet/SQParams/Year[count(/SQServlet/SQParams/Year)]+1"/>
				</figis:TableHeader>
			</figis:TableHeaderGroup>
			<!-- ======================= -->
			<!-- TABLE ROWS PROCESSING  -->
			<!-- ======================= -->
			<xsl:apply-templates select="Table"/>
		</figis:QUESTIONNAIRE>
	</xsl:template>
<!-- ====================================  -->
<!--  TEMPLATE FOR TABLE ROWS PROCESSING  -->
<!-- ====================================  --> 
	<xsl:template match="Table">
		<figis:Table ID="AGT">
			<figis:Footnote LANG="EN"/>
			<figis:Footnote LANG="FR"/>
			<figis:Footnote LANG="ES"/>
			<figis:RowSet ID="AGG">
				<figis:RowSetTitle ID="SPC" LANG="EN">Total all items</figis:RowSetTitle>
				<figis:RowSetTitle ID="SPC" LANG="FR">Total toutes catégories</figis:RowSetTitle>
				<figis:RowSetTitle ID="SPC" LANG="ES">Total todas partidas</figis:RowSetTitle>
<!--  ============================================  -->				
<!--  PROCESS ROWS IN xm_totals.xml FOR  TOTALS PAGE -->
<!--  ============================================  -->
				<xsl:apply-templates select="$xm_totals/RowSet/RowSet/Row">  <!-- PRODUCE ROWS OF FISHING AREA TOTALS  -->
					<xsl:with-param name="context" select="'tot'"/> 				<!-- SET PARAMETER FOR TEMPLATE ROW = tot -->
				</xsl:apply-templates>											<!-- TO PROCESS Totals  -->
				<xsl:if test="count($xm_totals/RowSet/RowSet/Row)=1">
					<figis:TableTotalRow>
						<figis:value SYMB=" ">SUM</figis:value>
						<xsl:apply-templates select="$xm_totals/RowSet/RowSet/Row/V"/> <!-- PRODUCE SUM ROW -->
					</figis:TableTotalRow>
				</xsl:if>
			</figis:RowSet>
		</figis:Table>
<!-- =======================  -->		
<!--  PROCESSING DETAIL ROWS -->
<!-- ======================= -->
		<figis:Table ID="ALL">
			<xsl:apply-templates select="Body"/>
		</figis:Table>
	</xsl:template>
<!-- ====================== -->
<!-- processing OF Body ROWS -->
<!-- ====================== -->	
	<xsl:template match="Body">
		<xsl:apply-templates select="RowSet"/>
	</xsl:template>
<!-- ===========================================  -->
<!-- TEMPLATE THAT PROCESSES THE RowSet  elements  -->
<!-- ===========================================  --> 
<xsl:template match="RowSet">
		<xsl:choose>
			<xsl:when test="@k='-1'">								<!-- RowSet of Higher position -->
				<xsl:apply-templates select="RowSet"/>				
			</xsl:when>
			<xsl:when test="@k='0'">								<!-- RowSet within RowSet -1 -->
				<xsl:apply-templates select="RowSet"/>
			</xsl:when>
			<xsl:when test="@k='1'">								<!-- RowSet within RowSet -0 -->
				<xsl:apply-templates select="RowSet"/>
			</xsl:when>
			<xsl:when test="@k='2'">
				<xsl:apply-templates select="RowSet">
					<xsl:sort select="Row/Key[16]"/>   					 <!--  SORT BY TAXONOMIC CODE - is 16th key of Row of RowSet=2 -->
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="@k='3' ">
				<figis:RowSet ID="TSR">
					<figis:RowSetTitle ID="SPC" LANG="SN">  
						<xsl:value-of select="Row/Key[11]"/>  			<!-- SPECIES SCIENTIFIC NAME  -->
					</figis:RowSetTitle>
					<figis:RowSetTitle ID="SPC" LANG="EN">				<!-- SPECIES ENGLISH NAME  -->
						<xsl:value-of select="Row/Key[12]"/>
					</figis:RowSetTitle>
					<figis:RowSetTitle ID="SPC" LANG="FR">				<!-- SPECIES FRENCH  NAME  -->
						<xsl:value-of select="Row/Key[13]"/>
					</figis:RowSetTitle>
					<figis:RowSetTitle ID="SPC" LANG="ES">				<!-- SPECIES SPANISH NAME  -->
						<xsl:value-of select="Row/Key[14]"/>
					</figis:RowSetTitle>
					<figis:RowSetTitle ID="3AC">						<!-- SPECIES TRI-ALFA CODE  --> 
						<xsl:value-of select="Row/Key[15]"/>
					</figis:RowSetTitle>
					<figis:RowSetTitle ID="ISG">						
						<xsl:value-of select="Row/Key[9]"/>				<!-- ISSCAAP GROUP CODE -->
					</figis:RowSetTitle>
					<figis:RowSetTitle ID="TAX">						<!-- SPECIES TAXONOMIC CODE -->
						<xsl:value-of select="Row/Key[16]"/>
					</figis:RowSetTitle>
					<figis:RowSetTitle ID="LCN">							<!-- SPECIES LOCAL NAME  -->
						<xsl:variable name="cd_tax" select="Row/Key[16]"/>
						<xsl:value-of select="$xm_locals/name[@tax=$cd_tax and @cnt = $cd_un_code]"/>
					</figis:RowSetTitle>
					<xsl:apply-templates select="Row">				     	<!--  ROW  TEMPLATE -->
						<xsl:with-param name="context" select="'tab'"/>	<!-- SET PARAMETER TO tab FOR TEMPLATE Row -->
					</xsl:apply-templates>								<!-- TO PROCESS DETAILS -->
					<xsl:if test="count(Row)=1">						<!-- IF ONLY ONE FISHING AREA IS PRESENT FOR A SPECIES -->
						<figis:TableTotalRow>							<!-- THEN THERE IS NO TOTAL ROW PRESENT AND THE SPECIES -->
							<figis:value SYMB=" ">SUM</figis:value>		<!-- TOTAL IS PRODUCED USING THE DETAIL ROW QUANTITIES -->
							<xsl:apply-templates select="Row/V"/>
							<figis:value ID="YR8"/>
						</figis:TableTotalRow>
					</xsl:if>
				</figis:RowSet>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
<!-- ===============  -->	
<!--  PROCESS  ROWS  -->
<!-- ===============  -->
<xsl:template match="Row">
		<xsl:param name="context"/>
		<xsl:choose>
			<xsl:when test="$context='tab' and @tot=16 ">   				<!-- PROCESS   SPECIES TOTAL - SUM ROW -->   
				<figis:TableTotalRow>
					<figis:value SYMB=" ">SUM</figis:value>
					<xsl:apply-templates select="V"/>					<!-- QUANTITIES  TOTALS-->
					<figis:value ID="YR8"/>								<!-- CURRENT YEAR QUANTITY = BLANK --> 
				</figis:TableTotalRow>
			</xsl:when>
			<xsl:when test="$context='tab' and  @leaf=1 ">				<!-- PROCESS DETAIL ROWS -->
				<figis:TableRow>
					<figis:value SYMB=" ">
						<xsl:value-of select="concat(Key[17], Key[20])"/>			<!-- ENVIRONMENT/FISHING AREA -->
					</figis:value>
					<xsl:apply-templates select="V"/>					<!-- QUANTITIES -->
					<figis:value ID="YR8"/>								<!-- CURRENT YEAR QUANTITY = BLANK  -->
				</figis:TableRow>
			</xsl:when>
			<xsl:when test="$context='tot'  and  @tot=4  ">                       <!-- PROCESS GRANTOTAL ROW -->
				<figis:TableTotalRow>
					<figis:value SYMB=" ">
						<xsl:value-of select="'SUM'"/>
					</figis:value>
					<xsl:apply-templates select="V"/>					<!--  PROCESS QUANTITIES  -->
					<figis:value ID="YR8"/>
				</figis:TableTotalRow>
			</xsl:when>
			<xsl:when test="$context='tot'  and  @leaf=1">				<!-- PROCESS FISHING AREA TOTALS  -->
				<figis:TableRow>
					<figis:value SYMB=" ">
						<xsl:value-of select="./Key[5]"/>					<!-- FISHING AREA CODE  -->
					</figis:value>
					<xsl:apply-templates select="V"/>					<!--  PROCESS QUANTITIES -->
					<figis:value ID="YR8"/>
				</figis:TableRow>
			</xsl:when>
		</xsl:choose>
</xsl:template>
<!-- ========================================================================= -->
<!--  PROCESS LAST 7 YEARS QUANTITIES  - THE COMPLETE TIME SERIES IS OF 30 ELEMENTS -->
<!--  TEN YEARS ARE SELECTED BY THE QUERY USING element &YearBookYears                     -->
<!-- ========================================================================= -->
	<xsl:template match="V">
		<!--=== Here I wanto to kake just the last 7 rows. So from the 24th to the last one===-->
		<xsl:if test="position() &gt; 3">
			<figis:value>
				<xsl:attribute name="ID"><xsl:value-of select="concat('YR' , position()-3)"/></xsl:attribute>  	<!-- SET ID to "YR"+ "1÷7"  -->  
				<xsl:attribute name="SYMB"><xsl:value-of select="@s"/></xsl:attribute>			<!-- GET SYMBOL VALUE  -->
				<xsl:value-of select="@n"/>													<!--  GET QUANTITIES  -->
			</figis:value>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>
