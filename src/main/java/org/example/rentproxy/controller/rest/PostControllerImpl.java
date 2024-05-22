package org.example.rentproxy.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.mapper.PostResponseMapper;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.PostResponse;
import org.example.rentproxy.service.PostService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostControllerImpl implements PostController{
    private final PostService postService;
    private final PostResponseMapper postResponseMapper;

    @Override
    public PostResponse savePost(PostDto postDto, UserDetails userDetails) {
        UserDto userDto = new UserDto();
        userDto.setLogin(userDetails.getUsername());
        postDto.setUserDto(userDto);
        return postResponseMapper.convertToPostResponse(postService.save(postDto));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or " +
            "authentication.name == @postRepositoryImpl.findUserLoginByPostId(#withIdRequest.id)")
    public void deleteById(@P("withIdRequest") WithIdRequest withIdRequest) {
        postService.deletePostById(withIdRequest.getId());
    }

    @Override
    public PostResponse findById(WithIdRequest withIdRequest, UserDetails userDetails) {
        PostDto postDto = postService.findPostById(userDetails.getUsername(), withIdRequest.getId());
        return postResponseMapper.convertToPostResponse(postDto);
    }

    @Override
    @PostAuthorize("returnObject.userResponse.login == principal.username || hasRole('ADMIN')")
    public PostResponse updateById(PostDto postDto) {
            return postResponseMapper.convertToPostResponse(postService.updatePost(postDto));
    }

    public List<PostResponse> findPostsByFilter(Filter filter) {
        return postResponseMapper.convertToListResponse(postService.findPostByFilter(filter));
    }
}
