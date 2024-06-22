package org.example.rentproxy.repository.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "receiver_id")
    private Long receiverId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dialog_id")
    private List<Message> messages;
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
}
