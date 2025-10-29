package com.example.club.sportif.config;

import com.example.club.sportif.domain.Activite;
import com.example.club.sportif.domain.Inscription;
import com.example.club.sportif.domain.Membre;
import com.example.club.sportif.repository.ActiviteRepository;
import com.example.club.sportif.repository.InscriptionRepository;
import com.example.club.sportif.repository.MembreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ActiviteRepository activiteRepository;
    private final MembreRepository membreRepository;
    private final InscriptionRepository inscriptionRepository;

    public DataInitializer(ActiviteRepository activiteRepository, 
                          MembreRepository membreRepository, 
                          InscriptionRepository inscriptionRepository) {
        this.activiteRepository = activiteRepository;
        this.membreRepository = membreRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Vider les tables existantes pour forcer la réinitialisation
        inscriptionRepository.deleteAll();
        activiteRepository.deleteAll();
        membreRepository.deleteAll();
        
        System.out.println("Tables vidées, initialisation des nouvelles données...");

        System.out.println("Initialisation des données de test...");

        // Créer des activités
        Activite natation1 = new Activite();
        natation1.setNom("Natation");
        natation1.setNiveau("Débutant");
        natation1.setCapacite(20);
        activiteRepository.save(natation1);

        Activite natation2 = new Activite();
        natation2.setNom("Natation");
        natation2.setNiveau("Intermédiaire");
        natation2.setCapacite(15);
        activiteRepository.save(natation2);

        Activite musculation = new Activite();
        musculation.setNom("Musculation");
        musculation.setNiveau("Débutant");
        musculation.setCapacite(25);
        activiteRepository.save(musculation);

        Activite yoga = new Activite();
        yoga.setNom("Yoga");
        yoga.setNiveau("Débutant");
        yoga.setCapacite(30);
        activiteRepository.save(yoga);

        Activite tennis = new Activite();
        tennis.setNom("Tennis");
        tennis.setNiveau("Avancé");
        tennis.setCapacite(12);
        activiteRepository.save(tennis);

        // Créer des membres
        Membre membre1 = new Membre();
        membre1.setNom("Jean Dupont");
        membre1.setCategorie("Adulte");
        membre1.setDateAdhesion(LocalDate.of(2024, 1, 15));
        membreRepository.save(membre1);

        Membre membre2 = new Membre();
        membre2.setNom("Marie Martin");
        membre2.setCategorie("Adulte");
        membre2.setDateAdhesion(LocalDate.of(2024, 1, 20));
        membreRepository.save(membre2);

        Membre membre3 = new Membre();
        membre3.setNom("Pierre Durand");
        membre3.setCategorie("Sénior");
        membre3.setDateAdhesion(LocalDate.of(2024, 2, 1));
        membreRepository.save(membre3);

        Membre membre4 = new Membre();
        membre4.setNom("Sophie Bernard");
        membre4.setCategorie("Adulte");
        membre4.setDateAdhesion(LocalDate.of(2024, 2, 10));
        membreRepository.save(membre4);

        Membre membre5 = new Membre();
        membre5.setNom("Lucas Petit");
        membre5.setCategorie("Junior");
        membre5.setDateAdhesion(LocalDate.of(2024, 2, 15));
        membreRepository.save(membre5);

        // Créer des inscriptions
        Inscription inscription1 = new Inscription();
        inscription1.setDateInscription(LocalDate.of(2024, 1, 15));
        inscription1.setStatut("Confirmée");
        inscription1.setMontant(new BigDecimal("50.00"));
        inscription1.setActivite(natation1);
        inscription1.setMembre(membre1);
        inscriptionRepository.save(inscription1);

        Inscription inscription2 = new Inscription();
        inscription2.setDateInscription(LocalDate.of(2024, 1, 20));
        inscription2.setStatut("Confirmée");
        inscription2.setMontant(new BigDecimal("50.00"));
        inscription2.setActivite(musculation);
        inscription2.setMembre(membre2);
        inscriptionRepository.save(inscription2);

        Inscription inscription3 = new Inscription();
        inscription3.setDateInscription(LocalDate.of(2024, 2, 1));
        inscription3.setStatut("En attente");
        inscription3.setMontant(new BigDecimal("40.00"));
        inscription3.setActivite(natation1);
        inscription3.setMembre(membre3);
        inscriptionRepository.save(inscription3);

        Inscription inscription4 = new Inscription();
        inscription4.setDateInscription(LocalDate.of(2024, 2, 10));
        inscription4.setStatut("Confirmée");
        inscription4.setMontant(new BigDecimal("50.00"));
        inscription4.setActivite(yoga);
        inscription4.setMembre(membre4);
        inscriptionRepository.save(inscription4);

        Inscription inscription5 = new Inscription();
        inscription5.setDateInscription(LocalDate.of(2024, 2, 15));
        inscription5.setStatut("Annulée");
        inscription5.setMontant(new BigDecimal("30.00"));
        inscription5.setActivite(natation2);
        inscription5.setMembre(membre5);
        inscriptionRepository.save(inscription5);

        // Ajouter plus d'inscriptions pour avoir des données plus riches
        Inscription inscription6 = new Inscription();
        inscription6.setDateInscription(LocalDate.of(2024, 3, 1));
        inscription6.setStatut("Confirmée");
        inscription6.setMontant(new BigDecimal("60.00"));
        inscription6.setActivite(tennis);
        inscription6.setMembre(membre1);
        inscriptionRepository.save(inscription6);

        Inscription inscription7 = new Inscription();
        inscription7.setDateInscription(LocalDate.of(2024, 3, 5));
        inscription7.setStatut("En attente");
        inscription7.setMontant(new BigDecimal("45.00"));
        inscription7.setActivite(natation2);
        inscription7.setMembre(membre2);
        inscriptionRepository.save(inscription7);

        Inscription inscription8 = new Inscription();
        inscription8.setDateInscription(LocalDate.of(2024, 3, 10));
        inscription8.setStatut("Confirmée");
        inscription8.setMontant(new BigDecimal("35.00"));
        inscription8.setActivite(yoga);
        inscription8.setMembre(membre3);
        inscriptionRepository.save(inscription8);

        System.out.println("Données de test initialisées avec succès !");
        System.out.println("- " + activiteRepository.count() + " activités");
        System.out.println("- " + membreRepository.count() + " membres");
        System.out.println("- " + inscriptionRepository.count() + " inscriptions");
    }
}
