package com.br.luizalab.comunicacao.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String errorCode;
    private final LocalDateTime timestamp;
    private List<String> errors;

    public ErrorResponse(int status, String errorCode, List<String> errors) {
        this.status = status;
        this.errorCode = errorCode;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
}
