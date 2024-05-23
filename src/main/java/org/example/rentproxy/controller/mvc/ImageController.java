package org.example.rentproxy.controller.mvc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Контроллер изображений")
@RequestMapping("/mvc")
public interface ImageController {
    @Operation(summary = "Получение изображений по идентификатору объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображения найдены"),
    })
    @GetMapping("/post/{id}")
    String getImagesByPostId(Model model, @PathVariable("id") long id);
}
