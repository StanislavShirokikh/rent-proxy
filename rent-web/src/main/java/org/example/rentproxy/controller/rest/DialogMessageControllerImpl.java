package org.example.rentproxy.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.request.SendMessageRequest;
import org.example.rentproxy.service.DialogMessageService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DialogMessageControllerImpl implements DialogMessageController{
    private final DialogMessageService dialogMessageService;

    public MessageDto sendMessage(UserDetails userDetails, SendMessageRequest sendMessageRequest) {
        return dialogMessageService.sendMessage(userDetails.getUsername(), sendMessageRequest.getPostId(), sendMessageRequest.getText());
    }
}
