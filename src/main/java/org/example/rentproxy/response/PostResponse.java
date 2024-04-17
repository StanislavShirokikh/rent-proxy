package org.example.rentproxy.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class PostResponse {
    private Long id;
    private UserResponse userResponse;
    private RentConditionInfoResponse rentConditionInfoResponse;
    private ApartmentInfoResponse apartmentInfoResponse;
    private HouseInfoResponse houseInfoResponse;
    private RentTypeResponse rentTypeResponse;
    private String name;
    private String title;
    private LocalDate date;
}
