package com.project.task.model;

public enum Status {
    PENDING("pending"),
    IN_PROGRESS("in_progress"),
    COMPLETED("completed");

    private String status;
    Status(String status)
    {
        this.status = status;
    }

    public String getEnumToString()
    {
        return status;
    }

    public static Status getStringToEnum(String value)
    {
        for(Status s: Status.values())
        {
            if(s.status.equalsIgnoreCase(value))
                return s;
        }
        return Status.PENDING;
    }
}
