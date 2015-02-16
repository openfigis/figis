select count(*) from fs_resource_observation ro ,  fs_observation_xml x,  FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
  where ro.cd_observation = x.cd_observation 
  and ro.cd_observation = xb.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and INSTR(x.xml, '<fi:ReferenceYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(xb.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 5) not like '2010b'
  and substr(x.xml, INSTR(xb.xml, '<fi:ReportingYear>') + 18 , 5) not like '2010b'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 2
  and xb.cd_language = 2
--15

select substr(xb.xml, INSTR(xb.xml, '<fi:ReportingYear>') + 18 , 4) , substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) 
from fs_resource_observation ro ,  fs_observation_xml x,  FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
  where ro.cd_observation = x.cd_observation 
  and ro.cd_observation = xb.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and INSTR(x.xml, '<fi:ReferenceYear>') > 0
  and substr(xb.xml, INSTR(xb.xml, '<fi:ReportingYear>') + 18 , 4) not like substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) 
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(xb.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 5) not like '2010b'
  and substr(x.xml, INSTR(xb.xml, '<fi:ReportingYear>') + 18 , 5) not like '2010b'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 2
  and xb.cd_language = 2
-- this proves that this is another problem, we don't need the backup table for that. 



select ro.reporting_year, substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) 
from fs_resource_observation ro ,  fs_observation_xml x
  where ro.cd_observation = x.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 5) not like '2010b'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 2
-- dev 0
-- qa 0



update fs_resource_observation ro 
set ro.reporting_year = (
select substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) 
from fs_observation_xml x
  where ro.cd_observation = x.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 5) not like '2010b'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 2
) 
where ro.cd_observation in
(
select ro.cd_observation
from fs_resource_observation ro ,  fs_observation_xml x
  where ro.cd_observation = x.cd_observation 
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 5) not like '2010b'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 2
)
-- dev, qa 0, prod 27




-- now do the  2010b stuff
select substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4)
from fs_resource_observation ro ,  fs_observation_xml x
  where ro.cd_observation = x.cd_observation 
  and ro.reporting_year = '2010b'
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 1




update fs_resource_observation ro 
set ro.reporting_year = (
select substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) 
from fs_observation_xml x
  where ro.cd_observation = x.cd_observation 
  and ro.reporting_year = '2010b'
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 1
) 
where ro.cd_observation in
(
select ro.cd_observation
from fs_resource_observation ro ,  fs_observation_xml x
  where ro.cd_observation = x.cd_observation 
  and ro.reporting_year = '2010b'
  and INSTR(x.xml, '<fi:ReportingYear>') > 0
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like ro.reporting_year
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') + 18 , 4) not like '0000'
  and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) is not null
  and x.cd_language = 1
)
-- 2 rows updated



select count(*) 
from fs_resource_observation ro, fs_observation_xml x 
where ro.cd_observation = x.cd_observation 
and x.xml not like '%<fi:ReportingYear>' || ro.reporting_year ||'</fi:ReportingYear>%' 
and ro.reporting_year != '0000R' 
-- prod 0, all consistent now. 










