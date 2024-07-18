package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.Post;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    @Query("SELECT p.user.login FROM Post p WHERE p.id = :id")
    String findUserLoginByPostId(@Param("id") Long id);
}
