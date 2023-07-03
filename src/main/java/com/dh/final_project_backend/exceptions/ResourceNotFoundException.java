package com.dh.final_project_backend.exceptions;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Se produjo la siguiente Excepción "+ this.getClass().getName() +"\n" +
                " Mensaje: " + this.getMessage() + "\n" ;
    }
}
