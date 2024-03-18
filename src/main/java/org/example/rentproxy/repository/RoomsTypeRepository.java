package org.example.rentproxy.repository;

import org.example.rentproxy.entities.RoomsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsTypeRepository extends JpaRepository<RoomsType, Long> {
    RoomsType findByName(String name);
}
