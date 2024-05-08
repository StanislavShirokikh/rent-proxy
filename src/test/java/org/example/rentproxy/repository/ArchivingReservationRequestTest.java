package org.example.rentproxy.repository;

import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.repository.jpa.entities.User;
import org.example.rentproxy.repository.jpa.ReservationRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArchivingReservationRequestTest  extends ReservationRequestRepositoryBaseTest{
    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    @Test
    void archiveReservationRequest() {
        User savedRenter = createUser(
                "Имя",
                "Имя",
                "Имя",
                "renterArchiveReservationRequestLogin",
                "renterArchiveConfirmReservationRequestPassword");

        User savedLandlord = createUser(
                "Имя",
                "Имя",
                "Имя",
                "landlordArchiveReservationRequestLogin",
                "landlordArchiveReservationRequestPassword");

        Post savedPost = createPost(savedLandlord);

        ReservationRequest reservationRequest = buildReservationRequest(savedRenter, savedPost, true, true);

        ReservationRequest savedReservationRequest = reservationRequestRepository.save(reservationRequest);
        reservationRequestRepository.archiveReservationRequest(savedReservationRequest.getId());

        ReservationRequest archivedReservationRequest = reservationRequestRepository.findReservationRequestById(
                savedReservationRequest.getId());

        assertEquals(savedReservationRequest.getId(), archivedReservationRequest.getId());
        assertEquals(savedReservationRequest.getPost().getId(), archivedReservationRequest.getPost().getId());
        assertEquals(
                savedReservationRequest.getUser().getId(),
                archivedReservationRequest.getUser().getId()
        );
        assertEquals(
                savedReservationRequest.getPost().getUser().getId(),
                archivedReservationRequest.getPost().getUser().getId()
        );
        assertTrue(archivedReservationRequest.getConfirmed());
        assertTrue(archivedReservationRequest.getArchived());
        assertEquals(savedReservationRequest.getDate(), archivedReservationRequest.getDate());
    }
}
