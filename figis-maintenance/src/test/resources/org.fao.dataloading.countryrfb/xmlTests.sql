select count(*) from fs_observation_xml
--5722

select count(distinct(cd_xml)) from fs_observation_xml
--5722

select * from fs_observation_xml

select xml.cd_observation , xml.xml  from figis.fs_observation_xml xml
where o.cd_observation = obs.cd_observation 