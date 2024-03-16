package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.entities.Post;
import org.example.rentproxy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TestController {

    private PostRepository postRepository;

    @GetMapping("rent-proxy/{id}")
    public Post getById(@PathVariable long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }
}
