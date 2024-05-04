package org.example.rentproxy.service;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertNull;

class ReservationServiceImplTest extends ReservationServiceBaseTest{

    @Autowired
    private ReservationService reservationService;

    @Test
    void deleteOutdatedReservations() {
        PostDto savedPost = createOutdatedPost();
        UserDto savedRenter = createUserDto(
                "Имя",
                "Имя",
                "Имя",
                "renterDeleteOutdatedReservationRequestLogin",
                "renterDeleteOutdatedReservationRequestPassword");
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto();
        reservationRequestDto.setPostDto(savedPost);
        reservationRequestDto.setUserDto(savedRenter);
        reservationRequestDto.setConfirmed(false);
        reservationRequestDto.setArchived(false);
        reservationRequestDto.setDate(LocalDate.of(2024, Month.APRIL, 30));
        ReservationRequestDto savedReservationRequest = reservationService.createReservationRequest(reservationRequestDto);

        reservationService.deleteOutdatedReservationRequest();

        ReservationRequestDto actualReservationRequestDto = reservationService.getReservationRequestById(savedReservationRequest.getId());

        assertNull(actualReservationRequestDto);
    }
}