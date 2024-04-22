package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    @Query("SELECT p.user.login FROM Post p WHERE p.id = :id")
    String findUserLoginByPostId(Long id);
}
