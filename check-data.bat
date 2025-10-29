@echo off
echo Verification des donnees dans la base H2...
echo.

echo Ouverture de la console H2...
echo URL: http://localhost:8081/h2
echo JDBC URL: jdbc:h2:mem:clubsportif
echo Username: sa
echo Password: (vide)
echo.

echo Tables a verifier:
echo - ACTIVITE (id, nom, niveau, capacite)
echo - MEMBRE (id, nom, categorie, date_adhesion)  
echo - INSCRIPTION (id, date_inscription, statut, montant, activite_id, membre_id)
echo.

pause
