package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.RentType;
import org.example.rentproxy.repository.jpa.entities.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.RentType;

@Repository
public interface RentTypeRepository extends JpaRepository<RentType, Long> {
    RentType findByName(String name);
}
