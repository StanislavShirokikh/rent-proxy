package org.example.rentproxy.dto;

import lombok.Data;

import java.util.Set;
@Data
public class ApartmentInfoDto {
    private String bathroomType;
    private String repairType;
    private String balconyType;
    private String roomsType;
    private Short roomsCount;
    private Double totalArea;
    private Double kitchenArea;
    private Short flour;
    private String additionally;
    private Set<String> furniture;
    private Set<String> appliance;
}
