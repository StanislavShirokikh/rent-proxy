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
            "SELECT m FROM Message m " +
                    "JOIN m.dialog d " +
                    "JOIN d.receiver u " +
                    "WHERE u.login = :receiver " +
                    "ORDER BY m.creationDateTime " +
                    "LIMIT :pageSize " +
                    "OFFSET :pageNumber"
    )
    List<Message> findMessagesByReceiverUsernameWithSortAndPagination(
            @Param("receiver") String receiver,
            @Param("pageSize") long pageSize,
            @Param("pageNumber") long pageNumber
    );
}
