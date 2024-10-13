package com.parcial1;

import com.parcial1.entities.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Persona persona = new Persona();
        persona.genomaAleatorio(8);
        persona.printGenomaString();
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


//        NucleotidoInstancia[][] matriz3 = {
//                {new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("C"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A")},
//                {new NucleotidoInstancia("T"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("G"), new NucleotidoInstancia("A")},
//                {new NucleotidoInstancia("T"), new NucleotidoInstancia("C"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A")},
//                {new NucleotidoInstancia("G"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("C"), new NucleotidoInstancia("C"), new NucleotidoInstancia("C")},
//                {new NucleotidoInstancia("G"), new NucleotidoInstancia("C"), new NucleotidoInstancia("T"), new NucleotidoInstancia("C"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A")},
//                {new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("G"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("G"), new NucleotidoInstancia("A")},
//                {new NucleotidoInstancia("G"), new NucleotidoInstancia("C"), new NucleotidoInstancia("A"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("C"), new NucleotidoInstancia("C"), new NucleotidoInstancia("C")},
//                {new NucleotidoInstancia("G"), new NucleotidoInstancia("C"), new NucleotidoInstancia("G"), new NucleotidoInstancia("G"), new NucleotidoInstancia("T"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A"), new NucleotidoInstancia("A")},
//        };
//        persona.setGenoma(matriz3);
//        persona.printGenoma();
//        persona.isMutante();
//        System.out.println(persona.isMutant());

        String[] matriz4 = {
                "AAAA",
                "CCCC",
                "GGGG",
                "TTTT",
        };
        persona.setGenomaString(matriz4);
        persona.printGenomaString();
        persona.isMutante();
        System.out.println(persona.isMutant());
    }
}



