package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.mapper.ResponseMapper;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.PostResponse;
import org.example.rentproxy.service.PostService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostControllerImpl implements PostController{
    private final PostService postService;
    private final ResponseMapper responseMapper;

    @Override
    public PostResponse savePost(PostDto postDto) {
        return responseMapper.convertToPostResponse(postService.save(postDto));
    }

    @Override
    public void deleteById(WithIdRequest withIdRequest) {
        postService.deletePostById(withIdRequest.getId());
    }

    @Override
    public PostResponse findById(WithIdRequest withIdRequest) {
        return responseMapper.convertToPostResponse(postService.findPostById(withIdRequest.getId()));
    }

    @Override
    public PostResponse updateById(PostDto postDto) {
        return responseMapper.convertToPostResponse(postService.updatePost(postDto));
    }

    public List<PostResponse> findPostsByFilter(Filter filter) {
        return responseMapper.convertToListResponse(postService.findPostByFilter(filter));
    }
}
