#!/bin/sh
set +v
set DEVICE_SIM=C:\Users\KALAIMAN\Documents\OMADM\demo-code-17.10.6.2-20180529121348\device\
#set DM_URL=http://localhost:8080/southbound-connector/dm
#set DM_URL=http://blr-mobility.motive.com/southbound-connector/dm (OR) http://<CDP_Instance>:<port>/southbound-connector/dm
export DM_URL=$1
if [ -z $2 ]; then
        export SMS_LIST_PORT=5555 #default port
else
    	export SMS_LIST_PORT= $2
fi
echo "SMS Listening Port = $SMS_LIST_PORT"
export CLASSPATH=.:$DEVICE_SIM/profiles/:$DEVICE_SIM/lib/*:$DEVICE_SIM/dmtree
set JAVA=C:\Users\KALAIMAN\Documents\OMADM\Java\jdk1.8.0_181\jre\bin\java
java -cp $CLASSPATH com.mdm.wds.demo.InteractiveSoftwareDevice %DM_URL% -sms-listening-port $SMS_LIST_PORT