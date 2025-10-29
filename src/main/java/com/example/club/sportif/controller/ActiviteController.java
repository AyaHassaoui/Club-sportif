package com.example.club.sportif.controller;

import com.example.club.sportif.domain.Activite;
import com.example.club.sportif.repository.ActiviteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/activites")
public class ActiviteController {

    private final ActiviteRepository activiteRepository;

    public ActiviteController(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String niveau, Model model) {
        if (niveau != null && !niveau.isBlank()) {
            model.addAttribute("activites", activiteRepository.findByNiveau(niveau));
        } else {
            model.addAttribute("activites", activiteRepository.findAll());
        }
        model.addAttribute("niveau", niveau);
        return "activites/list";
    }

    @GetMapping("/new")
    public String showForm(Activite activite) {
        return "activites/form";
    }

    @PostMapping
    public String create(@Valid Activite activite, BindingResult result) {
        if (result.hasErrors()) return "activites/form";
        activiteRepository.save(activite);
        return "redirect:/activites";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Activite a = activiteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Activite not found: " + id));
        model.addAttribute("activite", a);
        return "activites/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid Activite activite, BindingResult result) {
        if (result.hasErrors()) return "activites/form";
        activite.setId(id);
        activiteRepository.save(activite);
        return "redirect:/activites";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        activiteRepository.deleteById(id);
        return "redirect:/activites";
    }
}


