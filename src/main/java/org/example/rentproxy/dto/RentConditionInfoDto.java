package org.example.rentproxy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Set;

@Data
public class RentConditionInfoDto {
    private Long id;
    @NotNull
    @Positive
    private Double deposit;
    @NotNull
    @Positive
    private Integer commissionPercent;
    @NotNull
    @Positive
    private Double price;
    @NotBlank
    private String currency;
    @NotNull
    @Valid
    private Set<TypeOfPaymentDto> typeOfPaymentDto;
}
