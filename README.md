"# Club-sportif"

1. Description du projet
Contexte: Gestion d’un club sportif (activités, membres, inscriptions).
Objectif: Administrer les activités proposées, gérer l’adhésion des membres et suivre les inscriptions, avec des statistiques de fréquentation.
Public cible / cas d’usage: Responsables du club, secrétariat, encadrement sportif. Cas d’usage: créer/modifier des activités, inscrire des membres, consulter les statistiques.
En une phrase: Application web pour gérer les activités, les membres et leurs inscriptions avec tableaux de bord statistiques.
2. Architecture technique
2.1 Stack technologique
Backend: Spring Boot 3.5.6, Spring Data JPA (Hibernate)
Frontend: Thymeleaf, HTML/CSS, Bootstrap, Chart.js
Base de données: H2 (mémoire, profil dev)
Build: Maven, Java 17
2.2 Structure du code
domain/: classes JPA (Activite, Membre, Inscription)
repository/: interfaces Spring Data JPA
service/: logique métier (ex. StatsService)
controller/: contrôleurs MVC (pages Thymeleaf) + /controller/api (endpoints REST)
templates/: vues Thymeleaf (activites, membres, inscriptions, stats)
static/: CSS/JS (Bootstrap, app.css)
2.3 Diagramme d’architecture (texte)
Navigateur → Contrôleur Spring (MVC/REST) → Service (règles, agrégations) → Repository (JPA) → Base H2 → Retour modèle → Vue Thymeleaf (HTML + Chart.js).
<img width="894" height="896" alt="image" src="https://github.com/user-attachments/assets/7fbc8bf6-eacb-4304-8b55-c47d05683ed3" />


4. Fonctionnalités principales
CRUD: activités (nom, niveau, capacité), membres (nom, catégorie, date adhésion), inscriptions (date, statut, montant, liens vers activité et membre).
Recherche / filtrage:
Activités: par niveau (Débutant, Intermédiaire, Avancé)
Membres: par catégorie (Adulte, Junior, Sénior)
Inscriptions: par période (dates)
Tableau de bord / statistiques:
Fréquentation par activité (graphique bar/ligne/camembert)
Membres par catégorie
Activités par niveau
KPIs: total activités, membres, inscriptions, inscriptions du mois
Gestion des statuts (inscriptions): Confirmée, En attente, Annulée.
5. Modèle de données
4.1 Entités
Activite: id, nom, niveau (Débutant/Intermédiaire/Avancé), capacite
Membre: id, nom, categorie (Adulte/Junior/Sénior), dateAdhesion
Inscription: id, dateInscription, statut (Confirmée/En attente/Annulée), montant, activite (ref), membre (ref)
4.2 Relations
Inscription — Activite: @ManyToOne
Inscription — Membre: @ManyToOne
Schéma ER (texte): Activite(1) ← (N) Inscription (N) → (1) Membre.
4.3 Configuration base de données
URL: jdbc:h2:mem:clubsportif;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
Identifiants (H2): username sa, password vide
DDL: spring.jpa.hibernate.ddl-auto=create-drop (démo/dev)
6. Lancer le projet
5.1 Prérequis
Java 17
Maven Wrapper (inclus: mvnw.cmd sous Windows)
5.2 Installation
Cloner le dépôt
Vérifier src/main/resources/application.properties (port 8081, H2 actif)
Démarrer (PowerShell Windows):
Backend: .\mvnw.cmd spring-boot:run
Ou exécuter ClubSportifApplication dans l’IDE
5.3 Accès
Accueil: http://localhost:8081
Stats (tableau de bord): http://localhost:8081/stats
Fréquentation: http://localhost:8081/stats/frequentation
Stats avancées: http://localhost:8081/stats/advanced
Console H2: http://localhost:8081/h2 (JDBC: jdbc:h2:mem:clubsportif)
7. Jeu de données initial (optionnel)
Chargement: DataInitializer (CommandLineRunner)
Exemples:
Activités: Natation (Débutant/Intermédiaire), Musculation (Débutant), Yoga (Débutant), Tennis (Avancé)
Membres: Jean Dupont (Adulte), Marie Martin (Adulte), Pierre Durand (Sénior), Sophie Bernard (Adulte), Lucas Petit (Junior)
Inscriptions (8): statuts variés (Confirmée/En attente/Annulée), montants 30–60€
8. Démonstration (Vidéo)
https://drive.google.com/file/d/1JmYyhZSZF8PWmP5AsqIbBaExcJcImyrd/view?usp=drive_link

9. Auteurs / Encadrement
Étudiant: HASSAOUI aya
Encadrant : Mr.lachgar Mohamed
Module : Programmation avanceés et DevOPs
Établissement: École normale Supérieure
