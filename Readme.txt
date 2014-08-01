Currently compiled with:
Java 1.6.0
Groovy 1.8.1
Griffon 1.2.0

griffon compile
griffon package jar
griffon prepare-windows
(Move files from /installer-backup to the directory that prepare-windows creates. See /installer-backup/Readme.txt.)
griffon create-windows
(Windows executable and other necessary files will be created in /dist/windows.)