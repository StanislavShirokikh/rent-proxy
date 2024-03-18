package org.example.rentproxy.repository;

import org.example.rentproxy.entities.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    Appliance findByName(String name);
}
