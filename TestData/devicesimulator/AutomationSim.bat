@echo off
set DEV_SIM=C:\Users\KALAIMAN\Documents\OMADM\devicesimulator\
set DM_URL=http://localhost:8080/southbound-connector/dm
rem set DM_URL=%1
rem set DM_URL=%1
set UPDATE_TRIGGER=-update-trigger
rem init classpath
set CLASSPATH=%DEV_SIM%profiles\.;%DEV_SIM%lib\*;
set JAVA=C:\Users\KALAIMAN\Documents\OMADM\Java\jdk1.8.0_181\jre\bin\java
%JAVA% -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:1491 -cp %CLASSPATH% com.mdm.wds.demo.AutomatedSoftwareDevice %DM_URL% -sms-listening-port 6666  -simulate_download_alert false