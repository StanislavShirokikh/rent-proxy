package org.example.rentproxy.request;

import lombok.Data;

@Data
public class HouseInfoRequest {
    private String houseType;
    private Short floursCount;
}
