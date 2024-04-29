package org.example.rentproxy.service;

import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.exception.ReservationRequestException;

import java.util.List;

public interface ReservationService {
    ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto) throws ReservationRequestException;
    void deleteReservationRequestById(Long id);
    void deleteOutdatedReservationRequest();
    ReservationRequestDto getReservationRequestById(Long id);
    ReservationRequestDto confirmReservationRequest(ReservationRequestDto reservationRequestDto) throws ReservationRequestException;
    ReservationRequestDto archiveReservationRequest(ReservationRequestDto reservationRequestDto);
    List<ReservationRequestDto> getSentReservationsByUsername(String username);
    List<ReservationRequestDto> getReceivedReservationRequestsByUsername(String username);
    List<ReservationRequestDto> getArchivedReservationRequestsByUsername(String username);
}
