@echo off
set DEV_SIM=C:\Users\KALAIMAN\Documents\OMADM\devicesimulator\
set DM_URL=http://localhost:8080/southbound-connector/dm
rem set DM_URL=%1
set UPDATE_TRIGGER=-update-trigger
set ACCEPT_DOWNLOAD =true
set ACCEPT_UPDATE=-accept-update
set SIMULATE_DOWNLOAD_FAILURE=no
set SIMULATE_CORRELATOR_MISMATCH =no

rem init classpath
set CLASSPATH=%DEV_SIM%profiles\.;%DEV_SIM%dmtree\.;%DEV_SIM%lib\*;
set JAVA=C:\Users\KALAIMAN\Documents\OMADM\Java\jdk1.8.0_181\jre\bin\java
rem set JAVA=java -Xrunjdwp:transport=dt_socket,address=5000,server=y,suspend=n
rem %JAVA% -cp %CLASSPATH% com.mdm.wds.demo.InteractiveSoftwareDevice %DM_URL%
rem set "-Xrunjdwp:transport=dt_socket,address=127.0.0.1:5000,server=y,suspend=n"
%JAVA% -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:5556 -cp %CLASSPATH% com.mdm.wds.demo.InteractiveSoftwareDevice %DM_URL% -update-trigger