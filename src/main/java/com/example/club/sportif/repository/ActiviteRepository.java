package com.example.club.sportif.repository;

import com.example.club.sportif.domain.Activite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActiviteRepository extends CrudRepository<Activite, Long> {
    List<Activite> findByNiveau(String niveau);

    @Query("select new com.example.club.sportif.dto.LabelCountDTO(a.niveau, count(a)) from Activite a group by a.niveau order by count(a) desc")
    List<com.example.club.sportif.dto.LabelCountDTO> activitesParNiveau();
}


