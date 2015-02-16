select count(*)
  from fs_resource_observation ro, fs_observation_xml x
  where ro.cd_observation = x.cd_observation 
  and reporting_year = '0000R'
  and x.xml not like  '%<fi:ReportingYear>%'    
  and x.xml like  '%<fi:ReferenceYear>%'    
-- prod 0

