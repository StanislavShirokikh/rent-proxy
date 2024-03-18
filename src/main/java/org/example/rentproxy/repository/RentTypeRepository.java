package org.example.rentproxy.repository;

import org.example.rentproxy.entities.RentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentTypeRepository extends JpaRepository<RentType, Long> {
    RentType findByName(String name);
}
