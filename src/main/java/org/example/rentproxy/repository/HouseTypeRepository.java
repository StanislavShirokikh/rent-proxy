package org.example.rentproxy.repository;

import org.example.rentproxy.entities.HouseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseTypeRepository extends JpaRepository<HouseType, Long> {
    HouseType findByName(String name);
}
