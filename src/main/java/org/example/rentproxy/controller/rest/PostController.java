package org.example.rentproxy.controller.rest;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.PostResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Validated
@RequestMapping("/post")
public interface PostController {
    @PostMapping("/create")
    PostResponse savePost(@Valid @RequestBody PostDto postDto, @AuthenticationPrincipal UserDetails userDetails);

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
    PostResponse updateById(@Valid @RequestBody PostDto postDto);

    @PostMapping("/find")
    List<PostResponse> findPostsByFilter(
            @RequestBody Filter filter,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpSession session,
            @RequestParam(required = false) String currency
    );
}
