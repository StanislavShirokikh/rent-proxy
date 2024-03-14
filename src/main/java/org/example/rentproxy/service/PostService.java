package org.example.rentproxy.service;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.entities.Post;
import org.example.rentproxy.filter.Filter;

import java.util.List;

public interface PostService {
    Post createPost(PostDto postDto);
    Post updatepost(PostDto postDto);
    void deletePost(Long id);
    Post getPostById(Long id);
    List<Post> getPostByFilter(Filter filter);
}
