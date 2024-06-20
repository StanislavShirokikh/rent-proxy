package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
