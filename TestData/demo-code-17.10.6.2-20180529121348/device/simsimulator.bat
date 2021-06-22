@echo off
set DEV_SIM=%~dp0
set DM_URL=http://localhost:8180/sim-ota/simotaConnector
set psk=c033f52671c61c8128f7f8a40be88038bcf2b07a6eb3095c36e3759f0cf40837
set psk_id=localhost
set msisdn=1234567899
set CLASSPATH=%DEV_SIM%profiles\.;%DEV_SIM%lib\*;
set JAVA=java -Xrunjdwp:transport=dt_socket,address=5056,server=y,suspend=n
%JAVA% -cp %CLASSPATH% com.mdm.wds.demo.SIMOTASimulator %DM_URL% %msisdn% %psk_id% %psk%  
