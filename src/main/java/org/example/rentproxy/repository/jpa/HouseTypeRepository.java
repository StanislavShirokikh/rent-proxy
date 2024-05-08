package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.HouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseTypeRepository extends JpaRepository<HouseType, Long> {
    HouseType findByName(String name);
}
