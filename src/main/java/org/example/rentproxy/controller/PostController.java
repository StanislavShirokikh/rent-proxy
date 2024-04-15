package org.example.rentproxy.controller;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.request.WithIdRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PostController {
    @PostMapping("/create")
    PostDto savePost(@RequestBody PostDto postDto);

    @DeleteMapping("/delete")
    void deleteById(@RequestBody WithIdRequest withIdRequest);

    @GetMapping("/get")
    PostDto findById(@RequestBody WithIdRequest withIdRequest);

    @PostMapping("/update")
    PostDto updateById(@RequestBody PostDto postDto);
    @PostMapping("/posts")
    List<PostDto> findPostsByFilter(@RequestBody Filter filter);
}
