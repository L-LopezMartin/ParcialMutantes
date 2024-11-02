package com.parcial1.controllers;

import com.parcial1.dtos.DTOPersonaInput;
import com.parcial1.services.PersonaServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PersonaControllerTest {

    @Autowired
    PersonaController personaController;

    @Autowired
    PersonaServiceImpl personaService;

    @AfterEach
    public void limpiarBD(){
        personaService.deleteAll();
    }

    @Test
    public void testMutantMutant(){
          try{
              String[] string = {
                      "AAAA",
                      "AAAA",
                      "AAAA",
                      "AAAA"
              };
              DTOPersonaInput dtoI = new DTOPersonaInput(string);
              ResponseEntity<?> resp = personaController.mutant(dtoI);
              Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
    }

    @Test
    public void testMutantHuman(){
        try{
            String[] string = {
                    "ATTT",
                    "CCCG",
                    "GGGC",
                    "AATT"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(string);
            ResponseEntity<?> resp = personaController.mutant(dtoI);
            Assertions.assertEquals(HttpStatus.FORBIDDEN, resp.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testExistente(){
        try{
            String[] string = {
                    "AAAA",
                    "ACCG",
                    "AGGC",
                    "AATT"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(string);
            personaController.mutant(dtoI);
            ResponseEntity<?> resp = personaController.mutant(dtoI);
            Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMutantWrongChar(){
        try{
            String[] string = {
                    "ADAA",
                    "AAAA",
                    "AAAA",
                    "AAAA"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(string);
            ResponseEntity<?> resp = personaController.mutant(dtoI);
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMutantNotSquare(){
        try{
            String[] string = {
                    "AAAAAAAA",
                    "AAAAAAAA",
                    "AAAATTTT",
                    "AAAAGGGG"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(string);
            ResponseEntity<?> resp = personaController.mutant(dtoI);
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAll(){
        try{
            ResponseEntity<?> resp = personaController.getAll();
            Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Order(0)
    @Test
    public void testGetOne(){
        try{
            String[] string = {
                    "ATTT",
                    "CCCG",
                    "GGGC",
                    "AATT"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(string);
            personaController.mutant(dtoI);
            ResponseEntity<?> resp = personaController.getOne(1L);
            Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
