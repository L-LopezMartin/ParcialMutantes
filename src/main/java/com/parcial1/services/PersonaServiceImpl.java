package com.parcial1.services;


import com.parcial1.entities.Persona;
import com.parcial1.repositories.BaseRepository;
import com.parcial1.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository){
        super(baseRepository);
    }

    @Override
    public Persona isMutant(Long id) throws Exception {
        
        try{
            Optional<Persona> optional = personaRepository.findById(id);
            Persona persona = optional.get();
            
            int dimension = persona.getGenoma().length;
            int acumulador = 0;
            
            //Para cada carecter de genoma, según el tercer índice indica si es parte de una cadena...
            //0-Vertical,1-Horizontal,2-Diagonal derecha /, 3-Diagonal izquierda \
            boolean[][][] parteDeCadena = new boolean[dimension][dimension][4];

            /*
                A todo + se le chequea su + vecino.
                En caso de ser iguales, se chequean los _ que pueden formar una línea de 4 caracteres iguales

                +   _   +   _   +   _   +   _
                +   _   +   _   +   _   +   _
                _   +   _   +   _   +   _   +
                _   +   _   +   _   +   _   +
                +   _   +   _   +   _   +   _
                +   _   +   _   +   _   +   _
                _   +   _   +   _   +   _   +
                _   +   _   +   _   +   _   +

            */
                // i es la fila
                for (int i=0;i< dimension;i++){
                    // j es la columna
                    for (int j = 0; j < dimension; j++) {

                        char actual = persona.getGenoma()[i].charAt(j);

                        //Para cada valor verificado, buscar si forma segementos con 4 valores iguales consecutivos
                        //Para ello, se chequea el valor vecino de la derecha, debajo y diagonales hacia abajo
                        if(!((i%4 >= 2 && j%2 == 1) || (i%4 <= 1 && j%2 == 0))) continue;             // Si se cumple, no es uno de los elementos que chequeamos
                        System.out.println("Checking: "+actual + ";" + i+";"+j);

                        //Las búsquedas hacia delante son iguales sin importar la fila
                        // ---- Chequear delante
                        if (j + 2 < dimension)
                        {             //Está dentro del límite hacia delante?
                            if (actual == persona.getGenoma()[i].charAt(j + 2)) {
                                //Chequear horizontal delante
                                if (j+3 < dimension && !parteDeCadena[i][j+1][1] && actual == persona.getGenoma()[i].charAt(j+1) && actual == persona.getGenoma()[i].charAt(j+3))
                                {
                                    acumulador ++;
                                    System.out.println(actual + ";" + i+";"+j );
                                    parteDeCadena[i][j+3][1] = true;
                                }
                                //Chequear horizontal detrás
                                else if (j-1 >= 0 && !parteDeCadena[i][j-1][1] && actual == persona.getGenoma()[i].charAt(j-1) && !parteDeCadena[i][j+1][1] && actual == persona.getGenoma()[i].charAt(j+1))
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
                            if (i + 1 < dimension && !parteDeCadena[i][j][0] &&  actual == persona.getGenoma()[i+1].charAt(j)) {                //Está dentro del límite hacia abajo y es igual?
                                //Sólo se contabiliza 1 de las siguientes situaciones, caso contrario se contarían x2 las líneas de 5 iguales y x3 las de 6

                                //Chequear vertical arriba mucho
                                if (i-2 >= 0 && !parteDeCadena[i-2][j][0] && actual == persona.getGenoma()[i-2].charAt(j) && actual == persona.getGenoma()[i-1].charAt(j))
                                {
                                    System.out.println(actual + ";" + i+";"+j+ "Bien arriba");
                                    parteDeCadena[i+1][j][0] = true;
                                    acumulador ++;
                                }
                                //Chequear vertical ambos
                                else if (i-1 >= 0 && !parteDeCadena[i-1][j][0] && i+2 < dimension && actual == persona.getGenoma()[i-1].charAt(j) && actual == persona.getGenoma()[i+2].charAt(j))
                                {
                                    System.out.println(actual + ";" + i+";"+j + "Ambos");
                                    parteDeCadena[i+2][j][0] = true;
                                    acumulador ++;
                                }
                                //Chequear vertical abajo mucho
                                else if (i+3 < dimension && actual == persona.getGenoma()[i+2].charAt(j) && actual == persona.getGenoma()[i+3].charAt(j))
                                {
                                    System.out.println(actual + ";" + i+";"+j + "Bien abajo");
                                    parteDeCadena[i+3][j][0] = true;
                                    acumulador ++;
                                }
                            }

                            // ---- Chequear diagonales
                            if (i + 3 < dimension) {                       //Está dentro del límite hacia abajo?
                                //Chequear diagonal atrás
                                if (!parteDeCadena[i][j][2] && j - 3 >= 0 && actual == persona.getGenoma()[i+3].charAt(j-3) && actual == persona.getGenoma()[i+1].charAt(j-1) && actual == persona.getGenoma()[i+2].charAt(j-2))
                                {
                                    acumulador ++;
                                    parteDeCadena[i+3][j-3][2] = true;
                                    System.out.println(actual + ";" + i+";"+j );
                                }
                                //Chequear diagonal delante
                                if (!parteDeCadena[i][j][3] && j + 3 < dimension && actual == persona.getGenoma()[i+3].charAt(j+3) && actual == persona.getGenoma()[i+1].charAt(j+1) && actual == persona.getGenoma()[i+2].charAt(j+2))
                                {
                                    acumulador ++;
                                    parteDeCadena[i+3][j+3][3] = true;
                                    System.out.println(actual + ";" + i+";"+j );
                                }
                            }
                        }
                        else                        //Diagonales cortas para i impar y j 2 en 2 desplazado por 2
                        {
                            // ---- Chequear abajo
                            if (!parteDeCadena[i][j][0] && i + 3 < dimension && actual == persona.getGenoma()[i+1].charAt(j) && actual == persona.getGenoma()[i+2].charAt(j) && actual == persona.getGenoma()[i+3].charAt(j))
                            {
                                acumulador ++;
                                System.out.println(actual + ";" + i+";"+j );
                                parteDeCadena[i+3][j][0] = true;
                            }

                            // ---- Chequear diagonales
                            if (i + 3 < dimension) {                       //Está dentro del límite hacia abajo?
                                //Chequear diagonal atrás
                                if (!parteDeCadena[i][j][2] && j - 3 >= 0 && actual == persona.getGenoma()[i+3].charAt(j-3) && actual == persona.getGenoma()[i+1].charAt(j-1) && actual == persona.getGenoma()[i+2].charAt(j-2))
                                {
                                    acumulador ++;
                                    System.out.println(actual + ";" + i+";"+j );
                                    parteDeCadena[i+1][j-3][2] = true;
                                }
                                //Chequear diagonal delante
                                if (!parteDeCadena[i][j][3] && j + 3 < dimension && actual == persona.getGenoma()[i+3].charAt(j+3) && actual == persona.getGenoma()[i+1].charAt(j+1) && actual == persona.getGenoma()[i+2].charAt(j+2))
                                {
                                    acumulador ++;
                                    System.out.println(actual + ";" + i+";"+j );
                                    parteDeCadena[i+1][j+3][3] = true;
                                }
                            }
                        }

                        //Si hay al menos 2 segmentos con 4 elementos iguales consecutivos, devolver isMutante = true
                        if (acumulador >= 2){
                            persona.setMutant(true);
                            return persona;
                        }
                    }
                }
                persona.setMutant(false);
            return persona;

        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
