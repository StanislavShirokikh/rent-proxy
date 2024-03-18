package org.example.rentproxy.repository;

import org.example.rentproxy.entities.RepairType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairTypeRepository extends JpaRepository<RepairType, Long> {
    RepairType findByName(String name);
}
