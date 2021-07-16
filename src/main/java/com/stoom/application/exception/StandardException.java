package com.stoom.application.exception;

/**
 * Class that represents a standard for exceptions in API.
 */
public class StandardException {

    private Integer statusCode;
    private String date;
    private String message;

    public StandardException() {
        super();
    }

    public StandardException(Integer statusCode, String date, String message) {
        super();
        this.statusCode = statusCode;
        this.date = date;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}