package org.example.rentproxy.converter;

import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.filter.PostOrder;

import java.util.Set;

public class Converter {
    public static Filter convertToFilter(String rentType,
                                         Double roomsCount,
                                         Double minPrice,
                                         Double maxPrice,
                                         Double minTotalArea,
                                         Double maxTotalArea,
                                         Integer minFlour,
                                         Integer maxFlour,
                                         String houseType,
                                         String repairType,
                                         Set<String> furniture,
                                         Set<String> appliance,
                                         Integer minHouseFlour,
                                         Integer maxHouseFlour,
                                         PostOrder postOrder,
                                         Integer pageNumber,
                                         Integer pageSize) {
        Filter filter = new Filter();
        filter.setRentType(rentType);
        filter.setRoomsCount(roomsCount);
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);
        filter.setMinTotalArea(minTotalArea);
        filter.setMaxTotalArea(maxTotalArea);
        filter.setMinFlour(minFlour);
        filter.setMaxFlour(maxFlour);
        filter.setHouseType(houseType);
        filter.setRepairType(repairType);
        filter.setFurniture(furniture);
        filter.setAppliance(appliance);
        filter.setMinHouseFlour(minHouseFlour);
        filter.setMaxHouseFlour(maxHouseFlour);
        filter.setPostOrder(postOrder);
        filter.setPageNumber(pageNumber);
        filter.setPageSize(pageSize);

        return filter;
    }
}
