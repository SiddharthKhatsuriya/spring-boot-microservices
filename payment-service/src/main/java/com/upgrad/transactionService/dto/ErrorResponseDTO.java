package com.upgrad.transactionService.dto;

public class ErrorResponseDTO {

    private int statusCode;
    private String message;

    public ErrorResponseDTO(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PaymentResponseDTO{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
