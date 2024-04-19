package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.service.PostService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostControllerImpl implements PostController{
    private final PostService postService;

    public PostDto savePost(PostDto postDto, UserDetails userDetails) {
        UserDto userDto = new UserDto();
        userDto.setLogin(userDetails.getUsername());
        postDto.setUserDto(userDto);
        return postService.save(postDto);
    }

    public void deleteById(WithIdRequest withIdRequest) {
        postService.deletePostById(withIdRequest.getId());
    }

    public PostDto findById(WithIdRequest withIdRequest) {
        return postService.findPostById(withIdRequest.getId());
    }

    public PostDto updateById(PostDto postDto) {
        return postService.updatePost(postDto);
    }

    public List<PostDto> findPostsByFilter(Filter filter) {
        return postService.findPostByFilter(filter);
    }
}
