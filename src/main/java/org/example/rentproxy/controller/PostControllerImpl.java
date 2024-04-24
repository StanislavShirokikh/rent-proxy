package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.mapper.ResponseMapper;
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
    private final ResponseMapper responseMapper;

    @Override
    public PostResponse savePost(PostDto postDto, UserDetails userDetails) {
        UserDto userDto = new UserDto();
        userDto.setLogin(userDetails.getUsername());
        postDto.setUserDto(userDto);
        return responseMapper.convertToPostResponse(postService.save(postDto));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or " +
            "authentication.name == @postRepositoryImpl.findUserLoginByPostId(#withIdRequest.id)")
    public void deleteById(@P("withIdRequest") WithIdRequest withIdRequest) {
        postService.deletePostById(withIdRequest.getId());
    }

    @Override
    public PostResponse findById(WithIdRequest withIdRequest) {
        return responseMapper.convertToPostResponse(postService.findPostById(withIdRequest.getId()));
    }

    @Override
    @PostAuthorize("returnObject.userDto.login == principal.username || hasRole('ADMIN')")
    public PostResponse updateById(PostDto postDto) {
            return responseMapper.convertToPostResponse(postService.updatePost(postDto));
    }

    public List<PostResponse> findPostsByFilter(Filter filter) {
        return responseMapper.convertToListResponse(postService.findPostByFilter(filter));
    }
}
