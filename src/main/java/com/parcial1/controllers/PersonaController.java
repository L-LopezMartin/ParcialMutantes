package com.parcial1.controllers;

import com.parcial1.entities.Persona;
import com.parcial1.services.PersonaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/personas")
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl>{
    @GetMapping("/checkMutant/{id}")
    public ResponseEntity<?> search(@RequestParam Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.isMutant(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
