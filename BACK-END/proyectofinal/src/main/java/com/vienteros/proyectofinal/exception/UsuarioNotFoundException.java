package com.vienteros.proyectofinal.exception;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String mensaje){
        super(mensaje);
    }
}
