package com.project.authentication.exception;

public class UserNotFoundException extends RuntimeException{
   public UserNotFoundException(String username)
    {
        super("User can not be identified with the name of "+username);
    }

    public UserNotFoundException(Long id)
    {
        super("User can not be identified with the Id of  "+id);
    }
}
