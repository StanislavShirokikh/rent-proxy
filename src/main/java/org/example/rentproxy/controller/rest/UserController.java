package org.example.rentproxy.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.response.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@Tag(name = "Контроллер пользователей")
@RequestMapping("/user")
public interface UserController {
    @Operation(summary = "Регистрация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь зарегистрирован",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))
                    }),
    })
    @PostMapping("/register")
    UserResponse register(@RequestBody UserDto userDto);
}
