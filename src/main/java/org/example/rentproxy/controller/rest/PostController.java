package org.example.rentproxy.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import java.util.List;
@Tag(name = "PostController")
@RequestMapping("/post")
public interface PostController {
    @Operation(summary = "Создание объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Объявление создано",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostResponse.class))
                    }),
    })
    @PostMapping("/create")
    PostResponse savePost(@RequestBody PostDto postDto, @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Удаление по идентификатору объявления")
    @DeleteMapping("/delete")
    void deleteById(@RequestBody WithIdRequest withIdRequest);

    @Operation(summary = "Получение объявления по идентификатору")
    @PostMapping("/get")
    PostResponse findById(@RequestBody WithIdRequest withIdRequest);


    @PostMapping("/update")
    PostResponse updateById(@RequestBody PostDto postDto);

    @PostMapping("/find")
    List<PostResponse> findPostsByFilter(@RequestBody Filter filter);
}
