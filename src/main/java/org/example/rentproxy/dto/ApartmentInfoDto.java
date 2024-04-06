package org.example.rentproxy.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ApartmentInfoDto {
    private Long id;
    private BathroomTypeDto bathroomTypeDto;
    private RepairTypeDto repairTypeDto;
    private BalconyTypeDto balconyTypeDto;
    private RoomsTypeDto roomsTypeDto;
    private Integer roomsCount;
    private Double totalArea;
    private Double kitchenArea;
    private Double livingSpace;
    private Integer flour;
    private String additionally;
    private Set<FurnitureDto> furnitureDto;
    private Set<ApplianceDto> applianceDto;
}
