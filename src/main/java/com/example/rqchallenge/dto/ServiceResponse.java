package com.example.rqchallenge.dto;

public class ServiceResponse<T> {
    private String status;
    private T data;
    private String message;

    public ServiceResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message= message;
    }
}
