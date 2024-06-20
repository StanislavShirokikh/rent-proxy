package org.example.rentproxy.repository.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "dialog_id")
    private Long dialogId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;
    @JoinColumn(name = "receiver_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User receiver;
    @Column(name = "message_text")
    private String messageText;
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
}
