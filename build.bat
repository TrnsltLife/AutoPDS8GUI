REM Currently compiled with:
REM Java 1.6.0
REM Groovy 1.8.1
REM Griffon 1.2.0
REM Currently my System Properties->Environment variables have:
REM JAVA_HOME6=C:\Program Files\Java\jdk1.6.0_33
REM GROOVY_HOME_1_8=C:\Dev\Groovy\groovy-1.8.1
echo
copy /Y C:\Dev\Eclipse-Helios\workspace\AutoPDS8\AutoPDS8.jar .\lib\
copy /Y C:\Dev\Eclipse-Helios\workspace\AutoPDS8\AutoPDS8.jar .\staging\
set JAVA_HOME=%JAVA_HOME6%
set GROOVY_HOME=%GROOVY_HOME_1_8%
call griffon compile
call griffon package jar
call griffon prepare-windows
REM (Move files from /installer-backup to the directory that prepare-windows creates. See /installer-backup/Readme.txt.)
cd installer-backup\jsmooth
copy /Y * D:\Jeremy\.griffon\1.2.0\projects\AutoPDS8GUI\installer\jsmooth\
cd ..\..
call griffon create-windows
REM (Windows executable and other necessary files will be created in /dist/windows.)
