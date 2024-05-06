package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.mapper.ReservationRequestResponseMapper;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.ReservationRequestResponse;
import org.example.rentproxy.service.ReservationService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
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

    @PostAuthorize("returnObject.renter.login == authentication.name || " +
            "returnObject.postResponse.userResponse.login == authentication.name || hasRole('ADMIN')")
    @Override
    public ReservationRequestResponse getReservationRequestById(WithIdRequest withIdRequest) {
        return reservationRequestResponseMapper.convertToReservationRequestResponse(
                reservationService.getReservationRequestById(withIdRequest.getId()));
    }

    @PreAuthorize("hasRole('ADMIN') or authentication.name == @reservationRequestRepository.findUserLoginById(#withIdRequest.id)")
    @Override
    public void deleteReservationRequestById(@P("withIdRequest") WithIdRequest withIdRequest) {
        reservationService.deleteReservationRequestById(withIdRequest.getId());
    }

    @PostAuthorize("returnObject.postResponse.userResponse.login == authentication.name || hasRole('ADMIN')")
    @Override
    public ReservationRequestResponse confirmReservationRequest(WithIdRequest withIdRequest) {
        return reservationRequestResponseMapper.convertToReservationRequestResponse(
                reservationService.confirmReservationRequest(withIdRequest.getId())
        );
    }

    @PostAuthorize("returnObject.postResponse.userResponse.login == authentication.name || hasRole('ADMIN')")
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

    @Override
    public List<ReservationRequestResponse> getArchivedReservationRequestsByPostUsername(UserDetails userDetails) {
        return reservationRequestResponseMapper.convertToListReservationRequestResponse(
                reservationService.getArchivedReservationRequestsByPostUsername(userDetails.getUsername()));
    }
}
