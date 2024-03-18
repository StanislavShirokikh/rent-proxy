package org.example.rentproxy.repository;

import org.example.rentproxy.entities.TypeOfPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfPaymentRepository extends JpaRepository<TypeOfPayment, Long> {
    TypeOfPayment findByName(String name);
}
