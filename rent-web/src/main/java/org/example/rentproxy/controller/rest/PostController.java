package org.example.rentproxy.controller.rest;

import jakarta.servlet.http.HttpSession;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.PostResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
public interface PostController {
    @PostMapping("/create")
    PostResponse savePost(@RequestBody PostDto postDto, @AuthenticationPrincipal UserDetails userDetails);

    @DeleteMapping("/delete")
    void deleteById(@RequestBody WithIdRequest withIdRequest);

    @PostMapping("/get")
    PostResponse findById(
            @RequestBody WithIdRequest withIdRequest,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpSession session,
            @RequestParam(required = false) String currency
    );

    @PostMapping("/update")
    PostResponse updateById(@RequestBody PostDto postDto);

    @PostMapping("/find")
    List<PostResponse> findPostsByFilter(
            @RequestBody Filter filter,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpSession session,
            @RequestParam(required = false) String currency
    );
}
