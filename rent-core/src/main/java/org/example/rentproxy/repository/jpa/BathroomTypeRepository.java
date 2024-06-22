package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.BathroomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BathroomTypeRepository extends JpaRepository<BathroomType, Long> {
    BathroomType findByName(String name);
}
