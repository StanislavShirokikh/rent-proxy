package org.example.rentproxy.exception.handler;

import lombok.Data;

@Data
public class ErrorMessageResponse {
    private String errorMessage;
    private String fieldName;
}
