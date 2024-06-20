package org.example.rentproxy.repository.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
public class RentConditionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    @ToString.Exclude
    private Post post;
    private Double deposit;
    private Integer commissionPercent;
    private Double price;
    private String currency;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "type_of_payment_to_rent_condition",
            joinColumns = @JoinColumn(name = "rent_condition_info_id"),
            inverseJoinColumns = @JoinColumn(name = "type_of_payment_id"))
    private Set<TypeOfPayment> typeOfPayment;
}
