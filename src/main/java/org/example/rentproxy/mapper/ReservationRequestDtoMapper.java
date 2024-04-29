package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationRequestDtoMapper extends Mapper {
    public ReservationRequestDto convertToReservationRequestDto(ReservationRequest reservationRequest) {
        UserDto userDto = map(reservationRequest.getUser(), UserDto.class);
        PostDto postDto = map(reservationRequest.getPost(), PostDto.class);
        ReservationRequestDto reservationRequestDto = map(reservationRequest, ReservationRequestDto.class);
        reservationRequestDto.setUserDto(userDto);
        reservationRequestDto.setPostDto(postDto);

        return reservationRequestDto;
    }

    public List<ReservationRequestDto> convertToListReservationRequestDto(List<ReservationRequest> reservations) {
        return convertToList(reservations, this::convertToReservationRequestDto);
    }

}
