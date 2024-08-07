package org.example.rentproxy.service;

import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;

import java.util.List;

public interface PostService {
    PostDto save(PostDto postDto);
    void deletePostById(long id);
    PostDto findPostById(String username, long id, String currencyValueFromSession);
    PostDto updatePost(PostDto postDto);
    List<PostDto> findPostByFilter(Filter filter, String userName, String currencyValueFromSession);
}
