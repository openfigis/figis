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
-- devel 1162, qa 1176, prod 1193


-- this one can only run in prod
select count(*) from fs_resource_observation ro, fs_observation_xml x, FIGIS_JAN_BCK.FS_OBSERVATION_XML xb
where ro.cd_observation = x.cd_observation 
and ro.cd_observation = xb.cd_observation 
and INSTR(x.xml, '<fi:ReportingYear>') > 0
and INSTR(x.xml, '<fi:ReferenceYear>') > 0
and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) like ro.reporting_year
and substr(x.xml, INSTR(x.xml, '<fi:ReportingYear>') +18 , 4) not like '0000'
--1189 prod 

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





