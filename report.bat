cd target\
rmdir allure-report /s /q
call ..\lib\allure\bin\allure.bat generate
call ..\lib\allure\bin\allure.bat open