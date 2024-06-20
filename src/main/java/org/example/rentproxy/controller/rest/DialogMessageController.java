package org.example.rentproxy.controller.rest;

import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.dto.MessageDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chat")
public interface DialogMessageController {
    @PostMapping("/send")
    DialogDto createDialogAndSendMessage(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MessageDto messageDto);
}
