package com.parcial1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;


@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class NucleotidoInstancia extends Base{


    private String valor;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private transient String posiblesValores = "GCAT";

    //Estas variables indican si elemento forma parte de una cadena...
    //... horizontal
    private boolean horizontal = false;
    //... vertical
    private boolean vertical = false;
    //... diagonal hacia la derecha (/)
    private boolean dDerecha = false;
    //... diagonal hacia la izquierda (\)
    private boolean dIzquierda = false;
    //Son usados para evitar que cadenas de 5, 6 o 7 caracteres sean tomadas como múltiples cadenas de 4

    public void valorRand(){
        Random random = new Random();
        int i = random.nextInt(posiblesValores.length());
        valor = String.valueOf(posiblesValores.charAt(i));
    }

    public NucleotidoInstancia(String valor){
        for (int i = 0; i < posiblesValores.length(); i++) {
            if (valor.charAt(0) == posiblesValores.charAt(i)) {
                this.valor = valor;
                break;
            }
        }
    }
}
