package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    Boolean existsByPostId(Long id);
    Boolean existsReservationRequestById(Long id);
    ReservationRequest findReservationRequestById(Long id);
    @Query("SELECT date FROM ReservationRequest WHERE id = :id")
    LocalDate findReservationRequestDateById(@Param("id")Long id);
    List<ReservationRequest> findReservationRequestByDateBefore(LocalDate localDate);
    Long getPostIdById(Long id);
}
