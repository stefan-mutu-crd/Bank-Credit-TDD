package com.cedacri.internship.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("No such record in DB");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
