package com.blogProject.payLoad;

import java.util.Date;

public class ErrorDetails {
    private String message;
    private Date date;
    private String description;



    public ErrorDetails(Date date, String message, String description) {
        this.message=message;
        this.date=date;
        this.description=description;
    }

    public String getMessage() {
        return message;
    }


    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }


}
