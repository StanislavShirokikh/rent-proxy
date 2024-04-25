package org.example.rentproxy.service;

import org.example.rentproxy.dto.ArchiveDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.exception.ReservationRequestException;

public interface ReservationService {
    ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto) throws ReservationRequestException;
    void deleteReservationRequestById(Long id);
    void deleteOutdatedReservationRequest();
    ReservationRequestDto getReservationRequestById(Long id);
    ArchiveDto addToArchive(ArchiveDto archiveDto);
    void deleteFromArchiveById(Long id);
    ArchiveDto getFromArchiveById(Long id);
}
