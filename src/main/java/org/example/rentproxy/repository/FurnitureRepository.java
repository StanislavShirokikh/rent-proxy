package org.example.rentproxy.repository;

import org.example.rentproxy.entities.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
    Furniture findByName(String name);
}
