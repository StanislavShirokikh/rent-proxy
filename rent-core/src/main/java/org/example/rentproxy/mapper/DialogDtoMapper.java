package org.example.rentproxy.mapper;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DialogDtoMapper {
    private final MessageDtoMapper messageDtoMapper;

    public DialogDto convertToDialogDialog(Dialog dialog) {
        DialogDto dialogDto = new DialogDto();
        dialogDto.setId(dialog.getId());
        dialogDto.setMessageDtos(messageDtoMapper.convertToListMessageDto(dialog.getMessages()));
        dialogDto.setCreationDateTime(dialog.getCreationDateTime());

        return dialogDto;
    }
}
