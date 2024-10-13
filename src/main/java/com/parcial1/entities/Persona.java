package com.parcial1.entities;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
@Entity
public class Persona extends Base{

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private NucleotidoInstancia[][] genoma;

    private String[] genomaString;

    @Setter(AccessLevel.NONE)
    private boolean isMutant = false;

    //Función que verifica si un ADN corresponde a un mutante
    /*
        A todo + se le chequea su + vecino.
        En caso de ser iguales, se chequean los _ que pueden formar una línea de 4 caracteres iguales

        +   _   +   _   +   _   +   _   +   _
        +   _   +   _   +   _   +   _   +   _
        _   +   _   +   _   +   _   +   _   +
        _   +   _   +   _   +   _   +   _   +
        +   _   +   _   +   _   +   _   +   _
        +   _   +   _   +   _   +   _   +   _
        _   +   _   +   _   +   _   +   _   +
        _   +   _   +   _   +   _   +   _   +

    */
    public void isMutante(){
        int dimension = genoma.length;
        int acumulador = 0;
        // i es la fila
        for (int i=0;i< dimension;i++){
            // j es la columna
            for (int j = 0; j < dimension; j++) {

                NucleotidoInstancia actual = genoma[i][j];

                //Para cada valor verificado, buscar si forma segementos con 4 valores iguales consecutivos
                //Para ello, se chequea el valor vecino de la derecha, debajo y diagonales hacia abajo
                if(!((i%4 >= 2 && j%2 == 1) || (i%4 <= 1 && j%2 == 0))) continue;             // Si se cumple, no es uno de los elementos que chequeamos
                System.out.println("Checking: "+actual + ";" + i+";"+j);

                //Las búsquedas hacia delante son iguales sin importar la fila
                // ---- Chequear delante
                if (j + 2 < dimension)
                {             //Está dentro del límite hacia delante?
                    if (actual.getValor() == genoma[i][j + 2].getValor()) {
                        //Chequear horizontal delante
                        if (j+3 < dimension && !genoma[i][j+1].isHorizontal() && actual.getValor() == genoma[i][j+1].getValor() && actual.getValor() == genoma[i][j+3].getValor())
                            {
                                acumulador ++;
                                System.out.println(actual + ";" + i+";"+j );
                                genoma[i][j+3].setHorizontal(true);
                            }
                            //Chequear horizontal detrás
                        else if (j-1 >= 0 && !genoma[i][j-1].isHorizontal() && actual.getValor() == genoma[i][j-1].getValor() && !genoma[i][j+1].isHorizontal() && actual.getValor() == genoma[i][j+1].getValor())
                            {
                                acumulador ++;
                                System.out.println(actual + ";" + i+";"+j );
                            }
                    }
                }

                // ---- Chequear abajo y diagonales
                // Las búsquedas diagones y hacia abajo cambian dependiendo de si es la diagonal corta(i par) o larga(i impar)
                if (i%2 == 0)              //Diagonales largas para i par y j de 2 en 2
                {
                    // ---- Chequear abajo
                    if (i + 1 < dimension && !genoma[i][j].isVertical() &&  actual.getValor() == genoma[i+1][j].getValor()) {                //Está dentro del límite hacia abajo y es igual?
                        //Sólo se contabiliza 1 de las siguientes situaciones, caso contrario se contarían x2 las líneas de 5 iguales y x3 las de 6

                        //Chequear vertical arriba mucho
                        if (i-2 >= 0 && !genoma[i-2][j].isVertical() && actual.getValor() == genoma[i-2][j].getValor() && actual.getValor() == genoma[i-1][j].getValor())
                            {
                                System.out.println(actual + ";" + i+";"+j+ "Bien arriba");
                                genoma[i+1][j].setVertical(true);
                                acumulador ++;
                            }
                            //Chequear vertical ambos
                        else if (i-1 >= 0 && !genoma[i-1][j].isVertical() && i+2 < dimension && actual.getValor() == genoma[i-1][j].getValor() && actual.getValor() == genoma[i+2][j].getValor())
                            {
                                System.out.println(actual + ";" + i+";"+j + "Ambos");
                                genoma[i+2][j].setVertical(true);
                                acumulador ++;
                            }
                            //Chequear vertical abajo mucho
                        else if (i+3 < dimension && actual.getValor() == genoma[i+2][j].getValor() && actual.getValor() == genoma[i+3][j].getValor())
                            {
                                System.out.println(actual + ";" + i+";"+j + "Bien abajo");
                                genoma[i+3][j].setVertical(true);
                                acumulador ++;
                            }
                    }

                    // ---- Chequear diagonales
                    if (i + 3 < dimension) {                       //Está dentro del límite hacia abajo?
                        //Chequear diagonal atrás
                        if (!genoma[i][j].isDDerecha() && j - 3 >= 0 && actual == genoma[i+3][j-3] && actual == genoma[i+1][j-1] && actual == genoma[i+2][j-2])
                            {
                                acumulador ++;
                                genoma[i+3][j-3].setDDerecha(true);
                                System.out.println(actual + ";" + i+";"+j );
                            }
                        //Chequear diagonal delante
                        if (!genoma[i][j].isDIzquierda() && j + 3 < dimension && actual == genoma[i+3][j+3] && actual == genoma[i+1][j+1] && actual == genoma[i+2][j+2])
                            {
                                acumulador ++;
                                genoma[i+3][j+3].setDIzquierda(true);
                                System.out.println(actual + ";" + i+";"+j );
                            }
                    }
                }
                else                        //Diagonales cortas para i impar y j 2 en 2 desplazado por 2
                {
                    // ---- Chequear abajo
                    if (!genoma[i][j].isVertical() && i + 3 < dimension && actual == genoma[i+1][j] && actual == genoma[i+2][j] && actual == genoma[i+3][j])
                        {
                            acumulador ++;
                            System.out.println(actual + ";" + i+";"+j );
                            genoma[i+3][j].setVertical(true);
                        }

                    // ---- Chequear diagonales
                    if (i + 3 < dimension) {                       //Está dentro del límite hacia abajo?
                        //Chequear diagonal atrás
                        if (!genoma[i][j].isDDerecha() && j - 3 >= 0 && actual == genoma[i+3][j-3] && actual == genoma[i+1][j-1] && actual == genoma[i+2][j-2])
                            {
                                acumulador ++;
                                System.out.println(actual + ";" + i+";"+j );
                                genoma[i+1][j-3].setDDerecha(true);
                            }
                        //Chequear diagonal delante
                        if (!genoma[i][j].isDIzquierda() && j + 3 < dimension && actual == genoma[i+3][j+3] && actual == genoma[i+1][j+1] && actual == genoma[i+2][j+2])
                            {
                                acumulador ++;
                                System.out.println(actual + ";" + i+";"+j );
                                genoma[i+1][j-3].setDIzquierda(true);
                            }
                    }
                }

                //Si hay al menos 2 segmentos con 4 elementos iguales consecutivos, devolver isMutante = true
                if (acumulador >= 2){
                    isMutant = true;
                    return;
                }
            }
        }
        isMutant = false;
    }

