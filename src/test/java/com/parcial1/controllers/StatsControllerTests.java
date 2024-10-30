package com.parcial1.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class StatsControllerTests {
    @Autowired
    StatsController statsController;

    @Test
    public void testGetStats(){
        try{
            ResponseEntity<?> resp = statsController.getStats();
            Assertions.assertEquals(HttpStatus.OK , resp.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
