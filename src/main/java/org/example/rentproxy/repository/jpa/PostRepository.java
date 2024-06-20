package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.repository.jpa.entities.Post;

import java.util.List;


public interface PostRepository {
    Post save(Post post);
    void deletePostById(long id);
    Post findPostById(long id);
    Post updatePost(Post post);
    List<Post> findPostByFilter(Filter filter);
}
