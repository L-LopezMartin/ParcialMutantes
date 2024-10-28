package com.parcial1.services;

import com.parcial1.dtos.DTOPersonaInput;
import com.parcial1.dtos.DTOPersonaOutput;
import com.parcial1.entities.Persona;

import java.util.List;

public interface PersonaService{
    public DTOPersonaOutput isMutant(DTOPersonaInput per) throws Exception;
    public List<DTOPersonaOutput> findAll() throws Exception;
    public DTOPersonaOutput findById(Long id) throws Exception;
}
