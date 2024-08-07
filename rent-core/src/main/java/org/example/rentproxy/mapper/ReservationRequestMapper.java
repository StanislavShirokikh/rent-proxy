package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.repository.jpa.entities.User;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.repository.jpa.entities.User;
import org.springframework.stereotype.Component;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.ReservationRequest;
import org.example.rentproxy.repository.jpa.entities.User;

@Component
public class ReservationRequestMapper extends Mapper{
    public ReservationRequest convertToReservationRequest(ReservationRequestDto reservationRequestDto) {
        User user = map(reservationRequestDto.getUserDto(), User.class);
        Post post = map(reservationRequestDto.getPostDto(), Post.class);
        ReservationRequest reservationRequest = map(reservationRequestDto, ReservationRequest.class);
        reservationRequest.setUser(user);
        reservationRequest.setPost(post);

        return reservationRequest;
    }
}
