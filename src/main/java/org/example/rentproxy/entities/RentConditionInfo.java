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
    private List<Post> posts;
    private String deposit;
    private String commission;
    @ManyToMany
    @JoinTable(name = "type_of_payment_to_rent_condition",
            joinColumns = @JoinColumn(name = "rent_condition_info_id"),
            inverseJoinColumns = @JoinColumn(name = "type_of_payment_id"))
    private Set<TypeOfPayment> typeOfPayment;
}
