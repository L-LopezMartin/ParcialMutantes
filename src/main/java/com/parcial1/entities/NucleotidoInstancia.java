package com.parcial1.entities;

import lombok.*;

import java.util.Random;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class NucleotidoInstancia {

    private Nucleotidos valor;

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
        Nucleotidos[] valores = Nucleotidos.values();
        Random random = new Random();
        int i = random.nextInt(valores.length);
        valor = valores[i];
    }
    @Override
    public String toString(){
        return valor.toString();
    }
    public NucleotidoInstancia(String valor){
        this.valor = Nucleotidos.valueOf(valor);
    }
}
