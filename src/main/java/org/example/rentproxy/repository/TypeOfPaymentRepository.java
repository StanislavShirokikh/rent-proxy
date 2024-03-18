package org.example.rentproxy.repository;

import org.example.rentproxy.entities.TypeOfPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfPaymentRepository extends JpaRepository<TypeOfPayment, Long> {
    TypeOfPayment findByName(String name);
}
