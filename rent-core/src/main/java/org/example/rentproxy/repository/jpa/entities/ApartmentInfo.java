package org.example.rentproxy.repository.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
public class ApartmentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    @ToString.Exclude
    private Post post;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "bathroom_type_id")
    private BathroomType bathroomType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repair_type_id")
    private RepairType repairType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "balcony_type_id")
    private BalconyType balconyType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rooms_type_id")
    private RoomsType roomsType;
    @Column(name = "rooms_count", columnDefinition = "int2")
    private Integer roomsCount;
    @Column(name = "total_area")
    private Double totalArea;
    @Column(name = "kitchen_area")
    private Double kitchenArea;
    @Column(name = "living_space")
    private Double livingSpace;
    @Column(name = "flour", columnDefinition = "int2")
    private Integer flour;
    private String additionally;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "furniture_to_apartment_info",
            joinColumns = @JoinColumn(name = "apartment_info_id"),
            inverseJoinColumns = @JoinColumn(name = "furniture_id"))
    private Set<Furniture> furniture;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "appliance_to_apartment_info",
            joinColumns = @JoinColumn(name = "apartment_info_id"),
            inverseJoinColumns = @JoinColumn(name = "appliance_id"))
    private Set<Appliance> appliance;
}
