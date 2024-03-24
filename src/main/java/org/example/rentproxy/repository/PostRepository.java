package org.example.rentproxy.repository;

import org.example.rentproxy.entities.Post;


public interface PostRepository {
    Post save(Post post);
    void deletePostById(long id);
    Post findPostById(long id);
}
