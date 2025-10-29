package com.example.club.sportif.controller.api;

import com.example.club.sportif.domain.Membre;
import com.example.club.sportif.repository.MembreRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/membres")
@CrossOrigin(origins = "http://localhost:5173")
public class MembreApiController {

    private final MembreRepository membreRepository;

    public MembreApiController(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    @GetMapping
    public List<Membre> getAllMembres(@RequestParam(required = false) String categorie,
                                     @RequestParam(required = false) String nom,
                                     @RequestParam(required = false) String dateDebut,
                                     @RequestParam(required = false) String dateFin) {
        List<Membre> membres = (List<Membre>) membreRepository.findAll();
        
        // Filtrage par catégorie
        if (categorie != null && !categorie.isBlank()) {
            membres = membres.stream()
                    .filter(m -> m.getCategorie().equalsIgnoreCase(categorie))
                    .toList();
        }
        
        // Filtrage par nom (recherche partielle)
        if (nom != null && !nom.isBlank()) {
            membres = membres.stream()
                    .filter(m -> m.getNom().toLowerCase().contains(nom.toLowerCase()))
                    .toList();
        }
        
        // Filtrage par date d'adhésion
        if (dateDebut != null && !dateDebut.isBlank()) {
            LocalDate debut = LocalDate.parse(dateDebut);
            membres = membres.stream()
                    .filter(m -> !m.getDateAdhesion().isBefore(debut))
                    .toList();
        }
        
        if (dateFin != null && !dateFin.isBlank()) {
            LocalDate fin = LocalDate.parse(dateFin);
            membres = membres.stream()
                    .filter(m -> !m.getDateAdhesion().isAfter(fin))
                    .toList();
        }
        
        return membres;
    }

    @GetMapping("/{id}")
    public Membre getMembre(@PathVariable Long id) {
        return membreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Membre not found: " + id));
    }

    @PostMapping
    public Membre createMembre(@RequestBody Membre membre) {
        return membreRepository.save(membre);
    }

    @PutMapping("/{id}")
    public Membre updateMembre(@PathVariable Long id, @RequestBody Membre membre) {
        membre.setId(id);
        return membreRepository.save(membre);
    }

    @DeleteMapping("/{id}")
    public void deleteMembre(@PathVariable Long id) {
        membreRepository.deleteById(id);
    }
}
