package org.example.rentproxy.repository;

import org.example.rentproxy.entities.BalconyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalconyTypeRepository extends JpaRepository<BalconyType, Long> {
    BalconyType findByName(String name);
}
