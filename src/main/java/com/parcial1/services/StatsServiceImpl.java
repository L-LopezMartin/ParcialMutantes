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
        dto.setCount_mutant_dna(mutants);
        dto.setCount_human_dna(nonMutants);
        dto.setRatio(nonMutants == 0 ? mutants : (float) mutants / nonMutants);

        return dto;
    }
}
