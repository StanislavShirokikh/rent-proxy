package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.Post;
import org.example.rentproxy.filter.Filter;

import java.util.List;


public interface PostRepository {
    Post save(Post post);
    void deletePostById(long id);
    Post findPostById(long id);
    Post updatePost(Post post);
    List<Post> findPostByFilter(Filter filter);
    String findUserLoginByPostId(Long id);
}
