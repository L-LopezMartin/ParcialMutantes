package com.parcial1;

import com.parcial1.entities.Persona;
import com.parcial1.repositories.BaseRepository;
import com.parcial1.repositories.PersonaRepository;
import com.parcial1.services.PersonaServiceImpl;
import io.swagger.v3.core.util.Json;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

        @Bean
        @Transactional
        CommandLineRunner init(PersonaRepository personaRepository){
            return args ->{

                Persona persona = new Persona();
                persona.genomaAleatorio(12);
                persona.printGenoma();
                //persona.isMutante();
                //System.out.println(persona.isMutant());


                Persona persona1 = new Persona();
                String[] matriz4 = {
                        "ATTC",
                        "CACC",
                        "GGAG",
                        "TTTA",
                };
                persona1.setGenoma(matriz4);
                persona1.printGenoma();
                //persona1.isMutante();
                //System.out.println(persona1.isMutant());

                Persona persona2 = new Persona();
                String[] matriz1 = {
                        "TGAGTGGACT",
                        "TATAGGCTTA",
                        "GTCCCCATGC",
                        "TCTGTTAAGA",
                        "ATATAGGATT",
                        "AAACGTAATC",
                        "CAGCTGAGGG",
                        "CACGCAGGAG",
                        "CAGCCTGTTG",
                        "TGTCAATGCA"
                };
                persona2.setGenoma(matriz1);
                persona2.printGenoma();

                Persona persona3 = new Persona();
                String[] matriz2 = {
                        "TGAGA",
                        "GTCCC",
                        "GATCC",
                        "GACTT",
                        "AGCCT"
                };
                persona3.setGenoma(matriz2);
                persona3.printGenoma();
            };
        }
}



