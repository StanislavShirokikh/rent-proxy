package org.example.rentproxy.service;

import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.dto.MessageDto;

import java.util.List;

public interface DialogMessageService {
    MessageDto sendMessage(String username, long postId, String text);
    DialogDto closeDialog(String username, long chatId);
    Long getCountOfUnreadMessages(String username);
    List<DialogDto> findOpenedDialogs(String username);
    List<MessageDto> getMessagesByDialogId(String username, long dialogId, long pageSize, long pageNumber);
}
