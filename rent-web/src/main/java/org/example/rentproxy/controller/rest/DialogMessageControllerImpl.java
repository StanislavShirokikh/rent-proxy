package org.example.rentproxy.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.request.SendMessageRequest;
import org.example.rentproxy.service.DialogMessageService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DialogMessageControllerImpl implements DialogMessageController{
    private final DialogMessageService dialogMessageService;

    public MessageDto sendMessage(UserDetails userDetails, SendMessageRequest sendMessageRequest) {
        return dialogMessageService.sendMessage(userDetails.getUsername(), sendMessageRequest.getPostId(), sendMessageRequest.getText());
    }

    @Override
    public List<MessageDto> getMessages(UserDetails userDetails, long pageSize, long pageNumber) {
        return dialogMessageService.getMessagesByReceiverUsername(userDetails.getUsername(), pageSize, pageNumber);
    }

    @Override
    public List<MessageDto> findUnreadMessages(UserDetails userDetails, long pageSize, long pageNumber) {
        return dialogMessageService.findUnreadMessageByReceiverUsername(userDetails.getUsername(), pageSize, pageNumber);
    }

    @Override
    public List<Long> findIdOpenedDialogs(UserDetails userDetails, long postId) {
        return dialogMessageService.findIdOpenedDialogs(userDetails.getUsername(), postId);
    }

    @Override
    public DialogDto closeDialog(UserDetails userDetails, long chatId) {
        return dialogMessageService.closeDialog(userDetails.getUsername(), chatId);
    }
}
