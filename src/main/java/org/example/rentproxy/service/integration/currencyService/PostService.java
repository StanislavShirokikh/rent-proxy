package org.example.rentproxy.service.integration.currencyService;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.repository.entities.Post;

import java.util.List;

public interface PostService {
    PostDto save(PostDto postDto);
    void deletePostById(long id);
    PostDto findPostById(long id);
    PostDto updatePost(PostDto post);
    List<PostDto> findPostByFilter(Filter filter);
}
