package com.example.dogs;

public class DogsImage {

    private String message;
    private String status;

    public DogsImage(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "DogsImage{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
