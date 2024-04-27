package org.example.rentproxy.response;

import lombok.Data;
@Data
public class HouseInfoResponse {
    private Long id;
    private HouseTypeResponse houseTypeResponse;
    private String address;
    private Integer floursCount;
}
