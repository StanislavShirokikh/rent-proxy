package org.example.rentproxy.repository.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;
    @Column(name = "message_text")
    private String messageText;
    @CreationTimestamp
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
}
