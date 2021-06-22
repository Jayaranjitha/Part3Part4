#!/bin/sh
set +v
export DEVICE_SIM=$PWD
#set DM_URL=http://blr-mobility.motive.com/southbound-connector/dm (OR) http://<CDP_Instance>:<port>/southbound-connector/dm
export DM_URL=$1
if [ -z $2 ]; then
        export SMS_LIST_PORT=5555 #default port
else
    	export SMS_LIST_PORT=$2
fi
echo "SMS Listening Port = $SMS_LIST_PORT"
export CLASSPATH=.:$DEVICE_SIM/profiles/:$DEVICE_SIM/lib/*:$DEVICE_SIM/dmtree
java -cp $CLASSPATH com.mdm.wds.demo.InteractiveSoftwareDevice %DM_URL% -sms-listening-port $SMS_LIST_PORT