package com.parcial1.exceptions;

public class MalGenomaException extends RuntimeException {
    public MalGenomaException() {
        super("El genoma ingresado tiene un caracter que no es válido");
    }
}
