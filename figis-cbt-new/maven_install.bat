# add the java 7 tool.jar for the build temporary in this directory
mvn install:install-file -Dfile=tools.jar -DgroupId=cbt -DartifactId=tools -Dversion=7 -Dpackaging=jar