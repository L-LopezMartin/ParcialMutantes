package com.parcial1;

import com.parcial1.entities.ADN;
import com.parcial1.entities.ADNValues;

public class Main {

    public static void main(String[] args) {
        ADN persona = new ADN();
        persona.genomaAleatorio(8);
        persona.printGenoma();
        persona.isMutant();
        System.out.println(persona.isMutant);
    }
}



