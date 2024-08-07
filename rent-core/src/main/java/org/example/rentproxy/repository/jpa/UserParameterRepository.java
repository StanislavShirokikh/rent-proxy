package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.UserParameter;
import org.example.rentproxy.repository.jpa.entities.UserParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.UserParameter;

@Repository
public interface UserParameterRepository extends JpaRepository<UserParameter, Long> {
    UserParameter findByNameAndUserId(String name, Long userId);
}
