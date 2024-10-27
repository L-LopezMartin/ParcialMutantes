package com.parcial1.services;

import com.parcial1.entities.Persona;

import java.util.List;

public interface PersonaService extends BaseService<Persona, Long>{
    public Persona isMutant(Long id) throws Exception;
}
