package org.example.rentproxy.repository.jpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "receiver_id")
    private Long receiver_id;
    @Column(name = "message_text")
    private String messageText;
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
}
