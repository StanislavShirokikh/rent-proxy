package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.Post;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.example.rentproxy.repository.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SaveReservationRequestTest {
    @Autowired
    private ReservationRequestRepository reservationRequestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void saveReservationRequestWithNotExistsPostId() {
        ReservationRequest reservationRequest = new ReservationRequest();

        User user = new User();
        user.setFirstName("Игорь");
        user.setLogin("unique");
        user.setPassword("1234");
        User savedUser = userRepository.save(user);

        Post post = new Post();
        post.setUser(savedUser);
        post.setName("Аренда");
        Post savedPost = postRepository.save(post);

        reservationRequest.setPost(savedPost);
        reservationRequest.setUser(user);
        reservationRequest.setDate(LocalDate.now());

        ReservationRequest savedReservationRequest = reservationRequestRepository.save(reservationRequest);

        assertEquals(reservationRequest.getPost().getId(), savedReservationRequest.getPost().getId());
        assertEquals(reservationRequest.getUser().getId(), savedReservationRequest.getUser().getId());
        assertEquals(reservationRequest.getUser().getFirstName(), savedReservationRequest.getUser().getFirstName());
        assertEquals(reservationRequest.getUser().getLogin(), savedReservationRequest.getUser().getLogin());
        assertEquals(reservationRequest.getDate(), savedReservationRequest.getDate());
    }
}
