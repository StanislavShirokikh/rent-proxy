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

    boolean existsByUserLoginAndPostId(String login, Long id);

    boolean existsReservationRequestById(Long id);

    ReservationRequest findReservationRequestById(Long id);

    @Query("SELECT r.user.login from ReservationRequest r WHERE r.id = :id")
    String findUserLoginById(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("DELETE from ReservationRequest WHERE date <= :date")
    void deleteOutdatedReservationRequest(@Param("date") LocalDate date);

    @Modifying
    @Transactional
    @Query("UPDATE ReservationRequest r SET r.confirmed = true WHERE r.id = :id")
    void confirmReservationRequest(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE ReservationRequest r SET r.archived = true WHERE r.id = :id")
    void archiveReservationRequest(@Param("id") Long id);

    @Query("SELECT r, p FROM ReservationRequest r JOIN Post p ON r.post.id = p.id WHERE r.archived != true AND r.user.login = :username")
    List<ReservationRequest> getSentReservationsByUsername(@Param("username") String username);

    @Query("SELECT r, p FROM ReservationRequest r JOIN Post p ON r.post.id = p.id WHERE r.archived != true AND p.user.login = :username")
    List<ReservationRequest> getReceivedReservationRequestsByUsername(@Param("username") String username);

    @Query("SELECT r, p FROM ReservationRequest r JOIN Post p ON r.post.id = p.id WHERE r.confirmed = true AND r.archived = true AND r.user.login = :username")
    List<ReservationRequest> getArchivedReservationRequestsByUsername(@Param("username") String username);

    @Query("SELECT r, p FROM ReservationRequest r JOIN Post p ON r.post.id = p.id WHERE r.confirmed = true AND r.archived = true AND p.user.login = :username")
    List<ReservationRequest> getArchivedReservationRequestsByPostUsername(@Param("username") String username);
}
