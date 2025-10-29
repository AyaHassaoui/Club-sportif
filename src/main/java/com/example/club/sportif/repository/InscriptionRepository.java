package com.example.club.sportif.repository;

import com.example.club.sportif.domain.Inscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
// import removed: no longer returning Map
import java.util.Set;

public interface InscriptionRepository extends CrudRepository<Inscription, Long> {
    List<Inscription> findByDateInscriptionBetween(LocalDate start, LocalDate end);

    @Query("select new com.example.club.sportif.dto.ActiviteFrequentationDTO(i.activite.nom, count(i)) from Inscription i group by i.activite.nom order by count(i) desc")
    List<com.example.club.sportif.dto.ActiviteFrequentationDTO> frequentationParActivite();

    @Query("select distinct i.membre.id from Inscription i where i.dateInscription between :start and :end")
    Set<Long> membresActifsEntre(@Param("start") LocalDate start, @Param("end") LocalDate end);

    long countByDateInscriptionBetween(LocalDate start, LocalDate end);
}


