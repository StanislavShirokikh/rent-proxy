package org.example.rentproxy.controller.rest;

import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.request.SendMessageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chat")
public interface DialogMessageController {
    @PostMapping("/send")
    MessageDto sendMessage(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody SendMessageRequest sendMessageRequest
    );
    @GetMapping("/messages")
    List<MessageDto> getMessages(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam long pageSize,
            @RequestParam long pageNumber
    );
}
