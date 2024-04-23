package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
}
