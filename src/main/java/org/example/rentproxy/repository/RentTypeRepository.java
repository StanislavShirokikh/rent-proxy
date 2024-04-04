package org.example.rentproxy.repository;

import org.example.rentproxy.entities.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentTypeRepository extends JpaRepository<RentType, Long> {
    RentType findByName(String name);
}
