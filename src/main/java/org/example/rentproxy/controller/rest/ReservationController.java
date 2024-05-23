package org.example.rentproxy.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.exception.handler.ErrorMessageResponse;
import org.example.rentproxy.request.WithIdRequest;
import org.example.rentproxy.response.ReservationRequestResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Tag(
        name = "Контроллер бронирований",
        description = "Управляет созданием, получением, удалением, подтверждением, отклонением бронирований"
)
@RequestMapping("/reservation")
public interface ReservationController {
    @Operation(summary = "Создание бронирования")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирование создано",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReservationRequestResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Бронирование уже существует",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessageResponse.class))
            )
    })
    @PostMapping("/create")
    ReservationRequestResponse createReservationRequest(@RequestBody ReservationRequestDto reservationRequestDto,
                                                        @AuthenticationPrincipal UserDetails userDetails);
    @PostMapping("/get")
    ReservationRequestResponse getReservationRequestById(@RequestBody WithIdRequest withIdRequest);
    @DeleteMapping("delete")
    void deleteReservationRequestById(@RequestBody WithIdRequest withIdRequest);

    @Operation(summary = "Подтверждение бронирования")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирование подтверждено",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReservationRequestResponse.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessageResponse.class))
            )
    })
    @PostMapping("/confirm")
    ReservationRequestResponse confirmReservationRequest(@RequestBody WithIdRequest withIdRequest);

    @Operation(summary = "Архивирование объявлений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирование архивировано",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReservationRequestResponse.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessageResponse.class))
            )
    })
    @PostMapping("/archive")
    ReservationRequestResponse archiveReservationRequest(@RequestBody WithIdRequest withIdRequest );

    @Operation(summary = "Получение списка отправленных бронирований для арендодателя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирования найдены",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema (implementation = ReservationRequestResponse.class)
                                    )
                            )
                    }),
    })
    @GetMapping("/sent")
    List<ReservationRequestResponse> getSentReservationsByUsername(@AuthenticationPrincipal UserDetails userDetails);


    @Operation(summary = "Получение списка бронирований арендатора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирования найдены",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema (implementation = ReservationRequestResponse.class)
                                    )
                            )
                    }),
    })
    @GetMapping("/received")
    List<ReservationRequestResponse> getReceivedReservationRequestsByUsername(@AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Получение списка архивных бронирований арендатора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирования найдены",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema (implementation = ReservationRequestResponse.class)
                                    )
                            )
                    }),
    })
    @GetMapping("/renter/archived")
    List<ReservationRequestResponse> getArchivedReservationRequestsByUsername(@AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Получение списка архивных бронирований арендодателя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирования найдены",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema (implementation = ReservationRequestResponse.class)
                                    )
                            )
                    }),
    })
    @GetMapping("/landlord/archived")
    List<ReservationRequestResponse> getArchivedReservationRequestsByPostUsername(@AuthenticationPrincipal UserDetails userDetails);
}
