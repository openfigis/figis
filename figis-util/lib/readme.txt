update 19 juni 2015
Downloaded the latest Oracle Database 12.1.0.2 JDBC Driver & UCP Downloads driver, in order to be sure:
Download	ojdbc7.jar (3,698,857 bytes) - (SHA1 Checksum: 7c9b5984b2c1e32e7c8cf3331df77f31e89e24c2) 
For use with JDK 7; It contains the JDBC driver classes except classes for NLS support in Oracle Object and Collection types.

vme				ojdbc60.jar
Geoserver 		ojdbc62.jar
Geoserver dev	ojdbc7.jar
figis			ojdbc5.jar	because by then, figis was on Java 5, now can be upgraded to ojdbc7.jar



Update 20 August 2014
See also vme/vme-service-integrationtest/lib/readme.txt
Starting to use ojdbc7.jar now also for VME


- Oracle Database 11g Release 2 JDBC Drivers
- Thank you for accepting the OTN License Agreement; you may now download this software.
- Oracle Database 11g Release 2 (11.2.0.4) JDBC Drivers
- Download	ojdbc5.jar (2,091,135 bytes) - Classes for use with JDK 1.5.
 It contains the JDBC driver classes, except classes for NLS support in Oracle Object and Collection types.
 
 13 February 2014 Erik van Ingen
 
Used initially version 6 but that one works only for Java 6. Oracle 12C does not come with a version for Java 5, therefore I am testing 
with the Oracle 11g version, because that one has a version for Oracle 5.

  
See also  
http://km.fao.org/FIGISwiki/index.php/Oracle_migration_version_10_to_12C
the ojdbc14.jar is the one originally found in /usr/local/tomcat/5.5.23/common/lib/ojdbc14.jar and will be replaced with the ojdbc5.jar. 

The ojdbc7.jar in this directory is yet only used for the new Geoserver 8 installation, which is running on Tomcat 8 and Java 7.   
  