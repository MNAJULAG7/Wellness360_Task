package com.project.task.exception;


public class TaskNotExistException extends RuntimeException {
    public TaskNotExistException(String msg)
    {
        super(msg);
    }
}
