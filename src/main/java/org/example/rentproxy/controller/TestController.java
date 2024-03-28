package org.example.rentproxy.controller;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.example.rentproxy.converter.Converter;
import org.example.rentproxy.entities.Post;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.repository.PostRepository;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final PostRepository postRepository;

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

    @PostMapping("rent-proxy/update/")
    public Post updateById(@RequestBody Post post) {
        return postRepository.save(post);
    }
    @GetMapping("rent-proxy/posts/")
    public List<Post> findPostsByFilter(@RequestParam String rentType,
                                        @RequestParam Double roomsCount,
                                        @RequestParam Double minPrice,
                                        @RequestParam Double maxPrice,
                                        @RequestParam Double minTotalArea,
                                        @RequestParam Double maxTotalArea,
                                        @RequestParam Integer minFlour,
                                        @RequestParam Integer maxFlour,
                                        @RequestParam String houseType,
                                        @RequestParam String repairType,
                                        @RequestParam Set<String> furniture,
                                        @RequestParam Set<String> appliance,
                                        @RequestParam Integer minHouseFlour,
                                        @RequestParam Integer maxHouseFlour,
                                        @RequestParam Integer pageNumber,
                                        @RequestParam Integer pageSize) {
        Filter filter = Converter.convertToFilter(rentType, roomsCount, minPrice, maxPrice, minTotalArea, maxTotalArea,
                minFlour, maxFlour, houseType, repairType, furniture, appliance, minHouseFlour, maxHouseFlour,
                pageNumber, pageSize);

        return null;
    }
}
