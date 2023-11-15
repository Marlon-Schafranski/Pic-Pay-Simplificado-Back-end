package com.picpacsimplificado.picpacsimplificado.dtos;

import org.springframework.http.HttpStatus;

public record NotificationDTO(String email, String message) {

    public HttpStatus getStatusCode() {
        return null;
    }

}