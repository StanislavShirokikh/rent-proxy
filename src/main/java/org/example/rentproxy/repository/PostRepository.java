package org.example.rentproxy.repository;

import org.example.rentproxy.entities.Post;
import org.springframework.stereotype.Repository;


public interface PostRepository {
    Post save(Post post);
    void deletePostById(long id);
    Post findPostById(long id);
}
