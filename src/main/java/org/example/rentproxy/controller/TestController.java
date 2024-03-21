package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.entities.Post;
import org.example.rentproxy.repository.PostRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final PostRepository postRepository;

//    @GetMapping("rent-proxy/{id}")
//    public Post getById(@PathVariable long id) {
////        Optional<Post> post = postRepository.findById(id);
//        return post.orElse(null);
//    }

    @PostMapping("rent-proxy/create")
    public Post savePost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @DeleteMapping("rent-proxy/delete/{id}")
    public void deleteById(@PathVariable long id) {
        postRepository.deletePostById(id);
    }

    @GetMapping("rent-proxy/get/{id}")
    public Post findById(@PathVariable long id) {
        return postRepository.findPostById(id);
    }
}
