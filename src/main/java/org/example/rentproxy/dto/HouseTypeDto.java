package org.example.rentproxy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HouseTypeDto {
    private Long id;
    @NotBlank
    private  String name;
}
