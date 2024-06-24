package org.example.rentproxy.service;

import org.example.rentproxy.dto.MessageDto;

import java.util.List;

public interface DialogMessageService {
    MessageDto sendMessage(String username, long postId, String text);
    List<MessageDto> getMessagesByReceiverUsername(String username, long pageSize, long pageNumber);
}
