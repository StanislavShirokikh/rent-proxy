package org.example.rentproxy.repository;

import jakarta.transaction.Transactional;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    boolean existsByPostId(Long id);

    boolean existsReservationRequestById(Long id);

    ReservationRequest findReservationRequestById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE from ReservationRequest WHERE date = :date")
    void deleteOutdatedReservationRequest(@Param("date") LocalDate date);

    @Modifying
    @Transactional
    @Query("UPDATE ReservationRequest r SET r.confirmed = true WHERE r.id = :id")
    ReservationRequest confirmReservationRequest(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE ReservationRequest r SET r.archived = true WHERE r.id = :id")
    ReservationRequest archiveReservationRequest(@Param("id") Long id);

    @Query("SELECT r.id, r.post.id, r.user.id, r.date FROM ReservationRequest r WHERE r.confirmed = false AND r.user.login = :username")
    List<ReservationRequest> getSentReservationsByUsername(@Param("username") String username);

    @Query("SELECT r.id, r.post.id, r.user.id FROM ReservationRequest r JOIN Post p WHERE r.confirmed = false AND p.user.login = :username")
    List<ReservationRequest> getReceivedReservationRequestsByUsername(@Param("username") String username);

    @Query("SELECT r.id, r.post.id, r.user.id FROM ReservationRequest r JOIN Post p WHERE r.archived = true AND p.user.login = :username")
    List<ReservationRequest> getArchivedReservationRequestsByUsername(@Param("username") String username);
}
