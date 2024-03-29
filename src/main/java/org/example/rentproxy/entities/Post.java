package org.example.rentproxy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @OneToOne(mappedBy = "post")
    private RentConditionInfo rentConditionInfo;
    @OneToOne(mappedBy = "post")
    private ApartmentInfo apartmentInfo;
    @OneToOne(mappedBy = "post")
    private HouseInfo houseInfo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_type_id")
    private RentType rentType;
    private String name;
    private String title;
    private LocalDate date;
}
