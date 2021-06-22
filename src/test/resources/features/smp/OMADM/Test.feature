@jjes
Feature: Sample Test

@MotiveBridge001
Scenario: Sample Test
  
#	Given user is printing the data set
#
#		| 1210           | Tom                 |1001| 1001|
#		| 1110           | Kenny               |1001| 1001|
#		| 1001           | someone@someone.com |1001| 1001|
		
	Given user runs the simulator "CPP"
#     And user hits the action "AddObjectLWM2M-Server-/1/3" from the Impact
	Then user is printing the data set 

		| First Name             | Tom                 |1001| 1001|
		| Last Name              | Kenny               |1001| 1001|
		| Email Address          | someone@someone.com |1001| 1001|
		| Re-enter Email Address | someone@someone.com |1001| 1001|
		| Password               | Password1           |1001| 1001|
		| Birthdate              | 01                  |1001| 1001|
 Then user closes the simulator in port "5546"
 
 