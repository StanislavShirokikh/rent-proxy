package org.example.rentproxy.repository.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class HouseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    @ToString.Exclude
    private Post post;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_type_id")
    private HouseType houseType;
    private String address;
    @Column(name = "flours_count", columnDefinition = "int2")
    private Integer floursCount;
}
