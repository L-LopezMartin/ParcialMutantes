package com.parcial1.controllers;

import com.parcial1.services.StatsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/stats")
public class StatsController {
    @Autowired
    StatsServiceImpl servicio;

    @GetMapping(path = "")
    public ResponseEntity<?> getStats(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getStats());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}
