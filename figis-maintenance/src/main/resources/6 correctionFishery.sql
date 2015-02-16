-- xb = xml backup 


select count(*) from fs_fishery_observation ro, fs_observation_xml x, fs_observation_xml xb
where ro.cd_observation = x.cd_observation 
and ro.cd_observation = xb.cd_observation 
and INSTR(x.xml, '<fi:ReportingYear>') > 0
and INSTR(x.xml, '<fi:ReferenceYear>') > 0
and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) like ro.reporting_year
and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) not like '0000'
and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
and x.cd_language = 1
and xb.cd_language = 1
-- devel 211, qa 218 , prod 228

-- this one can only run in prod
select count(*) from fs_fishery_observation ro, fs_observation_xml x, FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
where ro.cd_observation = x.cd_observation 
  and ro.cd_observation = xb.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and INSTR(x.xml, '<fi:ReferenceYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 1
  and xb.cd_language = 1
-- prod 228




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
CREATE TABLE fs_fish_obs_FIRMS_142
  AS (SELECT * FROM fs_fishery_observation)
-- prod table FS_RES_OBS_FIRMS_142 created.
select count(*)  from fs_fishery_observation
--731
select count(*)  from fs_fish_obs_FIRMS_142
--731

  









select count (*) from FS_OBSERVATION_XML
-- devel , qa, prod 5616
select count (*) from fs_observation_xml_FIRMS_142
--devel , qa , prod 5616


-- do not run in prod
update fs_fishery_observation ro 
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
-- devel 211 qa 218 , prod 228 rows updated.


select distinct reporting_year from fs_fishery_observation
-- only nice years plus 0000R



-- copy from above with the xb table pointing to the real backup table
-- can only be run in prod
update fs_fishery_observation ro 
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
--prod 228  rows updated.

-- now analyze how many records have been really affected (changed)
select count(*) from fs_fishery_observation ro, fs_fish_obs_FIRMS_142 b
where ro.cd_observation = b.cd_observation
and ro.cd_fishery = b.cd_fishery
and ro.reporting_year != b.reporting_year
-- prod 0

select distinct  x.cd_language from fs_fishery_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 

update fs_fishery_observation ro 
set ro.reporting_year = (
  select substr(xb.xml, INSTR(xb.xml, '<fi:ReportingYear>') + 18 , 4) from fs_observation_xml x,  FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
  where ro.cd_observation = x.cd_observation 
  and ro.cd_observation = xb.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and INSTR(x.xml, '<fi:ReferenceYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 2
  and xb.cd_language = 2
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
  and x.cd_language = 2
  and xb.cd_language = 2
)
-- 168 rows updated

select distinct  ro.reporting_year from fs_fishery_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 


select count(*) from fs_fishery_observation ro, fs_fish_obs_FIRMS_142 b
where ro.cd_observation = b.cd_observation
and ro.cd_fishery = b.cd_fishery
and ro.reporting_year != b.reporting_year
-- now there are 3 differences! 


-- check the consistency
select count(*) 
from fs_fishery_observation ro, fs_observation_xml x 
where ro.cd_observation = x.cd_observation 
and x.xml not like '%<fi:ReportingYear>' || ro.reporting_year ||'</fi:ReportingYear>%' 
and ro.reporting_year != '0000R' 
-- production 3 hits here


-- try to understand what the differences are, whether we need to repeat "3 CorrectionXML.sql" also for Fishery
select ro.reporting_year, substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4)
from fs_fishery_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 
and x.xml not like '%<fi:ReportingYear>' || ro.reporting_year ||'</fi:ReportingYear>%' 
and ro.reporting_year != '0000R' 

-- and yes, it needs to be applied, see 7











