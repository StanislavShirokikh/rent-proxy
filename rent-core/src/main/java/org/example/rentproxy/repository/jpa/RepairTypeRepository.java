package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.RepairType;
import org.example.rentproxy.repository.jpa.entities.RepairType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.RepairType;

@Repository
public interface RepairTypeRepository extends JpaRepository<RepairType, Long> {
    RepairType findByName(String name);
}
