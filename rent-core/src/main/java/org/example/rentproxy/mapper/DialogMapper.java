package org.example.rentproxy.mapper;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DialogMapper {
    private final MessageMapper messageMapper;

    public Dialog convertToDialogDialog(DialogDto dialogDto) {
        Dialog dialog = new Dialog();
        dialog.setMessages(messageMapper.convertToListMessage(dialogDto.getMessageDtos()));
        dialog.setCreationDateTime(dialogDto.getCreationDateTime());

        return dialog;
    }
}
