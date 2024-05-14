package org.example.rentproxy.repository;

import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.repository.jpa.entities.User;
import org.example.rentproxy.repository.jpa.ReservationRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfirmationReservationRequestTest extends ReservationRequestRepositoryBaseTest{
    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    @Test
    void confirmReservationRequest() {
        User savedRenter = createUser(
                "Имя",
                "Имя",
                "Имя",
                "renterConfirmReservationRequestLogin",
                "renterCreateConfirmReservationRequestPassword");

        User savedLandlord = createUser(
                "Имя",
                "Имя",
                "Имя",
                "landlordConfirmReservationRequestLogin",
                "landlordConfirmReservationRequestPassword");

        Post savedPost = createPost(savedLandlord);

        ReservationRequest reservationRequest = buildReservationRequest(savedRenter, savedPost, false, false);
        ReservationRequest savedReservationRequest = reservationRequestRepository.save(reservationRequest);

        reservationRequestRepository.confirmReservationRequest(savedReservationRequest.getId());

        ReservationRequest confirmedReservationRequest = reservationRequestRepository.findReservationRequestById(
                savedReservationRequest.getId());

        assertEquals(savedReservationRequest.getId(), confirmedReservationRequest.getId());
        assertEquals(savedReservationRequest.getPost().getId(), confirmedReservationRequest.getPost().getId());
        assertEquals(
                savedReservationRequest.getUser().getId(),
                confirmedReservationRequest.getUser().getId()
        );
        assertEquals(
                savedReservationRequest.getPost().getUser().getId(),
                confirmedReservationRequest.getPost().getUser().getId()
        );
        assertTrue(confirmedReservationRequest.getConfirmed());
        assertFalse(confirmedReservationRequest.getArchived());
        assertEquals(savedReservationRequest.getDate(), confirmedReservationRequest.getDate());
    }
}
