package com.parcial1.services;


import com.parcial1.dtos.DTOPersonaInput;
import com.parcial1.dtos.DTOPersonaOutput;
import com.parcial1.entities.Persona;
import com.parcial1.exceptions.MalGenoma;
import com.parcial1.exceptions.MatrizNoCuadrada;
import com.parcial1.exceptions.NoMutante;
import com.parcial1.repositories.PersonaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    private PersonaRepository personaRepository;

    @Transactional
    public DTOPersonaOutput isMutant(DTOPersonaInput per) throws Exception {
        
        try{
            //Verificar que el genoma ingresado sea una matriz cuadrada
            isGenomeSquare(per.getGenoma());

            //Crear la persona
            Persona persona = new Persona();
            persona.setGenoma(per.getGenoma());

            //Verificar que la matriz no tenga caracteres distintos a los permitidos
            checkChars(persona);

            //Variables auxiliares
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
                                System.out.println("Cadena en " +actual + ";" + i+";"+j );
                                parteDeCadena[i][j+3][1] = true;
                            }
                            //Chequear horizontal detrás
                            else if (j-1 >= 0 && !parteDeCadena[i][j-1][1] && actual == persona.getGenoma()[i].charAt(j-1) && !parteDeCadena[i][j+1][1] && actual == persona.getGenoma()[i].charAt(j+1))
                            {
                                acumulador ++;
                                System.out.println("Cadena en " +actual + ";" + i+";"+j );
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
                                System.out.println("Cadena en " +actual + ";" + i+";"+j );
                                parteDeCadena[i+1][j][0] = true;
                                acumulador ++;
                            }
                            //Chequear vertical ambos
                            else if (i-1 >= 0 && !parteDeCadena[i-1][j][0] && i+2 < dimension && actual == persona.getGenoma()[i-1].charAt(j) && actual == persona.getGenoma()[i+2].charAt(j))
                            {
                                System.out.println("Cadena en " +actual + ";" + i+";"+j );
                                parteDeCadena[i+2][j][0] = true;
                                acumulador ++;
                            }
                            //Chequear vertical abajo mucho
                            else if (i+3 < dimension && actual == persona.getGenoma()[i+2].charAt(j) && actual == persona.getGenoma()[i+3].charAt(j))
                            {
                                System.out.println("Cadena en " +actual + ";" + i+";"+j );
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
                                System.out.println("Cadena en " +actual + ";" + i+";"+j );
                            }
                            //Chequear diagonal delante
                            if (!parteDeCadena[i][j][3] && j + 3 < dimension && actual == persona.getGenoma()[i+3].charAt(j+3) && actual == persona.getGenoma()[i+1].charAt(j+1) && actual == persona.getGenoma()[i+2].charAt(j+2))
                            {
                                acumulador ++;
                                parteDeCadena[i+3][j+3][3] = true;
                                System.out.println("Cadena en " +actual + ";" + i+";"+j );
                            }
                        }
                    }
                    else                        //Diagonales cortas para i impar y j 2 en 2 desplazado por 2
                    {
                        // ---- Chequear abajo
                        if (!parteDeCadena[i][j][0] && i + 3 < dimension && actual == persona.getGenoma()[i+1].charAt(j) && actual == persona.getGenoma()[i+2].charAt(j) && actual == persona.getGenoma()[i+3].charAt(j))
                        {
                            acumulador ++;
                            System.out.println("Cadena en " +actual + ";" + i+";"+j );
                            parteDeCadena[i+3][j][0] = true;
                        }

                        // ---- Chequear diagonales
                        if (i + 3 < dimension) {                       //Está dentro del límite hacia abajo?
                            //Chequear diagonal atrás
                            if (!parteDeCadena[i][j][2] && j - 3 >= 0 && actual == persona.getGenoma()[i+3].charAt(j-3) && actual == persona.getGenoma()[i+1].charAt(j-1) && actual == persona.getGenoma()[i+2].charAt(j-2))
                            {
                                acumulador ++;
                                System.out.println("Cadena en " +actual + ";" + i+";"+j );
                                parteDeCadena[i+1][j-3][2] = true;
                            }
                            //Chequear diagonal delante
                            if (!parteDeCadena[i][j][3] && j + 3 < dimension && actual == persona.getGenoma()[i+3].charAt(j+3) && actual == persona.getGenoma()[i+1].charAt(j+1) && actual == persona.getGenoma()[i+2].charAt(j+2))
                            {
                                acumulador ++;
                                System.out.println("Cadena en " +actual + ";" + i+";"+j );
                                parteDeCadena[i+1][j+3][3] = true;
                            }
                        }
                    }

                    //Si hay al menos 2 segmentos con 4 elementos iguales consecutivos, devolver isMutante = true
                    if (acumulador >= 2){
                        persona.setMutant(true);
                        personaRepository.save(persona);
                        return new DTOPersonaOutput(persona.getId(), persona.getGenoma(), persona.isMutant());
                    }
                }
            }

            persona.setMutant(false);
            personaRepository.save(persona);
            throw new NoMutante();
        } catch (MalGenoma e) {
            throw new MalGenoma();
        }
        catch (NoMutante e){
            throw new NoMutante();
        }
        catch (MatrizNoCuadrada e){
            throw new MatrizNoCuadrada();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List findAll() throws Exception {
        try{
            List<Persona> entities = personaRepository.findAll();
            List<DTOPersonaOutput> dtos = new ArrayList<>();
            for(Persona persona : entities){
                DTOPersonaOutput dto = new DTOPersonaOutput(persona.getId(), persona.getGenoma(), persona.isMutant());
                dtos.add(dto);
            }
            return dtos;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public DTOPersonaOutput findById(Long id) throws Exception {
        try{
            Optional<Persona> entityOptional = personaRepository.findById(id);
            Persona persona = entityOptional.get();
            DTOPersonaOutput dto = new DTOPersonaOutput(persona.getId(), persona.getGenoma(), persona.isMutant());
            return dto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private void isGenomeSquare(String[] genoma) throws Exception{
        int dimension = genoma.length;
        for (String genLine : genoma){
            if (genLine.length() != dimension){
                throw new MatrizNoCuadrada();
            }
        }
    }

    private void checkChars(Persona persona) throws MalGenoma{
        String[] genoma = persona.getGenoma();
        int dimension = persona.getGenoma().length;
        for (String linea: genoma){
            for (int i=0;i<dimension;i++){
                char actual = linea.charAt(i);
                if(
                        actual != persona.getPosiblesValores()[0] &&
                                actual != persona.getPosiblesValores()[1] &&
                                actual != persona.getPosiblesValores()[2] &&
                                actual != persona.getPosiblesValores()[3]
                ) throw new MalGenoma();
            }
        }
    }
}
