package org.example.rentproxy.filter;

import lombok.Data;

import java.util.Set;
@Data
public class Filter {
    private String rentType;
    private Double roomsCount;
    private Double minPrice;
    private Double maxPrice;
    private Double minTotalArea;
    private Double maxTotalArea;
    private Integer minFlour;
    private Integer maxFlour;
    private String houseType;
    private String repairType;
    private Set<String> furniture;
    private Set<String> appliance;
    private Integer minHouseFlour;
    private Integer maxHouseFlour;
    private PostOrder postOrder;
    private Integer pageNumber;
    private Integer pageSize;
}
