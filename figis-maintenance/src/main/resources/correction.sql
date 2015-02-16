-- xb = xml backup 

select x.cd_language from fs_observation_xml x




select count(*) from fs_resource_observation ro, fs_observation_xml x, fs_observation_xml xb
where ro.cd_observation = x.cd_observation 
and ro.cd_observation = xb.cd_observation 
and INSTR(x.xml, '<fi:ReportingYear>') > 0
and INSTR(x.xml, '<fi:ReferenceYear>') > 0
and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) like ro.reporting_year
and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) not like '0000'
and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
and x.cd_language = 1
and xb.cd_language = 1
-- devel 1090, qa 1104, prod 1121

-- this one can only run in prod
select count(*) from fs_resource_observation ro, fs_observation_xml x, FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
where ro.cd_observation = x.cd_observation 
  and ro.cd_observation = xb.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and INSTR(x.xml, '<fi:ReferenceYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 1
  and xb.cd_language = 1
--1117 prod 




CREATE TABLE fs_observation_xml_FIRMS_142
  AS (SELECT * FROM fs_observation_xml)
-- Error report: SQL Command: table FS_OBSERVATION_XML_ERIK_BACKUP Failed: Warning: execution completed with warning  
-- qa and dev: 
-- Error report: SQL Command: table FS_OBSERVATION_XML_ERIK_BACKUP Failed: Warning: execution completed with warning  
-- prod:  
-- Error starting at line 37 in command: CREATE TABLE fs_observation_xml_FIRMS_142   AS (SELECT * FROM fs_observation_xml) Error report: SQL Command: table FS_OBSERVATION_XML_FIRMS_142 Failed: 
-- Warning: execution completed with warning

-- this bacup table is created before applying the below correction
-- in order to be able to compare and test the results
CREATE TABLE fs_res_obs_FIRMS_142
  AS (SELECT * FROM fs_resource_observation)
-- prod table FS_RES_OBS_FIRMS_142 created.
select count(*)  from fs_resource_observation
--2271
select count(*)  from FS_RES_OBS_FIRMS_142
--2271

  









select count (*) from FS_OBSERVATION_XML
-- devel 5722, qa 6437
select count (*) from fs_observation_xml_FIRMS_142
--devel 5722, qa 6437


-- do not run in prod
update fs_resource_observation ro 
set ro.reporting_year = (
  select substr(xb.xml, INSTR(xb.xml, '<fi:ReportingYear>') + 18 , 4) from fs_observation_xml x, fs_observation_xml xb
  where ro.cd_observation = x.cd_observation 
  and ro.cd_observation = xb.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and INSTR(x.xml, '<fi:ReferenceYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 1
  and xb.cd_language = 1
) 
where ro.cd_observation in
(
  select ro.cd_observation from fs_observation_xml x, fs_observation_xml xb
  where ro.cd_observation = x.cd_observation 
  and ro.cd_observation = xb.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and INSTR(x.xml, '<fi:ReferenceYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 1
  and xb.cd_language = 1
)
-- devel 1090 qa 1,104 rows updated.



-- copy from above with the xb table pointing to the real backup table
-- can only be run in prod
update fs_resource_observation ro 
set ro.reporting_year = (
  select substr(xb.xml, INSTR(xb.xml, '<fi:ReportingYear>') + 18 , 4) from fs_observation_xml x,  FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
  where ro.cd_observation = x.cd_observation 
  and ro.cd_observation = xb.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and INSTR(x.xml, '<fi:ReferenceYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 1
  and xb.cd_language = 1
) 
where ro.cd_observation in
(
  select ro.cd_observation from fs_observation_xml x, FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
  where ro.cd_observation = x.cd_observation 
  and ro.cd_observation = xb.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and INSTR(x.xml, '<fi:ReferenceYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 1
  and xb.cd_language = 1
)
--prod 1,117 rows updated.

-- now analyze how many records have been really affected (changed)
select count(*) from fs_resource_observation ro, FS_RES_OBS_FIRMS_142 b
where ro.cd_observation = b.cd_observation
and ro.cd_resource = b.cd_resource
and ro.reporting_year != b.reporting_year
-- prod 954
-- In analyses.sql, 997 xmls had been wrongly affected. With the above update statement, the table should now be correct. 
-- Those pieces of XML wrongly touched can now be correced by the CIO backup table ( FIGIS_JAN_BCK.FS_OBSERVATION_XML). 
-- See therefore correctionXML.sql











