package com.parcial1.controllers;

import com.parcial1.dtos.DTOPersonaInput;
import com.parcial1.entities.Persona;
import com.parcial1.exceptions.MatrizNoCuadrada;
import com.parcial1.exceptions.NoMutante;
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
    public ResponseEntity<?> mutant(@RequestBody DTOPersonaInput persona){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.isMutant(persona));
        }
        catch (MatrizNoCuadrada e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, "+ e.getMessage() +" }\"}");
        }
        catch (NoMutante e) {
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
