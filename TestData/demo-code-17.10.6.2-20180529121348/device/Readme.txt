ABOUT
------
	This document explains how to use the software device.

INSTALLATION
-------------------------
	Extract the device.zip into a folder of your choice 
	

FOLDER STRUCTURE
-------------------------
[root] 
  |------ Readme.txt
  |------ DevSim.sh
  |------ simsimulator.bat
  |------ simsimulator.sh
  |------ [lib]
             |------demo-code-4.1.1-SNAPSHOT-jar-with-dependencies.jar
  |------ [dmtree]
             |------default.csv
             |------hs20.csv
             |------andsf.csv
  |------ [profiles]
            |------ 0.properties
            |------ 1.properties
            |------ 2.properties
            |------ 3.properties

HOW TO RUN SOFTWARE DEVICE
------------------------------
	Goto the folder and run DevSim <http-url-of-southbound-dm-connector> <sms-listening-port>to start the device.
	eg. sh DevSim.sh http://localhost:8080/southbound-connector/dm 6667
	Note: The default sms listening port '5555' will be set if second parameter is not provided.

HOW TO ADD NEW DEVICE PROFILES
-------------------------------------
	Goto to the <device-installed-folder>/profiles and you could add a new device in the next sequence.
	Copy all the attributes for an existing device profile and modify it accordingly.

DEVICE COMMAND REFERENCE
-------------------------------
'server (s)': wait for server
'update (u)': ask server - sending request
    '--noprompt (-np)' : Auto accept without prompting for queries.
'update-trigger (ut)': setup update-triger
'info (i)': get information about the device
'help (h)': show this message
'switch (sw) <device-id> <dmtree-name>': switch device 
'quit (q)': finish the session
'initiate-fwupgrade (fu)': initial-client for fw upgrade alert
    '--noprompt (-np)' : Auto accept without prompting upgrade queries.
    '--alert-data=<alert data>' : Update with generic alert with alert data.
	'--install' : Installs the downloaded firmware.
'initiate-andsfprovision (andsf)': initiate-UE-alert for ANDSF Policies
'initiate-hs20sp (sp)': initiate-UE-alert for Hotspot 2.0 Subscription Provisioning    
'no-alert-update(nau)': update with no generic alert
'revert-no-alert-update(r-nau)': revert back to update with generic alert
'execute-update-deferred(eud)': send update deferred to the device during exec
'xml-response(xmlrs)': switches the device interaction from wbxml to xml and validates the namespace in the meta tags
'validate-session-length(vsl)': validates if the session length is 16 size
'validate-respuri(vrsp)': validates if the resp uri is not in https
'check-content-type (ccty)': validates if the content type corresponds to the legacy 3g device

FEATURES AVAILABLE
------------------------------------
1) Nodes for Power Cycle command and LAWMO Handler Updates.
2) Default values for AddNAP nodes.
3) Fix for FUMO Refactored nodes
4) Support for factory bootstrap on several devices. For more information see section "Factory Bootstrap Support for Several Devices", below.
5) Support for ANDSF and HotSpot2.0 generic alerts to be added to the DM sessions.
6) Devices can be dynamically loaded with a custom DM-Tree, loaded from dmtree csv files. By default, default.csv is associated to any device.
7)Support for sending download result reporting after successful download of firmware.

FACTORY BOOTSTRAP SUPPORT FOR SEVERAL DEVICES
-------------------------------------------
1) Please refer to sample device profile as 3.properties within profiles folder.
2) Six new properties are introduced: client_username_node, client_password_node, client_nonce_node, server_username_node, server_password_node and server_nonce_node.
3) Specify the node structure to above properties. Eg: "client_username_node" = Path to node where the client username will be stored in the device.
4) In case these properties are not specified then default tree structute will be used.
5) Note: in SCE server id should be "MDM"

DM-TREE SUPPORT
--------------------------
Used when switching device profile to use a specific dm-tree using

sw <device-id> <dmtree-name>

a) default.csv - All devices by default, refer to default dm-tree, or if a dm-tree specified does not exist in the path.
b) andsf.csv - Required for ANDSF related operations, and defining UE_Location details
b) hs20.csv - Required for HotSpot 2.0 related operations