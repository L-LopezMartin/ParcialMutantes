package com.parcial1.exceptions;

public class MatrizNoCuadrada extends RuntimeException {
    public MatrizNoCuadrada() {
        super("La matriz ingresada no es cuadrada");
    }
}
