package org.example.rentproxy.exception.handler;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessageResponse {
    private String message;
}
