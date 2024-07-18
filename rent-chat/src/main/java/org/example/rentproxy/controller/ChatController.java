package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
