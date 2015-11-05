set ProjectPath=D:\PDT\selenium-addressbook-tests5
cd /d %ProjectPath%
if exist="d %ProjectPath%\contacts.txt" goto
del contacts.txt
set path=C:\Program Files\Java\jre1.8.0_45\bin
java -cp bin com.example.tests.ContactDataGenerator 2 contacts.txt csv

