package org.example.rentproxy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArchiveDto {
    private ReservationRequestDto reservationRequestDto;
    private LocalDate date;
}
