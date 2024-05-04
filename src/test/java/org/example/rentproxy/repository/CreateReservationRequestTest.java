package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.Post;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.example.rentproxy.repository.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        assertNotNull(savedReservationRequest.getPost().getId());
        assertEquals(reservationRequest.getUser().getId(), savedReservationRequest.getUser().getId());
        assertEquals(reservationRequest.getPost().getUser().getId(), savedReservationRequest.getPost().getUser().getId() );
        assertEquals(reservationRequest.getConfirmed(), savedReservationRequest.getConfirmed());
        assertEquals(reservationRequest.getArchived(), savedReservationRequest.getArchived());
        assertEquals(reservationRequest.getDate(), savedReservationRequest.getDate());
    }
}
