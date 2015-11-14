set ProjectPath=D:\PDT\selenium-addressbook-tests5
cd /d %ProjectPath%
if exist="d %ProjectPath%\groups.xml" goto
del groups.xml
set path=C:\Program Files\Java\jre1.8.0_45\bin
set Xstreamfilepath=D:\xstream-1.4.8.jar
set Xstreamxpp3filepath=D:\xpp3_min-1.1.4c.jar
set amount=3
java -cp bin;%Xstreamxpp3filepath%;%Xstreamfilepath% com.example.tests.GroupDataGenerator %amount% groups.xml xml
