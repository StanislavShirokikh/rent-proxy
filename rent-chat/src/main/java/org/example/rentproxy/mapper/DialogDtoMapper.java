package org.example.rentproxy.mapper;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DialogDtoMapper {
    private final MessageDtoMapper messageDtoMapper;

    public DialogDto convertToDialogDto(Dialog dialog) {
        DialogDto dialogDto = null;
        if (dialog != null) {
            dialogDto = new DialogDto();
            dialogDto.setId(dialog.getId());
            dialogDto.setPostId(dialog.getPost().getId());
            dialogDto.setSenderId(dialog.getSender().getId());
            dialogDto.setReceiverId(dialog.getReceiver().getId());
            dialogDto.setIsClosed(dialog.getIsClosed());
            dialogDto.setMessageDtos(messageDtoMapper.convertToListMessageDto(dialog.getMessages()));
            dialogDto.setCreationDateTime(dialog.getCreationDateTime());
        }

        return dialogDto;
    }

    public List<DialogDto> convertToListDialogsDto(List<Dialog> dialogs) {
        return dialogs.stream()
                .map(this::convertToDialogDto)
                .collect(Collectors.toList());
    }
}
