select count(*) 
from fs_resource_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and reporting_year != '0000R'
-- dev 549 
-- qa 506
-- prod 997

select count(*) 
from fs_fishery_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and reporting_year != '0000R'
--dev 36
-- qa 25
-- prod 50


select count(*) 
from fs_resource_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and reporting_year = '0000R'
-- dev 35
-- qa 35
-- prod 35


select  count(*) 
from fs_fishery_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and reporting_year = '0000R'
-- dev 0
-- qa 0
-- prod 0




-- This query may have touched too many factsheets
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
-- qa 506
-- prod 997 updated


UPDATE fs_observation_xml x
SET x.xml = 
(
select replace(x.xml, substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>'), 41),  '<fi:ReportingYear>' || ro.reporting_year || '</fi:ReportingYear>')
from fs_fishery_observation ro
where ro.cd_observation = x.cd_observation 
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and x.xml like  '%<fi:ReportingYear>%'    
)
where x.cd_observation in
(
select ro.cd_observation
from fs_fishery_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 
and x.xml not like  '%<fi:ReportingYear>' ||  ro.reporting_year ||'</fi:ReportingYear>%'
and x.xml like  '%<fi:ReportingYear>%'
)
-- dev 36 rows
-- qa 25 rows 
-- prod 50 rows updated.



-- only necessary for resource: 35 cases


select x.xml, substr(x.xml, 0, INSTR(x.xml, '</fi:ReferenceYear>') + 19) ||  '<fi:ReportingYear>0000R</fi:ReportingYear>' || substr(x.xml, INSTR(x.xml, '</fi:ReferenceYear>') + 19)
from fs_resource_observation ro, fs_observation_xml x
where ro.cd_observation = x.cd_observation 
and reporting_year = '0000R'
and x.xml not like  '%<fi:ReportingYear>%'    
and x.xml like  '%<fi:ReferenceYear>%'    





UPDATE fs_observation_xml x
SET x.xml = 
(
select substr(x.xml, 0, INSTR(x.xml, '</fi:ReferenceYear>') + 19) ||  '<fi:ReportingYear>0000R</fi:ReportingYear>' || substr(x.xml, INSTR(x.xml, '</fi:ReferenceYear>') + 19)
from fs_resource_observation ro
where ro.cd_observation = x.cd_observation 
and reporting_year = '0000R'
and x.xml not like  '%<fi:ReportingYear>%'    
and x.xml like  '%<fi:ReferenceYear>%'    
)
where x.cd_observation in
( select ro.cd_observation
  from fs_resource_observation ro, fs_observation_xml x
  where ro.cd_observation = x.cd_observation 
  and reporting_year = '0000R'
  and x.xml not like  '%<fi:ReportingYear>%'    
  and x.xml like  '%<fi:ReferenceYear>%'    
)
-- dev, prod, qa, 35 rows updated. 






