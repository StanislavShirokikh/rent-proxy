package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.repository.jpa.entities.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageDtoMapper {
    public MessageDto convertToMessageDto(Message message) {
        MessageDto messageDto = null;
        if (message != null) {
            messageDto = new MessageDto();
            messageDto.setId(message.getId());
            messageDto.setDialogId(message.getDialog().getId());
            messageDto.setText(message.getText());
            messageDto.setCreationDateTime(message.getCreationDateTime());
        }

        return messageDto;
    }

    public List<MessageDto> convertToListMessageDto(List<Message> messages) {
        return messages.stream()
                .map(this::convertToMessageDto)
                .collect(Collectors.toList());
    }
}
