package org.example.rentproxy.repository;

import org.example.rentproxy.repository.jpa.ReservationRequestRepository;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.repository.jpa.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateReservationRequestTest extends ReservationRequestRepositoryBaseTest{
    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    @Test
    void createReservationRequest() {
        User savedRenter = createUser(
                "Имя",
                "Имя",
                "Имя",
                "renterCreateReservationRequestLogin",
                "renterCreateReservationRequestPassword");

        User savedLandlord = createUser(
                "Имя",
                "Имя",
                "Имя",
                "landlordCreateReservationRequestLogin",
                "landlordCreateReservationRequestPassword");

        Post savedPost = createPost(savedLandlord);

        ReservationRequest reservationRequest = buildReservationRequest(savedRenter, savedPost, false, false);
        ReservationRequest savedReservationRequest = reservationRequestRepository.save(reservationRequest);

        Assertions.assertNotNull(savedReservationRequest.getPost().getId());
        assertEquals(reservationRequest.getUser().getId(), savedReservationRequest.getUser().getId());
        assertEquals(reservationRequest.getPost().getUser().getId(), savedReservationRequest.getPost().getUser().getId() );
        assertEquals(reservationRequest.getConfirmed(), savedReservationRequest.getConfirmed());
        assertEquals(reservationRequest.getArchived(), savedReservationRequest.getArchived());
        assertEquals(reservationRequest.getDate(), savedReservationRequest.getDate());
    }
}
