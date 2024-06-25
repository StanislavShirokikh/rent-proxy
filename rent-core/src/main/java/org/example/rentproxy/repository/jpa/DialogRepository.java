package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
    Dialog findDialogBySenderLoginAndPostId(String username, Long postId);
    @Query("SELECT d FROM Dialog d " +
            "JOIN FETCH d.sender " +
            "JOIN FETCH d.receiver " +
            "JOIN FETCH d.messages " +
            "JOIN FETCH d.post p " +
            "JOIN FETCH p.user u " +
            "WHERE d.id = :dialogId AND u.login = :userLogin")
    Dialog findDialogByUserIdAndDialogId(@Param("dialogId") Long dialogId, @Param("userLogin") String userLogin);
}
