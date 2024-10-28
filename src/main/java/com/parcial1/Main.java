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
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}



