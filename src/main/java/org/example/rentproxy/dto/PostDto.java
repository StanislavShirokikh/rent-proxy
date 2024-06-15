package org.example.rentproxy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.rentproxy.exception.handler.Marker;

import java.time.LocalDate;
@Data
public class PostDto {
    @NotNull(groups = Marker.Reservation.class)
    private Long id;
    private UserDto userDto;
    @NotNull(groups = Marker.Post.class)
    @Valid
    private RentConditionInfoDto rentConditionInfoDto;
    @NotNull(groups = Marker.Post.class)
    @Valid
    private ApartmentInfoDto apartmentInfoDto;
    @NotNull(groups = Marker.Post.class)
    @Valid
    private HouseInfoDto houseInfoDto;
    @NotNull(groups = Marker.Post.class)
    @Valid
    private RentTypeDto rentTypeDto;
    @NotBlank(groups = Marker.Post.class)
    private String name;
    @NotBlank(groups = Marker.Post.class)
    private String title;
    @NotNull(groups = Marker.Post.class)
    private LocalDate date;
}
