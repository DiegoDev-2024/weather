package com.pfc2.weather.exception;

public class ApiResourceException extends RuntimeException{
    public ApiResourceException(String message) {
        super(message);
    }
}
