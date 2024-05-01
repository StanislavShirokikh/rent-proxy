package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.mapper.ReservationRequestResponseMapper;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.ReservationRequestResponse;
import org.example.rentproxy.service.ReservationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationControllerImpl implements ReservationController {
    private final ReservationService  reservationService;
    private final ReservationRequestResponseMapper reservationRequestResponseMapper;

    @Override
    public ReservationRequestResponse createReservationRequest(ReservationRequestDto reservationRequestDto,
                                                               UserDetails userDetails) {
        UserDto userDto = new UserDto();
        userDto.setLogin(userDetails.getUsername());
        reservationRequestDto.setUserDto(userDto);

        return reservationRequestResponseMapper.convertToReservationRequestResponse(
                reservationService.createReservationRequest(reservationRequestDto));
    }

    @Override
    public ReservationRequestResponse getReservationRequestById(WithIdRequest withIdRequest) {
        return reservationRequestResponseMapper.convertToReservationRequestResponse(
                reservationService.getReservationRequestById(withIdRequest.getId()));
    }

    @Override
    public ReservationRequestResponse confirmReservationRequest(WithIdRequest withIdRequest) {
        return reservationRequestResponseMapper.convertToReservationRequestResponse(
                reservationService.confirmReservationRequest(withIdRequest.getId())
        );
    }

    @Override
    public ReservationRequestResponse archiveReservationRequest(WithIdRequest withIdRequest) {
        return reservationRequestResponseMapper.convertToReservationRequestResponse(
                reservationService.archiveReservationRequest(withIdRequest.getId())
        );
    }

    @Override
    public List<ReservationRequestResponse> getSentReservationsByUsername(UserDetails userDetails) {
        return reservationRequestResponseMapper.convertToListReservationRequestResponse(
                reservationService.getSentReservationsByUsername(userDetails.getUsername()));
    }

    @Override
    public List<ReservationRequestResponse> getReceivedReservationRequestsByUsername(UserDetails userDetails) {
        return  reservationRequestResponseMapper.convertToListReservationRequestResponse(
                reservationService.getReceivedReservationRequestsByUsername(userDetails.getUsername()));
    }

    @Override
    public List<ReservationRequestResponse> getArchivedReservationRequestsByUsername(UserDetails userDetails) {
        return  reservationRequestResponseMapper.convertToListReservationRequestResponse(
                reservationService.getArchivedReservationRequestsByUsername(userDetails.getUsername()));
    }
}
