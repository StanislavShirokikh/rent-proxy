package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.BathroomType;
import org.example.rentproxy.repository.jpa.entities.BathroomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.BathroomType;

@Repository
public interface BathroomTypeRepository extends JpaRepository<BathroomType, Long> {
    BathroomType findByName(String name);
}
