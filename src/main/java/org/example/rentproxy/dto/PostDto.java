package org.example.rentproxy.dto;

import lombok.Data;

import java.util.Set;
@Data
public class PostDto {
    private String name;
    private String title;
    private Double price;
    private RentConditionInfoDto rentConditionInfoDto;
}
