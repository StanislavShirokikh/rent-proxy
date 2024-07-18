package org.example.rentproxy.controller;


import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.request.SendMessageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/chat")
public interface DialogMessageController {
    @PostMapping("/send")
    MessageDto sendMessage(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody SendMessageRequest sendMessageRequest
    );

    @GetMapping("/messages")
    List<MessageDto> getMessagesByDialogId(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam long dialogId,
            @RequestParam long pageSize,
            @RequestParam long pageNumber
    );

    @PostMapping("/close")
    DialogDto closeDialog(@AuthenticationPrincipal UserDetails userDetails, @RequestParam long chatId);

    @GetMapping("/unread")
    Long getCountOfUnreadMessages(@AuthenticationPrincipal UserDetails userDetails);

    @GetMapping("/open")
    List<DialogDto> findOpenedDialogs(@AuthenticationPrincipal UserDetails userDetails);
}
