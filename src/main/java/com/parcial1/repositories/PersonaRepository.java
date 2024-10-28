package com.parcial1.repositories;

import com.parcial1.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long>{
    @Query(
            value = "SELECT COUNT(*) FROM persona WHERE persona.is_mutant = ?1",
            nativeQuery = true
    )
    int sumCountByMutant(@Param("VoF") boolean VoF);

}
