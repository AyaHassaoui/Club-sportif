package com.example.club.sportif.controller.api;

import com.example.club.sportif.domain.Inscription;
import com.example.club.sportif.repository.InscriptionRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/inscriptions")
@CrossOrigin(origins = "http://localhost:5173")
public class InscriptionApiController {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionApiController(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    @GetMapping
    public List<Inscription> getAllInscriptions(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        if (start != null && end != null && !end.isBefore(start)) {
            return inscriptionRepository.findByDateInscriptionBetween(start, end);
        }
        return (List<Inscription>) inscriptionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Inscription getInscription(@PathVariable Long id) {
        return inscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inscription not found: " + id));
    }

    @PostMapping
    public Inscription createInscription(@RequestBody Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    @PutMapping("/{id}")
    public Inscription updateInscription(@PathVariable Long id, @RequestBody Inscription inscription) {
        inscription.setId(id);
        return inscriptionRepository.save(inscription);
    }

    @DeleteMapping("/{id}")
    public void deleteInscription(@PathVariable Long id) {
        inscriptionRepository.deleteById(id);
    }
}
