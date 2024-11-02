package com.parcial1.services;

import com.parcial1.dtos.DTOPersonaInput;
import com.parcial1.dtos.DTOStats;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatsServiceImplTest {
    @Autowired
    StatsServiceImpl statsService;

    @Autowired
    PersonaServiceImpl personaService;

    @AfterEach
    public void limpiarBD(){
        personaService.deleteAll();
    }

    //Debería devolver 0 en sus tres valores cuando no hay matrices en la base de datos
    @Test
    public void testReturnOnEmpty(){
        DTOStats dto = statsService.getStats();
        Assertions.assertEquals(0,dto.getCountHumanDna());
        Assertions.assertEquals(0,dto.getCountMutantDna());
        Assertions.assertEquals(0,dto.getRatio());
    }
    //Debería devolver la cantidad de mutantes en el ratio si no hay humanos
    @Test
    public void testReturnOnNoHumans(){
        try{
            String[] genoma1 = {
                    "ATCG",
                    "ATCT",
                    "ATTG",
                    "ATCG"
            };
            DTOPersonaInput dtoI1 = new DTOPersonaInput(genoma1);
            personaService.isMutant(dtoI1);

            String[] genoma2 ={
                    "AAAA",
                    "TGCA",
                    "AAAA",
                    "TCCG"
            };
            DTOPersonaInput dtoI2 = new DTOPersonaInput(genoma2);
            personaService.isMutant(dtoI2);

            DTOStats dto = statsService.getStats();
            Assertions.assertEquals(0,dto.getCountHumanDna());
            Assertions.assertEquals(2,dto.getCountMutantDna());
            Assertions.assertEquals(2,dto.getRatio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //Debería devolver un ratio de 0.5 para 1 mutante y 2 humanos
    @Test
    public void testReturnCorrectRatio(){
        try{
            String[] genoma1 = {
                    "ATCG",
                    "ATCT",
                    "ATTG",
                    "ATCG"
            };
            DTOPersonaInput dtoI1 = new DTOPersonaInput(genoma1);
            personaService.isMutant(dtoI1);

            String[] genoma2 ={
                    "AACA",
                    "TGCA",
                    "AATA",
                    "TCCG"
            };
            DTOPersonaInput dtoI2 = new DTOPersonaInput(genoma2);
            personaService.isMutant(dtoI2);

            String[] genoma3 ={
                    "ACAA",
                    "TGCA",
                    "ATAA",
                    "TCCG"
            };
            DTOPersonaInput dtoI3 = new DTOPersonaInput(genoma3);
            personaService.isMutant(dtoI3);

            DTOStats dto = statsService.getStats();
            Assertions.assertEquals(2,dto.getCountHumanDna());
            Assertions.assertEquals(1,dto.getCountMutantDna());
            Assertions.assertEquals(0.5,dto.getRatio());
        } catch (Exception ignored) { }
    }
}
