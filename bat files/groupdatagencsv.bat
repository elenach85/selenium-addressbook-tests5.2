set ProjectPath=D:\PDT\selenium-addressbook-tests5
cd /d %ProjectPath%
if exist="d %ProjectPath%\groups.txt" goto
del groups.txt
set path=C:\Program Files\Java\jre1.8.0_45\bin
set amountofgroups=5
java -cp bin com.example.tests.GroupDataGenerator %amountofgroups% groups.txt csv

