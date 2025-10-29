package com.example.club.sportif.controller;

import com.example.club.sportif.domain.Membre;
import com.example.club.sportif.repository.MembreRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/membres")
public class MembreController {

    private final MembreRepository membreRepository;

    public MembreController(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String categorie, Model model) {
        if (categorie != null && !categorie.isBlank()) {
            model.addAttribute("membres", membreRepository.findByCategorie(categorie));
        } else {
            model.addAttribute("membres", membreRepository.findAll());
        }
        model.addAttribute("categorie", categorie);
        return "membres/list";
    }

    @GetMapping("/new")
    public String showForm(Membre membre) {
        return "membres/form";
    }

    @PostMapping
    public String create(@Valid Membre membre, BindingResult result) {
        if (result.hasErrors()) return "membres/form";
        membreRepository.save(membre);
        return "redirect:/membres";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Membre m = membreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Membre not found: " + id));
        model.addAttribute("membre", m);
        return "membres/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid Membre membre, BindingResult result) {
        if (result.hasErrors()) return "membres/form";
        membre.setId(id);
        membreRepository.save(membre);
        return "redirect:/membres";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        membreRepository.deleteById(id);
        return "redirect:/membres";
    }
}


