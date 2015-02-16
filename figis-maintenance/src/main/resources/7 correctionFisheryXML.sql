-- 

select count(*) 
from fs_fishery_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and reporting_year != '0000R'
-- prod 3

select count(*) 
from fs_fishery_observation ro, fs_observation_xml x, FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
where ro.cd_observation = x.cd_observation 
and x.cd_observation = xb.cd_observation 
and x.cd_language = xb.cd_language
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and reporting_year != '0000R'
-- prod 3


select count(*) from fs_observation_xml_FIRMS_142
-- prod 5616
select count(*) from fs_observation_xml
-- prod 5616


select x.cd_language,  substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4)
from fs_fishery_observation ro, fs_observation_xml x, FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
where ro.cd_observation = x.cd_observation 
and x.cd_observation = xb.cd_observation 
and x.cd_language = xb.cd_language
and xb.xml like  '%<fi:ReportingYear>%'    
and ro.reporting_year != '0000R'
and ro.reporting_year not like substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4)
-- 50






UPDATE fs_observation_xml x
SET x.xml = 
(
select replace(x.xml,  substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>'), 41), substr(xb.xml, INSTR(xb.xml, '<fi:ReportingYear>'), 41) ) as manipulated
from fs_fishery_observation ro, FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
where ro.cd_observation = x.cd_observation 
and x.cd_observation = xb.cd_observation 
and x.cd_language = xb.cd_language
and xb.xml like  '%<fi:ReportingYear>%'    
and ro.reporting_year != '0000R'
and ro.reporting_year not like substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4)
)
where x.cd_observation in
(
select x.cd_observation
from fs_fishery_observation ro, fs_observation_xml x, FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
where ro.cd_observation = x.cd_observation 
and x.cd_observation = xb.cd_observation 
and x.cd_language = xb.cd_language
and xb.xml like  '%<fi:ReportingYear>%'    
and ro.reporting_year != '0000R'
and ro.reporting_year not like substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4)
)
--  3 rows updated.

select count(*) 
from fs_fishery_observation ro, fs_observation_xml x 
where ro.cd_observation = x.cd_observation 
and x.xml not like '%<fi:ReportingYear>' || ro.reporting_year ||'</fi:ReportingYear>%' 
and ro.reporting_year != '0000R' 
