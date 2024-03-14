package org.example.rentproxy.service;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.entities.Post;
import org.example.rentproxy.filter.Filter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Override
    public Post createPost(PostDto postDto) {
        return null;
    }

    @Override
    public Post updatepost(PostDto postDto) {
        return null;
    }

    @Override
    public void deletePost(Long id) {

    }

    @Override
    public Post getPostById(Long id) {
        return null;
    }

    @Override
    public List<Post> getPostByFilter(Filter filter) {
        return null;
    }
}
