package org.example.rentproxy.mapper;

import org.example.rentproxy.response.PostResponse;
import org.example.rentproxy.response.ReservationRequestResponse;
import org.example.rentproxy.response.UserResponse;
import org.springframework.stereotype.Component;
import org.example.rentproxy.dto.ReservationRequestDto;

import java.util.List;

@Component
public class ReservationRequestResponseMapper extends PostResponseMapper {
    public ReservationRequestResponse convertToReservationRequestResponse(ReservationRequestDto reservationRequestDto) {
        UserResponse userResponse = null;
        if (reservationRequestDto.getUserDto() != null) {
            userResponse = map(reservationRequestDto.getUserDto(), UserResponse.class);
        }
        PostResponse postResponse = null;
        if (reservationRequestDto.getPostDto() != null) {
            postResponse = convertToPostResponse(reservationRequestDto.getPostDto());
        }
        ReservationRequestResponse reservationRequestResponse = map(reservationRequestDto,
                ReservationRequestResponse.class);
        reservationRequestResponse.setRenter(userResponse);
        reservationRequestResponse.setPostResponse(postResponse);
        return reservationRequestResponse;
    }

    public List<ReservationRequestResponse> convertToListReservationRequestResponse(List<ReservationRequestDto> reservations) {
        return convertToList(reservations, this::convertToReservationRequestResponse);
    }
}
