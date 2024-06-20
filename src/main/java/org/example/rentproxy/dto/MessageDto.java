package org.example.rentproxy.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MessageDto {
    private Long id;
    private Long postId;
    private String senderName;
    private String receiverName;
    private String messageText;
    private LocalDateTime creationDateTime;
}
