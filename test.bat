@ECHO off

cd "./app"

:run:
cls
java -classpath "./build/libs/app.jar" "ICT373Asn1.test.Test"

pause
goto run