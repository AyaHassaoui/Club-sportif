# Système de Filtrage - Club Sportif

## 🎯 Fonctionnalités de Filtrage Implémentées

### 📋 **Activités**
- **Niveau** : Débutant, Intermédiaire, Avancé
- **Recherche par nom** : Recherche partielle dans le nom de l'activité
- **Capacité** : Filtrage par capacité minimale et maximale
- **Bouton "Effacer"** : Pour réinitialiser tous les filtres

### 👥 **Membres**
- **Catégorie** : Adulte, Junior, Sénior
- **Recherche par nom** : Recherche partielle dans le nom du membre
- **Date d'adhésion** : Filtrage par plage de dates (début et fin)
- **Bouton "Effacer"** : Pour réinitialiser tous les filtres

### 📝 **Inscriptions**
- **Statut** : Confirmée, En attente, Annulée
- **Date d'inscription** : Filtrage par plage de dates
- **Montant** : Filtrage par montant minimal et maximal
- **Bouton "Effacer"** : Pour réinitialiser tous les filtres

## 🏗️ Architecture Technique

### Backend (Spring Boot)
- **API REST** : Endpoints `/api/activites`, `/api/membres`, `/api/inscriptions`
- **Filtrage côté serveur** : Pour les critères principaux (niveau, catégorie, dates)
- **CORS configuré** : Pour permettre la communication frontend-backend
- **Paramètres d'URL** : Transmission des filtres via query parameters

### Frontend (React + TypeScript)
- **Composants modulaires** : `FilterCard`, `ActiviteFilters`, `MembreFilters`
- **Cartes réutilisables** : `ActiviteCard`, `MembreCard`
- **Constantes centralisées** : `NIVEAUX`, `CATEGORIES`, `STATUTS`
- **Filtrage en temps réel** : Mise à jour automatique des résultats
- **Gestion d'état** : useState pour les filtres et les données

## 🎨 Interface Utilisateur

### Design
- **Cartes de filtres** : Interface claire avec en-têtes
- **Badges colorés** : Pour les niveaux, catégories et statuts
- **Compteurs de résultats** : Affichage du nombre d'éléments trouvés
- **Messages d'état vide** : Quand aucun résultat n'est trouvé
- **Boutons d'action** : Effacer les filtres facilement

### Couleurs des Badges
- **Niveaux** :
  - Débutant : Vert (`bg-success`)
  - Intermédiaire : Jaune (`bg-warning`)
  - Avancé : Rouge (`bg-danger`)

- **Catégories** :
  - Adulte : Bleu (`bg-primary`)
  - Junior : Vert (`bg-success`)
  - Sénior : Cyan (`bg-info`)

## 🚀 Utilisation

### Démarrage
1. **Backend** : `mvn spring-boot:run` (port 8081)
2. **Frontend** : `cd frontend && npm run dev` (port 5173)

### Navigation
1. Accédez à http://localhost:5173
2. Naviguez vers "Activités", "Membres" ou "Inscriptions"
3. Utilisez les filtres disponibles
4. Testez la combinaison de plusieurs filtres
5. Utilisez "Effacer les filtres" pour réinitialiser

## 📊 Données de Test

### Activités
- Natation (Débutant, Intermédiaire)
- Musculation (Débutant)
- Yoga (Débutant)
- Tennis (Avancé)

### Membres
- Jean Dupont (Adulte)
- Marie Martin (Adulte)
- Pierre Durand (Sénior)
- Sophie Bernard (Adulte)
- Lucas Petit (Junior)

### Inscriptions
- 5 inscriptions avec différents statuts et montants
- Dates d'inscription variées
- Liens entre membres et activités

## 🔧 Personnalisation

### Ajouter de nouveaux filtres
1. Modifiez les interfaces TypeScript
2. Ajoutez les paramètres dans les contrôleurs API
3. Mettez à jour les composants de filtres
4. Ajoutez les constantes si nécessaire

### Modifier les couleurs
1. Éditez les fonctions `getNiveauBadgeClass` et `getCategorieBadgeClass`
2. Utilisez les classes Bootstrap ou CSS personnalisées

### Ajouter de nouvelles catégories/niveaux
1. Modifiez les constantes dans `constants/filters.ts`
2. Mettez à jour les données de test si nécessaire
3. Ajustez les couleurs des badges
