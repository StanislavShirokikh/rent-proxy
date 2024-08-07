package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.springframework.stereotype.Component;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;

import java.util.List;

@Component
public class ReservationRequestDtoMapper extends PostDtoMapper {
    public ReservationRequestDto convertToReservationRequestDto(ReservationRequest reservationRequest) {
        if (reservationRequest == null) {
            return null;
        }

        UserDto userDto = map(reservationRequest.getUser(), UserDto.class);

        PostDto postDto = convertToPostDto(reservationRequest.getPost());
        ReservationRequestDto reservationRequestDto = map(reservationRequest, ReservationRequestDto.class);
        reservationRequestDto.setUserDto(userDto);
        reservationRequestDto.setPostDto(postDto);

        return reservationRequestDto;
    }

    public List<ReservationRequestDto> convertToListReservationRequestDto(List<ReservationRequest> reservations) {
        return convertToList(reservations, this::convertToReservationRequestDto);
    }
}
