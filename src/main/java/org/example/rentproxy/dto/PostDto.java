package org.example.rentproxy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDto {
    private Long id;
    private UserDto userDto;
    private RentConditionInfoDto rentConditionInfoDto;
    private ApartmentInfoDto apartmentInfoDto;
    private HouseInfoDto houseInfoDto;
    private RentTypeDto rentTypeDto;
    private String name;
    private String title;
    private LocalDate date;
}
