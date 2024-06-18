package org.example.rentproxy.repository.jpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dialog_id")
    private List<Message> messages;
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
}
