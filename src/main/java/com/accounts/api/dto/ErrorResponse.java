package com.accounts.api.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {


    private String apiPath;


    private HttpStatus errorCode;


    private String errorMessage;

    private LocalDateTime errorTime;
}
