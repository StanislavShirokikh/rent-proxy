package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
    Furniture findByName(String name);
    @Query("FROM Furniture WHERE name IN :names")
    Set<Furniture> findByNameIn(@Param("names")Set<String> names);
}
