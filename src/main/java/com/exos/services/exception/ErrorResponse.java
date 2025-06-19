package com.exos.services.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final int statusCode;
    private final Date timestamp;
    private final String message;
    private final List<String> details;
}
