package org.example.customercare360.dto;

public class ApiResponseDTO {

    private int status;
    private String message;

    public ApiResponseDTO() {
    }

    public ApiResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
