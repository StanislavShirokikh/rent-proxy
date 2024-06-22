package org.example.rentproxy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class HouseInfoDto {
    private Long id;
    @NotNull
    private HouseTypeDto houseTypeDto;
    @NotBlank
    private String address;
    @NotNull
    @Positive
    private Integer floursCount;
}
