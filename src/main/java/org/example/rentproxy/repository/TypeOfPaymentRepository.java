package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.TypeOfPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TypeOfPaymentRepository extends JpaRepository<TypeOfPayment, Long> {
    TypeOfPayment findByName(String name);

    @Query("FROM TypeOfPayment WHERE name IN :names")
    Set<TypeOfPayment> findByNameIn(@Param("names") Set<String> names);
}
