package org.example.rentproxy.controller.rest;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.PostResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/post")
public interface PostController {
    @PostMapping("/create")
    PostResponse savePost(@RequestBody PostDto postDto, @AuthenticationPrincipal UserDetails userDetails);

    @DeleteMapping("/delete")
    void deleteById(@RequestBody WithIdRequest withIdRequest);

    @PostMapping("/get")
    PostResponse findById(@RequestBody WithIdRequest withIdRequest, @RequestParam String currency);

    @PostMapping("/update")
    PostResponse updateById(@RequestBody PostDto postDto);

    @PostMapping("/find")
    List<PostResponse> findPostsByFilter(@RequestBody Filter filter);
}
