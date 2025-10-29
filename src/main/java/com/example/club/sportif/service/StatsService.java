package com.example.club.sportif.service;

import com.example.club.sportif.dto.ActiviteFrequentationDTO;
import com.example.club.sportif.dto.LabelCountDTO;
import com.example.club.sportif.repository.ActiviteRepository;
import com.example.club.sportif.repository.InscriptionRepository;
import com.example.club.sportif.repository.MembreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatsService {
    private final InscriptionRepository inscriptionRepo;
    private final MembreRepository membreRepo;
    private final ActiviteRepository activiteRepo;
    public StatsService(InscriptionRepository inscriptionRepo, MembreRepository membreRepo, ActiviteRepository activiteRepo){
        this.inscriptionRepo = inscriptionRepo;
        this.membreRepo = membreRepo;
        this.activiteRepo = activiteRepo;
    }

    public Map<String, Long> frequentation() {
        return inscriptionRepo.frequentationParActivite().stream()
                .collect(Collectors.toMap(
                        ActiviteFrequentationDTO::getActiviteNom,
                        ActiviteFrequentationDTO::getTotalInscriptions
                ));
    }

    public double retention(LocalDate p1Start, LocalDate p1End,
                            LocalDate p2Start, LocalDate p2End) {
        Set<Long> p1 = inscriptionRepo.membresActifsEntre(p1Start, p1End);
        Set<Long> p2 = inscriptionRepo.membresActifsEntre(p2Start, p2End);
        if (p1.isEmpty()) return 0.0;
        long kept = p1.stream().filter(p2::contains).count();
        return (double) kept / (double) p1.size();
    }

    public long retentionBase(LocalDate p1Start, LocalDate p1End) {
        return inscriptionRepo.membresActifsEntre(p1Start, p1End).size();
    }

    public long retentionKept(LocalDate p1Start, LocalDate p1End,
                              LocalDate p2Start, LocalDate p2End) {
        Set<Long> p1 = inscriptionRepo.membresActifsEntre(p1Start, p1End);
        Set<Long> p2 = inscriptionRepo.membresActifsEntre(p2Start, p2End);
        return p1.stream().filter(p2::contains).count();
    }

    public List<ActiviteFrequentationDTO> frequentationParActivite() {
        return inscriptionRepo.frequentationParActivite();
    }


    public List<LabelCountDTO> membresParCategorie() {
        return membreRepo.repartitionParCategorie();
    }

    public List<LabelCountDTO> activitesParNiveau() {
        return activiteRepo.activitesParNiveau();
    }

    public long totalActivites() {
        return activiteRepo.count();
    }

    public long totalMembres() {
        return membreRepo.count();
    }

    public long totalInscriptions() {
        return inscriptionRepo.count();
    }

    public long inscriptionsCeMois() {
        LocalDate debutMois = LocalDate.now().withDayOfMonth(1);
        LocalDate finMois = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        return inscriptionRepo.countByDateInscriptionBetween(debutMois, finMois);
    }
}


