package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
    Dialog findDialogBySenderLoginAndPostId(String username, Long postId);
}
