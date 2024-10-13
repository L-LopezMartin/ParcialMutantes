package com.parcial1;

import com.parcial1.entities.ADN;
import com.parcial1.entities.Nucleotidos;
import com.parcial1.entities.NucleotidoInstancia;

public class Main {

    public static void main(String[] args) {
        ADN persona = new ADN();
        persona.genomaAleatorio(1);
        persona.printGenoma();
        persona.isMutante();
        System.out.println(persona.isMutant());
        //ADNValues[][] matriz2 = {
        //        {ADNValues.G,ADNValues.C,ADNValues.T,ADNValues.C,ADNValues.A},
        //        {ADNValues.A,ADNValues.T,ADNValues.A,ADNValues.T,ADNValues.G},
        //        {ADNValues.A,ADNValues.T,ADNValues.C,ADNValues.G,ADNValues.G},
        //        {ADNValues.C,ADNValues.C,ADNValues.G,ADNValues.T,ADNValues.G},
        //        {ADNValues.C,ADNValues.G,ADNValues.G,ADNValues.G,ADNValues.G},
        //};
        //persona.setGenoma(matriz2);
        //persona.printGenoma();
        //persona.isMutante();
        //System.out.println(persona.isMutant());


        NucleotidoInstancia[][] matriz3 = {
                {new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("C"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A")},
                {new NucleotidoInstancia("T"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("G"), new NucleotidoInstancia("A")},
                {new NucleotidoInstancia("T"), new NucleotidoInstancia("C"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A")},
                {new NucleotidoInstancia("G"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("C"), new NucleotidoInstancia("C"), new NucleotidoInstancia("C")},
                {new NucleotidoInstancia("G"), new NucleotidoInstancia("C"), new NucleotidoInstancia("T"), new NucleotidoInstancia("C"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A")},
                {new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("G"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("G"), new NucleotidoInstancia("A")},
                {new NucleotidoInstancia("G"), new NucleotidoInstancia("C"), new NucleotidoInstancia("A"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("C"), new NucleotidoInstancia("C"), new NucleotidoInstancia("C")},
                {new NucleotidoInstancia("G"), new NucleotidoInstancia("C"), new NucleotidoInstancia("G"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A")},
        };
        persona.setGenoma(matriz3);
        persona.printGenoma();
        persona.isMutante();
        System.out.println(persona.isMutant());
    }
}



