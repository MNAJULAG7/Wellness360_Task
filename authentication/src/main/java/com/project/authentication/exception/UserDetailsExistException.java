package com.project.authentication.exception;

public class UserDetailsExistException extends RuntimeException{
    public UserDetailsExistException(String fileldName,String value)
    {
        super(String.format("User with %s : %s already exist",fileldName,value));
    }
}
