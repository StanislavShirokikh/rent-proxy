package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.User;
import org.example.rentproxy.repository.jpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    @Query("SELECT u FROM User u JOIN Post p ON u.id = p.user.id WHERE p.id = :postId")
    User findByPostId(@Param("postId") long postId);

}
