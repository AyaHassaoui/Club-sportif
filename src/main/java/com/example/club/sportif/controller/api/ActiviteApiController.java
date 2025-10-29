package com.example.club.sportif.controller.api;

import com.example.club.sportif.domain.Activite;
import com.example.club.sportif.repository.ActiviteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activites")
@CrossOrigin(origins = "http://localhost:5173")
public class ActiviteApiController {

    private final ActiviteRepository activiteRepository;

    public ActiviteApiController(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    @GetMapping
    public List<Activite> getAllActivites(@RequestParam(required = false) String niveau,
                                        @RequestParam(required = false) String nom,
                                        @RequestParam(required = false) Integer capaciteMin,
                                        @RequestParam(required = false) Integer capaciteMax) {
        List<Activite> activites = (List<Activite>) activiteRepository.findAll();
        
        // Filtrage par niveau
        if (niveau != null && !niveau.isBlank()) {
            activites = activites.stream()
                    .filter(a -> a.getNiveau().equalsIgnoreCase(niveau))
                    .toList();
        }
        
        // Filtrage par nom (recherche partielle)
        if (nom != null && !nom.isBlank()) {
            activites = activites.stream()
                    .filter(a -> a.getNom().toLowerCase().contains(nom.toLowerCase()))
                    .toList();
        }
        
        // Filtrage par capacité minimale
        if (capaciteMin != null) {
            activites = activites.stream()
                    .filter(a -> a.getCapacite() >= capaciteMin)
                    .toList();
        }
        
        // Filtrage par capacité maximale
        if (capaciteMax != null) {
            activites = activites.stream()
                    .filter(a -> a.getCapacite() <= capaciteMax)
                    .toList();
        }
        
        return activites;
    }

    @GetMapping("/{id}")
    public Activite getActivite(@PathVariable Long id) {
        return activiteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Activite not found: " + id));
    }

    @PostMapping
    public Activite createActivite(@RequestBody Activite activite) {
        return activiteRepository.save(activite);
    }

    @PutMapping("/{id}")
    public Activite updateActivite(@PathVariable Long id, @RequestBody Activite activite) {
        activite.setId(id);
        return activiteRepository.save(activite);
    }

    @DeleteMapping("/{id}")
    public void deleteActivite(@PathVariable Long id) {
        activiteRepository.deleteById(id);
    }
}
