package com.example.club.sportif.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private LocalDate dateInscription;
    @NotBlank private String statut;
    @NotNull @Column(precision = 12, scale = 2)
    private BigDecimal montant;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    private Activite activite;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    private Membre membre;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public Activite getActivite() { return activite; }
    public void setActivite(Activite activite) { this.activite = activite; }
    public Membre getMembre() { return membre; }
    public void setMembre(Membre membre) { this.membre = membre; }
}


