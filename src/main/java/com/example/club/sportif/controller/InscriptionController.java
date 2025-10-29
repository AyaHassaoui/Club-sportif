package com.example.club.sportif.controller;

import com.example.club.sportif.domain.Activite;
import com.example.club.sportif.domain.Inscription;
import com.example.club.sportif.domain.Membre;
import com.example.club.sportif.repository.ActiviteRepository;
import com.example.club.sportif.repository.InscriptionRepository;
import com.example.club.sportif.repository.MembreRepository;
import com.example.club.sportif.service.InscriptionFilter;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/inscriptions")
public class InscriptionController {

    private final InscriptionRepository inscriptionRepository;
    private final ActiviteRepository activiteRepository;
    private final MembreRepository membreRepository;

    public InscriptionController(InscriptionRepository insRepo, ActiviteRepository actRepo, MembreRepository memRepo) {
        this.inscriptionRepository = insRepo;
        this.activiteRepository = actRepo;
        this.membreRepository = memRepo;
    }

    @GetMapping
    public String list(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                       Model model) {
        List<Inscription> data;
        if (start != null && end != null && !end.isBefore(start)) {
            data = inscriptionRepository.findByDateInscriptionBetween(start, end);
        } else {
            data = (List<Inscription>) inscriptionRepository.findAll();
        }
        model.addAttribute("inscriptions", data);
        model.addAttribute("filter", new InscriptionFilter(start, end));
        return "inscriptions/list";
    }

    @GetMapping("/new")
    public String showForm(Inscription inscription, Model model) {
        model.addAttribute("activites", activiteRepository.findAll());
        model.addAttribute("membres", membreRepository.findAll());
        return "inscriptions/form";
    }

    @PostMapping
    public String create(@Valid Inscription inscription, BindingResult result, Model model,
                        @RequestParam("activiteId") Long activiteId,
                        @RequestParam("membreId") Long membreId) {
        
        if (result.hasErrors()) {
            model.addAttribute("activites", activiteRepository.findAll());
            model.addAttribute("membres", membreRepository.findAll());
            return "inscriptions/form";
        }
        
        // Récupérer les objets Activite et Membre
        Activite activite = activiteRepository.findById(activiteId)
                .orElseThrow(() -> new IllegalArgumentException("Activité non trouvée: " + activiteId));
        Membre membre = membreRepository.findById(membreId)
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé: " + membreId));
        
        // Associer les objets à l'inscription
        inscription.setActivite(activite);
        inscription.setMembre(membre);
        
        inscriptionRepository.save(inscription);
        return "redirect:/inscriptions";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Inscription i = inscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inscription not found: " + id));
        model.addAttribute("inscription", i);
        model.addAttribute("activites", activiteRepository.findAll());
        model.addAttribute("membres", membreRepository.findAll());
        return "inscriptions/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid Inscription inscription, BindingResult result, Model model,
                        @RequestParam("activiteId") Long activiteId,
                        @RequestParam("membreId") Long membreId) {
        
        if (result.hasErrors()) {
            model.addAttribute("activites", activiteRepository.findAll());
            model.addAttribute("membres", membreRepository.findAll());
            return "inscriptions/form";
        }
        
        // Récupérer les objets Activite et Membre
        Activite activite = activiteRepository.findById(activiteId)
                .orElseThrow(() -> new IllegalArgumentException("Activité non trouvée: " + activiteId));
        Membre membre = membreRepository.findById(membreId)
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé: " + membreId));
        
        // Associer les objets à l'inscription
        inscription.setId(id);
        inscription.setActivite(activite);
        inscription.setMembre(membre);
        
        inscriptionRepository.save(inscription);
        return "redirect:/inscriptions";
    }

    @PostMapping("/{id}/statut")
    public String updateStatut(@PathVariable Long id, @RequestParam String statut) {
        Inscription i = inscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inscription not found: " + id));
        i.setStatut(statut);
        inscriptionRepository.save(i);
        return "redirect:/inscriptions";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        inscriptionRepository.deleteById(id);
        return "redirect:/inscriptions";
    }
}


