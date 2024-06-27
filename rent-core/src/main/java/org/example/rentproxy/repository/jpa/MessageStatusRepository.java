package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageStatusRepository extends JpaRepository<Status, Long> {
    Status findByName(String name);
}
