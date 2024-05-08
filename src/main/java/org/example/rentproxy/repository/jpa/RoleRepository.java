package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
