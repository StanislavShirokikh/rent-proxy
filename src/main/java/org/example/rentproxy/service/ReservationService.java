package org.example.rentproxy.service;

import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.exception.ReservationRequestBadRequestException;
import org.example.rentproxy.exception.ReservationRequestNotFoundException;

import java.util.List;

public interface ReservationService {
    ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto) throws ReservationRequestBadRequestException;
    void deleteReservationRequestById(long id);
    void deleteOutdatedReservationRequest();
    ReservationRequestDto getReservationRequestById(long id);
    ReservationRequestDto confirmReservationRequest(long id) throws ReservationRequestNotFoundException;
    ReservationRequestDto archiveReservationRequest(long id) throws ReservationRequestNotFoundException;
    List<ReservationRequestDto> getSentReservationsByUsername(String username);
    List<ReservationRequestDto> getReceivedReservationRequestsByUsername(String username);
    List<ReservationRequestDto> getArchivedReservationRequestsByUsername(String username);
}
