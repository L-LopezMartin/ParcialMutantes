package com.parcial1.controllers;

import com.parcial1.dtos.DTOPersonaInput;
import com.parcial1.dtos.DTOPersonaOutput;
import com.parcial1.exceptions.MalGenomaException;
import com.parcial1.exceptions.MatrizNoCuadradaException;
import com.parcial1.exceptions.NoMutanteException;
import com.parcial1.services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/personas")
public class PersonaController{

    @Autowired
    PersonaServiceImpl servicio;

    @PostMapping("/mutant/")
    public ResponseEntity<?> mutant(@RequestBody DTOPersonaInput dtoInput){
        try{
            DTOPersonaOutput existente = servicio.genomaExistente(dtoInput);
            if (existente == null){
                return ResponseEntity.status(HttpStatus.OK).body(servicio.isMutant(dtoInput));
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body("{\"El genoma ya existe en la base de datos:\"}" + "\"" + existente+ "\"");
            }
        }
        catch (MalGenomaException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, "+ e.getMessage() +" }\"}");
        }
        catch (MatrizNoCuadradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, "+ e.getMessage() +" }\"}");
        }
        catch (NoMutanteException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"Error, "+ e.getMessage() +" }\"}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).
                    body(servicio.findAll());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @GetMapping("getOne/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}
