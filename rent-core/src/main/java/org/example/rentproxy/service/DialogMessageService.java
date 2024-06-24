package org.example.rentproxy.service;

import org.example.rentproxy.dto.MessageDto;

public interface DialogMessageService {
    MessageDto sendMessage(String username, long postId, String text);
}
