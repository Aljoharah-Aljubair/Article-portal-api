package com.artical.portal.api.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException (String message){
        super(message);
    }
}
