package org.example.rentproxy.controller.rest;

import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.ReservationRequestResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/reservation")
public interface ReservationController {
    @PostMapping("/create")
    ReservationRequestResponse createReservationRequest(@RequestBody ReservationRequestDto reservationRequestDto,
                                                        @AuthenticationPrincipal UserDetails userDetails);
    @PostMapping("/get")
    ReservationRequestResponse getReservationRequestById(@RequestBody WithIdRequest withIdRequest);
    @DeleteMapping("delete")
    void deleteReservationRequestById(@RequestBody WithIdRequest withIdRequest);
    @PostMapping("/confirm")
    ReservationRequestResponse confirmReservationRequest(@RequestBody WithIdRequest withIdRequest);
    @PostMapping("/archive")
    ReservationRequestResponse archiveReservationRequest(@RequestBody WithIdRequest withIdRequest );

    @GetMapping("/sent")
    List<ReservationRequestResponse> getSentReservationsByUsername(@AuthenticationPrincipal UserDetails userDetails);
    @GetMapping("/received")
    List<ReservationRequestResponse> getReceivedReservationRequestsByUsername(@AuthenticationPrincipal UserDetails userDetails);
    @GetMapping("/renter/archived")
    List<ReservationRequestResponse> getArchivedReservationRequestsByUsername(@AuthenticationPrincipal UserDetails userDetails);
    @GetMapping("/landlord/archived")
    List<ReservationRequestResponse> getArchivedReservationRequestsByPostUsername(@AuthenticationPrincipal UserDetails userDetails);
}
