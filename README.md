# Reference Template Generator

java -jar ReferenceTemplateGenerator.jar --class X --package x --specify-root-path Y

--class Aircraft required
--package reference required
--specify-root-path Y optional

#### **!YOU WILL NOT LOSE YOUR OLD CLASSES, NOT ALLOWED TO OVERWRITE OLD CLASSES!**

Example Usage Below :

Specify Reference Manager Main Folder Path 
*** Ex => C:/tams/tams-reference-manager ***
C:/tams/tams-reference-manager


/////////////FOLDER////////////////
.flattened-pom.xml
.git
.gitignore
.idea
.properties
.script
Dockerfile
Jenkinsfile
logs
pom.xml
tams-reference-manager-app
tams-reference-manager-dal-api
tams-reference-manager-dal-nosql
tams-reference-manager-dal-rdbms
tams-reference-manager.iml
/////////////FOLDER////////////////


Is this path correct? Y/N
y


usage: Reference Template Generator
    --class <arg>     Required. Class Name
    --package <arg>   Optional. Package Name

THIS WILL NOT REMOVE OR CHANGE PRESENT EXISTING CLASSES BEFORE!!!