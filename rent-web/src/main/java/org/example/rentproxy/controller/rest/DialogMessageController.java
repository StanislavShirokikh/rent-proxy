package org.example.rentproxy.controller.rest;

import org.example.rentproxy.dto.DialogDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chat")
public interface DialogMessageController {
    @PostMapping("/send")
    DialogDto createDialog(@AuthenticationPrincipal UserDetails userDetails, @RequestBody DialogDto dialogDto);
}
