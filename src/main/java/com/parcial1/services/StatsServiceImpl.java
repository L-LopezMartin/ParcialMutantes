package com.parcial1.services;

import com.parcial1.dtos.DTOStats;
import com.parcial1.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public DTOStats getStats() {
        int mutants = personaRepository.sumCountByMutant(true);
        int nonMutants = personaRepository.sumCountByMutant(false);

        DTOStats dto = new DTOStats();
        dto.setCountMutantDna(mutants);
        dto.setCountHumanDna(nonMutants);
        float ratio = mutants;
        if (nonMutants != 0 )
            ratio = (float) mutants / nonMutants;
        dto.setRatio(ratio);

        return dto;
    }
}
