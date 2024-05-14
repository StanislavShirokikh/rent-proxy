package org.example.rentproxy.dto;

import lombok.Data;

@Data
public class HouseInfoDto {
    private Long id;
    private HouseTypeDto houseTypeDto;
    private String address;
    private Integer floursCount;
}
