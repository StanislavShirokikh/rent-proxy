package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.repository.jpa.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageDtoMapper {
    public MessageDto convertToMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setMessageText(message.getMessageText());
        messageDto.setCreationDateTime(message.getCreationDateTime());
        messageDto.setSenderName(message.getSender().getLogin());
        messageDto.setReceiverName(message.getReceiver().getLogin());
        messageDto.setPostId(message.getPostId());

        return messageDto;
    }
}
