package org.example.rentproxy.repository;

import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.repository.jpa.entities.User;
import org.example.rentproxy.repository.jpa.ReservationRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GetArchivedReservationRequestsTest extends ReservationRequestRepositoryBaseTest {
    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    @Test
    void getThreeArchivedLandlordsReservationRequest() {
        User savedRenter = createUser(
                "ArchivedИмя",
                "ArchivedИмя",
                "ArchivedИмя",
                "renterGetArchivedReservationRequestLogin",
                "renterGetArchivedReservationRequestPassword");

        User savedLandlord = createUser(
                "AuthorPostИмя",
                "AuthorPostИмя",
                "AuthorPostИмя",
                "landlordGetArchivedReservationRequestLogin1",
                "landlordArchivedSentReservationRequestPassword1");


        Post savedPost1 = createPost(savedLandlord);
        Post savedPost2 = createPost(savedLandlord);
        Post savedPost3 = createPost(savedLandlord);

        ReservationRequest savedReservationRequest1 = reservationRequestRepository.save(
                buildReservationRequest(savedRenter, savedPost1, true, true));
        ReservationRequest savedReservationRequest2 = reservationRequestRepository.save(
                buildReservationRequest(savedRenter, savedPost2, true, true));
        ReservationRequest savedReservationRequest3 = reservationRequestRepository.save(
                buildReservationRequest(savedRenter, savedPost3, true, true));

        List<ReservationRequest> actualSentReservations = reservationRequestRepository.getArchivedReservationRequestsByPostUsername(
                savedLandlord.getLogin());

        assertEquals(3, actualSentReservations.size());
        assertTrue(actualSentReservations.stream()
                .map(ReservationRequest::getId)
                .allMatch(
                        element ->
                                Arrays.asList(savedReservationRequest1.getId(),
                                        savedReservationRequest2.getId(),
                                        savedReservationRequest3.getId()).contains(element)
                )
        );
        assertTrue(actualSentReservations.stream()
                .map(ReservationRequest::getUser)
                .map(User::getLogin)
                .allMatch(
                        element ->
                                Arrays.asList(savedReservationRequest1.getUser().getLogin(),
                                        savedReservationRequest2.getUser().getLogin(),
                                        savedReservationRequest3.getUser().getLogin()).contains(element)
                )
        );
        assertTrue(actualSentReservations.stream()
                .map(ReservationRequest::getPost)
                .map(Post::getId)
                .allMatch(
                        element ->
                                Arrays.asList(savedPost1.getId(),
                                        savedPost2.getId(),
                                        savedPost3.getId()).contains(element)
                )
        );
        assertTrue(actualSentReservations.stream()
                .map(ReservationRequest::getPost)
                .map(Post::getUser)
                .map(User::getLogin)
                .allMatch(
                        element ->
                                Arrays.asList(savedPost1.getUser().getLogin(),
                                        savedPost2.getUser().getLogin(),
                                        savedPost3.getUser().getLogin()).contains(element)
                )
        );
    }
}
