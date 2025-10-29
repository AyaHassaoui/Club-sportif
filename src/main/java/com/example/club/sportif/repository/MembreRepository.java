package com.example.club.sportif.repository;

import com.example.club.sportif.domain.Membre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MembreRepository extends CrudRepository<Membre, Long> {
    List<Membre> findByCategorie(String categorie);

    @Query("select new com.example.club.sportif.dto.LabelCountDTO(m.categorie, count(m)) from Membre m group by m.categorie order by count(m) desc")
    List<com.example.club.sportif.dto.LabelCountDTO> repartitionParCategorie();
}


