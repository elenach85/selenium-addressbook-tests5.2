set ProjectPath=D:\PDT\selenium-addressbook-tests5
cd /d %ProjectPath%
set path=C:\Program Files\Java\jre1.8.0_45\bin
set SeleniumPath=D:\PDT\selenium-server-standalone-2.47.1.jar
set XstreampullPath=xmlpull-1.1.3.1.jar
set XstreamfilePath=D:\xstream-1.4.8.jar
set kxml2Path=D:\PDT\kxml2-2.3.0.jar
set Xstreamxpp3filePath=D:\xpp3_min-1.1.4c.jar
set MySqlPath=D:\PDT\mysql-connector-java-5.0.8-bin.jar
set HibernateantlrfilePath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\required\antlr-2.7.7.jar
set HibernatedomfilePath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\required\dom4j-1.6.1.jar
set HibernatecommonannotationsfilePath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\required\hibernate-commons-annotations-5.0.0.Final.jar
set HibernatecorefilePath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\required\hibernate-core-5.0.0.Final.jar
set Hibernatejavaassistpath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\required\javassist-3.18.1-GA.jar
set Hibernatejbossloggingpath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\required\jboss-logging-3.3.0.Final.jar
set Hibernatelucenepath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\required\lucene-core-4.10.4.jar
set Hibernatexmlapispath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\required\xml-apis-1.3.03.jar
set Hibernatejpapath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\provided\hibernate-jpa-2.1-api-1.0.0.Final.jar
set Hibernatejbossannotationspath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\provided\jboss-annotations-api_1.2_spec-1.0.0.Final.jar
set Hibernatejmspath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\provided\jboss-jms-api_2.0_spec-1.0.0.Final.jar
set Hibernatejbosstransactionpath=D:\PDT\hibernate-search-5.4.0.Final\dist\lib\provided\jboss-transaction-api_1.2_spec-1.0.0.Final.jar
java -cp bin;%SeleniumPath%;%MySqlPath%;%kxml2Path%;%XstreampullPath%;%HibernatejbosstransactionPath%;%HibernatejmsPath%;%HibernatejbossannotationsPath%;%HibernatejpaPath%;%HibernatexmlapisPath%;%HibernatelucenePath%;%HibernatejbossloggingPath%;%HibernatejavaassistPath%;%HibernatecorefilePath%;%HibernatedomfilePath%;%HibernatecommonannotationsfilePath%;%HibernateantlrfilePath%;%XstreamfilePath%;%Xstreamxpp3filePath% org.testng.TestNG testng-customsuite.xml
