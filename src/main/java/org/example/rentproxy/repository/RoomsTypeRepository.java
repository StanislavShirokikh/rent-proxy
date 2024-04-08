package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.RoomsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsTypeRepository extends JpaRepository<RoomsType, Long> {
    RoomsType findByName(String name);
}
