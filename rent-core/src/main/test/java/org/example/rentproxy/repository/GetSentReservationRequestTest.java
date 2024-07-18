package org.example.rentproxy.repository;

import org.example.rentproxy.repository.jpa.ReservationRequestRepository;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.repository.jpa.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

class GetSentReservationRequestTest extends ReservationRequestRepositoryBaseTest {
    @Autowired
    private ReservationRequestRepository reservationRequestRepository;
    @Test
    void getThreeSentReservationRequestWithDifferentLandlords() {
        User savedRenter = createUser(
                "SentИмя",
                "SentИмя",
                "SentИмя",
                "renterGetSentReservationRequestLogin",
                "renterGetSentReservationRequestPassword");

        User savedLandlord1 = createUser(
                "AuthorИмя",
                "AuthorИмя",
                "AuthorИмя",
                "landlordGetSentReservationRequestLogin1",
                "landlordGetSentReservationRequestPassword1");

        User savedLandlord2 = createUser(
                "Author1Имя",
                "Author1Имя",
                "Author1Имя",
                "landlordGetSentReservationRequestLogin2",
                "landlordGetSentReservationRequestPassword2");

        User savedLandlord3 = createUser(
                "Author2Имя",
                "Author2Имя",
                "AuthorИмя",
                "landlordGetSentReservationRequestLogin3",
                "landlordGetSentReservationRequestPassword3");

        Post savedPost1 = createPost(savedLandlord1);
        Post savedPost2 = createPost(savedLandlord2);
        Post savedPost3 = createPost(savedLandlord3);

        ReservationRequest savedReservationRequest1 = reservationRequestRepository.save(
                buildReservationRequest(savedRenter, savedPost1, false, false));
        ReservationRequest savedReservationRequest2 = reservationRequestRepository.save(
                buildReservationRequest(savedRenter, savedPost2, false, false));
        ReservationRequest savedReservationRequest3 = reservationRequestRepository.save(
                buildReservationRequest(savedRenter, savedPost3, false, false));

        List<ReservationRequest> actualSentReservations = reservationRequestRepository.getSentReservationsByUsername(
                savedRenter.getLogin());

        Assertions.assertEquals(3, actualSentReservations.size());
        Assertions.assertTrue(actualSentReservations.stream()
                .map(ReservationRequest::getId)
                .allMatch(
                        element ->
                        Arrays.asList(savedReservationRequest1.getId(),
                                savedReservationRequest2.getId(),
                                savedReservationRequest3.getId()).contains(element)
                )
        );
        Assertions.assertTrue(actualSentReservations.stream()
                .map(ReservationRequest::getUser)
                .map(User::getLogin)
                .allMatch(
                        element ->
                                Arrays.asList(savedReservationRequest1.getUser().getLogin(),
                                        savedReservationRequest2.getUser().getLogin(),
                                        savedReservationRequest3.getUser().getLogin()).contains(element)
                )
        );
    }
}
