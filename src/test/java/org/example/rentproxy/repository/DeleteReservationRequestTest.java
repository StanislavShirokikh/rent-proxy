package org.example.rentproxy.repository;

import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.repository.jpa.entities.User;
import org.example.rentproxy.repository.jpa.ReservationRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNull;

class DeleteReservationRequestTest extends ReservationRequestRepositoryBaseTest {
    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    @Test
    void deleteReservationRequest() {
        User savedRenter = createUser(
                "Имя",
                "Имя",
                "Имя",
                "renterDeleteReservationRequestLogin",
                "renterDeleteReservationRequestPassword");

        User savedLandlord = createUser(
                "Имя",
                "Имя",
                "Имя",
                "landlordDeleteReservationRequestLogin",
                "landlordDeleteReservationRequestPassword");

        Post savedPost = createPost(savedLandlord);

        ReservationRequest reservationRequest = buildReservationRequest(savedRenter, savedPost, false, false);
        ReservationRequest savedReservationRequest = reservationRequestRepository.save(reservationRequest);

        reservationRequestRepository.deleteById(savedReservationRequest.getId());

        assertNull(reservationRequestRepository.findReservationRequestById(savedReservationRequest.getId()));
    }
}
