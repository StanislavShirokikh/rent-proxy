package org.example.rentproxy.response;

import lombok.Data;

import java.util.Set;
@Data
public class ApartmentInfoResponse {
    private Long id;
    private BathroomTypeResponse bathroomTypeResponse;
    private RepairTypeResponse repairTypeResponse;
    private BalconyTypeResponse balconyTypeResponse;
    private RoomsTypeResponse roomsTypeResponse;
    private Integer roomsCount;
    private Double totalArea;
    private Double kitchenArea;
    private Double livingSpace;
    private Integer flour;
    private String additionally;
    private Set<FurnitureResponse> furnitureResponse;
    private Set<ApplianceResponse> applianceResponse;
}
