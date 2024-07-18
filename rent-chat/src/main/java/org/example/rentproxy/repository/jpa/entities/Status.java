package org.example.rentproxy.repository.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "message_status")
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}