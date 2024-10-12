package com.parcial1.entities;

import lombok.*;

import java.util.Random;


@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class ADN {
    public ADNValues[][] genoma;
    public int dimension;
    public boolean isMutant = false;


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
    public void isMutant(){
        int acumulador = 0;
        // i es la fila
        for (int i=0;i< dimension;i++){
            // j es la columna
            for (int j = 0; j < dimension; j++) {

                ADNValues actual = genoma[i][j];
                //Para cada valor verificado, buscar si forma segementos con 4 valores iguales consecutivos
                //Para ello, se chequea el valor vecino de la derecha, debajo y diagonales hacia abajo
                boolean displ00 = i%2 == 0 && (j%4 == 0 ||j%4 == 1);
                boolean displ12 = i%2 == 1 && (j%4 == 2 ||j%4 == 3);

                //Las búsquedas hacia delante son iguales sin importar la fila
                // ---- Chequear delante
                if (j + 2 < dimension && (displ00 || displ12))
                {             //Está dentro del límite hacia delante?
                    if (actual == genoma[i][j + 2]) {
                        //Chequear horizontal delante
                        if (j+3 < dimension && actual == genoma[i][j+1] && actual == genoma[i][j+3])
                            {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}
                            //Chequear horizontal detrás
                        else if (j-1 > 0 && actual == genoma[i][j-1] && actual == genoma[i][j+1])
                            {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}
                    }
                }

                // ---- Chequear abajo y diagonales
                // Las búsquedas diagones y hacia abajo cambian dependiendo de si es la diagonal corta(i par) o larga(i impar)
                if (displ00)              //Diagonales largas para i par y j de 2 en 2
                {
                    // ---- Chequear abajo
                    if (i + 1 < dimension && actual == genoma[i+1][j]) {                //Está dentro del límite hacia abajo y es igual?
                        //Sólo se contabiliza 1 de las siguientes situaciones, caso contrario se contarían x2 las líneas de 5 iguales y x3 las de 6
                        //Chequear vertical arriba mucho
                        if (i-2 > 0 && actual == genoma[i-1][j] && actual == genoma[i-2][j])
                            {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}
                            //Chequear vertical abajo mucho
                        else if (i+3 < dimension && actual == genoma[i+2][j] && actual == genoma[i+3][j])
                            {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}
                            //Chequear vertical ambos
                        else if (i-1 > 0 && i+2 < dimension && actual == genoma[i-1][j] && actual == genoma[i+2][j])
                            {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}
                    }

                    // ---- Chequear diagonales
                    if (i + 3 < dimension) {                       //Está dentro del límite hacia abajo?
                        //Chequear diagonal atrás
                        if (j - 3 > 0 && actual == genoma[i+3][j-3] && actual == genoma[i+1][j-1] && actual == genoma[i+2][j-2])
                            {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}
                        //Chequear diagonal delante
                        if (j + 3 < dimension && actual == genoma[i+3][j+3] && actual == genoma[i+1][j+1] && actual == genoma[i+2][j+2])
                            {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}
                    }
                }
                else if(displ12)        //Diagonales cortas para i impar y j 2 en 2 desplazado por 2
                {
                    // ---- Chequear abajo
                    if (i + 3 < dimension && actual == genoma[i+1][j] && actual == genoma[i+2][j] && actual == genoma[i+3][j])
                        {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}

                    // ---- Chequear diagonales
                    if (i + 3 < dimension) {                       //Está dentro del límite hacia abajo?
                        //Chequear diagonal atrás
                        if (j - 3 > 0 && actual == genoma[i+3][j-3] && actual == genoma[i+1][j-1] && actual == genoma[i+2][j-2])
                            {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}
                        //Chequear diagonal delante
                        if (j + 3 < dimension && actual == genoma[i+3][j+3] && actual == genoma[i+1][j+1] && actual == genoma[i+2][j+2])
                            {acumulador ++; System.out.println(genoma[i][j] + ";" + i+";"+j );}
                    }
                }

                //Si hay al menos 2 segmentos con 4 elementos iguales consecutivos, devolver isMutante = true
                if (acumulador >= 2){
                    isMutant = true;
                    return;
                }
            }
                System.out.println(acumulador);
        }
        isMutant = false;
    }

    //Imprime la matriz en consola
    public void printGenoma(){
        for (int i=0;i< genoma.length;i++){
            for (int j = 0; j < genoma[i].length; j++) {
                System.out.print(genoma[i][j] + ",");
            }
            System.out.println();
        }
    }

    //Genera una matriz cuadrada con la dimensión que se le envíe
    public void genomaAleatorio(int dim){
        ADNValues[][] matriz = new ADNValues[dim][dim];
        ADNValues[] valores = ADNValues.values();
        Random random = new Random();
        for (int i=0;i< dim;i++){
            for (int j = 0; j < dim; j++) {
                int k = random.nextInt(4);
                matriz[i][j] = valores[k];
            }
        }
        dimension = dim;
        genoma = matriz;
    }
}
