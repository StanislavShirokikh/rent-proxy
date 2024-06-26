package org.example.rentproxy.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DialogDto {
    private Long id;
    private Long postId;
    private Long senderId;
    private Long receiverId;
    private Boolean isClosed;
    private List<MessageDto> messageDtos;
    private LocalDateTime creationDateTime;
}
