package org.example.rentproxy.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfPaymentResponse {
    private Long id;
    private String name;
}
