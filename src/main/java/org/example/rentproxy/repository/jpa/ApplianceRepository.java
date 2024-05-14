package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    Appliance findByName(String name);
    @Query("FROM Appliance WHERE name IN :names")
    Set<Appliance> findByNameIn(@Param("names")Set<String> names);
}
