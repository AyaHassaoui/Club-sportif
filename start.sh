#!/bin/bash

echo "========================================"
echo "   Club Sportif - Application de Gestion"
echo "========================================"
echo

echo "Démarrage du backend Spring Boot..."
mvn spring-boot:run &
BACKEND_PID=$!

echo
echo "Attente du démarrage du backend..."
sleep 10

echo
echo "Démarrage du frontend React..."
cd frontend
npm run dev &
FRONTEND_PID=$!

echo
echo "========================================"
echo "Application accessible sur :"
echo "- Frontend: http://localhost:5173"
echo "- Backend API: http://localhost:8081/api"
echo "- H2 Console: http://localhost:8081/h2"
echo "========================================"
echo

# Fonction pour arrêter les processus
cleanup() {
    echo "Arrêt des processus..."
    kill $BACKEND_PID 2>/dev/null
    kill $FRONTEND_PID 2>/dev/null
    exit
}

# Capturer Ctrl+C
trap cleanup SIGINT

# Attendre
wait
