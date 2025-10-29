@echo off
echo Demarrage de l'application Club Sportif...
echo.

REM Nettoyer et compiler
echo Compilation...
call mvnw.cmd clean compile -q

REM Demarrer l'application
echo Demarrage de l'application...
call mvnw.cmd spring-boot:run

pause
