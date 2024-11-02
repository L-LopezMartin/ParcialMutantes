package com.parcial1.exceptions;

public class MatrizNoCuadradaException extends RuntimeException {
    public MatrizNoCuadradaException() {
        super("La matriz ingresada no es cuadrada");
    }
}
