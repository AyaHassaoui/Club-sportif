package com.example.club.sportif.controller;
import com.example.club.sportif.dto.ActiviteFrequentationDTO;
import com.example.club.sportif.dto.LabelCountDTO;
import com.example.club.sportif.dto.RetentionDTO;
import com.example.club.sportif.service.StatsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;
    public StatsController(StatsService statsService) { this.statsService = statsService; }

    @GetMapping("/frequentation")
    public String frequentation(Model model) {
        List<ActiviteFrequentationDTO> rows = statsService.frequentationParActivite();
        model.addAttribute("rows", rows);
        return "stats/frequentation";
    }

    @GetMapping("/retention")
    public String retention(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate p1Start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate p1End,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate p2Start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate p2End,
            Model model
    ) {
        LocalDate now = LocalDate.now();
        if (p1Start == null || p1End == null) {
            p1Start = now.minusMonths(2).withDayOfMonth(1);
            p1End   = now.minusMonths(2).withDayOfMonth(now.minusMonths(2).lengthOfMonth());
        }
        if (p2Start == null || p2End == null) {
            p2Start = now.minusMonths(1).withDayOfMonth(1);
            p2End   = now.minusMonths(1).withDayOfMonth(now.minusMonths(1).lengthOfMonth());
        }

        double rate = statsService.retention(p1Start, p1End, p2Start, p2End);
        long base = statsService.retentionBase(p1Start, p1End);
        long kept = statsService.retentionKept(p1Start, p1End, p2Start, p2End);
        RetentionDTO dto = new RetentionDTO(rate, base, kept);
        model.addAttribute("p1Start", p1Start);
        model.addAttribute("p1End", p1End);
        model.addAttribute("p2Start", p2Start);
        model.addAttribute("p2End", p2End);
        model.addAttribute("retention", dto);
        return "stats/retention";
    }


    @GetMapping("/members-by-category")
    public String membersByCategory(Model model) {
        List<LabelCountDTO> rows = statsService.membresParCategorie();
        model.addAttribute("rows", rows);
        return "stats/members_by_category";
    }

    @GetMapping
    public String dashboard(Model model) {
        // Données pour le tableau de bord
        model.addAttribute("frequentation", statsService.frequentationParActivite());
        model.addAttribute("membresParCategorie", statsService.membresParCategorie());
        model.addAttribute("activitesParNiveau", statsService.activitesParNiveau());
        
        // Statistiques générales
        model.addAttribute("totalActivites", statsService.totalActivites());
        model.addAttribute("totalMembres", statsService.totalMembres());
        model.addAttribute("totalInscriptions", statsService.totalInscriptions());
        model.addAttribute("inscriptionsCeMois", statsService.inscriptionsCeMois());
        
        return "stats/dashboard";
    }

    @GetMapping("/activities-by-level")
    public String activitiesByLevel(Model model) {
        List<LabelCountDTO> rows = statsService.activitesParNiveau();
        model.addAttribute("rows", rows);
        return "stats/activities_by_level";
    }

    @GetMapping("/advanced")
    public String advanced(Model model) {
        // Données pour les statistiques avancées
        model.addAttribute("frequentation", statsService.frequentationParActivite());
        model.addAttribute("membresParCategorie", statsService.membresParCategorie());
        model.addAttribute("activitesParNiveau", statsService.activitesParNiveau());
        return "stats/advanced";
    }
}


