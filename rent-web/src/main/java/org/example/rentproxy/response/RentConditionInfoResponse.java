package org.example.rentproxy.response;

import lombok.Data;

import java.util.Set;
@Data
public class RentConditionInfoResponse {
    private Long id;
    private Double deposit;
    private Integer commissionPercent;
    private Double price;
    private String currency;
    private Set<TypeOfPaymentResponse> typeOfPaymentResponse;
}
