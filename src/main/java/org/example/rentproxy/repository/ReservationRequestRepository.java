package org.example.rentproxy.repository;

import org.example.rentproxy.dto.ReservationRequestDto;
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
    Boolean existsByPostId(Long id);
    Boolean existsReservationRequestById(Long id);
    ReservationRequest findReservationRequestById(Long id);
    @Query("SELECT date FROM ReservationRequest WHERE id = :id")
    LocalDate findReservationRequestDateById(@Param("id")Long id);
    Long getPostIdById(Long id);
    @Modifying
    @Query("DELETE from ReservationRequest WHERE date = :date")
    void deleteOutdatedReservationRequest(@Param("date") LocalDate date);
    @Modifying
    @Query("UPDATE ReservationRequest r SET r.confirmed = :confirmed WHERE r.id = :id")
    ReservationRequest confirmReservationRequest(@Param("id")Long id, @Param("confirmed")Boolean confirmed);
    @Modifying
    @Query("UPDATE ReservationRequest r SET r.archived = :archived WHERE r.id = :id")
    ReservationRequest archiveReservationRequest(@Param("id")Long id, @Param("archived")Boolean archived);
    @Query("SELECT r.id, r.post.id, r.user.id, r.date FROM ReservationRequest r WHERE r.user.login = :username")
    List<ReservationRequest> getSentReservationsByUsername(@Param("username")String username);
    @Query("SELECT r.id, r.post.id, r.user.id FROM ReservationRequest r " +
                    "WHERE r.post.id = " + "(SELECT p.id FROM Post p WHERE p.user.login = :username)")
    List<ReservationRequest> getReceivedReservationRequestsByUsername(@Param("username") String username);
    @Query("SELECT r.id, r.post.id, r.user.id FROM ReservationRequest r " +
            "WHERE r.archived = true AND r.post.id = (SELECT p.id FROM Post p WHERE p.user.login = :username)")
    List<ReservationRequest> getArchivedReservationRequestsByUsername(@Param("username") String username);
}
