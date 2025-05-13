package com.accounts.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response {

    private String statusCode;

    private String statusMsg;
}
