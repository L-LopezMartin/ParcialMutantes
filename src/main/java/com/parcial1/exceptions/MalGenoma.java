package com.parcial1.exceptions;

public class MalGenoma extends RuntimeException {
    public MalGenoma() {
        super("El genoma ingresado tiene un caracter que no es válido");
    }
}