    //Imprime la matriz en consola
    public void printGenoma(){
        int length = genoma.length;
        for (int i=0;i< length;i++){
            for (int j = 0; j < length; j++) {
                System.out.print(genoma[i][j] + ",");
            }
            System.out.println();
        }
    }

    //Genera una matriz cuadrada con la dimensión que se le envíe
    public void genomaAleatorio(int dim){
        NucleotidoInstancia[][] matriz = new NucleotidoInstancia[dim][dim];
        for (int i=0;i< dim;i++){
            for (int j = 0; j < dim; j++) {
                matriz[i][j] = new NucleotidoInstancia();
                matriz[i][j].valorRand();
            }
        }
        genoma = matriz;
        genomaToString();
    }

    private void genomaToString(){
        int length = genoma.length;
        String[] genomaString = new String[length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                genomaString[i] = genomaString[i] + genoma[i][j].toString();
            }
            StringBuffer stringBuffer = new StringBuffer(genomaString[i]);
            stringBuffer.delete(0,4);
            genomaString[i] = stringBuffer.toString();
        }
        this.genomaString = genomaString;
    }

    public void printGenomaString(){
        int length = genoma.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(genomaString[i].charAt(j) + ",");
            }
            System.out.println();
        }
    }

    public void setGenomaString(String[] input){
        int length = input.length;
        genoma = new NucleotidoInstancia[length][length];
        for (int i = 0; i< length; i++){
            for (int j = 0; j < length; j++) {
                genoma[i][j] = new NucleotidoInstancia(String.valueOf(input[i].charAt(j))) ;
            }
        }
        this.genomaString = input;
        isMutant = false;
    }
}
