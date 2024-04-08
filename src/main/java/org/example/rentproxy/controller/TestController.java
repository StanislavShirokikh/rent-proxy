package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.repository.entities.Post;
import org.example.rentproxy.filter.PostOrder;
import org.example.rentproxy.repository.PostRepository;
import org.example.rentproxy.service.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final PostService postService;

    @PostMapping("/create")
    public PostDto savePost(@RequestBody PostDto postDto) {
        return postService.save(postDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id) {
        postService.deletePostById(id);
    }

    @GetMapping("/get/{id}")
    public PostDto findById(@PathVariable long id) {
        return postService.findPostById(id);
    }

    @PostMapping("/update")
    public PostDto updateById(@RequestBody PostDto postDto) {
        return postService.updatePost(postDto);
    }
    @PostMapping("/posts")
    public List<PostDto> findPostsByFilter(@RequestBody Filter filter) {
        return postService.findPostByFilter(filter);
    }
}
