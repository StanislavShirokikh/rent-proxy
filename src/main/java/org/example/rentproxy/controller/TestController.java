package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.exception.ReservationRequestException;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.example.rentproxy.service.ReservationService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.ApiLayerService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
public class TestController {
    private final ApiLayerService apiLayerService;
    private final ReservationService reservationService;

    @GetMapping("/test")
    public ApiLayerResponse getApilayerResponse(@RequestParam String fromCurrency,
                                                @RequestParam String toCurrency,
                                                @RequestParam String amount) {
        return apiLayerService.convertCurrency(fromCurrency, toCurrency, amount);
    }

    @PostMapping("/create")
    public ReservationRequestDto createReservation(ReservationRequestDto reservationRequestDto) throws ReservationRequestException {
        return reservationService.createReservationRequest(reservationRequestDto);
    }

    @DeleteMapping("delete")
    public void deleteReservationRequestById(ReservationRequestDto reservationRequestDto) {
        reservationService.deleteReservationRequestById(reservationRequestDto.getId());
    }
}
