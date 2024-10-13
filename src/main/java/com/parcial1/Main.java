package com.parcial1;

import com.parcial1.entities.ADN;
import com.parcial1.entities.ADNValues;

public class Main {

    //TODO que los atributos de ADN vuelvan a ser privados. Getter y setter de lombok no funcionan

    public static void main(String[] args) {
        ADN persona = new ADN();
        persona.genomaAleatorio(8);
        persona.printGenoma();
        persona.isMutant();
        System.out.println(persona.isMutant);
        //ADNValues[][] matriz2 = {
        //        {ADNValues.G,ADNValues.C,ADNValues.T,ADNValues.C,ADNValues.A},
        //        {ADNValues.A,ADNValues.T,ADNValues.A,ADNValues.T,ADNValues.G},
        //        {ADNValues.A,ADNValues.T,ADNValues.C,ADNValues.G,ADNValues.G},
        //        {ADNValues.C,ADNValues.C,ADNValues.G,ADNValues.T,ADNValues.G},
        //        {ADNValues.C,ADNValues.G,ADNValues.G,ADNValues.G,ADNValues.G},
        //};
        //persona.genoma = matriz2;
        //persona.printGenoma();
        //persona.isMutant();
        //System.out.println(persona.isMutant);


        ADNValues[][] matriz3 = {
                {ADNValues.A,ADNValues.A,ADNValues.A,ADNValues.A},
                {ADNValues.C,ADNValues.G,ADNValues.T,ADNValues.G},
                {ADNValues.T,ADNValues.T,ADNValues.T,ADNValues.T},
                {ADNValues.G,ADNValues.G,ADNValues.G,ADNValues.G},
        };
        persona.genoma = matriz3;
        persona.printGenoma();
        persona.isMutant();
        System.out.println(persona.isMutant);
    }
}



