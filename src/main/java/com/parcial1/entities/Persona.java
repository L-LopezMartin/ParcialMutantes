package com.parcial1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Entity
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
@ToString
public class Persona extends Base{

    @Column (nullable = false)
    private String[] genoma;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Transient
    private transient final String posiblesValores = "GCAT";

    private boolean isMutant = false;

    //Genera una matriz cuadrada con la dimensión que se le envíe
    public void genomaAleatorio(int dim){
        Random random = new Random();
        genoma = new String[dim];
        for (int i=0;i< dim;i++){
            genoma[i] = "";
            for (int j = 0; j < dim; j++) {
                int k = random.nextInt(posiblesValores.length());
                genoma[i] = genoma[i] + posiblesValores.charAt(k);
            }
        }
    }

    //Imprime la matriz en consola
    public void printGenoma(){
        int length = genoma.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(genoma[i].charAt(j) + ",");
            }
            System.out.println();
        }
    }

    //Colocar un nuevo genoma a la persona marca la persona como no verificada
    public void setGenoma(String[] input){
        this.genoma = input;
        isMutant = false;
    }

    public Persona(String[] genoma){
        this.genoma = genoma;
    }
}
