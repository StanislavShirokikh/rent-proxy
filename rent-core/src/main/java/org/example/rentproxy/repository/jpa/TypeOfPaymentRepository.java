package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.TypeOfPayment;
import org.example.rentproxy.repository.jpa.entities.TypeOfPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.TypeOfPayment;

import java.util.Set;

@Repository
public interface TypeOfPaymentRepository extends JpaRepository<TypeOfPayment, Long> {
    TypeOfPayment findByName(String name);

    @Query("FROM TypeOfPayment WHERE name IN :names")
    Set<TypeOfPayment> findByNameIn(@Param("names") Set<String> names);
}
