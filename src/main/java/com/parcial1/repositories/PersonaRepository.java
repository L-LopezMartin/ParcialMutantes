package com.parcial1.repositories;

import com.parcial1.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long>{
    @Query(
            value = "SELECT COUNT(*) FROM persona WHERE persona.is_mutant = ?1",
            nativeQuery = true
    )
    int sumCountByMutant(@Param("VoF") boolean VoF);

    @Query(
            value = "SELECT * FROM persona p WHERE p.genoma = :gen",
            nativeQuery = true
    )
    Optional<Persona> findByGenoma(@Param("gen") String[] gen);

}
