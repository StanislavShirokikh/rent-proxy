package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.service.DialogMessageService;
import org.example.rentproxy.request.SendMessageRequest;
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
    public List<MessageDto> getMessagesByDialogId(UserDetails userDetails, long pageSize, long pageNumber, long dialogId) {
        return dialogMessageService.getMessagesByDialogId(userDetails.getUsername(), dialogId, pageSize, pageNumber);
    }

    @Override
    public Long getCountOfUnreadMessages(UserDetails userDetails) {
        return dialogMessageService.getCountOfUnreadMessages(userDetails.getUsername());
    }

    @Override
    public List<DialogDto> findOpenedDialogs(UserDetails userDetails) {
        return dialogMessageService.findOpenedDialogs(userDetails.getUsername());
    }

    @Override
    public DialogDto closeDialog(UserDetails userDetails, long chatId) {
        return dialogMessageService.closeDialog(userDetails.getUsername(), chatId);
    }
}
