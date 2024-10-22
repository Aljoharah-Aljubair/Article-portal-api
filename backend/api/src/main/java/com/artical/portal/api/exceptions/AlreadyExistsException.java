package com.artical.portal.api.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException (String message){
        super(message);
    }
}
