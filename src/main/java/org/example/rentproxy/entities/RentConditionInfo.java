package org.example.rentproxy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class RentConditionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @OneToMany(mappedBy = "rentConditionInfo")
    private List<Post> posts; // оставляем однонаправленную связь + 1 к 1
    private String deposit; // это число + надо хранить валюту
    private String commission; // это инт в процентах (может тип такой есть в базе)
    @ManyToMany
    @JoinTable(name = "type_of_payment_to_rent_condition",
            joinColumns = @JoinColumn(name = "rent_condition_info_id"),
            inverseJoinColumns = @JoinColumn(name = "type_of_payment_id"))
    private Set<TypeOfPayment> typeOfPayment;
}
