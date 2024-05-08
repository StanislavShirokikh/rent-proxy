package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.BalconyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalconyTypeRepository extends JpaRepository<BalconyType, Long> {
    BalconyType findByName(String name);
}
