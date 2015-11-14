set ProjectPath=D:\PDT\selenium-addressbook-tests5
cd /d %ProjectPath%
if exist="d %ProjectPath%\contacts.xml" goto
del contacts.xml
set path=C:\Program Files\Java\jre1.8.0_45\bin
set Xstreamfilepath=D:\xstream-1.4.8.jar
set Xstreamxpp3filepath=D:\xpp3_min-1.1.4c.jar
set amount=4
java -cp bin;%Xstreamfilepath%;%Xstreamxpp3filepath% com.example.tests.ContactDataGenerator %amount% contacts.xml xml

