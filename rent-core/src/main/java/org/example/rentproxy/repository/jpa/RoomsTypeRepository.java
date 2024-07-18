package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.RoomsType;
import org.example.rentproxy.repository.jpa.entities.RoomsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.RoomsType;

@Repository
public interface RoomsTypeRepository extends JpaRepository<RoomsType, Long> {
    RoomsType findByName(String name);
}
