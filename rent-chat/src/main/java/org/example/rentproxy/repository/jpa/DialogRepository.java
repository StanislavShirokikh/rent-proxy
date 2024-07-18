package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
    Dialog findDialogBySenderLoginAndPostId(String username, Long postId);

    @Query(
            "SELECT d " +
            "FROM Dialog d " +
            "JOIN FETCH d.post p " +
            "JOIN FETCH p.user u " +
            "WHERE d.id = :dialogId AND u.login = :username")
    Dialog findDialogByPostUserLoginAndDialogId(@Param("dialogId") Long dialogId, @Param("username") String username);

    @Query(
            "SELECT d " +
            "FROM Dialog d " +
            "JOIN d.receiver r " +
            "WHERE r.login = :username " +
            "AND d.isClosed = false"
    )
    List<Dialog> findDialogByReceiverLoginWhereIsClosedFalse(@Param("username") String username);
}
