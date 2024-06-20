package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.repository.jpa.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public Message convertToMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setPostId(messageDto.getPostId());
        message.setMessageText(messageDto.getMessageText());
        message.setCreationDateTime(messageDto.getCreationDateTime());

        return message;
    }
}
