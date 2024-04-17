package org.example.rentproxy.response;

import lombok.Data;
import org.example.rentproxy.dto.HouseTypeDto;
@Data
public class HouseInfoResponse {
    private Long id;
    private HouseTypeResponse houseTypeResponse;
    private String address;
    private Integer floursCount;
}
