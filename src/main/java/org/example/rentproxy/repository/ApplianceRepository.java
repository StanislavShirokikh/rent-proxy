package org.example.rentproxy.repository;

import org.example.rentproxy.entities.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    Appliance findByName(String name);
}
