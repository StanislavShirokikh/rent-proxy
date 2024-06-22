package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.repository.jpa.entities.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {
    public Message convertToMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setDialogId(messageDto.getDialogId());
        message.setMessageText(messageDto.getMessageText());
        message.setCreationDateTime(messageDto.getCreationDateTime());

        return message;
    }

    public List<Message> convertToListMessage(List<MessageDto> messageDtos) {
        return messageDtos.stream()
                .map(this::convertToMessage)
                .collect(Collectors.toList());
    }
}
