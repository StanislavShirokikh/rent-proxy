package org.example.rentproxy.request;

import lombok.Data;

import java.util.Set;
@Data
public class ApartmentInfoRequest {
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
