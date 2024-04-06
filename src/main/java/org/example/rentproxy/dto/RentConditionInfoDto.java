package org.example.rentproxy.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RentConditionInfoDto {
    private Long id;
    private Double deposit;
    private Integer commissionPercent;
    private Double price;
    private Set<TypeOfPaymentDto> typeOfPaymentDto;
}
