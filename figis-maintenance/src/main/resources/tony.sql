UPDATE fs_observation_xml x
SET x.xml = 
(
select replace(x.xml, substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>'), 41),  '<fi:ReportingYear>' || ro.reporting_year || '</fi:ReportingYear>')
from fs_resource_observation ro
where ro.cd_observation = x.cd_observation 
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and x.xml like  '%<fi:ReportingYear>%'    
)
where x.cd_observation in
(
select ro.cd_observation
from fs_resource_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and x.xml like  '%<fi:ReportingYear>%'
)
;

