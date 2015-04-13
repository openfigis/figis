
<fi:FigisID>22090</fi:FigisID>
<fi:OrgRef>
<fi:FigisID MetaID="104000">22090</fi:FigisID>
</fi:OrgRef>

select * from md_refobject  
where id = 104000

select * from md_classinit 
where id = 104000
or init_xml like '%104000%'

select * from ref_institute_full
where cd_institute = 22090


<fi:CollectionRef>
<fi:FigisID MetaID="264000">2290</fi:FigisID>
</fi:CollectionRef>

select * from md_refobject  
where id = 264000

select * from REF_DATA_COLLECTION
where cd_collection = 2290


select * from redirect_map
where from_url like '%copescal%'
or to_url like '%copescal%'

select count(*) from fs_observation
where cd_collection = 2290
--52


select count(*) from  fs_organization_observation o, fs_observation obs
where o.cd_observation = obs.cd_observation
and cd_collection = 2290
--52

select xml from  fs_organization_observation o, fs_observation obs, fs_observation_xml xml
where o.cd_observation = obs.cd_observation
and cd_collection = 2290
and obs.cd_observation = xml.cd_observation 
and cd_language = 1
and fg_status = 2
--52











