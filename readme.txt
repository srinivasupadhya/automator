Install instructions:
Import project to eclipse

Setup:
Path changes:
in Automator-Parent/pom.xml - <project.path>
in Automator-DatabaseAccess/src/main/resources/META-INF/persistence.xml - path to db
in Automator-Common/tool.automator.common.constants.PathConstants - BASE_PROJECT_PATH

In Cmd-Prompt:
cd to Automator-Parent.
execute: mvn clean install - build all modules

cd to Automator-DatabaseAdmin
execute: mvn jetty:run - run jetty server with Automator-DatabaseAdmin. open localhost:8080/Automator-DatabaseAdmin in browser

cd to Automator-Generator
execute: mvn assembly:single - create executable jar of generator

cd to Automator-ExecutorGUI
execute: mvn assembly:single - create executable jar of GUI based test-script executor

cd to Automator-ExecutorStandalone
execute: mvn assembly:single - create executable jar of Standalone test-script executor

Output Paths:
Automator-Parent/output/Database/automator.h2.db - database for all modules
Automator-Parent/output/DatabaseSettings - all xmls generated are dumped here
Automator-Parent/output/GeneratedTestscripts - all generated test-scripts are dumped here

Executables:
tool.automator.database.test.SetupProject - to create some basic data if you delete database
tool.automator.database.xml.write.DBToXMLConverter - to create xmls out of database (required for executors)
tool.automator.generator.main.EntryPoint - to generate test-scripts
tool.automator.executor.standalone.main.TestScriptExecutorMain - to execute test-scripts