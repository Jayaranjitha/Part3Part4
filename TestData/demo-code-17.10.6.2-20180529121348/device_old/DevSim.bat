@echo off
set DEV_SIM=C:\Users\KALAIMAN\Documents\OldCodeBase\DocomoTestAut18thmarch\MotiveBridge\test-framework\TestData\demo-code-17.10.6.2-20180529121348\device\
set DM_URL=http://localhost:8085/southbound-connector/dm
rem set DM_URL=%1
set UPDATE_TRIGGER=-update-trigger
rem init classpath
set CLASSPATH=%DEV_SIM%profiles\.;%DEV_SIM%dmtree\.;%DEV_SIM%lib\*;
set JAVA=C:\Users\KALAIMAN\Documents\OMADM\Java\jdk1.8.0_181\jre\bin\java
rem set JAVA=java -Xrunjdwp:transport=dt_socket,address=5000,server=y,suspend=n
rem %JAVA% -cp %CLASSPATH% com.mdm.wds.demo.InteractiveSoftwareDevice %DM_URL%
rem set "-Xrunjdwp:transport=dt_socket,address=127.0.0.1:5000,server=y,suspend=n"
%JAVA% -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:8888 -cp %CLASSPATH% com.mdm.wds.demo.InteractiveSoftwareDevice %DM_URL% -sms-listening-port 6779 -update-trigger
rem C:\Users\KALAIMAN\Documents\OMADM\Java\jdk1.8.0_181\jre\bin\java -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:7777 -Dfile.encoding=UTF-8 com.mdm.wds.demo.InteractiveSoftwareDevice http://localhost:8080/southbound-connector/dm -sms-listening-port 5699 -update-trigger
rem C:\Users\KALAIMAN\Documents\OMADM\Java\jdk1.8.0_181\jre\bin\java -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:7777 -Dfile.encoding=UTF-8 com.mdm.wds.demo.InteractiveSoftwareDevice http://localhost:8080/southbound-connector/dm -update-trigger
pause