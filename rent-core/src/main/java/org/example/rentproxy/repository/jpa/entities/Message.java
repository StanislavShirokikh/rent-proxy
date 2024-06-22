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
    @Column(name = "dialog_id")
    private Long dialogId;
    @Column(name = "message_text")
    private String messageText;
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
}
