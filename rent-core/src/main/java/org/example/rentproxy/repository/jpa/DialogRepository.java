package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
    @Query("Select d, m FROM Dialog d JOIN Message m ON d.id = m.dialog.id WHERE m.sender_name = :username AND m.post_id = : postId ")
    Dialog getDialogBySenderNameAndPostId(@Param("username") String username, @Param("postId") Long postId);
}
