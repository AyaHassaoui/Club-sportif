@echo off
echo ========================================
echo    Club Sportif - Application de Gestion
echo ========================================
echo.

echo Demarrage du backend Spring Boot...
start "Backend" cmd /k "mvn spring-boot:run"

echo.
echo Attente du demarrage du backend...
timeout /t 10 /nobreak > nul

echo.
echo Demarrage du frontend React...
cd frontend
start "Frontend" cmd /k "npm run dev"

echo.
echo ========================================
echo Application accessible sur :
echo - Frontend: http://localhost:5173
echo - Backend API: http://localhost:8081/api
echo - H2 Console: http://localhost:8081/h2
echo ========================================
echo.
pause
