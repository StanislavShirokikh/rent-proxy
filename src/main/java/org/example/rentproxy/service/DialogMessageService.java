package org.example.rentproxy.service;

import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.dto.MessageDto;

public interface DialogMessageService {
    DialogDto createDialogAndSendMessage(MessageDto messageDto);
}
