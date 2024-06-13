package org.example.rentproxy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Data
@Validated
public class ApartmentInfoDto {
    private Long id;
    @Valid
    @NotNull
    private BathroomTypeDto bathroomTypeDto;
    @Valid
    @NotNull
    private RepairTypeDto repairTypeDto;
    @Valid
    @NotNull
    private BalconyTypeDto balconyTypeDto;
    @Valid
    @NotNull
    private RoomsTypeDto roomsTypeDto;
    @NotNull
    private Integer roomsCount;
    @NotNull
    private Double totalArea;
    private Double kitchenArea;
    private Double livingSpace;
    @NotNull
    @Positive
    private Integer flour;
    private String additionally;
    @Valid
    private Set<FurnitureDto> furnitureDto;
    @Valid
    private Set<ApplianceDto> applianceDto;
}
