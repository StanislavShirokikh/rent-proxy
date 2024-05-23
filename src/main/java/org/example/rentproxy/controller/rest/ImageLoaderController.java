package org.example.rentproxy.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.rentproxy.dto.PostImageDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Tag(name = "Контроллер загрузки изображения")
public interface ImageLoaderController {
    @Operation(summary = "Загрузка изображения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображения сохранено",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = PostImageDto.class)))
                    }),
    })
    @PostMapping("/uploadImage")
    PostImageDto uploadImage(@RequestParam("postId") long postId,
                             @RequestParam("files") List<MultipartFile> files) throws IOException;
}
