package com.parcial1.services;

import com.parcial1.dtos.DTOPersonaInput;
import com.parcial1.dtos.DTOPersonaOutput;
import com.parcial1.exceptions.MalGenoma;
import com.parcial1.exceptions.MatrizNoCuadrada;
import com.parcial1.exceptions.NoMutante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonaServiceImplTest {

    @Autowired
    PersonaServiceImpl personaService;

    // ======= Tests de inputs incorrectos
    @Test
    public void testNullGenome(){
        String[] genoma ={
                null
        };
        DTOPersonaInput dto = new DTOPersonaInput(genoma);
        Assertions.assertThrows(Exception.class, () -> {personaService.isMutant(dto);});
    }

    @Test
    public void testWrongChar(){
        String[] genoma ={
                "AAAA",
                "ATAG",
                "AATG",
                "AAAF"
        };
        DTOPersonaInput dto = new DTOPersonaInput(genoma);
        Assertions.assertThrows(MalGenoma.class, () -> {personaService.isMutant(dto);});
    }

    @Test
    public void testEmptyGenome(){
        String[] genoma ={
                ""
        };
        DTOPersonaInput dto = new DTOPersonaInput(genoma);
        Assertions.assertThrows(Exception.class, () -> {personaService.isMutant(dto);});
    }

    @Test
    public void testDifferentLengthRow(){
        String[] genoma ={
                "AAAA",
                "ATCG",
                "ATCG",
                "ATCGT"
        };
        DTOPersonaInput dto = new DTOPersonaInput(genoma);
        Assertions.assertThrows(MatrizNoCuadrada.class, () -> {personaService.isMutant(dto);});
    }

    @Test
    public void testNonSquareGenome(){
        String[] genoma ={
                "AAAAT",
                "ATCGT",
                "ATCGT",
                "ATCGT"
        };
        DTOPersonaInput dto = new DTOPersonaInput(genoma);
        Assertions.assertThrows(MatrizNoCuadrada.class, () -> {personaService.isMutant(dto);});
    }

    // ======== Tests de resultados
    @Test
    public void testHumanGenome(){
        String[] genoma = {
                "ATCG",
                "CACT",
                "ATTG",
                "ATCG"
        };
        DTOPersonaInput dto = new DTOPersonaInput(genoma);
        Assertions.assertThrows(NoMutante.class, () -> {personaService.isMutant(dto);});
    }

    //Chequea horizontal hacia delante y hacia atrás
    @Test
    public void testMutantByHorizontals(){
        try{
            String[] genoma ={
                    "AAAA",
                    "TGCA",
                    "AAAA",
                    "TCCG"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(genoma);
            DTOPersonaOutput dtoO = personaService.isMutant(dtoI);
            Assertions.assertTrue(dtoO.isMutant());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Chequea vertical de diagonales cortas
    //Chequea vertical de diagonales largas, mucho para abajo
    @Test
    public void testMutantByVertical1(){
        try{
            String[] genoma ={
                    "AGACT",
                    "AGCAA",
                    "ATCTT",
                    "ACCGT",
                    "GCCTA"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(genoma);
            DTOPersonaOutput dtoO = personaService.isMutant(dtoI);
            Assertions.assertTrue(dtoO.isMutant());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Chequea vertical de diagonales largas, ambas
    //Chequea vertical de diagonales largas, mucho para arriba
    @Test
    public void testMutantByVertical2(){
        try{
            String[] genoma ={
                    "AGATT",
                    "TCATA",
                    "ACCTT",
                    "ACCTT",
                    "GCATA"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(genoma);
            DTOPersonaOutput dtoO = personaService.isMutant(dtoI);
            Assertions.assertTrue(dtoO.isMutant());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Chequea diagonal izquierda de diagonales largas
    //Chequea diagonal izquierda de diagonales cortas, mucho para abajo
    @Test
    public void testMutantDiagonalLeft1(){
        try{
            String[] genoma ={
                    "AGATT",
                    "TAATA",
                    "ATATT",
                    "ACTAT",
                    "GCATA"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(genoma);
            DTOPersonaOutput dtoO = personaService.isMutant(dtoI);
            Assertions.assertTrue(dtoO.isMutant());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Chequea diagonal izquierda de diagonales cortas, ambas
    //Chequea diagonal izquierda de diagonales cortas, mucho para arriba
    @Test
    public void testMutantDiagonalLeft2(){
        try{
            String[] genoma ={
                    "AAATTA",
                    "TTATAT",
                    "ACTATA",
                    "AGCTAC",
                    "GCATTG"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(genoma);
            DTOPersonaOutput dtoO = personaService.isMutant(dtoI);
            Assertions.assertTrue(dtoO.isMutant());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
