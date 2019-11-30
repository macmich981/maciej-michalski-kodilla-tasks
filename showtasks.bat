call runcrud.bat
if "%ERRORLEVEL%" =="0" goto showtasks
echo.
echo RUNCRUD has errors - breaking work
goto fail

:showtasks
set wait_time=1
cd "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"
start http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors.

:end
echo.
echo Work is finished.