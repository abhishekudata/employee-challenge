package com.example.rqchallenge.exception;

@SuppressWarnings("serial")
public class OperationFailedException extends Exception {

    private String errorMessage;

    public OperationFailedException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
