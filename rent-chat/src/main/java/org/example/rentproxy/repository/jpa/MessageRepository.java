package org.example.rentproxy.repository.jpa;


import org.example.rentproxy.repository.jpa.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(
            "SELECT COUNT(m) FROM Message m " +
            "JOIN m.dialog d " +
            "JOIN d.receiver u " +
            "JOIN m.status ms " +
            "WHERE u.login = :username AND ms.name = 'UNREAD'")
    Long getCountOfUnreadMessages(String username);
    @Query(
            "SELECT m FROM Message m " +
                    "JOIN m.dialog d " +
                    "JOIN d.receiver r " +
                    "WHERE r.login = :receiver AND d.id = :dialogId " +
                    "ORDER BY m.creationDateTime " +
                    "LIMIT :pageSize " +
                    "OFFSET :pageNumber"
    )
    List<Message> getMessagesByDialogId(
            @Param("receiver") String receiver,
            @Param("dialogId") long dialogId,
            @Param("pageSize") long pageSize,
            @Param("pageNumber") long pageNumber
    );
}
