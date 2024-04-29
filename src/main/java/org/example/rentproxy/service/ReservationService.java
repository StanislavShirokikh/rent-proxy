package org.example.rentproxy.service;

import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.exception.ReservationRequestException;

import java.util.List;

public interface ReservationService {
    ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto) throws ReservationRequestException;
    void deleteReservationRequestById(long id);
    void deleteOutdatedReservationRequest();
    ReservationRequestDto getReservationRequestById(long id);
    ReservationRequestDto confirmReservationRequest(long id) throws ReservationRequestException;
    ReservationRequestDto archiveReservationRequest(long id) throws ReservationRequestException;
    List<ReservationRequestDto> getSentReservationsByUsername(String username);
    List<ReservationRequestDto> getReceivedReservationRequestsByUsername(String username);
    List<ReservationRequestDto> getArchivedReservationRequestsByUsername(String username);
}
