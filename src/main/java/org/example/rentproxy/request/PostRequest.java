package org.example.rentproxy.request;

import lombok.Data;

import java.util.Set;

@Data
public class PostRequest {
    private String name;
    private String title;
    private Double price;
    private RentConditionInfoRequest rentConditionInfoRequest;
    private ApartmentInfoRequest apartmentInfoRequest;
    private HouseInfoRequest houseInfoRequest;
    private  String rentType;
}
