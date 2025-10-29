package com.example.club.sportif.dto;

public class ActiviteFrequentationDTO {
    private String activiteNom;
    private long totalInscriptions;

    public ActiviteFrequentationDTO() {}
    public ActiviteFrequentationDTO(String activiteNom, long totalInscriptions) {
        this.activiteNom = activiteNom;
        this.totalInscriptions = totalInscriptions;
    }
    public String getActiviteNom() { return activiteNom; }
    public void setActiviteNom(String activiteNom) { this.activiteNom = activiteNom; }
    public long getTotalInscriptions() { return totalInscriptions; }
    public void setTotalInscriptions(long totalInscriptions) { this.totalInscriptions = totalInscriptions; }
}


