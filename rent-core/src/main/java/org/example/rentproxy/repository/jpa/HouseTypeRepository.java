package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.HouseType;
import org.example.rentproxy.repository.jpa.entities.HouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.HouseType;

@Repository
public interface HouseTypeRepository extends JpaRepository<HouseType, Long> {
    HouseType findByName(String name);
}
