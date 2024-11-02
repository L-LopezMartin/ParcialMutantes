package com.parcial1.services;

import com.parcial1.dtos.DTOPersonaInput;
import com.parcial1.dtos.DTOPersonaOutput;
import com.parcial1.exceptions.MalGenomaException;
import com.parcial1.exceptions.MatrizNoCuadradaException;
import com.parcial1.exceptions.NoMutanteException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PersonaServiceImplTest {

    @Autowired
    PersonaServiceImpl personaService;

    @AfterEach
    public void limpiarBD(){
        personaService.deleteAll();
    }

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
        Assertions.assertThrows(MalGenomaException.class, () -> {personaService.isMutant(dto);});
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
        Assertions.assertThrows(MatrizNoCuadradaException.class, () -> {personaService.isMutant(dto);});
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
        Assertions.assertThrows(MatrizNoCuadradaException.class, () -> {personaService.isMutant(dto);});
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
        Assertions.assertThrows(NoMutanteException.class, () -> {personaService.isMutant(dto);});
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

    //Chequea diagonal delante de diagonales largas
    //Chequea diagonal delante de diagonales cortas, mucho para abajo
    @Test
    public void testMutantDiagonalFront1(){
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

    //Chequea diagonal delante de diagonales cortas, ambas
    //Chequea diagonal delante de diagonales cortas, mucho para arriba
    @Test
    public void testMutantDiagonalFront2(){
        try{
            String[] genoma ={
                    "AAATTA",
                    "TTATAT",
                    "ACTATA",
                    "AGCTAC",
                    "GCATTG",
                    "GCACCC"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(genoma);
            DTOPersonaOutput dtoO = personaService.isMutant(dtoI);
            Assertions.assertTrue(dtoO.isMutant());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Chequea diagonal detrás de diagonales largas
    //Chequea diagonal detrás de diagonales cortas, mucho para abajo
    @Test
    public void testMutantDiagonalBack1(){
        try{
            String[] genoma ={
                    "AGATA",
                    "GAAAT",
                    "ATATT",
                    "AATGT",
                    "GTATA"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(genoma);
            DTOPersonaOutput dtoO = personaService.isMutant(dtoI);
            Assertions.assertTrue(dtoO.isMutant());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Chequea diagonal detrás de diagonales cortas, ambas
    //Chequea diagonal detrás de diagonales cortas, mucho para arriba
    @Test
    public void testMutantDiagonalBack2(){
        try{
            String[] genoma ={
                    "ACAACA",
                    "TTATAT",
                    "AATATA",
                    "ATCTAC",
                    "TCATCG",
                    "GCACCC"
            };
            DTOPersonaInput dtoI = new DTOPersonaInput(genoma);
            DTOPersonaOutput dtoO = personaService.isMutant(dtoI);
            Assertions.assertTrue(dtoO.isMutant());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Debería devolver una lista con DTOPersonaOutput
    @Test
    public void testFindAll(){
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

            List<?> listO = personaService.findAll();
            Assertions.assertEquals(DTOPersonaOutput.class, listO.get(0).getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Debería devolver el elemento con cierto índice
    @Test
    public void testFindByIDIndex1(){
        try{
            String[] genoma1 = {
                    "ATCG",
                    "ATCT",
                    "ATTG",
                    "ATCG"
            };
            DTOPersonaInput dtoI1 = new DTOPersonaInput(genoma1);
            DTOPersonaOutput dtoOS = personaService.isMutant(dtoI1);

            String[] genoma2 ={
                    "AAAA",
                    "TGCA",
                    "AAAA",
                    "TCCG"
            };
            DTOPersonaInput dtoI2 = new DTOPersonaInput(genoma2);
            personaService.isMutant(dtoI2);

            Long index = dtoOS.getId();
            DTOPersonaOutput dtoOF = personaService.findById(index);
            Assertions.assertEquals(dtoOS.getId(), dtoOF.getId());
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
            DTOPersonaOutput dtoO1 = personaService.isMutant(dtoI);
            DTOPersonaOutput dtoO2 = personaService.genomaExistente(dtoI);
            Assertions.assertEquals(dtoO2.getId().toString(), dtoO1.getId().toString());
            Assertions.assertArrayEquals(dtoO2.getGenoma(), dtoO1.getGenoma());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
