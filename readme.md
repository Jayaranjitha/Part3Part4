## how to run the framework
For ANALYTICS:

export MAVEN_OPTS="-Xmx2048m"
export JAVA_OPTIONS="-Xms1024m -Xmx2048m -XX:MaxPermSize=512m"
# If you want to run all tests without JIRA
mvn -s settings.xml -gs settings.xml clean verify -Pana-vlab

# If you want to run all tests with JIRA
mvn -s settings.xml -gs settings.xml clean verify -Djira.username= -Djira.password='' -Pana-vlab,with-jira

# If you want to run specific group of tests filtered by tag value
mvn -s settings.xml -gs settings.xml clean verify -Djira.username= -Djira.password='' -Pana-vlab -Dcucumber.options="--tags @test"

mvn -s settings.xml -gs settings.xml clean verify -Psmp-local -Dcucumber.options="--tags @CSC"

For SMP:
mvn -s settings.xml -gs settings.xml clean verify -Psmp-dev
mvn -s settings.xml -gs settings.xml clean verify -Psmp-dev,with-jira -Djira.username= -Djira.password=''

## how to add more profiles specific to new product

Just simple copy paste following code and change necessary folder names and profile name and put it in pom.xml. Following code is for 
profiles.

<profile>
	<id>ana-prod</id>
	<properties>
		<environment>ana-prod</environment>
		<java_include>**/*Analytics*AcceptanceTests.java</java_include>
	</properties>
</profile>
i.e. change environment value
i.e. change java_includes to point to acceptance test class.

Also, add related properties file in src/main/filters/filter-<environment>.properties.


# How to create archetype from project
rm .project .classpath 
rm -r .settings
mvn archetype:create-from-project
archetype will be available in folder - target/generated-sources/archetype

