package org.example.rentproxy.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ReservationRequestResponse {
    private Long id;
    private UserResponse renter;
    private PostResponse postResponse;
    private Boolean confirmed;
    private Boolean archived;
    private LocalDate date;
}
