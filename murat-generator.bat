echo off
set /P class= "write --class parameter : "
set /P package= "write --package parameter : "
set /P root= "do yo want to set root folder ? (Y/N) : "

java -jar ReferenceTemplateGenerator.jar --class %class% --package %package% --specify-root-path %root%

pause