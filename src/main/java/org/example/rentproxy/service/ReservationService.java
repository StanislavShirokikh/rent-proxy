package org.example.rentproxy.service;

import org.example.rentproxy.dto.ArchiveDto;
import org.example.rentproxy.dto.ReservationRequestDto;

public interface ReservationService {
    ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto);
    void deleteReservationRequestById(Long id);
    void deleteOldReservationRequest();
    ReservationRequestDto getReservationRequestById(Long id);
    ArchiveDto addToArchive(ReservationRequestDto reservationRequestDto);
    void deleteFromArchiveById(Long id);
    ArchiveDto getFromArchiveById(Long id);
}
